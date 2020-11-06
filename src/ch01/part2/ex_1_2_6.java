package ch01.part2;

import org.junit.Test;

/**
 * @program: Alg4_Code
 * @author: hhmy27
 * @created: 2020/11/06 21:42
 * @description:
 */
public class ex_1_2_6 {

    @Test
    public void test1() {
        String s = "ACTGACG";
        String t = "TGACGAC";

        assert func(s, t) == true;
    }

    @Test
    public void test2() {
        String s = "asde";
        String t = "eads";

        assert func(s, t) == false;
    }

    @Test
    public void test3() {
        String s = "";
        String t = "";

        assert func(s, t) == true;
    }

    @Test
    public void test4() {
        String s = "a";
        String t = "a";

        assert func(s, t) == true;
    }

    public boolean func(String s, String t) {
        // we call String tmp constructed by s connect itself
        // tmp contain all String s circular move case
        // if t is s circular rotation, t must be contained by tmp
        // and if t is s circular rotation, s is t circular rotation too
        return s.concat(s).contains(t);
    }
}