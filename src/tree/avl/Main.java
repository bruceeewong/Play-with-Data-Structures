package tree.avl;

import map.BSTMap;
import map.FileOperation;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        String filename = "src/map/pride-and-prejudice.txt";

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());

            // 将单词排序，BST退化成链表
            Collections.sort(words);

            System.out.println("========================");

            long startTime = System.nanoTime();

            BSTMap<String, Integer> bstMap = new BSTMap<>();
            for (String word : words) {
                if (bstMap.contains(word)) {
                    bstMap.set(word, bstMap.get(word) + 1); // 词频统计
                } else {
                    bstMap.add(word, 1);
                }
            }
            for (String word: words) {
                bstMap.contains(word);
            }

            long endTime = System.nanoTime();
            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("BST: " + time + " s");

            System.out.println("========================");

            long startTime2 = System.nanoTime();

            AVLTree<String, Integer> avlTree = new AVLTree<>();
            for (String word : words) {
                if (avlTree.contains(word)) {
                    avlTree.set(word, avlTree.get(word) + 1); // 词频统计
                } else {
                    avlTree.add(word, 1);
                }
            }
            for (String word: words) {
                avlTree.contains(word);
            }

            long endTime2 = System.nanoTime();
            double time2 = (endTime2 - startTime2) / 1000000000.0;
            System.out.println("AVL: " + time2 + " s");
        }
    }
}
