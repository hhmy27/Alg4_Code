package ch01;

import edu.princeton.cs.algs4.StdIn;
import ch01.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 *  \* Created with IntelliJ IDEA.
 *  \* User: hmy
 *  \* Date: 2020/4/30
 *  \* Time: 10:04
 *  \* To change this template use File | Settings | File Templates.
 *  \* Description: 
 *  \
 */
public class InfixToPostfix {
    //    public static void main(String[] args) {
//
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
    public static void main(String[] args) {
        Stack<String> ops = new Stack<String>();
        Stack<String> vals = new Stack<String>();
        String str="( ( ( 2 * 3 ) / ( 2 - 1 ) ) + ( 3 * ( 4 - 1 ) ) )";
//        String str="( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )";
        String[] slist=str.split(" ");
        for(String s : slist){
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
}
