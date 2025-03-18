package coding.arrays;

import java.util.*;

public class Test {
    public static int maxMeetings(int[][] intervals, int k) {
        if (intervals == null || intervals.length == 0 || k == 0)
            return 0;

        // Step 1: Sort meetings by their start times
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // Min-heap to track meeting end times
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        int meetingCount = 0;

        // Step 2: Process each meeting
        for (int[] meeting : intervals) {
            // Remove all meetings whose end time is less than the start time of the current
            // meeting
            while (!heap.isEmpty() && heap.peek() <= meeting[0]) {
                heap.poll();
            }

            // If the number of rooms in the heap is less than k, we can add the current
            // meeting
            if (heap.size() < k) {
                heap.offer(meeting[1]);
                meetingCount++;
            }
        }

        return meetingCount;
    }

    public static void main(String[] args) {
        int[][] intervals = {
                { 0, 30 },
                { 5, 10 },
                { 15, 20 },
                { 10, 15 }
        };

        // int k = 2; // Number of available conference rooms
        // System.out.println("Maximum number of meetings that can be held: " +
        // maxMeetings(intervals, k));

        LinkedList<Integer> deque = new LinkedList<>();
        deque.addLast(1);
        deque.addLast(2);
        Stack<Integer> st = new Stack<>();
        Queue<String> q = new LinkedList<>();
        System.out.println("abcd".substring(0, 1));

        System.out.println("---peekFirst: " + deque.peekFirst());
        int arr[] = { 5, 3, 1, 2, 2, 0 };
        int res[] = twoSum(arr, 0);
        System.out.println("result: " + res[0] + ", " + res[1]);
        int res3[] = threeSum(arr, 5);
        System.out.println("threeSum: " + res3[0] + ", " + res3[1] + ", " + res3[2]);
    }

    private static int[] twoSum(int arr[], int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            Integer index = map.get(target - arr[i]);
            if (index != null) {
                return new int[] { index, i };
            }

            map.put(arr[i], i);
        }
        return new int[] { -1, -1 };
    }

    private static int[] threeSum(int arr[], int target) {
        Arrays.sort(arr);

        for (int i = 0; i < arr.length; i++) {
            if (i < arr.length - 1 && arr[i] == arr[i + 1])
                continue;
            int curr = arr[i];
            int left = i + 1;
            int right = arr.length - 1;

            while (left < right) {
                int sum = curr + arr[left] + arr[right];
                if (sum == target) {
                    while (left < right && arr[left] == arr[left + 1])
                        left++;
                    while (left < right && arr[right] == arr[right - 1])
                        right--;
                    System.out.println(arr[i] + "--" + arr[left] + "--" + arr[right]);
                    // return new int[] { arr[i], arr[left], arr[right] };
                    left++;
                    right--;
                }

                if (sum < target) {
                    left++;
                }
                if (sum > target) {
                    right--;
                }
            }
        }
        return new int[] { -1, -1, -1 };
    }
}
