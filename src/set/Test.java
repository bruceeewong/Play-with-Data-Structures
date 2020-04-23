package set;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        // 通过SET进行词汇去重统计
        String filename = "src/set/pride-and-prejudice.txt";
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());
            BSTSet<String> set = new BSTSet<>();
            for (String word: words) {
                set.add(word);
            }
            System.out.println("Total different words: " + set.getSize());
        } else {
            System.out.println("File Not Found.");
        }
    }
}
