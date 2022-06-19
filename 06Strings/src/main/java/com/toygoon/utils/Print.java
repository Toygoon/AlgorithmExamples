package com.toygoon.utils;

import com.toygoon.search.algs.KMP;

import java.util.Arrays;

public class Print {
    public static void printArray(String[] a, String msg) {
        System.out.printf("%-40s", msg);
        for (String s : a)
            System.out.print(s + " ");
        System.out.println();
    }

    public static void printSpaceString(String txt) {
        System.out.printf("%-5s", "i");
        System.out.printf("%-5s", "j");
        System.out.printf("%-10s", "i+j");

        for (int i = 0; i < txt.length(); i++)
            System.out.printf("%-2d ", i);

        System.out.printf("\n%-20s", "");
        for (int i = 0; i < txt.length(); i++)
            System.out.printf("%-2c ", txt.charAt(i));
        System.out.println();
    }

    public static void printMatchingPattern(int I, int J, int IJ, String pat) {
        System.out.printf("%-5s", I);
        System.out.printf("%-5s", J);
        System.out.printf("%-10s", IJ);

        for (int i = 0; i < I; i++)
            System.out.printf("%-2s ", "");

        for (int i = 0; i < pat.length(); i++)
            System.out.printf("%-2c ", pat.charAt(i));
        System.out.println();
    }

    public static void printDFA(KMP kmp) {
        System.out.printf("\n%-20s", "j");
        for (int i = 0; i < kmp.pat.length(); i++)
            System.out.printf("%-2d ", i);

        System.out.printf("\n%-20s", "pat.charAt(j)");
        for (int i = 0; i < kmp.pat.length(); i++)
            System.out.printf("%-2c ", kmp.pat.charAt(i));


        String[] pat = Arrays.stream(kmp.pat.split("")).distinct().toArray(String[]::new);
        Arrays.sort(pat);

        System.out.printf("\n%-10s", "dfa[][j]");
        for (int i = 0; i < pat.length; i++) {
            if (i != 0)
                System.out.printf("%-10s%-10s", "", pat[i]);
            else
                System.out.printf("%-10s", pat[i]);

            for (int j = 0; j < kmp.pat.length(); j++)
                System.out.printf("%-2d ", kmp.dfa[pat[i].charAt(0)][j]);
            System.out.println();
        }

        System.out.printf("\n%-10s%-10s", "", "j");
        for (int i = 0; i < kmp.xList.size(); i++)
            System.out.printf("%-2d ", i);
        System.out.printf("\n%-10s%-10s", "", "X");
        for (int i = 0; i < kmp.xList.size(); i++)
            System.out.printf("%-2d ", kmp.xList.get(i));

        System.out.println();
    }
}
