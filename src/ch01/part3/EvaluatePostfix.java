package ch01.part3;

import ch01.part3.Stack;
import org.junit.Test;

/**
 *  \* Created with IntelliJ IDEA.
 *  \* User: hmy
 *  \* Date: 2020/4/30
 *  \* Time: 10:34
 *  \* To change this template use File | Settings | File Templates.
 *  \* Description: answer for ex 1.3.11
 *  \
 */
public class EvaluatePostfix {
    public Double func(String input) {
        Stack<Double> vals = new Stack<>();
        String[] slist = input.split(" ");
        for (String s : slist) {
            Double a;
            Double b;
            double t;
            switch (s) {
                case "*":
                    a = vals.pop();
                    b = vals.pop();
                    t = b * a;
                    vals.push(t);
                    break;
                case "+":
                    a = vals.pop();
                    b = vals.pop();
                    t = b + a;
                    vals.push(t);
                    break;
                case "-":
                    a = vals.pop();
                    b = vals.pop();
                    t = b - a;
                    vals.push(t);
                    break;
                case "/":
                    a = vals.pop();
                    b = vals.pop();
                    t = b / a;
                    vals.push(t);
                    break;
                default:
                    vals.push(Double.parseDouble(s));
            }
        }
        return vals.pop();
    }

    @Test
    public void test() {
        String input = "2 3 * 2 1 - / 3 4 1 - * +";
        assert func(input) == 15.0;
    }
}
