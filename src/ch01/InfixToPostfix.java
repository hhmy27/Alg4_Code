package ch01;

import edu.princeton.cs.algs4.StdIn;
import ch01.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 *  \* Created with IntelliJ IDEA.
 *  \* User: hmy
 *  \* Date: 2020/4/30
 *  \* Time: 10:04
 *  \* Description: ex 1.3.10的实现，中序表达式转后序，输入表达式要求：每一个操作周围都有括号包围
 * \* 思路是用两个栈，碰到)的时候弹出，然后按照 表达式1 表达式2 操作符 的顺序再入栈 ，最后栈顶就是完整的后缀表达式
 *  \
 */
public class InfixToPostfix {

    public static void main(String[] args) {
        Stack<String> ops = new Stack<String>();
        Stack<String> vals = new Stack<String>();
        String str = "( ( ( 2 * 3 ) / ( 2 - 1 ) ) + ( 3 * ( 4 - 1 ) ) )";
//        String str="( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )";
        String[] slist = str.split(" ");
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

        StdOut.println(vals.pop());
    }
    //    public static void main(String[] args) {
//        //无sqrt操作符的实现版本
//        Stack<String> stack = new Stack<String>();
////        String str="( ( ( 1 + 2 ) * 3 ) - 4 )";
//        String str="( ( ( 2 * 3 ) / ( 2 - 1 ) ) + ( 3 * ( 4 - 1 ) ) )";
////        String str="( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )";
//        String[] slist=str.split(" ");
//        for(String s : slist){
//            switch (s) {
//                case "+":
//                case "-":
//                case "*":
//                case "/":
//                    stack.push(s);
//                    break;
//                case "(":
//                    break;
//                case ")":
//                    System.out.print(stack.pop() + " ");
//                    break;
//                default:
//                    System.out.print(s + " ");
//            }
//        }
//        System.out.println();
//    }

}
