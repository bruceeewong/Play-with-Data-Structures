package bst;

public class BinarySearchTree<E extends Comparable<E>> {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num: nums) {
            bst.add(num);
        }
        bst.preOrder();
        System.out.println();

        bst.inOrder();
        System.out.println();

        bst.postOrder();
        System.out.println();
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
