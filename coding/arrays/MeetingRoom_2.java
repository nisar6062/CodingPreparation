package coding.arrays;

import java.util.*;

public class MeetingRoom_2 {
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) {
            return 1;
        }
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < intervals.length; i++) {
            while (!pq.isEmpty() && intervals[i][0] >= pq.peek()) {
                pq.poll();
            }
            pq.add(intervals[i][1]);
            max = Math.max(max, pq.size());
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] intervals = {
                { 0, 30 },
                { 5, 10 },
                { 15, 20 }
        };

        System.out.println("Minimum number of meeting rooms required: " + minMeetingRooms(intervals));
        System.out.println(getMeetingRoomsReqd(intervals));
    }

    public static int getMeetingRoomsReqd(int intervals[][]) {
        if (intervals.length == 0)
            return 0;
        Arrays.sort(intervals, ((a, b) -> a[0] - b[0]));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // minHeap.offer(intervals[0][1]);

        for (int i = 0; i < intervals.length; i++) {
            while (!minHeap.isEmpty() && intervals[i][0] >= minHeap.peek()) {
                minHeap.poll();
            }
            minHeap.add(intervals[i][1]);
        }
        return minHeap.size();
    }
}
