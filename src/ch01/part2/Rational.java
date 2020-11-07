package ch01.part2;

import javax.swing.text.StyleContext;

/**
 * @program: Alg4_Code
 * @author: hhmy27
 * @created: 2020/11/07 14:19
 * @description: answer for ex1.2.16
 */
public class Rational {
    private long numerator;
    private long denominator;

    public Rational(long numerator, long denominator) {
        // ex 1.2.17
        assert numerator >= Long.MIN_VALUE && numerator <= Long.MAX_VALUE;
        assert denominator >= Long.MIN_VALUE && denominator <= Long.MAX_VALUE;

        this.numerator = numerator;
        this.denominator = denominator;
        check();
        adjustNegativeSign();
        simplify();
    }

    private void check() {
        if (this.denominator == 0)
            throw new Error("denominator can not be zero");
    }

    // greatest common divisor
    private long gcd(long a, long b) {
        if (a % b == 0)
            return b;
        else return gcd(b, a % b);
    }

    // least common multiple
    private long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }

    // negative sign always in numerator
    private void adjustNegativeSign() {
        if ((numerator < 0 && denominator < 0) || (numerator > 0 && denominator < 0)) {
            numerator = -numerator;
            denominator = -denominator;
        }
    }

    private void simplify() {
        long res = gcd(Math.abs(numerator), Math.abs(denominator));
        numerator /= res;
        denominator /= res;
    }

    public Rational plus(Rational b) {
        long anum = this.numerator;
        long ade = this.denominator;
        long bnum = b.numerator;
        long bde = b.denominator;

        long lcm = lcm(ade, bde);

        long ma = lcm / ade;
        long mb = lcm / bde;

        anum = anum * ma;
        ade = ade * ma;
        bnum = bnum * mb;

        anum += bnum;

        return new Rational(anum, ade);
    }

    public Rational minus(Rational b) {
        return this.plus(new Rational(-b.numerator, b.denominator));
    }

    public Rational times(Rational b) {
        long anum = this.numerator;
        long ade = this.denominator;
        long bnum = b.numerator;
        long bde = b.denominator;

        return new Rational(anum * bnum, ade * bde);
    }

    public Rational divides(Rational b) {
        return this.times(new Rational(b.denominator, b.numerator));
    }

    public boolean equals(Rational b) {
        return b.numerator == this.numerator && b.denominator == this.denominator;
    }

    @Override
    public String toString() {
        return this.numerator + "/" + this.denominator;
    }

    public static void main(String[] args) {
        Rational ra = new Rational(3, 5);
        Rational rb = new Rational(5, 2);

        System.out.println(ra.plus(rb));
        System.out.println(ra.minus(rb));
        System.out.println(ra.times(rb));
        System.out.println(ra.divides(rb));
        System.out.println(ra.equals(new Rational(ra.numerator, ra.denominator)));

    }
}
