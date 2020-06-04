package tree.avl;

import map.BSTMap;
import map.FileOperation;

import java.util.ArrayList;
import java.util.Collections;

public class AVLTree<K extends Comparable<K>, V> {
    public static void main(String[] args) {
        String filename = "src/map/pride-and-prejudice.txt";

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());

            AVLTree<String, Integer> avlTree = new AVLTree<>();
            for (String word : words) {
                if (avlTree.contains(word)) {
                    avlTree.set(word, avlTree.get(word) + 1); // 词频统计
                } else {
                    avlTree.add(word, 1);
                }
            }
            for (String word : words) {
                avlTree.remove(word);
                if (!avlTree.isBST() || !avlTree.isBalanced()) {
                    throw new RuntimeException("AVLTree is not bst or balanced!");
                }
            }
        }
    }

    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            // 否则是找到了对应key的节点，更新节点值
            node.value = value;
        }

        // 平衡维护
        return reBalance(node);
    }

    private Node reBalance(Node node) {
        if (node == null) {
            return null;
        }

        // 更新当前节点的高度值
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(node);

        // 平衡维护
        // LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            // 如果是在当前节点的左侧子树的左侧插入新节点导致不平衡
            // 对当前节点执行右旋转
            return rightRotate(node);
        }
        // RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            // 如果是在当前节点的右侧子树的右侧插入新节点导致不平衡
            // 对当前节点执行左旋转
            return leftRotate(node);
        }

        // LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            // 如果是在当前节点的左侧子树的右侧插入新节点导致不平衡
            // 先对当前节点的左孩子执行左旋转
            node.left = leftRotate(node.left);
            // 再对当前节点做右旋转
            return rightRotate(node);
        }

        // RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            // 如果是在当前节点的右侧子树的左侧插入新节点导致不平衡
            // 先对当前节点的右孩子执行右旋转
            node.right = rightRotate(node.right);
            // 再对当前节点做左旋转
            return leftRotate(node);
        }

        // 否则，节点是满足平衡二叉树定义，直接返回
        return node;
    }

    private Node rightRotate(Node y) {
        // 对节点y进行向右旋转操作, 返回旋转后新的根节点 x
        //       y                               x
        //      / \                            /   \
        //     x   T4       向右旋转（y）       z     y
        //    / \        - - - - -- - - ->   / \    / \
        //   z   T3                         T1  T2 T3  T4
        //  / \
        // T1  T2
        Node x = y.left;
        Node T3 = x.right;

        // 向右旋转过程
        x.right = y;
        y.left = T3;

        // 更新height值
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
        return x;
    }

    private Node leftRotate(Node y) {
        // 对节点y进行向左旋转操作, 返回旋转后新的根节点 x
        //       y                                   x
        //      / \                                 /  \
        //     T1 　x      　　　向左旋转（y）        y     z
        //    　　　/ \        - - - - -- - - ->   / \   / \
        //   　　　T2 　z                         T1  T2 T3 T4
        //  　　　　　　/ \
        // 　　　　　　T3  T4
        Node x = y.right;
        Node T2 = x.left;

        // 向右旋转过程
        x.left = y;
        y.right = T2;

        // 更新height值
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
        return x;
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

        Node retNode;

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else {
            // 找到了要删除的节点
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            } else {
                // 当删除节点存在左右子树时，采用后继节点(右子树最小值的节点)作为衔接
                Node successor = getMin(node.right);
                // 注意顺序，必须先将后继节点的右孩子指向当前节点删掉了自身后继元的右子树
                successor.right = remove(node.right, successor.key);  // 注意这里也要保证高度
                successor.left = node.left; // 一定要放在右操作之后

                node.left = node.right = null;
                retNode = successor;
            }
        }
        // 进行删除后节点的平衡性维护
        return reBalance(retNode);
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

        if (key.compareTo(node.key) == 0) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else {
            return getNode(node.right, key);
        }
    }

    // 获取节点的高度
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    // 是否是平衡二叉树, 看节点的左右子树高度差是否大于１
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    // 是否是一棵二分搜索树, 看其中序遍历后是否为升序数组
    public boolean isBST() {
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);

        for (int i = 1; i < keys.size(); i++) {
            // 如果不是升序，则不是二分搜索树
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    private void inOrder(Node node, ArrayList<K> keys) {
        if (node == null) {
            return;
        }
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
