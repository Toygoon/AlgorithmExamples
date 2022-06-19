package com.toygoon.search.algs;

import com.toygoon.utils.Print;

public class Brute {
    // return offset of first match or n if no match
    public static int search(String pat, String txt) {
        int m = pat.length();
        int n = txt.length();

        int i = 0, j = 0;
        for (i = 0; i <= n - m; i++) {
            for (j = 0; j < m; j++) {
                Print.printMatchingPattern(i, j, i + j, pat);
                if (txt.charAt(i + j) != pat.charAt(j))
                    break;
            }
            if (j == m) {
                Print.printMatchingPattern(i, j, i + j, pat);
                return i;
            }// found at offset i
        }


        return n;                            // not found
    }
}
