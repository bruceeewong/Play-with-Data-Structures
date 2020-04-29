package map;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("pride and prejudice");

        ArrayList<String> words = new ArrayList<>();
        String filename = "src/map/pride-and-prejudice.txt";
        if (FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());

            LinkedListMap<String, Integer> map = new LinkedListMap<>();
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
    }
}
