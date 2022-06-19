package com.toygoon;

public class Main {
    public static void main(String[] args) {
        String[] a = "no is th ti fo co ai of th pa".split(" ");
        Print.printArray(a, "x");
        MSD.sort(a);
        ThreeWayQuick.sort(a);
    }
}