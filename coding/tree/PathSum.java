package coding.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import coding.tree.BST.Node;

/*          2
 *       1     -3  
 *         3      4 
 *              2   5
 */
public class PathSum {
    public static void mainE(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(-3);
        n2.left = n1;
        n2.right = n3;
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(2);
        Node n7 = new Node(3);
        n3.right = n4;
        n4.right = n5;
        n4.left = n6;
        n5.right = n7;

        System.out.println("pathSumFromRoot: " + pathSumFromRoot(n2, 0, 5, new ArrayList<>()));

        System.out.println("maxAnyPathSum: " + maxAnyPathSum(n2, 0, 3, new ArrayList<>()));

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        System.out.println("pathCount: " + pathCount(n2, 0, 3, map));
        System.out.println("pathCount_2: " + countPaths(n2, 3));
    }

    public static int maxAnyPathSum(Node root, int sum, int target, List<Integer> list) {
        if (root == null) {
            return 0;
        }
        int left = maxAnyPathSum(root.left, sum, target, list);
        int right = maxAnyPathSum(root.right, sum, target, list);
        return Math.max(left, 0) + Math.max(right, 0) + root.val;
    }

    public static boolean pathSumFromRoot(Node root, int sum, int target, List<Integer> list) {
        if (root == null)
            return false;

        sum += root.val;
        list.add(root.val);
        if (root.left == null && root.right == null && sum == target) {
            System.out.println("matched path: " + list + ", sum: " + sum);
            return true;
        }
        return pathSumFromRoot(root.left, sum, target, list) || pathSumFromRoot(root.right, sum, target, list);
    }

    public static int pathCount(Node root, int sum, int target, Map<Integer, Integer> map) {
        if (root == null)
            return 0;

        sum += root.val;
        int count = map.getOrDefault(sum - target, 0);
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        System.out.println("sum:" + sum + ", keys: " + map.keySet());
        count += pathCount(root.left, sum, target, map);
        count += pathCount(root.right, sum, target, map);
        map.put(sum, map.getOrDefault(sum, 0) - 1);

        return count;
    }

    public static int countPaths(Node root, int targetSum) {
        HashMap<Integer, Integer> prefixSums = new HashMap<>();
        prefixSums.put(0, 1); // Base case: One path with sum 0 before starting
        return dfs(root, 0, targetSum, prefixSums);
    }

    private static int dfs(Node node, int currSum, int targetSum, Map<Integer, Integer> prefixSums) {
        if (node == null)
            return 0;

        currSum += node.val;
        int count = prefixSums.getOrDefault(currSum - targetSum, 0);
        prefixSums.put(currSum, prefixSums.getOrDefault(currSum, 0) + 1);
        count += dfs(node.left, currSum, targetSum, prefixSums);
        count += dfs(node.right, currSum, targetSum, prefixSums);
        prefixSums.put(currSum, prefixSums.get(currSum) - 1);

        return count;
    }

    /**
     * 10
     * / \
     * 5 15
     * / \ \
     * 3 7 18
     */
    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(15);
        root.left.left = new Node(3);
        root.left.right = new Node(7);
        root.right.right = new Node(18);

        int low = 7, high = 15;
        rangeSearch(root, low, high);
        pathSum(root, 0, 22, new ArrayList<>());
    }

    private static void pathSum(Node root, int sum, int target, List<Integer> list) {
        if (root == null)
            return;

        sum += root.val;
        list.add(root.val);
        if (sum == target) {
            System.out.println("Found: " + list);
        }
        pathSum(root.left, sum, target, list);
        pathSum(root.right, sum, target, list);
        list.remove(list.size() - 1);
    }

    private static void rangeSearch(Node root, int low, int high) {
        if (root == null) {
            return;
        }
        if (root.val > low) {
            rangeSearch(root.left, low, high);
        }
        if (root.val >= low && root.val <= high) {
            System.out.println(root.val);
        }
        if (root.val < high) {
            rangeSearch(root.right, low, high);
        }
    }
}
