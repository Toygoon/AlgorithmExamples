package util;

import algorithm.BinarySearchST;
import algorithm.SequentialSearchST;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FrequencyCounter {
    private static final String RES_DIR = System.getProperty("user.dir") + System.getProperty("file.separator") + "res";
    private static final int DEFAULT_MIN_LENGTH = 8;
    private static int minLength;

    public FrequencyCounter() {
        this(DEFAULT_MIN_LENGTH);
    }

    public FrequencyCounter(int minLength) {
        this.minLength = minLength;
    }

    public FrequencyCounter(String[] args) {
        this(Integer.parseInt(args[0]));
    }

    public static void count() {
        BinarySearchST<String, Integer> st = new BinarySearchST<>();
        File file = null;
        JFileChooser jFileChooser = new JFileChooser(RES_DIR);
        String word, maxKey = "";
        int maxValue = 0;
        long start, end;

        if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            file = jFileChooser.getSelectedFile();
        else {
            JOptionPane.showMessageDialog(null, "Select the file",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        Scanner sc = null;
        // Calculate elapsed time
        start = System.currentTimeMillis();

        /* Save the key-value pairs to the LinkedList
          Key : Word
          Value : Frequency of a word
         */
        try {
            sc = new Scanner(file);
            start = System.currentTimeMillis();

            while (sc.hasNext()) {
                word = sc.next();
                // Ignore length is less than minimum length
                if (word.length() < minLength)
                    continue;
                // If the word not exists in the LinkedList, put the word to it
                if (!st.contains(word))
                    st.put(word, 1);
                // If the word exist in the LinkedList, increase the count of it
                else
                    st.put(word, st.get(word) + 1);
            }

            for(String s : st.keys())
                // Find out maximum value from the LinkedList like Selection Search
                if (st.get(s) > maxValue) {
                    maxValue = st.get(s);
                    maxKey = s;
                }

            end = System.currentTimeMillis();
            System.out.println(maxKey + " " + maxValue);
            System.out.println("Elapsed time : " + (end - start) + "ms");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
