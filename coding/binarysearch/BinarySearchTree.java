package coding.binarysearch;

public class BinarySearchTree {
    public static void main(String[] args) {
        Node n1 = new Node(5, null, null);
        Node n3 = new Node(1, null, null);
        Node n2 = new Node(2, n3, null);
        Node n4 = new Node(3, n2, n1);
        System.out.println("result: " + doBinarySearch(n4, 0));
    }

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public static boolean doBinarySearch(Node root, int find) {
        if (root == null)
            return false;
        if (root.value == find)
            return true;
        if (root.value < find)
            return doBinarySearch(root.right, find);
        if (root.value > find)
            return doBinarySearch(root.left, find);

        return false;
    }
}
