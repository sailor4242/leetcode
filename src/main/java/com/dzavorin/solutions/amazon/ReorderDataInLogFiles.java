package com.dzavorin.solutions.amazon;

import java.util.Arrays;

public class ReorderDataInLogFiles {

    public String[] reorderLogFiles(String[] logs) {
        return Arrays.stream(logs)
                .map(log -> log.split(" ", 2))
                .sorted((str1, str2) -> {
                    boolean isDg1 = Character.isDigit(str1[1].charAt(0));
                    boolean isDg2 = Character.isDigit(str2[1].charAt(0));
                    if (isDg1 && isDg2) {
                        return 0;
                    } else if (isDg1 && !isDg2) {
                        return 1;
                    } else if (!isDg1 && isDg2) {
                        return -1;
                    } else {

                        int cmp = str1[1].compareTo(str2[1]);
                        if (cmp != 0)
                            return cmp;

                        return str1[0].compareTo(str2[0]);
                    }
                })
                .map(str -> str[0] + " " + str[1])
                .toArray(String[]::new);
    }

    public static void main(String[] args) {
        System.out.println(new ReorderDataInLogFiles().reorderLogFiles(new String[]{"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"}));
    }

}
