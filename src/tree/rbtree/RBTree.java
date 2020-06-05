package tree.rbtree;

public class RBTree<K extends Comparable<K>, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public boolean color;  //

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED;  // 红黑树插入节点默认是要融合的
        }
    }

    private Node root;
    private int size;

    public RBTree() {
        root = null;
        size = 0;
    }

    private boolean isRed(Node node) {
        if (node == null) {
            return BLACK;
        }
        return node.color;
    }

    //   node                     x
    //  /   \     左旋转         /  \
    // T1   x   --------->   node   T3
    //     / \              /   \
    //    T2 T3            T1   T2
    private Node leftRotate(Node node) {
        Node x = node.right;

        // 左旋转
        node.right = x.left;
        x.left = node;

        // 改变x节点颜色，此过程不保证红黑树的性质
        x.color = node.color;
        node.color = RED;

        return x;
    }

    //     node                   x
    //    /   \     右旋转       /  \
    //   x    T2   ------->   y   node
    //  / \                       /  \
    // y  T1
    private Node rightRotate(Node node) {
        Node x = node.left;

        // 右旋转
        node.left = x.right;
        x.right = node;

        // 改变颜色
        x.color = node.color;
        node.color = RED;

        return x;
    }

    // 4-节点 变为 3-节点, 反转颜色
    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    public void add(K key, V value) {
        root = add(root, key, value);
        root.color = BLACK;  // 最终根节点为黑色
    }

    // 向红黑树中插入节点
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);  // 默认插入红节点
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            // 否则是找到了对应key的节点，更新节点值
            node.value = value;
        }

        // 红黑树性质维护
        if (isRed(node.right) && !isRed(node.left)) {
            node = leftRotate(node);
        }

        if (isRed(node.left) && isRed(node.left.left)) {
            node = rightRotate(node);
        }

        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        return node;
    }

    public V remove(K key) {
        Node node = getNode(root, key);
        if (node == null) {
            return null;
        }
        root = remove(root, key);
        return node.value;
    }

    // 返回删除操作之后的节点
    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {
            // 找到了要删除的节点
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            } else {
                // 当删除节点存在左右子树时，采用后继节点(右子树最小值的节点)作为衔接
                Node successor = getMin(node.right);
                // 注意顺序，必须先将后继节点的右孩子指向当前节点删掉了自身后继元的右子树
                successor.right = remove(node.right, successor.key);  // 注意这里也要保证高度
                successor.left = node.left; // 一定要放在右操作之后

                node.left = node.right = null;
                return successor;
            }
        }
    }

    private Node getMin(Node node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node;
        }
        return getMin(node.left);
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V value) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + " does not exsit");
        }
        node.value = value;
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.equals(node.key)) {
            return node;
        }
        if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else {
            // key.compareTo(node.key) > 0
            return getNode(node.right, key);
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}