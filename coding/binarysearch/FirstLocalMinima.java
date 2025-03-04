package coding.binarysearch;

public class FirstLocalMinima {

    public static void main(String[] args) {
        int input[] = { 6, 1, 3, 4, 2 };
        int minima = firstLocalMinima(input);
        System.out.println("Minima : " + minima);
    }

    public static int bfs(int[] input, int left, int right) {
        int mid = (left + right) / 2;

        if (mid > 0 && mid < input.length - 1 && input[mid - 1] > input[mid] && input[mid] < input[mid + 1]) { // found
            return mid;
        }
        if (mid > 0 && input[mid - 1] < input[mid]) { // go left
            return bfs(input, left, mid - 1);
        }
        if (mid < input.length - 1 && input[mid + 1] < input[mid]) { // go right
            return bfs(input, mid + 1, right);
        }
        return -1;
    }

    public static int firstLocalMinima(int[] input) {
        return bfs(input, 0, input.length - 1);
    }

}
