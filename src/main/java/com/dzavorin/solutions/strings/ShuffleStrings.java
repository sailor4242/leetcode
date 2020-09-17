package com.dzavorin.solutions.strings;

public class ShuffleStrings {

    public String restoreString(String s, int[] indices) {

        char[] arr = new char[s.length()];

        for (int i = 0; i < indices.length; i++) {
            int idx = indices[i];
            arr[idx] = s.charAt(i);
        }

        return new String(arr);
    }

}
