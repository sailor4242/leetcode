package com.dzavorin.solutions;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LongestWordInDictionaryThroughDeleting {

    public String findLongestWord(String s, List<String> d) {
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
        }

        List<String> words = d.stream()
                .filter(word -> hasAllLetters(arr, word))
                .sorted(Comparator.<String, Integer>comparing(word -> -word.length()).thenComparing(word -> word))
                .collect(Collectors.toList());

        for (String word : words) {
            int j = -1;
            for (int i = 0; i < word.length(); i++) {
                char cur = word.charAt(i);
                j++;
                while (j < s.length() && s.charAt(j) != cur) {
                    j++;
                }
                if (j > s.length()) {
                    break;
                }
            }
            if (j < s.length()) {
                return word;
            }
        }

        return "";
    }

    private boolean hasAllLetters(int[] arr1, String s) {
        int[] arr2 = new int[26];
        for (int i = 0; i < s.length(); i++) {
            arr2[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (arr2[i] != 0 && arr1[i] < arr2[i]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
//        System.out.println(new LongestWordInDictionaryThroughDeleting().findLongestWord("aewfafwafjlwajflwajflwafj",
//                List.of("apple","ewaf","awefawfwaf","awef","awefe","ewafeffewafewf")));
        System.out.println(new LongestWordInDictionaryThroughDeleting()
                .findLongestWord("mjmqqjrmzkvhxlyruonekhhofpzzslupzojfuoztvzmmqvmlhgqxehojfowtrinbatjujaxekbcydldglkbxsqbbnrkhfdnpfbuaktupfftiljwpgglkjqunvithzlzpgikixqeuimmtbiskemplcvljqgvlzvnqxgedxqnznddkiujwhdefziydtquoudzxstpjjitmiimbjfgfjikkjycwgnpdxpeppsturjwkgnifinccvqzwlbmgpdaodzptyrjjkbqmgdrftfbwgimsmjpknuqtijrsnwvtytqqvookinzmkkkrkgwafohflvuedssukjgipgmypakhlckvizmqvycvbxhlljzejcaijqnfgobuhuiahtmxfzoplmmjfxtggwwxliplntkfuxjcnzcqsaagahbbneugiocexcfpszzomumfqpaiydssmihdoewahoswhlnpctjmkyufsvjlrflfiktndubnymenlmpyrhjxfdcq",
                List.of("ettphsiunoglotjlccurlre","ntgcykxhdfescxxypyew")));
    }

}
