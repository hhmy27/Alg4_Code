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
    public String func(String input) {
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
                // queue store string like "(A+B)", now we need pop it and push to stack
                while (!queue.isEmpty()) {
                    stack.push(queue.dequeue());
                }
            }
        }

        // we pop stack to tstack,and push to ans from tstack
        StringBuilder sb = new StringBuilder();
        Stack<Character> tstack = new Stack<>();
        while (!stack.isEmpty())
            tstack.push(stack.pop());

        while (!tstack.isEmpty())
            sb.append(tstack.pop());
        return sb.toString();
    }

    // this is func helper function
    // it can pop a entity for a stack
    public String popEntity(Stack<Character> stack) {
        // the entity maybe is 1, (1+2), (3+(1+2))
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
            ops.push(stack.pop());   // maybe stack top is operation sign, we just pop it
        }

        StringBuilder sb = new StringBuilder();
        while (!ops.isEmpty())
            sb.append(ops.pop());
        return sb.toString();
    }

    @Test
    public void test1() {
        String s = "1+2)*3-4)*5-6)))";
        System.out.println(func(s));
        assert func(s).equals("((1+2)*((3-4)*(5-6)))");
    }

    @Test
    public void test2() {
        String s = "1+2)";
        assert func(s).equals("(1+2)");
    }


}
