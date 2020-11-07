package ch01.part3;

/**
 * @program: Alg4_Code
 * @author: hhmy27
 * @created: 2020/11/07 16:58
 * @description: answer for ex1.3.3
 */
public class ex_1_3_3 {
    /*
     * Thread:
     * if the sequence is valid Pop sequence, for every number in sequence, current number must larger than all number after it
     * we can check this trait for all number */

    // we check all number in order
    // so this algorithm operate times like n-1,n-2,n-3...1
    // it time complexity is O(n^2)
    public boolean func1(String sequence) {
        for (int i = 0; i < sequence.length(); i++) {
            int cur = sequence.charAt(i) - '0';
            int last = cur;
            for (int j = i + 1; j < sequence.length(); j++) {
                int tmp = sequence.charAt(j) - '0';
                if (tmp < cur) {
                    if (tmp > last) {
                        return false;
                    } else
                        last = tmp;
                }
            }
        }
        return true;
    }

    // more simplify method is we manually simulate pop operate and compare to sequential
    // its time complexity is O(n)
    public boolean func2(String sequence) {
        Queue<Integer> queue = new Queue<>();
        for (int i = 0; i < sequence.length(); i++) {
            queue.enqueue(sequence.charAt(i) - '0');
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i <= 9; i++) {
            stack.push(i);
            while (!stack.isEmpty() && stack.peek().equals(queue.front())) {
                stack.pop();
                queue.dequeue();
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s0 = "4321098765";
        String s1 = "4687532901";
        String s2 = "2567489310";
        String s3 = "4321056789";
        String s4 = "1234569870";
        String s5 = "0465381729";
        String s6 = "1479865302";
        String s7 = "2143658790";
        String[] ss = {s0, s1, s2, s3, s4, s5, s6, s7};
        // presume all input is valid string that only contain number character
        // and all number will appear only once
        // and not number repeat
        ex_1_3_3 solution = new ex_1_3_3();
        for (String s : ss) {
            System.out.println(solution.func1(s));
        }

        System.out.println("==========================");

        for (String s : ss) {
            System.out.println(solution.func2(s));
        }
    }
}