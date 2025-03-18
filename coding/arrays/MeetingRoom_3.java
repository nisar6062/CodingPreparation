package coding.arrays;

import java.util.*;

public class MeetingRoom_3 {

    public static void main(String[] args) {
        int[][] intervals = {
                { 16, 22 },
                { 5, 10 },
                { 15, 20 },
                { 10, 17 }
        };

        int k = 2; // Number of available conference rooms
        System.out.println("Maximum number of meetings that can be held: " + maxMeetings(intervals, k));
    }

    public static int maxMeetings(int intervals[][], int k) {
        if (intervals.length == 0 || k == 0)
            return 0;
        Arrays.sort(intervals, ((a, b) -> a[0] - b[0]));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(intervals[0][1]);

        int meetings = 1;
        for (int i = 1; i < intervals.length; i++) {
            while (!minHeap.isEmpty() && intervals[i][0] >= minHeap.peek()) {
                minHeap.poll();
            }
            if (k > minHeap.size()) {
                meetings++;
                minHeap.offer(intervals[i][1]);
            }
        }
        return meetings;
    }
}
