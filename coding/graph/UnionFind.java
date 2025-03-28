package coding.graph;

import java.util.Arrays;

public class UnionFind {
    int[] parent;
    int[] rank;

    public UnionFind(int n) {
        this.parent = new int[n];
        this.rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return this.parent[x];
    }

    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        System.out.println("root: --X:" + rootX + "---Y:" + rootY + "----" + (rootX == rootY));
        if (rootX == rootY) {
            return false;
        }
        // Union by Rank
        if (this.rank[rootY] > this.rank[rootX]) {
            parent[rootX] = rootY; // Attach smaller tree to larger tree
        } else if (this.rank[rootX] > this.rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++; // Only increment rank when merging equal rank trees
        }
        return true;
    }

    public static boolean hasCycle(int[][] edges, int n) {
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            boolean isOk = uf.union(edge[0], edge[1]);
            System.out.println("Edge: --" + edge[0] + "---" + edge[1] + "--OK: " + isOk);
            if (!isOk) {
                return true;
            }
        }
        System.out.println("parent: " + Arrays.toString(uf.parent));
        System.out.println("rank: " + Arrays.toString(uf.rank));
        return false;
    }

    public static void main(String[] args) {
        int[][] edges_1 = { { 0, 1 }, { 1, 2 }, { 1, 3 }, { 2, 3 } };
        int[][] edges_2 = { { 0, 1 }, { 1, 2 }, { 1, 3 }, { 3, 4 } };
        System.out.println("isCycle: " + hasCycle(edges_2, 5));
    }
}
