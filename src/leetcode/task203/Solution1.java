package leetcode.task203;

/**
 * 删除链表中与传入值相同的所有元素，返回链表头
 * 不使用头结点
 */
class Solution1 {
    public ListNode removeElements(ListNode head, int val) {
        // 如果传入链表为空，或开头的都是要删除的节点
        while (head != null && head.val == val) {
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;
        }

        // 如果删完开头为空了
        if (head == null) {
            return null;
        }

        ListNode prev = head;
        while (prev.next != null) {
            // 如果下一个元素时要被删除的元素
            if (prev.next.val == val) {
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
                // 继续循环，看删了之后的下一个是不是也要删除
            } else {
                prev = prev.next;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution1()).removeElements(head, 6);
        System.out.println(res);
    }
}
