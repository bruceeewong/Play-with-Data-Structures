package tree;

import array.Array;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MyBST<E extends Comparable<E>> {
    public static void main(String[] args) {
        MyBST<Integer> bst = new MyBST<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num : nums) {
            bst.add(num);
        }
        System.out.println(bst);
        System.out.println("size: " + bst.getSize());
        System.out.println();

        System.out.println("preOrder");
        bst.preOrder();
        System.out.println();

        System.out.println("preOrder without recursive");
        bst.preOrderNonRecursive();
        System.out.println();

        System.out.println("inOrder");
        bst.inOrder();
        System.out.println();

        System.out.println("postOrder");
        bst.postOrder();
        System.out.println();

        System.out.println("levelOrder");
        bst.levelOrder();
        System.out.println();
    }

    private class Node {
        E e;
        Node left;
        Node right;

        public Node(E e) {
            this.e = e;
            left = right = null;
        }

        public Node() {
            this(null);
        }
    }

    private Node root;
    private int size;

    public MyBST() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 插入元素
     * @param e
     * @return
     */
    public void add(E e) {
        root = add(root, e);
    }

    /**
     * 递归插入元素
     * @param node
     * @param e
     * @return
     */
    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0) {
            // 待插入的值小于当前节点，递归左子树
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }
        // else 相等，不做操作
        return node;
    }


    /**
     * 前序遍历
     * 深度优先
     * 应用：从左至右打印树的形状
     */
    public void preOrder() {
        preOrder(root);
    }

    /**
     * 前序遍历：先访问节点，再访问左子树、右子树。
     * @param node
     */
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.e);  // 访问节点
        preOrder(node.left);  // 遍历左子树
        preOrder(node.right);  // 遍历右子树
    }

    /**
     * 前序遍历非递归实现
     * 思路：利用栈辅助遍历
     */
    public void preOrderNonRecursive() {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.println(node.e);

            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    /**
     * 中序遍历
     * 深度优先
     * 用途：二分搜索树中序遍历的结果是升序排序
     */
    public void inOrder() {
        inOrder(root);
    }

    /**
     * 中序遍历：先访问左子树，再访问节点、右子树。
     * @param node
     */
    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);  // 遍历左子树
        System.out.println(node.e);  // 访问节点
        inOrder(node.right);  // 遍历右子树
    }

    /**
     * 后序遍历
     * 深度优先
     * 用途：为二分搜索树释放内存
     */
    public void postOrder() {
        postOrder(root);
    }

    /**
     * 后序遍历：先访问左子树，再右子树、访问节点。
     * @param node
     */
    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);  // 遍历左子树
        postOrder(node.right);  // 遍历右子树
        System.out.println(node.e);  // 访问节点
    }

    /**
     * 层序遍历
     * 广度优先
     * 实现：利用队列遍历树
     */
    public void levelOrder() {
        if (root == null) {
            return;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            Node node = q.remove();
            System.out.println(node.e);

            if (node.left != null) {
                q.add(node.left);
            }
            if (node.right != null) {
                q.add(node.right);
            }
        }
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    /**
     * 递归记录树的信息
     *
     * @param node
     * @param depth
     * @param res
     */
    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(getDepthString(depth));
            res.append("null\n");
            return;
        }

        res.append(getDepthString(depth));
        res.append(node.e);
        res.append("\n");

        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String getDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }
}
