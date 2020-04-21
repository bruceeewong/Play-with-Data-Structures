package bst;

public class BinarySearchTree<E extends Comparable<E>> {

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
}
