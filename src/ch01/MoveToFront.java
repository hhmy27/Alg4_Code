package ch01;


/**
 * @program: AlgorithmBook
 * @author: hhmy27
 * @created: 2020/06/10 20:33
 * @description: this is answer of ex 1.3.40
 */
public class MoveToFront {
    LinkList<Character> link = new LinkList<>();

    public void operate(String s) {
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (link.find(ch)) {
                link.remove(ch);
            }
            link.preAppend(ch);
            link.visitList();
        }

    }

    public static void main(String[] args) {
        MoveToFront move = new MoveToFront();
        move.operate("sadwWEJRFXSFJsefisfnsdv");
    }
}
