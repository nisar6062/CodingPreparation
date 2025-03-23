package coding.linkedlist;

public class ReOrderList {
    static class Node {
        Node next;
        int val;

        Node(Node next, int val) {
            this.next = next;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Node n6 = new Node(null, 6);
        Node n5 = new Node(n6, 5);
        Node n4 = new Node(n5, 4);
        Node n3 = new Node(n4, 3);
        Node n2 = new Node(n3, 2);
        Node n1 = new Node(n2, 1);
        print(n1);
        reorder(n1);
        System.out.println("res:");
        print(n1);
    }

    public static void reorder(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        System.out.println("Mid: " + slow.val);

        Node start = slow;
        Node prev = null;
        while (start != null) {
            Node temp = start.next;
            start.next = prev;
            prev = start;
            start = temp;
        }
        System.out.println("prev");
        print(prev);
        Node second = prev;
        start = head;
        // 1-2-3-4-5-6
        // 1-2-3 6-5-4
        // 1-6-2-5-3-4
        while (second != null) {
            Node temp1 = start.next;
            Node temp2 = second.next;
            start.next = second;
            if (temp2 != null)
                second.next = temp1;
            start = temp1;
            second = temp2;
        }
    }

    public static void print(Node head) {
        Node start = head;
        int count = 0;
        while (start != null && count < 10) {
            System.out.print(start.val + ", ");
            count++;
            start = start.next;
        }
        System.out.println();
    }

}
