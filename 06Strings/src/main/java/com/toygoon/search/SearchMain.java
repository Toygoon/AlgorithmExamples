package com.toygoon.search;

import com.toygoon.search.algs.Brute;
import com.toygoon.search.algs.KMP;
import com.toygoon.utils.Print;

public class SearchMain {
    public static void main(String[] args) {
        String txt = "AAAAAAAAAB";
        Print.printSpaceString(txt);
        Brute.search("AAAAB", txt);

        System.out.println();
        txt = "ABACADABRAC";
        Print.printSpaceString(txt);
        Brute.search("ADACR", txt);

        Print.printDFA(new KMP("AACAAAB"));
    }
}
