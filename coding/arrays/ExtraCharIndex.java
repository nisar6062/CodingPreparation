package coding.arrays;

public class ExtraCharIndex {

    public static String printStringWithMarkers(String strn, int pValue) {
        String out = "";
        for (int i = 0; i < pValue; i++) {
            out += String.valueOf(strn.charAt(i));
        }
        out += "«";
        out += String.valueOf(strn.charAt(pValue)) + "»";
        for (int i = pValue + 1; i < strn.length(); i++) {
            out += String.valueOf(strn.charAt(i));
        }
        return out;
    }

    public static int extraCharacterIndex(String str1, String str2) {
        int result = 0;
        for (int i = 0; i < str1.length(); i++) {
            result ^= str1.charAt(i);
        }
        for (int i = 0; i < str2.length(); i++) {
            result ^= str2.charAt(i);
        }
        System.out.println("result: " + result + "---" + ((char) result));
        if (str1.length() > str2.length()) {
            char c = (char) result;
            return str1.indexOf(c);
        } else
            return str2.indexOf((char) result);

    }

    public static void main(String[] args) {
        // Driver code
        // Example - 1
        String[] string1 = {
                "wxyz",
                "cbda",
                "jlkmn",
                "courae",
                "hello"
        };
        String[] string2 = {
                "zwxgy",
                "abc",
                "klmn",
                "couearg",
                "helo"
        };
        for (int i = 0; i < string1.length; i++) {
            System.out.println(i + 1 + ".\tString1 = " + string1[i] + " \n\tString2 = " + string2[i]);
            extraCharacterIndex(string1[i], string2[i]);
        }
    }
}