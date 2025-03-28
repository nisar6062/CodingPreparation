package coding;

public class charRepl {
    public int characterReplacement(String s, int k) {
        int left = 0, maxCount = 0, maxLength = 0;
        int[] freq = new int[26]; // Frequency array for characters A-Z

        for (int right = 0; right < s.length(); right++) {
            freq[s.charAt(right) - 'A']++;
            maxCount = Math.max(maxCount, freq[s.charAt(right) - 'A']);

            // If more than k replacements are needed, shrink the window
            while ((right - left + 1) - maxCount > k) {
                freq[s.charAt(left) - 'A']--;
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }
        System.out.println(s.substring(left, left + maxLength));

        return maxLength;
    }

    public static void main(String[] args) {
        charRepl sol = new charRepl();
        // System.out.println(sol.characterReplacement("ABAB", 2)); // Output: 4
        System.out.println(sol.characterReplacement("AABBAB", 1)); // Output: 4
    }
}
