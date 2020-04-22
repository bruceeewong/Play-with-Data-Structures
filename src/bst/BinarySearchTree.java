package bst;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchTree<E extends Comparable<E>> {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num: nums) {
            bst.add(num);
        }
        System.out.println(bst);
        System.out.println(bst.getSize());

//        bst.removeMin();
//        System.out.println(bst);
//        System.out.println(bst.getSize());

        bst.remove(3);
        System.out.println(bst);
        System.out.println(bst.getSize());



//        bst.preOrder();
//        System.out.println();
//
//        bst.preOrderNR();

//        bst.levelOrder();

//        bst.inOrder();
//        System.out.println();
//
//        bst.postOrder();
//        System.out.println();
//        System.out.println(bst);
    }


    private class Node {
        E e;
        Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BinarySearchTree() {
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
     * 二分搜索数添加元素
     * @param e E
     */
    public void add(E e) {
        root = recursiveAdd(root, e);
    }

    /**
     * 向以node为根的二分搜索树，插入元素E
     * 返回插入新节点后的二分搜索数的根
     */
    private Node recursiveAdd(Node node, E e) {
        // 最小问题的解法:当前节点为空，直接创建
        if (node == null) {
            size ++;
            return new Node(e);
        }

        // 否则，递归
        if (e.compareTo(node.e) < 0) {
            // 小于当前节点，递归左子树
            node.left = recursiveAdd(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = recursiveAdd(node.right, e);
        }
        // 插入值与当前根节点相等，什么都不做
        return node;
    }

    public E getMin() {
        return getMinByRecursive(root).e;
    }

    private Node getMinByRecursive(Node node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node;
        }
        return getMinByRecursive(node.left);
    }

    /**
     * 删除二叉搜索树中最小节点，并返回最小值
     * @return E
     */
    public E removeMin() {
        E ret = getMin();
        removeMinByRecursive(root);
        return ret;
    }

    private Node removeMinByRecursive(Node node) {
        if (node == null) {
            return null;
        }
        // 遍历到最左叶节点即最小值，将其节点的右子树赋值给其上一节点
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }
        // 否则,递归删除左子树
        node.left = removeMinByRecursive(node.left);
        return node;
    }

    public E getMax() {
        return getMaxByRecursive(root).e;
    }

    private Node getMaxByRecursive(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return node;
        }
        return getMaxByRecursive(node.right);
    }

    /**
     * 删除二叉搜索树中最大节点，并返回最大值
     * @return E
     */
    public E removeMax() {
        E ret = getMax();
        removeMaxByRecursive(root);
        return ret;
    }

    private Node removeMaxByRecursive(Node node) {
        if (node == null) {
            return null;
        }
        // 遍历到最左叶节点即最小值，将其节点的右子树赋值给其上一节点
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size --;
            return leftNode;
        }
        // 否则,递归删除左子树
        node.right = removeMaxByRecursive(node.right);
        return node;
    }

    public Node remove(E e) {
        return removeByRecursive(root, e);
    }

    private Node removeByRecursive(Node node, E e) {
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) < 0) {
            node.left = removeByRecursive(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = removeByRecursive(node.right, e);
            return node;
        } else {
            // node.e == e
            // 删除分左子树为空、右子树为空、左右子树不为空的情况
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }
            // 左右子树都不为空，找到当前节点的右子树最小值节点作为后继节点
            // 将当前节点的左子树赋给后继节点
            // 将当前节点的删除了该后继节点的右子树，赋给该后继节点
//            return processBySuccessor(node);
            return processByFront(node);
        }
    }

    /**
     * 通过后继节点调整树
     * @param node
     * @return
     */
    private Node processByFront(Node node) {
        Node front = getMaxByRecursive(node.left);
        front.left = removeMaxByRecursive(node.left); // 此处发生了一次 size --
        front.right = node.right;

        // 删除当前节点
        node.left = node.right = null;
        return front;
    }

    /**
     * 通过后继节点调整树
     * @param node
     * @return
     */
    private Node processBySuccessor(Node node) {
        Node successor = getMinByRecursive(node.right);
        successor.right = removeMinByRecursive(node.right); // 此处发生了一次 size --
        successor.left = node.left;

        // 删除当前节点
        node.left = node.right = null;
        return successor;
    }

    /**
     * 查找目标元素是否包含元素e
     * @param e E
     * @return boolean
     */
    public boolean contains(E e) {
        return recursiveContains(root, e);
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        recursivePreOrder(root);
    }

    private void recursivePreOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.e); // 访问节点
        recursivePreOrder(node.left); // 遍历左子树
        recursivePreOrder(node.right); // 遍历右子树
    }

    /**
     * 前序遍历的非递归实现
     */
    public void preOrderNR() {
        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
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
     */
    private void inOrder() {
        recursiveInOrder(root);
    }

    private void recursiveInOrder(Node node) {
        if (node == null) {
            return;
        }

        recursiveInOrder(node.left);
        System.out.println(node.e); // 在中间访问节点
        recursiveInOrder(node.right);
    }

    /**
     * 后序遍历
     */
    private void postOrder() {
        recursivePostOrder(root);
    }

    private void recursivePostOrder(Node node) {
        if (node == null) {
            return;
        }

        recursivePostOrder(node.left);
        recursivePostOrder(node.right);
        System.out.println(node.e); // 在中间访问节点
    }

    /**
     * 层序遍历
     * 使用队列实现
     */
    public void levelOrder() {
        if (root == null) {
            return;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
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

    private boolean recursiveContains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return recursiveContains(node.left, e);
        } else { // e.compareTo(node.e) > 0
            return recursiveContains(node.right, e);
        }
    }

    /**
     * 向以node为根的二分搜索树，插入元素E，递归算法
     * @param node Node
     * @param e E
     */
    private void recursiveAdd2(Node node, E e) {
        // 最小问题的解法(3种情况)
        // 不插入已存在的节点
        if (e.equals(node.e)) {
            return;
        } else if (e.compareTo(node.e) < 0 && node.left == null) {
            // 若小于当前节点的值，且当前节点左子树为空，执行插入
            node.left = new Node(e);
            size ++;
            return;
        } else if (e.compareTo(node.e) > 0 && node.right == null) {
            node.right = new Node(e);
            size ++;
            return;
        }

        // 否则，递归
        if (e.compareTo(node.e) < 0) {
            // 小于当前节点，递归左子树
            recursiveAdd(node.left, e);
        } else {
            recursiveAdd(node.right, e);
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(getDepthString(depth)).append("null\n");
            return;
        }
        res.append(getDepthString(depth)).append(node.e).append("\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String getDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i ++) {
            res.append("--");
        }
        return res.toString();
    }
}
