package map;

import hashtable.HashTable;
import set.LinkedList;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String filename = "src/map/pride-and-prejudice.txt";
        BSTMap<String, Integer> bstMap = new BSTMap<>();
        System.out.println("BSTMap cost time: " + testMap(bstMap, filename));

        System.out.println("==================");

        LinkedListMap<String, Integer> linkedListMap = new LinkedListMap<>();
        testMap(linkedListMap, filename);
        System.out.println("LinkedListMap cost time: " + testMap(linkedListMap, filename));

        System.out.println("==================");

        AVLMap<String, Integer> avlMap = new AVLMap<>();
        testMap(avlMap, filename);
        System.out.println("avlMap cost time: " + testMap(avlMap, filename));

        System.out.println("==================");
        HashTable<String, Integer> hashMap = new HashTable<>();
        testMap(hashMap, filename);
        System.out.println("HashTable cost time: " + testMap(hashMap, filename));

    }

    private static double testMap(Map<String, Integer> map, String filename) {
        long startTime = System.nanoTime();
        System.out.println(filename);

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());

            for (String word : words) {
                if (map.contains(word)) {
                    map.set(word, map.get(word) + 1); // 词频统计
                } else {
                    map.add(word, 1);
                }
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 100000000.0;
    }
}
