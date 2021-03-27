package com.dzavorin.solutions.linkedlist;

import java.util.Stack;

public class ScoreOfParenthesis {

    public int scoreOfParentheses(String S) {
        return dfs(S, 0, S.length());
    }

    private int dfs(String s, int lo, int hi) {
        int res = 0;
        for (int i = lo; i < hi; i++) {
            int count = 0;
            int j = i;
            for (; j < hi; j++) {
                if (s.charAt(j) == '(') {
                    count++;
                } else {
                    count--;
                }
                if (count == 0) {

                    if (j - 1 != i) {
                        res += 2 * dfs(s, i + 1, j);
                    } else {
                        res += 1;
                    }

                    i = j;
                    break;
                }
            }

        }

        return res;
    }

    public int scoreOfParentheses2(String S) {
        Stack<Integer> stack = new Stack();
        stack.push(0); // The score of the current frame

        for (char c : S.toCharArray()) {
            if (c == '(')
                stack.push(0);
            else {
                int v = stack.pop();
                int w = stack.pop();
                stack.push(w + Math.max(2 * v, 1));
            }
        }

        return stack.pop();
    }

    public int scoreOfParentheses4(String S) {
        return F(S, 0, S.length());
    }

    public int F(String S, int i, int j) {
        //Score of balanced string S[i:j]
        int ans = 0, bal = 0;

        // Split string into primitives
        for (int k = i; k < j; ++k) {
            bal += S.charAt(k) == '(' ? 1 : -1;
            if (bal == 0) {
                if (k - i == 1) ans++;
                else ans += 2 * F(S, i + 1, k);
                i = k + 1;
            }
        }

        return ans;
    }

    public int scoreOfParentheses5(String S) {
        int ans = 0, bal = 0;
        for (int i = 0; i < S.length(); ++i) {
            if (S.charAt(i) == '(') {
                bal++;
            } else {
                bal--;
                if (S.charAt(i - 1) == '(')
                    ans += 1 << bal;
            }
        }

        return ans;
    }


}
