package ch01;

import edu.princeton.cs.algs4.DoublingRatio;
import edu.princeton.cs.algs4.DoublingTest;

import java.util.Arrays;

/**
 *  \* Created with IntelliJ IDEA.
 *  \* User: hmy
 *  \* Date: 2020/4/14
 *  \* Time: 19:54
 *  \* To change this template use File | Settings | File Templates.
 *  \* Description: 
 *  \
 */

public class test {
    public static void main(String[] args) {
//        ResizingArrayStack<String> stack = new ResizingArrayStack<>();
//        String[]sa={"it", "was", "-", "the", "best", "-", "of","times","-", "-", "-","it","was","-", "the", "-", "-" };
//        for (int i = 0; i < sa.length; i++) {
//            String t=sa[i];
//            if(t.equals("-"))
//                stack.pop();
//            else
//                stack.push(t);
//        }
//        for (String s:stack){
//            System.out.println(s);
//        }

//        String sa = "[()]{}{[()()]()}";
//        System.out.println(Parentheses(sa));
//        String s = "1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) ) ";
        // ex_1_3_09
//        String s="1 + 2 + 3 ) )";
//        String[] slist = s.split(" ");
//        for (String ts : slist)
//            System.out.print(ts);
//        System.out.println();
//        String[] ans = ex_1_3_09(slist);
//        for (String ts : ans) {
//            System.out.print(ts);
//        }
//        System.out.println(ex_1_3_09_b(slist));
        String []T={" "};
        DoublingRatio.main(T);
   }



    public static boolean helper(ch01.Stack<Character> stack, char a, char b) {
        // 开始匹配，如果匹配过程中栈为空，那么就返回false，否则匹配到了，返回true
        char t;
        do {
            if (stack.isEmpty())
                return false;
            t = stack.pop();
        } while (t != b);
        return true;
    }

    public static boolean Parentheses(String sa) {
        ch01.Stack<Character> stack = new Stack<>();
        for (int i = 0; i < sa.length(); i++) {
            char ch = sa.charAt(i);
            switch (ch) {
                case ']':
                    if (!helper(stack, ']', '['))
                        return false;
                    break;
                case '}':
                    if (!helper(stack, '}', '{'))
                        return false;
                    break;
                case ')':
                    if (!helper(stack, ')', '('))
                        return false;
                    break;
                default:
                    stack.push(ch);
                    break;
            }
        }
        return true;
    }
}

