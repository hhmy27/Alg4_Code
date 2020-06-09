package ch01;

/**
 *  \* Created with IntelliJ IDEA.
 *  \* User: hmy
 *  \* Date: 2020/4/30
 *  \* Time: 10:34
 *  \* To change this template use File | Settings | File Templates.
 *  \* Description: 
 *  \
 */
public class EvaluatePostfix {
    public static void main(String[] args) {
        Stack<Double> vals = new Stack<>();
        Stack<String> ops = new Stack<>();
        String str = "2 3 * 2 1 - / 3 4 1 - * +";
        String[] slist = str.split(" ");
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
        System.out.println(vals.pop());
    }
}
