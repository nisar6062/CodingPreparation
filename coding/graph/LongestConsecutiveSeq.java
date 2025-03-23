package coding.graph;

public class LongestConsecutiveSeq {
    private int[] parent;
    private int[] ranks;

    public LongestConsecutiveSeq(int[] arr) {
        parent = new int[arr.length];
        ranks = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            parent[i] = i;
            ranks[i] = 1;
        }
    }

    private boolean containsParent(int x) {
        for (int k : parent) {
            if (k == x)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int arr[] = { 100, 4, 200, 1, 3, 2 };
        System.out.println("longestConsecutiveSeq: " + longestConsecutiveSeq(arr));
    }

    public static int longestConsecutiveSeq(int[] arr) {
        LongestConsecutiveSeq obj = new LongestConsecutiveSeq(arr);
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int num : arr) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        for (int i = 0; i < arr.length; i++) {
            if (obj.containsParent(arr[i] + 1))
                obj.union(arr[i], arr[i] + 1);

            if (obj.containsParent(arr[i] - 1))
                obj.union(arr[i], arr[i] - 1);
        }
        int maxLen = 0;
        for (int i = 0; i < arr.length; i++) {
            maxLen = Math.max(maxLen, obj.ranks[obj.find(i)]);
        }
        return maxLen;
    }

    private int find(int x) {
        System.out.println("x: " + x);
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    private boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY)
            return false;

        if (ranks[rootY] > ranks[rootX]) {
            parent[rootX] = rootY;
            ranks[rootY] += ranks[rootX];
        } else {
            parent[rootY] = rootX;
            ranks[rootX] += ranks[rootY];
        }
        return true;
    }
}
