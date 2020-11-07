package ch01.part2;


import org.junit.Test;

import java.util.Calendar;
import java.util.zip.DataFormatException;

/**
 * @program: Alg4_Code
 * @author: hhmy27
 * @created: 2020/11/07 13:29
 * @description: answer for ex1.2.11
 */
public class SmartDate {
    private int year;
    private int month;
    private int day;

    public SmartDate(int month, int day, int year) {
        if (month > 13) {
            throw new Error("month out of the bound");
        }

        int leap = 0;
        // judge leap year
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            leap = 1;
        }

        // the first list is the number of days in each month in a normal year,second is leap year
        int[][] days = {{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}, {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}};

        if (day > days[leap][month - 1]) {
            throw new Error("day out of the bound");
        }
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int day() {
        return this.day;
    }

    public int month() {
        return this.month;
    }

    public int year() {
        return this.year;
    }

    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }

    public static void main(String[] args) {
        SmartDate sd1 = new SmartDate(11, 7, 2020);
        System.out.println(sd1);

        SmartDate sd2 = new SmartDate(5, 13, 2016);
        System.out.println(sd2);
        SmartDate sd3 = new SmartDate(8, 42, 2017);
        System.out.println(sd3);
        SmartDate sd4 = new SmartDate(1919, 8, 212320);
        System.out.println(sd4);

    }

}