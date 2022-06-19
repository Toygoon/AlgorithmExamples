package com.toygoon.compress;

import com.toygoon.compress.algs.Huffman;

public class CompressMain {
    public static void main(String[] args) {
        Huffman.Node root = Huffman.compress("ABRACADABRA!");

    }
}
