import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class SecretSharing {

    private static BigInteger decodeValue(String value, int base) {
        return new BigInteger(value, base);
    }

    private static BigInteger findConstantTerm(JSONObject data) {
        List<Double> xValues = new ArrayList<>();
        List<BigInteger> yValues = new ArrayList<>();

        // Populate xValues and yValues, skipping the "keys" object
        for (String key : data.keySet()) {
            if (key.equals("keys")) continue; // Skip the keys section
            JSONObject obj = data.getJSONObject(key);
            int base = Integer.parseInt(obj.getString("base"));
            String value = obj.getString("value");
            xValues.add(Double.parseDouble(key)); // Using the keys (1, 2, ...) as x values
            yValues.add(decodeValue(value, base)); // Decode the value based on its base
        }

        // Debugging output
        System.out.println("xValues: " + xValues);
        System.out.println("yValues: " + yValues);

        // Check if there are enough points to perform interpolation
        if (xValues.isEmpty() || yValues.isEmpty()) {
            throw new IllegalArgumentException("No data points available for interpolation.");
        }

        // Using Lagrange interpolation to calculate the constant term (f(0))
        BigInteger constantTerm = BigInteger.ZERO;
        int n = xValues.size();

        for (int i = 0; i < n; i++) {
            BigInteger li = BigInteger.ONE;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    BigInteger xj = BigInteger.valueOf(Math.round(xValues.get(j)));
                    BigInteger xi = BigInteger.valueOf(Math.round(xValues.get(i)));
                    
                    li = li.multiply(xj.negate()).divide(xi.subtract(BigInteger.valueOf(Math.round(xValues.get(j)))));
                }
            }
            constantTerm = constantTerm.add(li.multiply(yValues.get(i)));
        }

        return constantTerm;
    }

    public static void main(String[] args) {
        String filePath = "data.json"; // Path to your JSON file

        // Read the JSON data from the file
        StringBuilder jsonString = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading the JSON file: " + e.getMessage());
            return;
        }

        JSONObject data = new JSONObject(jsonString.toString());

        try {
            BigInteger constantTerm = findConstantTerm(data);
            System.out.println("Constant Term: " + constantTerm);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
