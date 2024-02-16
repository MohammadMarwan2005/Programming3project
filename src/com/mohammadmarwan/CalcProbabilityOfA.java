package com.mohammadmarwan;

import java.util.Random;

public class CalcProbabilityOfA {
    static Random random = new Random();
    public static void main(String[] args) {
        long n = 0;
        long an = 0;

        while (n < Long.MAX_VALUE) {
            n++;
            if (random.nextInt(10) == 5) {
                System.out.println(n);
                an++;
                System.out.println("P(A) = " + (double) an/n);
            }
        }
    }
}
