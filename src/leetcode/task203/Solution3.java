package leetcode.task203;

// 使用递归删除所有值为特定值的节点
//
// 将链表看成 头结点 + 剩余部分
// 如果头结点为要删除的值，则返回处理后的剩余部分
// 否则 返回 头结点 + 处理后的剩余部分

public class Solution3 {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        ListNode res = removeElements(head.next, val);
        if (head.val == val) {
            return res;
        }
        head.next = res;
        return head;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution3()).removeElements(head, 6);
        System.out.println(res);
    }
}
