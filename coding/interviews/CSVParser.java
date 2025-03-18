package coding.interviews;

import java.io.*;
import java.util.*;

public class CSVParser {
    private static final String ERROR = "#ERR";
    private static List<List<String>> csvData = new ArrayList<>();

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: java CSVReader <filename>");
            System.exit(1);
        }

        String filePath = args[0]; // Get filename from command-line argument
        try {
            readCsvFile(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readCsvFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] cells = line.split(",");
                List<String> row = new ArrayList<>();
                for (String cell : cells) {
                    row.add(cell.trim());
                }
                csvData.add(row);
            }

            // After reading all the input, evaluate the expressions in each cell
            for (int i = 0; i < csvData.size(); i++) {
                for (int j = 0; j < csvData.get(i).size(); j++) {
                    String evaluatedValue = evaluateExpression(csvData.get(i).get(j), i, j);
                    csvData.get(i).set(j, evaluatedValue);
                }
            }

            printCSV(csvData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void mainE(String[] args) throws IOException {
        // BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // String line;
        // while ((line = reader.readLine()) != null && !line.isEmpty()) {

        String lines[] = { "10, 1 3 +, 2 3 -", "b1 b2 *, a1, b1 a2 / c1 +", "+, 1 2 3, c3" };
        for (String line : lines) {
            String[] cells = line.split(",");
            List<String> row = new ArrayList<>();
            for (String cell : cells) {
                row.add(cell.trim());
            }
            csvData.add(row);
        }

        // After reading all the input, evaluate the expressions in each cell
        for (int i = 0; i < csvData.size(); i++) {
            for (int j = 0; j < csvData.get(i).size(); j++) {
                String evaluatedValue = evaluateExpression(csvData.get(i).get(j), i, j);
                csvData.get(i).set(j, evaluatedValue);
            }
        }

        printCSV(csvData);
    }

    private static boolean isNumber(String expression) {
        return expression.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    private static boolean isCellName(String expression) {
        return expression.matches("[a-z]+[0-9]+");
    }

    private static String evaluateExpression(String expr, int row, int col) {
        // If the expression is a number, return it formatted to 1 decimal place
        if (isNumber(expr)) {
            return String.format("%.1f", Double.parseDouble(expr));
        }

        // Process the expression (RPN-like evaluation or cell references)
        String[] tokens = expr.split(" ");
        Stack<Double> stack = new Stack<>();

        for (String token : tokens) {
            if (isNumber(token)) {
                // If it's a number, push it to the stack
                stack.push(Double.parseDouble(token));
            } else if (isCellName(token)) {
                String refValue = getCellValue(token);
                if (refValue.equals(ERROR)) {
                    return ERROR; // If the reference is invalid, return #ERR
                }
                stack.push(Double.parseDouble(refValue));
            } else {
                // For operators, ensure we have enough operands
                if (stack.size() < 2) {
                    return ERROR;
                }
                double b = stack.pop();
                double a = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(a + b);
                        break;
                    case "-":
                        stack.push(a - b);
                        break;
                    case "*":
                        stack.push(a * b);
                        break;
                    case "/":
                        if (b == 0) {
                            return ERROR; // Division by zero
                        }
                        stack.push(a / b);
                        break;
                    default:
                        return ERROR; // Invalid operator
                }
            }
        }

        // If the stack has exactly one element, return it, otherwise return error
        return stack.size() == 1 ? String.format("%.1f", stack.pop()) : ERROR;
    }

    static int count = 0;

    // Helper method to get cell value based on the reference (e.g., A1, B1)
    private static String getCellValue(String reference) {
        char col = reference.charAt(0);
        int row = Integer.parseInt(reference.substring(1)) - 1;

        int colIndex = col - 'a';

        String cellVal = csvData.get(row).get(colIndex);
        if (cellVal.matches("[a-z]+[0-9]+")) {
            count++;
            if (count == 15) {
                System.exit(1);
            }
            if (cellVal.equals(reference)) {
                // remove circular dependency
                return ERROR;
            }
            String cellValEvaluated = getCellValue(cellVal);
            csvData.get(row).set(colIndex, cellValEvaluated);
        }

        // Check if the row and column are within bounds
        if (row < 0 || row >= csvData.size() || colIndex < 0 || colIndex >= csvData.get(row).size()) {
            return ERROR;
        }

        return csvData.get(row).get(colIndex); // Return the value from the 2D list
    }

    // Print the final CSV data
    private static void printCSV(List<List<String>> csvData) {
        for (List<String> row : csvData) {
            System.out.println(String.join(",", row));
        }
    }
}
