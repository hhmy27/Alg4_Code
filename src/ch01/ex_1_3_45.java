package ch01;

/**
 * @program: AlgorithmBook
 * @author: hhmy27
 * @created: 2020/06/13 20:18
 * @description: ex 1.3.45
 */
public class ex_1_3_45 {
    // 判断栈是否会向下溢出
    public boolean judgeOverflow(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '-') {
                sum -= 1;
            } else {
                sum += 1;
            }
            if (sum < 0)
                return false;
        }
        return true;
    }

    // 在线性时间内判断这些操作是否能生成给定序列
    // s是输入序列，t是目标序列 target，判断由s是否能生成target
    public boolean judgeGenerate(String s, String t) {
        if (s.length() != t.length()) return false;
        Stack<Character> stack = new Stack<>();
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i += 1;
                j += 1;
            } else {
                stack.push(s.charAt(i++));
            }
        }
        // 如果t遍历完了 返回true
        if (j == t.length()) return true;
        // 否则查看栈里面和剩下的字符是否匹配
        while (!stack.isEmpty() && stack.peek() == t.charAt(j)) {
            stack.pop();
            j += 1;
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ex_1_3_45 ex = new ex_1_3_45();
        System.out.println(ex.judgeOverflow("as-d--"));
        System.out.println(ex.judgeGenerate("adsc", "cdsa"));
    }
}

