package com.dzavorin.solutions.strings;

public class ReverseWordsInAString {

    public String reverseWords(String s) {
        String[] arr = s.trim().split(" ");

        StringBuilder res = new StringBuilder();

        for (int i = arr.length - 1; i >= 0; i--) {
            if (!"".equals(arr[i])) {
                res.append(arr[i]);
                if (i > 0) {
                    res.append(" ");
                }
            }
        }

        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(new ReverseWordsInAString().reverseWords("  hello world!  "));
        System.out.println(new ReverseWordsInAString().reverseWords("the sky is blue"));
    }
}
