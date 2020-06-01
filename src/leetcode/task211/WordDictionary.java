package leetcode.task211;

import java.util.TreeMap;

// search(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母。
// https://leetcode-cn.com/problems/add-and-search-word-data-structure-design/
public class WordDictionary {
    private class Node {
        private boolean isWord;
        private TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            this.next = new TreeMap<>();
        }
        public Node() {
            this(false);
        }
    }

    private Node root;

    public WordDictionary() {
        root = new Node();
    }

    public void addWord(String word) {
        Node cur = root;
        for(int i = 0; i < word.length(); i ++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        cur.isWord = true;
    }

    public boolean search(String word) {
        return match(root, word, 0);
    }

    private boolean match(Node node, String word, int index) {
        // 递归到底
        if (index == word.length()) {
            return node.isWord;
        }

        // 调用递归
        char c = word.charAt(index);
        if (c != '.') {
            if (node.next.get(c) == null) {
                return false;
            }
            return match(node.next.get(c), word, index + 1);
        } else {
            // 遍历所有的键
            for (char nextChar: node.next.keySet()) {
                if (match(node.next.get(nextChar), word, index + 1)) {
                    // 有一个匹配中了就过
                    return true;
                }
            }
            return false;
        }
    }
}
