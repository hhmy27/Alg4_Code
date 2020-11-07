package ch01.part3;

import org.junit.Test;

/**
 * @program: Alg4_Code
 * @author: hhmy27
 * @created: 2020/11/07 20:21
 * @description: answer for ex1.3.4 , Parentheses
 */
public class ex_1_3_4 {
    public boolean func(String input) {
        Stack<Character> stack = new Stack<>();
        int i = 0;
        for (; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '{' || ch == '[' || ch == '(')
                stack.push(ch);
            switch (ch) {
                case '}':
                    while (!stack.isEmpty() && stack.peek() != '{') {
                        stack.pop();
                    }
                    if (stack.isEmpty())
                        return false;
                    stack.pop();
                    break;
                case ')':
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        stack.pop();
                    }
                    if (stack.isEmpty())
                        return false;
                    stack.pop();
                    break;
                case ']':
                    while (!stack.isEmpty() && stack.peek() != '[') {
                        stack.pop();
                    }
                    if (stack.isEmpty())
                        return false;
                    stack.pop();
                    break;
            }
        }
        return stack.isEmpty();
    }

    @Test
    public void test1() {
        String input = "[()]{}{[()()]()}";

        assert func(input) == true;
    }

    @Test
    public void test2() {
        String input = "[(])";

        assert func(input) == false;
    }

    @Test
    public void test3() {
        String input = "((((((((()";

        assert func(input) == false;
    }
}