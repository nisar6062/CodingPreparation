package coding.grid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TwoDMatrix {

    public static void main(String[] args) {
        int input[][] = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        int found[] = bs(input, 12);
        System.out.println("found : " + found[0] + ", " + found[1]);

        // Stack<Integer> stack = new Stack<>();
        // stack.add(1);
        // stack.add(2);
        // PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        // pq.add(1);
        // pq.add(2);
        // pq.add(6);
        // pq.add(0);
        // System.out.println("Stack: " + stack.peek());
        // System.out.println("pq: " + pq.peek());

        // LinkedList<Integer> ls = new LinkedList<>();
        // Queue<Integer> queue = new LinkedList<>();

        Set<Integer> set = new HashSet<>();
        set.add(6);
        set.add(3);
        set.add(1);
        List<Integer> list = new ArrayList<>(set);
        list.sort((a, b) -> (b - a));
        System.out.println(list);
    }

    public static int[] bs(int[][] input, int target) {
        int left = 0, right = input.length - 1, colToSearch = 0, mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (input[mid][0] == target) {
                return new int[] { mid, 0 };
            } else if (input[mid][0] > target) {

                right = mid - 1;
            } else if (input[mid][0] < target) {

                left = mid + 1;
            }
        }
        System.out.println("left: " + left);
        System.out.println("right: " + right);

        int rowToSearch = right;
        left = 0;
        right = input[0].length - 1;

        while (left <= right) {
            mid = (left + right) / 2;
            if (input[rowToSearch][mid] == target) {
                return new int[] { rowToSearch, mid };
            } else if (input[rowToSearch][mid] > target) {
                right = mid - 1;
            } else if (input[rowToSearch][mid] < target) {
                left = mid + 1;
            }
        }
        System.out.println("colToSearch: " + colToSearch);
        return new int[] { -1, -1 };
    }

    public static int[] search(int[][] input, int target) {
        // bs on cols
        int left = 0, right = input.length - 1;
        int rowToSearch = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (input[mid][0] == target) {
                return new int[] { mid, 0 };
            }
            if (input[mid][0] > target) { // go left
                if (mid > 0 && input[mid - 1][0] < target) {
                    rowToSearch = mid - 1;
                    break;
                }
                right = mid - 1;
            }
            if (input[mid][0] < target) { // go right
                if (mid < input.length - 1 && input[mid + 1][0] > target) {
                    rowToSearch = mid;
                    break;
                }
                left = mid + 1;
            }
        }
        System.out.println("rowToSearch: " + rowToSearch);
        // bs on row
        left = 0;
        right = input[0].length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (input[rowToSearch][mid] == target) {
                return new int[] { rowToSearch, mid };
            }
            if (input[rowToSearch][mid] > target) { // go left
                right = mid - 1;
            }
            if (input[rowToSearch][mid] < target) { // go right
                left = mid + 1;
            }
        }
        return new int[] { -1, -1 };
    }
}
