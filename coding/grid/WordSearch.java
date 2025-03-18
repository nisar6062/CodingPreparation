package coding.grid;

public class WordSearch {

    public static void main(String[] args) {
        Character words[][] = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };
        System.out.println("Result: " + wordSearch(words, "ABCCEDA"));
    }

    public static boolean wordSearch(Character words[][], String target) {
        int m = words.length, n = words[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (target.charAt(0) == words[i][j]) {
                    words[i][j] = '.';
                    if (exists(words, 1, i, j, target)) {
                        return true;
                    }
                    words[i][j] = target.charAt(0);
                }
            }
        }
        return false;
    }

    public static boolean exists(Character words[][], int x, int i, int j, String target) {
        if (x == target.length()) {
            return true;
        }
        int directions[][] = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        for (int[] dir : directions) {
            int newI = i + dir[0];
            int newJ = j + dir[1];
            if (newI >= 0 && newJ >= 0 && newI < words.length && newJ < words[0].length
                    && words[newI][newJ] == target.charAt(x)) {
                words[newI][newJ] = '.';
                if (exists(words, x + 1, newI, newJ, target))
                    return true;
                words[newI][newJ] = target.charAt(x);
            }
        }

        return false;
    }

}
