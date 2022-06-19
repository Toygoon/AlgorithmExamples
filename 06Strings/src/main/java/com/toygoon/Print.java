package com.toygoon;

public class Print {
    public static void printArray(String[] a, String msg) {
        System.out.printf("%-40s", msg);
        for (String s : a)
            System.out.print(s + " ");
        System.out.println();
    }
}
