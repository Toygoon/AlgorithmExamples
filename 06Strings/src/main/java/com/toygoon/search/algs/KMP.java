package com.toygoon.search.algs;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KMP {
    private int R;
    public String pat;
    public int[][] dfa;
    public HashMap<Integer, Integer> xList;

    public KMP(String pat) {
        this.R = 256;
        this.pat = pat;
        this.xList = new HashMap<>();
        xList.put(0, 0);

        int M = pat.length();
        dfa = new int[R][M];
        dfa[pat.charAt(0)][0] = 1;

        for (int X = 0, j = 1; j < M; j++) {
            for (int c = 0; c < R; c++) {
                dfa[c][j] = dfa[c][X];
            }
            dfa[pat.charAt(j)][j] = j + 1;
            X = dfa[pat.charAt(j)][X];
            xList.put(j, X);
        }
    }
}
