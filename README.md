# SecretSharingProject
This project implements a secret sharing scheme using Lagrange interpolation. It takes encoded values in different bases and computes the constant term of the polynomial representing these values.

## Features

- Decode values from various bases (e.g., base 3, base 6, etc.)
- Calculate the constant term using Lagrange interpolation
- Supports JSON input format

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- JSON library (e.g., `org.json`)

### Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/blackshadow22084/SecretSharingProject/
   cd SecretSharingProject
2. **Download the JSON library:**

Download the `json-*.jar` file from Maven Repository and place it in the project directory.

- Running the Project
  Compile the Java code: `javac -cp ".;json-*.jar" SecretSharing.java`
  Run the program: `java -cp ".;json-*.jar" SecretSharing`
3. **JSON Input Format**
The program expects a JSON input file named data.json in the following format:
`{
    "keys": {
        "n": 10,
        "k": 7
    },
    "1": {
        "base": "6",
        "value": "13444211440455345511"
    },
    ...
}
`
4. ***Example Output***
  
When running the program, you should see output similar to the following:
`xValues: [1.0, 2.0, ...]
yValues: [995085094601491, ...]
Constant Term: -952819033749472775`

5. ***Contributing***

If you would like to contribute to this project, please fork the repository and submit a pull request.
. ***License***

This project is licensed under the MIT License.
