package tree;

import map.AVLMap;
import map.BSTMap;
import tree.rbtree.RBTree;

import java.util.ArrayList;
import java.util.Random;

public class TestAdd2 {
    public static void main(String[] args) {
        int n = 20000000;

        // 顺序数据的测试
        ArrayList<Integer> testData = new ArrayList<>();
        for (int i = 0; i < n; i ++) {
            testData.add(i);
        }

        // Test AVL
        long startTime1 = System.nanoTime();

        AVLMap<Integer, Integer> avl = new AVLMap<>();
        for (Integer x: testData) {
            avl.add(x, null);
        }

        long endTime1 = System.nanoTime();
        double time1 = (endTime1 - startTime1) / 1000000000.0;
        System.out.println("AVL: " + time1 + " s");


        // Test AVL
        long startTime2 = System.nanoTime();

        RBTree<Integer, Integer> rbTree = new RBTree<>();
        for (Integer x: testData) {
            rbTree.add(x, null);
        }

        long endTime2 = System.nanoTime();
        double time2 = (endTime2 - startTime2) / 1000000000.0;
        System.out.println("RBTree: " + time2 + " s");
    }
}
