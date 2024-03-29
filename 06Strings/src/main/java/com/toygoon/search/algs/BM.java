package com.toygoon.search.algs;

import com.toygoon.utils.Print;

public class BM {
    private final int R;     // the radix
    private int[] right;     // the bad-character skip array

    private char[] pattern;  // store the pattern as a character array
    private String pat;      // or as a string

    /**
     * Preprocesses the pattern string.
     *
     * @param pat the pattern string
     */
    public BM(String pat) {
        this.R = 256;
        this.pat = pat;

        // position of rightmost occurrence of c in the pattern
        right = new int[R];
        for (int c = 0; c < R; c++)
            right[c] = -1;
        for (int j = 0; j < pat.length(); j++)
            right[pat.charAt(j)] = j;
    }

    public int search(String txt) {
        int m = pat.length();
        int n = txt.length();
        int skip = 0;
        int i = 0, j = 0;

        for (i = 0; i <= n - m; i += skip) {
            skip = 0;
            for (j = m - 1; j >= 0; j--) {
                if (pat.charAt(j) != txt.charAt(i + j)) {
                    skip = Math.max(1, j - right[txt.charAt(i + j)]);
                    Print.printMatchingPattern(i, j, skip, pat);
                    break;
                }
            }
            if (skip == 0) {
                Print.printMatchingPattern(i, j, skip, pat);
                return i;
            }    // found
        }
        return n;                       // not found
    }
}
