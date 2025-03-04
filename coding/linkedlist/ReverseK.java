/**
 * Definition for singly-linked list.
 * 
 */

// [5,4,3,2,1] s= 2,4,5 e = 1,3
// [2,1,4,3,5]

package coding.linkedlist;

import java.util.ArrayList;
import java.util.List;

class ReverseK {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        int count = 1, size = 0;
        ListNode prevStart = null, prevEnd = null;
        ListNode oldHead = head, resultHead = null;
        while (oldHead != null) {
            size++;
            oldHead = oldHead.next;
        }
        int possibilityCount = size / k;
        System.out.println("size : " + size);
        System.out.println("possibilityCount : " + possibilityCount);

        while (head != null) {
            System.out.println("count==>>: " + count + ", curr: " + head.val);
            // print(oldHead);
            if (possibilityCount + 1 == count) {
                prevEnd.next = head;
                break;
            }

            ListNode[] nodes = reverse(head, k);
            nodes[1].next = null;
            System.out.println("reversed: " + nodes[0].val + ", " + nodes[1].val + ", ");
            System.out.println("prevStart: " + (prevStart != null ? prevStart.val
                    : 0) + ", prevEnd:"
                    + (prevEnd != null
                            ? prevEnd.val
                            : 0)
                    + ", ");
            if (prevEnd != null) {
                prevEnd.next = nodes[0];
                System.out.println("print prevStart : " + prevStart.val);
                print(prevStart);
            }

            if (count == 1) {
                resultHead = nodes[0];
            }

            prevStart = nodes[0];
            prevEnd = nodes[1];

            if (nodes[2] == null)
                break;
            head = nodes[2];
            count++;

        }
        System.out.println("Last Head: " + resultHead.val);
        return resultHead;
    }

    public static void main(String[] args) {
        ListNode node6 = new ListNode(6, null);
        ListNode node5 = new ListNode(5, node6);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        print(node1);

        // ListNode newHead = reverseKGroup(node1, 3);
        // System.out.println("Last Print");
        // print(newHead);

        ListNode newHead = reverse_K_New(node1, 3);
        System.out.println("New reverse");
        print(newHead);

    }

    private static ListNode reverse_K_New(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }
        ListNode tempHead = head;
        int count = 0;
        while (count < k && tempHead != null) {
            tempHead = tempHead.next;
            count++;
        }

        if (count == k) {
            ListNode prev = null;
            ListNode curr = head;
            count = 0;
            while (count < k && curr != null) {
                ListNode temp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = temp;
                count++;
            }
            head.next = reverse_K_New(curr, k);
            return prev;
        } else {
            return head;
        }

    }

    public static ListNode[] reverse(ListNode head, int k) {
        ListNode current = head;
        ListNode prev = null;
        ListNode temp = null;
        int count = 1;
        while (current != null) {
            temp = current.next;
            current.next = prev;
            prev = current;
            if (temp == null || count == k) {
                break;
            }
            current = temp;
            count++;
        }
        return new ListNode[] { current, head, temp };
    }

    static class NodeGroup {
        ListNode start;
        ListNode end;
    }

    public static ListNode reverseNew(ListNode head, int k) {
        ListNode current = head;
        ListNode prev = null;
        // 1-->2-->3-->4-->5
        // 2,1, 4,3, 5
        // 2-->1-->4-->3-->5
        // 3-2-1-6-5-4-9-8-7
        // 9-8-7 3-2-1-6-5-4-
        // 1,3,4,6,7,9
        int index = 0;
        ListNode lastHead = null;
        List<NodeGroup> groupList = new ArrayList<>();
        while (current != null) {
            if (index % k == 0) {
                if (prev != null) {
                    groupList.get(groupList.size() - 1).start = prev;
                }
                NodeGroup group = new NodeGroup();
                group.end = current;
                groupList.add(group);
            }
            ListNode temp = current.next;
            current.next = prev;

            prev = current;
            current = temp;
            index++;
        }
        if (prev != null) {
            groupList.get(groupList.size() - 1).start = prev;
        }
        System.out.println("groupList---->>");
        NodeGroup prevGrp = null;
        for (NodeGroup group : groupList) {
            System.out.print((group.start != null ? group.start.val
                    : "NULL") + ", " + (group.end != null ? group.end.val : "NULL") + ", ");
            if (prevGrp != null) {
                if (prevGrp.end != null) {
                    if (group.start == null) {
                        prevGrp.end.next = group.end;
                    } else
                        prevGrp.end.next = group.start;
                }
            }
            prevGrp = group;
        }
        prevGrp.end.next = null;
        System.out.println();
        return groupList.get(0).start;
    }

    public static void print(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + "-->");
            current = current.next;
        }
        System.out.println();
    }

}