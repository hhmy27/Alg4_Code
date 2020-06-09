package ch01;

/**
 *  \* Created with IntelliJ IDEA.
 *  \* User: hmy
 *  \* Date: 2020/4/30
 *  \* Time: 10:02
 *  \* To change this template use File | Settings | File Templates.
 *  \* Description: 
 *  \
 */
public class ex_1_3_09 {

    public static String[] ex_1_3_09_a(String[] args) {
        Stack<String> stack = new Stack<>();
        Queue<String> queue = new Queue<>();
        for (String s : args) {
            if (!s.equals(")"))
                // 如果不等于右括号，直接压入栈
                stack.push(s);
            else {
                // 匹配到了右括号,在stack中弹出 A op B的形式
                queue.enqueue("(");
                String[] B = popEntirety(stack);
                String[] op = popEntirety(stack);
                String[] A = popEntirety(stack);
                for (String value : A) {
                    queue.enqueue(value);
                }
                for (String value : op) {
                    queue.enqueue(value);
                }
                for (String value : B) {
                    queue.enqueue(value);
                }
                queue.enqueue(")");
                // queue中存放了 ( A + B )的String，现在弹出并压入stack中 => ( A + B )
                while (!queue.isEmpty()) {
                    stack.push(queue.dequeue());
                }
            }
        }
        // 先将stack压入到tstack中，然后再把tstack中的String放入到ans中
        String[] ans = new String[stack.size()];
        Stack<String> tstack = new Stack<>();
        while (!stack.isEmpty())
            tstack.push(stack.pop());
        int index = 0;
        while (!tstack.isEmpty())
            ans[index++] = tstack.pop();
        return ans;
    }



    public static String[] popEntirety(Stack<String> stack) {
        // 使用这个函数在一个表达式栈中弹出一个整体
        // 整体可能是 1 ， ( 1 + 2 ) ，（3 + （1 + 2） ）,所以返回String[] ,每一个String表示一个操作符
        Stack<String> ops = new Stack<>();

        if (stack.peek().equals(")")) {
            // 如果当前的栈顶是右括号
            // 右括号数+1
            int r = 0;
//            r += 1;
            do {
                String top = stack.pop();
                if (top.equals(")"))
                    r += 1;   //匹配到右括号，右括号数+1
                else if (top.equals("("))
                    r -= 1;   //匹配到左括号，抵消一个有括号数
                ops.push(top);
            } while (r != 0);
        } else {
            ops.push(stack.pop());
        }
        String[] ans = new String[ops.size()];
        int index = 0;
        while (!ops.isEmpty())
            ans[index++] = ops.pop();
        return ans;
    }
    public static String  ex_1_3_09_b(String[] args) {
        Stack<String> ops=new Stack<>();
        Stack<String> vals=new Stack<>();
        for(String s: args){
            // 如果是数字，直接push vals
            // 如果是字符并且不是右括号，则push ops
            // 如果是右括号，开始补全，弹出操作数，操作符，然后再弹出操作数，补全左括号，再压回栈中 （假设没有sqrt操作
            if(s.equals("+")||s.equals("-")||s.equals("*")||s.equals("/"))
                ops.push(s);
            else if( s.equals(")")){
                String a=vals.pop();
                String b=vals.pop();
                String op=ops.pop();
                String t=String.format("( %s %s %s )",b,op,a);
                vals.push(t);
            }else
                vals.push(s);
        }
        return vals.pop();
    }

    public static void main(String[] args) {

    }
}
