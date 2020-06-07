package leetcode.task387;

// 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
// https://leetcode-cn.com/problems/first-unique-character-in-a-string/
public class Solution {
    public int firstUniqChar(String s) {
        int[] freq = new int[26];

        for (int i = 0; i < s.length(); i ++) {
            freq[s.charAt(i) - 'a'] += 1;
        }
        for (int i = 0; i < s.length(); i ++) {
            if (freq[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}
