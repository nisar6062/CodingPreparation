package coding.interviews;

import java.io.*;
import java.util.*;

public class CSVPostfixEvaluator {

    private static final String ERROR = "#ERR";
    private static List<List<String>> csvData = new ArrayList<>();
    private static Map<String, String> evaluatedCells = new HashMap<>();

    public static void main(String[] args) throws IOException {
        String lines[] = { "10, 1 3 +, 2 3 -", "b1 b2 *, a1, b1 a2 / c1 +", "+, 1 2 3, c3" };
        for (String line : lines) {
            String[] cells = line.split(",");
            List<String> row = new ArrayList<>();
            for (String cell : cells) {
                row.add(cell.trim());
            }
            csvData.add(row);
        }

        // Evaluate each cell row-wise
        for (int i = 0; i < csvData.size(); i++) {
            for (int j = 0; j < csvData.get(i).size(); j++) {
                String cellKey = getCellKey(j, i);
                if (!evaluatedCells.containsKey(cellKey)) {
                    evaluatedCells.put(cellKey, evaluateExpression(csvData.get(i).get(j), i, new HashSet<>()));
                }
                csvData.get(i).set(j, evaluatedCells.get(cellKey));
            }
        }

        // Print final evaluated CSV
        printCSV(csvData);
    }

    private static String evaluateExpression(String expr, int row, Set<String> visited) {
        if (expr.matches("[-+]?[0-9]*\\.?[0-9]+")) {
            return formatNumber(expr);
        }

        // Handle cyclic references
        if (visited.contains(expr)) {
            return ERROR;
        }
        visited.add(expr);

        // Process Reverse Polish Notation (RPN)
        String[] tokens = expr.split(" ");
        Stack<Double> stack = new Stack<>();

        for (String token : tokens) {
            if (token.matches("[-+]?[0-9]*\\.?[0-9]+")) {
                stack.push(Double.parseDouble(token));
            } else if (token.matches("[a-z]+[0-9]+")) {
                String refValue = getCellValue(token, row, visited);
                if (refValue.equals(ERROR))
                    return ERROR;
                stack.push(Double.parseDouble(refValue));
            } else {
                if (stack.size() < 2)
                    return ERROR;
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
                        if (b == 0)
                            return ERROR;
                        stack.push(a / b);
                        break;
                    default:
                        return ERROR;
                }
            }
        }
        return stack.size() == 1 ? formatNumber(String.valueOf(stack.pop())) : ERROR;
    }

    private static String getCellValue(String reference, int row, Set<String> visited) {
        int col = reference.charAt(0) - 'a';

        if (row < 0 || row >= csvData.size() || col < 0 || col >= csvData.get(row).size()) {
            return ERROR;
        }

        String cellKey = getCellKey(col, row);

        // If already evaluated, return cached value
        if (evaluatedCells.containsKey(cellKey)) {
            return evaluatedCells.get(cellKey);
        }

        // Evaluate the cell and store result
        String value = evaluateExpression(csvData.get(row).get(col), row, visited);
        evaluatedCells.put(cellKey, value);
        return value;
    }

    private static String getCellKey(int col, int row) {
        return getColumnLabel(col) + (row + 1);
    }

    private static String getColumnLabel(int col) {
        StringBuilder label = new StringBuilder();
        while (col >= 0) {
            label.insert(0, (char) ('A' + (col % 26)));
            col = (col / 26) - 1;
        }
        return label.toString();
    }

    private static String formatNumber(String num) {
        return String.format("%.1f", Double.parseDouble(num));
    }

    private static void printCSV(List<List<String>> csvData) {
        for (List<String> row : csvData) {
            System.out.println(String.join(",", row));
        }
    }
}
