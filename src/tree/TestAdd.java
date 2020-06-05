package tree;

import map.AVLMap;
import map.BSTMap;
import tree.rbtree.RBTree;

import java.util.ArrayList;
import java.util.Random;

public class TestAdd {
    public static void main(String[] args) {
        int n = 10000000;

        // 随机数据的测试
        Random random = new Random();
        ArrayList<Integer> testData = new ArrayList<>();
        for (int i = 0; i < n; i ++) {
            testData.add(random.nextInt(Integer.MAX_VALUE));
        }

        // Test BST
        long startTime = System.nanoTime();

        BSTMap<Integer, Integer> bst = new BSTMap<>();
        for (Integer x: testData) {
            bst.add(x, null);
        }

        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("BST: " + time + " s");

        // Test AVL
        startTime = System.nanoTime();

        AVLMap<Integer, Integer> avl = new AVLMap<>();
        for (Integer x: testData) {
            avl.add(x, null);
        }

        endTime = System.nanoTime();
        time = (endTime - startTime) / 1000000000.0;
        System.out.println("AVL: " + time + " s");


        // Test AVL
        startTime = System.nanoTime();

        RBTree<Integer, Integer> rbTree = new RBTree<>();
        for (Integer x: testData) {
            rbTree.add(x, null);
        }

        endTime = System.nanoTime();
        time = (endTime - startTime) / 1000000000.0;
        System.out.println("RBTree: " + time + " s");
    }
}
