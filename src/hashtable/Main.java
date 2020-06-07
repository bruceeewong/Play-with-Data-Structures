package hashtable;

import map.FileOperation;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String filename = "src/hashtable/pride-and-prejudice.txt";
        long startTime = System.nanoTime();

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());

            HashTable<String, Integer> ht = new HashTable<>();
            for (String word: words) {
                if (ht.contains(word)) {
                    ht.set(word, ht.get(word) + 1);
                } else {
                    ht.add(word, 1);
                }
            }

            for (String word: words) {
                ht.contains(word);
            }

            System.out.println("Total different words: " + ht.getSize());
            System.out.println("Frequency of PRIDE: " + ht.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + ht.get("prejudice"));
        }

        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 100000000.0;

        System.out.println("HashTable: " + time + " s");
    }
}
