package com.toygoon.regex;

import com.toygoon.regex.algs.Digraph;
import com.toygoon.regex.algs.NFA;
import com.toygoon.utils.Print;

public class RegexMain {
    public static void main(String[] args) {
        // NFA
        String regex = "((A*B|AC)D)", pat = "AABD";
        NFA nfa = new NFA(regex);
        Digraph digraph = nfa.graph;
        System.out.println(digraph.toString());
        nfa.recognizes(pat);


    }
}
