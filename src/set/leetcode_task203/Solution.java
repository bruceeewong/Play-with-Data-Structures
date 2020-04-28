package set.leetcode_task203;

import java.util.TreeSet;

class Solution {
    // 算法
    // 设置规则数组,
    // 对每个单词进行遍历,取字符,字符 - 'a' 的ASCII码就是对应的下标
    // 将单词转成摩斯密码
    // 存进Set中, Set中元素不会重复
    // 最后输出Set的size
    public int uniqueMorseRepresentations(String[] words) {
        String[] codes = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        TreeSet<String> set = new TreeSet<>();

        for (String word : words) {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                res.append(codes[index]);
            }
            set.add(res.toString());
        }
        return set.size();
    }
}