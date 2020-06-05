package tree;

import set.BSTSet;
import tree.rbtree.RBTree;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        testBSTSet();

        System.out.println("------------");

        testTrie();
    }

    private static void testTrie() {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("src/tree/pride-and-prejudice.txt", words)) {
            long startTime = System.nanoTime();

            Trie trie = new Trie();
            for (String word: words) {
                trie.add(word);
            }
            for (String word: words) {
                trie.contains(word);
            }

            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + trie.getSize());
            System.out.println("Trie: " + time + " s");
        }
    }

    private static void testBSTSet() {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("src/tree/pride-and-prejudice.txt", words)) {
            long startTime = System.nanoTime();

            BSTSet<String> set = new BSTSet<>();
            for (String word: words) {
                set.add(word);
            }
            for (String word: words) {
                set.contains(word);
            }

            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + set.getSize());
            System.out.println("BSTSet: " + time + " s");
        }
    }

    private void testSegmentTree() {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};

        SegmentTree<Integer> segTree = new SegmentTree<>(nums, ((a, b) -> a + b));
//        System.out.println(segTree);

        int result = segTree.query(0, 2);// [0,2]所有元素的和
        System.out.println(result);

        result = segTree.query(3, 5);// [3,5]所有元素的和
        System.out.println(result);

        result = segTree.query(1, 4);// [1,4]所有元素的和
        System.out.println(result);
    }
}
