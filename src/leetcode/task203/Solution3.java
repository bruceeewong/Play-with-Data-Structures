package leetcode.task203;

// 使用递归删除所有值为特定值的节点
//
// 将链表看成 头结点 + 剩余部分
// 如果头结点为要删除的值，则返回处理后的剩余部分
// 否则 返回 头结点 + 处理后的剩余部分

public class Solution3 {
    public ListNode removeElements(ListNode head, int val, int depth) {
        String depthString = getDepthString(depth);

        System.out.print(depthString);
        System.out.println("Call: remove " + val + " in " + head);

        // 1. 处理最小问题
        if (head == null) {
            System.out.print(depthString);
            System.out.println("Return: " + head);

            return null;
        }

        // 2. 分解成更小的同样的子问题
        ListNode res = removeElements(head.next, val, depth + 1);
        System.out.print(depthString);
        System.out.println("after remove " + val + ": " + res);

        // 3. 节点删除，返回判断结果
        ListNode ret;
        if (head.val == val) {
            ret = res;
        } else {
            head.next = res;
            ret = head;
        }
        System.out.print(depthString);
        System.out.println("Return " + ret);
        return ret;
    }

    private String getDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i ++) {
            res.append("--");
        }
        return res.toString();
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution3()).removeElements(head, 6, 0);
        System.out.println(res);
    }
}
