package tree;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

// 文件操作相关类
public class FileOperation {
    // 读文本文件，并将文本的单词进行粗略地分词
    public static boolean readFile(String filename, ArrayList<String> words) {
        if (filename == null || words == null) {
            System.out.println("filename or words is null");
            return false;
        }

        // 文件读取
        Scanner scanner;

        try {
            File file = new File(filename);
            if (!file.exists()) {
                return false;
            }
            FileInputStream fis = new FileInputStream(file);
            scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");
            scanner.useLocale(Locale.ENGLISH);
        } catch (IOException ioe) {
            System.out.println("Cannot open " + filename);
            return false;
        }

        if (scanner.hasNextLine()) {
            String contents = scanner.useDelimiter("\\A").next();

            int start = firstCharacterIndex(contents, 0);

            for (int i = start + 1; i <= contents.length();) {
                // 还没遍历到末尾 或者 当前字符是字母时，没结束分词
                if (i != contents.length() &&
                        Character.isLetter(contents.charAt(i))) {
                    i++;
                    continue;
                }
                // 否则结束本次分词
                String word = contents.substring(start, i).toLowerCase();
                // 将单词推入数组中
                words.add(word);
                // 找到下一个单词的开头
                start = firstCharacterIndex(contents, i);
                i = start + 1;
            }
        }

        return true;
    }

    // 从指定位置开始，找到字符串第一个字符所在索引
    private static int firstCharacterIndex(String s, int start) {
        for (int i = start; i < s.length(); i ++) {
            if (Character.isLetter(s.charAt(i))) {
                return i;
            }
        }
        return s.length();
    }
}
