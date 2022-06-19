package com.toygoon.search.algs;

import java.math.BigInteger;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class RK {
    // d is the number of characters in the input alphabet
    public final static int d = 256;
    public final static int R = d;
    public static List<Integer> hashList = new ArrayList<>(), txtHashList = new ArrayList<>();
    public static String pat, txt;
    /* pat -> pattern
        txt -> text
        q -> A prime number
    */

    // a random 31-bit prime
    private static long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }

    public static boolean search(String pat, String txt, int hornerTimes, int q) {
        int M = pat.length();
        int N = txt.length();
        int i, j;
        int p = 0; // hash value for pattern
        int t = 0; // hash value for txt
        int h = Character.getNumericValue(pat.charAt(0));
        int txtH = Character.getNumericValue(txt.charAt(0));
        hashList.add(h);
        txtHashList.add(txtH);

        // The value of h would be "pow(d, M-1)%q"
        for (i = 1; i <= hornerTimes - 1; i++) {
            h = (h * 10 + Character.getNumericValue(pat.charAt(i))) % q;
            txtH = (txtH * 10 + Character.getNumericValue(txt.charAt(i))) % q;
            hashList.add(h);
            txtHashList.add(txtH);
        }

        BigInteger RM = BigInteger.valueOf((long) Math.pow(10, M - hornerTimes - 2) % 997);

        // Calculate the hash value of pattern and first
        // window of text
        p = h;
        for (i = 0; i < M - hornerTimes; i++) {
            p = (((p + Character.getNumericValue(pat.charAt(i)) * (997 - RM.intValue())) * 10 + Character.getNumericValue(pat.charAt(i + hornerTimes))) % 997);
            hashList.add(p);
        }

        t = txtH;
        for (i = 0; i < N - hornerTimes; i++) {
            t = (((t + Character.getNumericValue(txt.charAt(i)) * (997 - RM.intValue())) * 10 + Character.getNumericValue(txt.charAt(i + hornerTimes))) % 997);
            txtHashList.add(t);
        }

        /*
        // Slide the pattern over text one by one
        for (i = 0; i <= N - M; i++) {
            // Check the hash values of current window of text
            // and pattern. If the hash values match then only
            // check for characters on by one
            if (p == t) {
                // Check for characters one by one
                for (j = 0; j < M; j++) {
                    if (txt.charAt(i + j) != pat.charAt(j))
                        break;
                }

                // if p == t and pat[0...M-1] = txt[i, i+1, ...i+M-1]
                if (j == M)
                    System.out.println("Pattern found at index " + i);
            }

            // Calculate hash value for next window of text: Remove
            // leading digit, add trailing digit
            if (i < N - M) {
                t = (d * (t - txt.charAt(i) * h) + txt.charAt(i + M)) % q;

                // We might get negative value of t, converting it
                // to positive
                if (t < 0)
                    t = (t + q);
            }
        }
        */

        RK.pat = pat;
        RK.txt = txt;

        for (i = 0; i < hashList.size(); i++) {
            if (!Objects.equals(hashList.get(i), txtHashList.get(i))) {
                return false;
            }
        }

        return true;
    }
}
