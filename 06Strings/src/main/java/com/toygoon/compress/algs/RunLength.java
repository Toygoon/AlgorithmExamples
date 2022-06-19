package com.toygoon.compress.algs;

public class RunLength {
    public static void compress(String txt, int logBit) {
        int count = 0, max = (int) Math.pow(2, logBit) - 2;
        boolean bit = false;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < txt.length(); i++) {
            if (txt.charAt(i) == '0') {

            } else {

            }
        }
    }
}
