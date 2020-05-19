package com.dzavorin.solutions;

public class LongestPalindromicSubstring {

    public String longestPalindrome2(String s) {

        String res = "";

        if (s.length() == 0) {
            return res;
        }

        for (int i = 0; i < s.length(); i++) {

            for (int j = s.length() - 1; j > i; j--) {

                if (s.charAt(i) == s.charAt(j)) {
                    int lo = i;
                    int hi = j;
                    while (lo + 1 < hi - 1 && s.charAt(lo + 1) == s.charAt(hi - 1)) {
                        lo++;
                        hi--;
                    }
                    if (hi - 2 == lo || hi - 1 == lo) {
                        if (j - i > res.length() - 1) {
                            res = s.substring(i, j + 1);
                        }
                        if (res.length() > s.length() / 2) {
                            i = hi;
                        }
                    }
                }
            }
        }

        return res.equals("") ? s.substring(0, 1) : res;
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    public static void main(String[] args) {
        System.out.println(new LongestPalindromicSubstring().longestPalindrome("babad")); // bab
        System.out.println(new LongestPalindromicSubstring().longestPalindrome("cbbd")); // bb
        System.out.println(new LongestPalindromicSubstring().longestPalindrome("babadecbbd")); // bab
        System.out.println(new LongestPalindromicSubstring().longestPalindrome("a")); // a
        System.out.println(new LongestPalindromicSubstring().longestPalindrome("abcda")); // a
        System.out.println(new LongestPalindromicSubstring().longestPalindrome("aaaa")); // a
        System.out.println(new LongestPalindromicSubstring().longestPalindrome("vaavqahaq")); // a
        System.out.println(new LongestPalindromicSubstring().longestPalindrome("kyyrjtdplseovzwjkykrjwhxquwxsfsorjiumvxjhjmgeueafubtonhlerrgsgohfosqssmizcuqryqomsipovhhodpfyudtusjhonlqabhxfahfcjqxyckycstcqwxvicwkjeuboerkmjshfgiglceycmycadpnvoeaurqatesivajoqdilynbcihnidbizwkuaoegmytopzdmvvoewvhebqzskseeubnretjgnmyjwwgcooytfojeuzcuyhsznbcaiqpwcyusyyywqmmvqzvvceylnuwcbxybhqpvjumzomnabrjgcfaabqmiotlfojnyuolostmtacbwmwlqdfkbfikusuqtupdwdrjwqmuudbcvtpieiwteqbeyfyqejglmxofdjksqmzeugwvuniaxdrunyunnqpbnfbgqemvamaxuhjbyzqmhalrprhnindrkbopwbwsjeqrmyqipnqvjqzpjalqyfvaavyhytetllzupxjwozdfpmjhjlrnitnjgapzrakcqahaqetwllaaiadalmxgvpawqpgecojxfvcgxsbrldktufdrogkogbltcezflyctklpqrjymqzyzmtlssnavzcquytcskcnjzzrytsvawkavzboncxlhqfiofuohehaygxidxsofhmhzygklliovnwqbwwiiyarxtoihvjkdrzqsnmhdtdlpckuayhtfyirnhkrhbrwkdymjrjklonyggqnxhfvtkqxoicakzsxmgczpwhpkzcntkcwhkdkxvfnjbvjjoumczjyvdgkfukfuldolqnauvoyhoheoqvpwoisniv")); // a
    }
}
