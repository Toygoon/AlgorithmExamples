package com.toygoon.sort;

import com.toygoon.sort.algs.LSD;
import com.toygoon.sort.algs.MSD;
import com.toygoon.sort.algs.ThreeWayQuick;
import com.toygoon.utils.Print;

public class SortMain {
    public static void main(String[] args) {
        //String[] a = "no is th ti fo co ai of th pa".split(" ");
        String[] a = "she sells seashells by the sea shore the shells she sells are surely seashells".split(" ");

        Print.printArray(a, "Original");
        ThreeWayQuick.sort(a.clone());
        System.out.println();

        Print.printArray(a, "Original");
        MSD.sort(a.clone());
        System.out.println();

        String[] lsd = "dab add cab fad fee bad dad bee fed bed ebb ace".split(" ");
        Print.printArray(lsd, "Original");
        LSD.sort(lsd.clone(), lsd[0].length());
        System.out.println();
    }
}