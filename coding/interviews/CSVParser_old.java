package coding.interviews;

import java.io.*;
import java.util.*;

public class CSVParser_old {
    private static final String ERROR = "#ERR";
    private static Map<String, String> cellMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<List<String>> csvData = new ArrayList<>();

        String line;
        int rowNum = 0;
        while ((line = reader.readLine()) != null) {
            String[] cells = line.split(",");
            List<String> row = new ArrayList<>();
            for (int colNum = 0; colNum < cells.length; colNum++) {
                String cell = cells[colNum].trim();
                String key = getCellKey(colNum, rowNum);
                cellMap.put(key, cell);
                row.add(cell);
            }
            csvData.add(row);
            rowNum++;
        }

        for (int i = 0; i < csvData.size(); i++) {
            for (int j = 0; j < csvData.get(i).size(); j++) {
                String key = getCellKey(j, i);
                csvData.get(i).set(j, evaluateExpression(cellMap.get(key)));
            }
        }

        printCSV(csvData);
    }

    private static String evaluateExpression(String expr) {
        if (expr.matches("[-+]?[0-9]*\\.?[0-9]+"))
            return expr;

        String[] tokens = expr.split(" ");
        Stack<Double> stack = new Stack<>();

        for (String token : tokens) {
            String regex = "[-+]?[0-9]*\\.?[0-9]+";
            if (token.matches(regex)) {
                stack.push(Double.parseDouble(token));
            } else if (token.matches("[A-Z]+[0-9]+")) {
                String refValue = cellMap.getOrDefault(token, ERROR);
                if (refValue.equals(ERROR))
                    return ERROR;
                refValue = evaluateExpression(refValue);
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
        return stack.size() == 1 ? String.valueOf(stack.pop()) : ERROR;
    }

    private static String getCellKey(int col, int row) {
        return (char) ('A' + col) + String.valueOf(row + 1);
    }

    private static void printCSV(List<List<String>> csvData) {
        for (List<String> row : csvData) {
            System.out.println(String.join(",", row));
        }
    }
}
