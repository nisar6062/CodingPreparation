package coding.dynamic_programming;

public class PatternMatch {
    public static void main(String[] args) {
        System.out.println("RegEx: " + regExEval_2("caab", "c*a*b"));
    }

    public static boolean regExEval_2(String input, String exp) {
        int m = exp.length(), n = input.length();
        boolean[][] match = new boolean[m + 1][n + 1];

        match[0][0] = true;

        // Handling cases where pattern contains "a*", "b*", etc.
        for (int i = 2; i <= m; i++) {
            if (exp.charAt(i - 1) == '*') {
                match[i][0] = match[i - 2][0];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char ec = exp.charAt(i - 1);
                char ic = input.charAt(j - 1);

                if (ec == '.' || ec == ic) {
                    match[i][j] = match[i - 1][j - 1];
                } else if (ec == '*') {
                    match[i][j] = match[i - 2][j] ||
                            (match[i][j - 1] && (exp.charAt(i - 2) == '.' || exp.charAt(i - 2) == ic));
                }
            }
        }

        return match[m][n];
    }

    public static boolean regExEval(String input, String exp) {
        int m = exp.length(), n = input.length();
        boolean match[][] = new boolean[m + 1][n + 1];
        match[0][0] = true;
        for (int i = 2; i <= m; i++) {
            if (exp.charAt(i) == '*')
                match[i][0] = match[i - 2][0];
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char ec = exp.charAt(i - 1);
                char ic = input.charAt(j - 1);
                if (ec == '.' || ec == ic) {
                    match[i][j] = match[i - 1][j - 1];
                } else if (ec == '*') {
                    char prevChar = exp.charAt(i - 2);
                    match[i][j] = match[i - 2][j] || (match[i][j - 1] && (prevChar == '.' || prevChar == ic));
                }
            }
        }
        return match[m][n];
    }

    public static boolean regEx(String input, String exp) {
        boolean match[][] = new boolean[exp.length()][input.length()];
        for (int i = 0; i < exp.length(); i++) {
            for (int j = 0; j < input.length(); j++) {
                if (input.charAt(j) == exp.charAt(i)) {
                    if (j <= i && i == 0 || (j == 0 && match[i - 1][j]))
                        match[i][j] = true;
                    else if (i > 0 && j > 0 && j <= i)
                        match[i][j] = match[i - 1][j - 1];
                } else if (exp.charAt(i) == '*') {
                    if (i <= 1 || (j == 0 && i >= 2 && match[i - 2][j]))
                        match[i][j] = true;
                    else if (i > 0 && j > 0) {
                        // System.out.println("i: " + i + ",j:" + j);
                        match[i][j] = match[i][j - 1];
                    }
                } else if (exp.charAt(i) == '.') {
                    if (i == 0 || (j == 0 && match[i - 1][j]))
                        match[i][j] = true;
                    else if (i > 0 && j > 0)
                        match[i][j] = match[i - 1][j - 1];
                }
            }
        }
        return match[exp.length() - 1][input.length() - 1];
    }
}
