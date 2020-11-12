package com.dzavorin.solutions.strings;

import java.util.Stack;

public class RemoveDuplicateLetters {

    public String removeDuplicateLetters(String s) {
        int[] arr = new int[26];

        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
        }

        boolean[] cont = new boolean[26];
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            while (sb.length() != 0
                    && sb.charAt(sb.length() - 1) > s.charAt(i)
                    && arr[sb.charAt(sb.length() - 1) - 'a'] > 0
                    && !cont[s.charAt(i) - 'a']) {

                cont[sb.charAt(sb.length() - 1) - 'a'] = false;
                sb.deleteCharAt(sb.length() - 1);
            }

            if (!cont[s.charAt(i) - 'a']) {
                sb.append(s.charAt(i));
                cont[s.charAt(i) - 'a'] = true;
            }
            arr[s.charAt(i) - 'a']--;
        }
        return sb.toString();
    }

    public String removeDuplicateLetters2(String s) {
        int[] lastIndex = new int[26];

        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        boolean[] seen = new boolean[26]; // keep track seen
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            if (seen[c]) continue;
            while (!st.isEmpty() && st.peek() > c && i < lastIndex[st.peek()]) {
                seen[st.pop()] = false;
            }
            st.push(c);
            seen[c] = true;
        }

        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty()) {
            sb.append((char) (st.pop() + 'a'));
        }
        return sb.reverse().toString();

    }

}
