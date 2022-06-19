package com.toygoon.search;

import com.toygoon.search.algs.BM;
import com.toygoon.search.algs.Brute;
import com.toygoon.search.algs.KMP;
import com.toygoon.search.algs.RK;
import com.toygoon.utils.Print;

public class SearchMain {
    public static void main(String[] args) {
        String txt = "AAAAAAAAAB", pat = "AAAAB";
        Print.printSpaceString(txt);
        Brute.search(pat, txt);

        System.out.println();
        txt = "ABACADABRAC";
        pat = "ADACR";
        Print.printSpaceString(txt);
        Brute.search(pat, txt);

        pat = "AACAAAB";
        Print.printDFA(new KMP(pat));

        System.out.println();
        txt = "FINDINAHAYSTACKNEEDLEINA";
        pat = "NEEDLE";
        Print.printSpaceStringSkip(txt);
        BM bm = new BM(pat);
        bm.search(txt);

        System.out.println();
        txt = "3141592653589793";
        pat = "31415926535";
        RK rk = new RK(pat);
        System.out.println(rk.search(txt));
    }
}
