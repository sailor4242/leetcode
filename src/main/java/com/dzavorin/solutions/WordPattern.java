package com.dzavorin.solutions;

import java.util.HashMap;
import java.util.Map;

public class WordPattern {

    public boolean wordPattern(String pattern, String str) {
        String[] arr = str.split(" ");
        HashMap<Character, String> dict = new HashMap<>();

        if (arr.length != pattern.length()) {
            return false;
        }

        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            if (dict.containsKey(ch)) {
                if (!dict.get(ch).equals(arr[i]))
                    return false;
            } else {
                if (dict.containsValue(arr[i]))
                    return false;
                dict.put(ch, arr[i]);
            }
        }
        return true;
    }

    public boolean wordPattern2(String pattern, String str) {

        String[] arr = str.split(" ");

        if (pattern.length() != arr.length) return false;

        Map<Character, String> dict = new HashMap<>();
        Map<String, Character> reverseDict = new HashMap<>();
        int i = 0;
        while (i < pattern.length()) {
            char ch = pattern.charAt(i);
            String s = arr[i];

            if (!dict.containsKey(ch) && !reverseDict.containsKey(s)) {
                dict.put(ch, s);
                reverseDict.put(s, ch);
            } else if ((dict.get(ch) != null && !dict.get(ch).equals(s))
                    || (reverseDict.get(s) != null && !reverseDict.get(s).equals(ch))) {
                return false;
            }

            i++;
        }

        return true;

    }
}
