package coding.arrays;

/* 1-a, 2-b,...26-z
 *  126 => az OR abf OR  => 3 ways
 */
public class CharacterCombWays {
    public static void main(String[] args) {
        System.out.println("noOfWays: " + noOfWays("26", 0));
    }

    public static int noOfWays(String input, int index) {
        if (index >= input.length()) {
            return 1;
        }
        int curr = Character.getNumericValue(input.charAt(index));
        if (curr == 0) {
            return 0;
        }
        if (index == input.length() - 1)
            return 1;
        int ways = noOfWays(input, index + 1);
        if (index + 1 < input.length()) {
            int next = Integer.parseInt(input.charAt(index + 1) + "");
            int num = 10 * curr + next;
            if (num <= 26) {
                ways += noOfWays(input, index + 2);
            }
        }

        return ways;
    }
}