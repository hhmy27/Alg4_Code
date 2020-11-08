package ch01.part3;

import org.junit.Test;

/**
 *  \* Created with IntelliJ IDEA.
 *  \* User: hmy
 *  \* Date: 2020/4/30
 *  \* Time: 10:02
 *  \* To change this template use File | Settings | File Templates.
 *  \* Description: answer for ex1.3.9
 *  \
 */
public class ex_1_3_9 {
    /*
     * input: a expression that lack left bracket but it's right bracket is not lack
     * output: a intact expression that has all bracket in right place
     * notice: for simplify, put off all space in input string
     * common thread: we can use right bracket thus it's in right place*/

    /*
     * thread: we see all element in expression is a entity*/
    public String func1(String input) {
        Stack<Character> stack = new Stack<>();
        Queue<Character> queue = new Queue<>();

        for (int i = 0; i < input.length(); i++) {
            Character s = input.charAt(i);
            if (!s.equals(')'))
                // if s not equal ")" than just push in stack
                stack.push(s);
            else {
                // we need pop "A op B" three entity in stack when we meet ")"
                queue.enqueue('(');
                String B = popEntity(stack);
                String op = popEntity(stack);
                String A = popEntity(stack);
                for (int j = 0; j < A.length(); j++) {
                    Character value = A.charAt(j);
                    queue.enqueue(value);
                }
                for (int j = 0; j < op.length(); j++) {
                    Character value = op.charAt(j);
                    queue.enqueue(value);
                }

                for (int j = 0; j < B.length(); j++) {
                    Character value = B.charAt(j);
                    queue.enqueue(value);
                }
                queue.enqueue(')');
                // queue中存放了 ( A + B )的String，现在弹出并压入stack中 => ( A + B )
                // queue store string like "(A+B)", now we need pop it and push to stack
                while (!queue.isEmpty()) {
                    stack.push(queue.dequeue());
                }
            }
        }

        // 先将stack压入到tstack中，然后再把tstack中的String放入到ans中
        StringBuilder sb = new StringBuilder();
        Stack<Character> tstack = new Stack<>();
        while (!stack.isEmpty())
            tstack.push(stack.pop());
        int index = 0;
        while (!tstack.isEmpty())
            sb.append(tstack.pop());
        return sb.toString();
    }

    // this is func1 helper function
    // it can pop a entity for a stack
    public String popEntity(Stack<Character> stack) {
        // the entity maybe is 1, ( 1 + 2 ), ( 3 + ( 1 + 2 ) ), so we need return String[] data
        // every String is denote a entity

        // ops use to store entity that need to pop
        Stack<Character> ops = new Stack<>();

        if (stack.peek().equals(')')) {
            // if current stack top is ")"
            // r denote right bracket number
            int r = 0;
            do {
                Character top = stack.pop();
                if (top.equals(')'))
                    r += 1;   // right bracket number add 1
                else if (top.equals('('))
                    r -= 1;   // meet left bracket, offset a right bracket
                ops.push(top);
            } while (r != 0);
        } else {
            ops.push(stack.pop());
        }
        StringBuilder sb = new StringBuilder();
        while (!ops.isEmpty())
            sb.append(ops.pop());
        return sb.toString();
    }

    // reference: https://github.com/reneargento/algorithms-sedgewick-wayne/blob/master/src/chapter1/section3/Exercise9.java
    public String func2(String[] args) {
        Stack<String> ops = new Stack<>();
        Stack<String> vals = new Stack<>();
        for (String s : args) {
            // 如果是数字，直接push vals
            // 如果是字符并且不是右括号，则push ops
            // 如果是右括号，开始补全，弹出操作数，操作符，然后再弹出操作数，补全左括号，再压回栈中 （假设没有sqrt操作
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"))
                ops.push(s);
            else if (s.equals(")")) {
                String a = vals.pop();
                String b = vals.pop();
                String op = ops.pop();
                String t = String.format("( %s %s %s )", b, op, a);
                vals.push(t);
            } else
                vals.push(s);
        }
        return vals.pop();
    }

    @Test
    public void test1() {
        String s = "1+2)*3-4)*5-6)))";
        System.out.println(func1(s));
    }


}
