package ch01.part3;

import org.junit.Test;

/**
 *  \* Created with IntelliJ IDEA.
 *  \* User: hmy
 *  \* Date: 2020/4/30
 *  \* Time: 10:04
 *  \* Description: answer for ex 1.3.10
 * \* 思路是用两个栈，碰到)的时候弹出，然后按照 表达式1 表达式2 操作符 的顺序再入栈 ，最后栈顶就是完整的后缀表达式
 * \* Thread: use two stack, one of stack store operate character, another one store number character
 * when we meet ')', we pop character like format val1 val2 op and push again
 * when we finish iterator, the vals stack is full expression
 *  \
 */
public class InfixToPostfix {
    public String func(String input) {
        Stack<String> ops = new Stack<String>();
        Stack<String> vals = new Stack<String>();
        String[] slist = input.split(" ");
        for (String s : slist) {
            switch (s) {
                case "(":
                    ;
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                case "sqrt":
                    ops.push(s);
                    break;
                case ")":
                    String op = ops.pop();
                    String v = vals.pop();

                    if (op.equals("+") ||
                            op.equals("-") ||
                            op.equals("*") ||
                            op.equals("/"))
                        v = String.format("%s %s %s", vals.pop(), v, op);
                    else if (op.equals("sqrt"))
                        v = String.format("%s %s", v, op);

                    vals.push(v);
                    break;
                default:
                    vals.push(s);
                    break;
            }
        }

        return vals.pop();
    }

    @Test
    public void test1() {
        String input = "( ( ( 2 * 3 ) / ( 2 - 1 ) ) + ( 3 * ( 4 - 1 ) ) )";
        assert func(input).equals("2 3 * 2 1 - / 3 4 1 - * +");
    }

    @Test
    public void test2() {
        String input = "( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )";
        assert func(input).equals("1 2 3 + 4 5 * * +");
    }
}
