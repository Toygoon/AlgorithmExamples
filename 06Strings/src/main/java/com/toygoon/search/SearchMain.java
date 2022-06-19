package com.toygoon.search;

import com.toygoon.search.algs.BM;
import com.toygoon.search.algs.Brute;
import com.toygoon.search.algs.KMP;
import com.toygoon.search.algs.RK;
import com.toygoon.utils.Print;

import static com.toygoon.utils.Print.printHash;

public class SearchMain {
    public static void main(String[] args) {
        // Brute
        String txt = "AAAAAAAAAB", pat = "AAAAB";
        Print.printSpaceString(txt);
        Brute.search(pat, txt);

        System.out.println();
        txt = "ABACADABRAC";
        pat = "ADACR";
        Print.printSpaceString(txt);
        Brute.search(pat, txt);

        // DFA
        pat = "AACAAAB";
        Print.printDFA(new KMP(pat));

        // Boyer-Moore
        System.out.println();
        txt = "FINDINAHAYSTACKNEEDLEINA";
        pat = "NEEDLE";
        Print.printSpaceStringSkip(txt);
        BM bm = new BM(pat);
        bm.search(txt);

        // Rabin-Karp
        System.out.println();
        txt = "3141592653589793";
        pat = "31415926535";
        RK rk = new RK();
        rk.search(pat, txt, 5, 997);
        printHash(rk);
    }
}
