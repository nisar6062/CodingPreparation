package coding.graph;

import java.util.HashMap;
import java.util.Map;

class LCS {
    public static void main(String[] args) {
        int arr[] = { 100, 4, 200, 1, 3, 2 };
        LCS obj = new LCS();
        System.out.println("longestConsecutiveSeq: " + obj.longestConsecutiveSeq(arr));
    }

    Map<Integer, Integer> parent = new HashMap<>(); // Stores parent of each element
    Map<Integer, Integer> size = new HashMap<>(); // Stores size of each set

    public int longestConsecutiveSeq(int[] nums) {
        if (nums.length == 0)
            return 0;

        // Step 1: Initialize Union-Find sets
        for (int num : nums) {
            parent.put(num, num); // Each element is its own parent initially
            size.put(num, 1); // Each element starts with size 1
        }

        // Step 2: Perform Union operations for consecutive numbers
        for (int num : nums) {
            if (parent.containsKey(num - 1)) {
                union(num, num - 1);
            }
            if (parent.containsKey(num + 1)) {
                union(num, num + 1);
            }
        }

        // Step 3: Find the largest component size
        int maxLen = 0;
        System.out.println(size);
        for (int num : nums) {
            maxLen = Math.max(maxLen, size.get(find(num)));
        }
        return maxLen;
    }

    // Find with path compression
    private int find(int x) {
        if (parent.get(x) != x) {
            parent.put(x, find(parent.get(x))); // Path compression
        }
        return parent.get(x);
    }

    // Union by size
    private void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            // Merge the smaller tree into the larger one
            if (size.get(rootX) > size.get(rootY)) {
                parent.put(rootY, rootX);
                size.put(rootX, size.get(rootX) + size.get(rootY));
            } else {
                parent.put(rootX, rootY);
                size.put(rootY, size.get(rootY) + size.get(rootX));
            }
        }
    }
}
