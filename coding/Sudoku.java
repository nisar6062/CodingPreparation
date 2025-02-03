package coding;

import java.util.*;

class Sudoku {
    public static void main(String[] args) {
        char arr[][] = { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '1', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
        char arr2[][] = {
                { '.', '.', '.', '.', '.', '.', '5', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' },

                { '9', '3', '.', '.', '2', '.', '4', '.', '.' },
                { '.', '.', '7', '.', '.', '.', '3', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' },

                { '.', '.', '.', '3', '4', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '3', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '5', '2', '.', '.' } };
        System.out.println("Valid: " + isValidSudoku(arr2));
    }

    public static boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            Set<Character> horizontalSet = new HashSet<>();
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
                if (board[i][j] == '.')
                    continue;
                if (horizontalSet.contains(board[i][j])) {
                    return false;
                }
                horizontalSet.add(board[i][j]);
            }
            System.out.println();
        }
        // System.out.println("----->>>>>");
        for (int j = 0; j < board[0].length; j++) {
            Set<Character> verticalSet = new HashSet<>();
            for (int i = 0; i < board.length; i++) {
                System.out.print(board[i][j] + " ");
                if (board[i][j] == '.')
                    continue;
                if (verticalSet.contains(board[i][j])) {
                    return false;
                }
                verticalSet.add(board[i][j]);
            }
            System.out.println();
        }
        // System.out.println("---After Vert-->>>>>");
        int i = 0, j = 0, split = 3;
        Set<Character> colSet = new HashSet<>();
        while (i < board.length && j < board[0].length) {
            System.out.print(board[i][j] + "=(" + i + "," + j + ") ,");
            if (board[i][j] != '.') {
                if (colSet.contains(board[i][j])) {
                    return false;
                }
                colSet.add(board[i][j]);
            }

            if (i != 0 && j != 0 && ((j + 1) % split) == 0 && ((i + 1) % split) == 0) {
                colSet = new HashSet<>();
                if (i == board.length - 1 && j == board[0].length - 1)
                    return true;
                if (j == board[0].length - 1) {
                    j = 0;
                    i++;
                } else {
                    i = i - split + 1;
                    j++;
                }
            } else if (j != 0 && ((j + 1) % split == 0)) {
                i++;
                j = j - split + 1;
            } else
                j++;
        }

        return true;
    }
}
