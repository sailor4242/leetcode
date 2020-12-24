package com.dzavorin.solutions.dynamic;

public class NumberOfWaysToFormATargetStringUsingADictionary {

    int mod = (int) Math.pow(10, 9) + 7;

    public int numWays(String[] words, String target) {
        int[][] freq = new int[words[0].length()][26];
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                freq[i][word.charAt(i) - 'a']++;
            }
        }

//        for (int i = 0; i < target.length(); i++) {
//            boolean exists = false;
//            for (int j = 0; j < freq.length; j++) {
//                if (freq[j][target.charAt(i) - 'a'] > 0) {
//                    exists = true;
//                }
//            }
//            if (!exists) {
//                return 0;
//            }
//        }

        return (int) dfs(freq, target, 0, 0, words[0].length(),
                new Long[target.length() + 1][words[0].length() + 1]);
    }

    private long dfs(int[][] freq, String target, int i, int j, int wl, Long[][] memo) {
        if (i == target.length()) return 1;
        if (memo[i][j] != null) return memo[i][j];
        if (j == wl) return 0;

        char ch = target.charAt(i);
        long res = dfs(freq, target, i, j + 1, wl, memo) % mod;

        int f = freq[j][ch - 'a'];
        if (f != 0) {
            res = (res + (f * dfs(freq, target, i + 1, j + 1, wl, memo)) % mod) % mod;
        }

        memo[i][j] = res;
        return res;
    }

    public static void main(String[] args) {
//        System.out.println(new NumberOfWaysToFormATargetStringUsingADictionary().numWays(new String[]{"acca","bbbb","caca"}, "aba"));
        System.out.println(new NumberOfWaysToFormATargetStringUsingADictionary().numWays(
                new String[]{"cbabddddbc","addbaacbbd","cccbacdccd","cdcaccacac","dddbacabbd","bdbdadbccb","ddadbacddd","bbccdddadd","dcabaccbbd","ddddcddadc","bdcaaaabdd","adacdcdcdd","cbaaadbdbb","bccbabcbab","accbdccadd","dcccaaddbc","cccccacabd","acacdbcbbc","dbbdbaccca","bdbddbddda","daabadbacb","baccdbaada","ccbabaabcb","dcaabccbbb","bcadddaacc","acddbbdccb","adbddbadab","dbbcdcbcdd","ddbabbadbb","bccbcbbbab","dabbbdbbcb","dacdabadbb","addcbbabab","bcbbccadda","abbcacadac","ccdadcaada","bcacdbccdb"},
                "bcbbcccc")); // 677452090
    }
}
