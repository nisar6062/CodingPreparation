package coding.tree;

import java.util.LinkedList;
import java.util.Queue;

public class BST {
    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        n2.left = n1;
        n2.right = n3;
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        n3.right = n4;
        n4.right = n5;
        System.out.println("Pre order: " + preOrder(n2));

        System.out.println("BFS");
        bfs(n2);
    }

    public static class Node {
        Node left;
        Node right;
        int val;

        Node(int val) {
            this.val = val;
        }
    }

    public static int preOrder(Node root) {
        if (root == null)
            return 0;
        int left = preOrder(root.left);
        System.out.print(root.val + ", ");
        int right = preOrder(root.right);
        return left + right + 1;
    }

    public static void bfs(Node root) {
        if (root == null)
            return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();
            for (int i = 1; i <= size; i++) {
                Node node = queue.poll();
                // System.out.print(node.val + ", ");
                if (i == size) {
                    System.out.print(node.val + ", ");
                }
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
        }
        System.out.println("Level: " + level);
    }
}
