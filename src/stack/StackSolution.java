package stack;

import java.util.Stack;

/**
 * LeetCode - 题库第20题， 有效的括号
 * 检查一组字符输入，判断小中大括号左右是否匹配，返回布尔值
 * https://leetcode-cn.com/problems/valid-parentheses
 */
class StackSolution {
    public boolean isValid(String s) {
        Character[] stack = new Character[s.length()];
        int size = 0;

        for (int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);

            if (c == '(' || c == '[' || c == '{') {
                stack[size] = c;
                size ++;
            } else {
                if (size == 0) {
                    return false;
                }

                char topChar = stack[size - 1];
                size --;

                if (topChar == '(' && c != ')') {
                    return false;
                }
                if (topChar == '[' && c != ']') {
                    return false;
                }
                if (topChar == '{' && c != '}') {
                    return false;
                }
            }
        }

        return size == 0;
    }

    public boolean isValid2(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);

            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                char topChar = stack.pop();

                if (topChar == '(' && c != ')') {
                    return false;
                }
                if (topChar == '[' && c != ']') {
                    return false;
                }
                if (topChar == '{' && c != '}') {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new StackSolution().isValid2(""));
        System.out.println(new StackSolution().isValid2("]"));
        System.out.println(new StackSolution().isValid2("()"));
        System.out.println(new StackSolution().isValid2("(){}"));
        System.out.println(new StackSolution().isValid2("({}]"));
    }
}