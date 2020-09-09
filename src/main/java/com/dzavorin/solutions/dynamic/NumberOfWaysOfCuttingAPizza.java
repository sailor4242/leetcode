package com.dzavorin.solutions.dynamic;

public class NumberOfWaysOfCuttingAPizza {

    public int ways(String[] pizza, int k) {

        char[][] ch = new char[pizza.length][pizza[0].length()];

        for (int i = 0; i < pizza.length; i++) {
            for (int j = 0; j < pizza[0].length(); j++) {
                ch[i][j] = pizza[i].charAt(j);
            }
        }

        return ways(pizza, ch, 0, 0, k);
    }

    public int ways(String[] pizza, char[][] ch, int t, int l, int k) {
        if (k == 1) {
            return 1;
        }

        if (l == ch.length || t == ch.length) {
            return 0;
        }


        int res = 0;

        int prevX = -1;
        int prevY = -1;

        for (int i = t; i < ch.length; i++) {
            for (int j = l; j < ch[0].length; j++) {
                if (ch[i][j] == 'A') {
                    if (prevX == -1 || prevY == -1) {
                        prevX = i;
                        prevY = j;
                        continue;
                    }

                    if (prevX < i) {
                        res += ways(pizza, ch, i, l, k - 1);
                        prevX = i;
                    }

                    if (prevY < j) {
                        res += ways(pizza, ch, t, j, k - 1);
                        prevY = j;
                    }
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new NumberOfWaysOfCuttingAPizza().ways(new String[]{".A","AA","A."}, 3)); // 3
        System.out.println(new NumberOfWaysOfCuttingAPizza().ways(new String[]{"A..","AAA","..."}, 3)); // 3
        System.out.println(new NumberOfWaysOfCuttingAPizza().ways(new String[]{"A..","AA.","..."}, 3)); // 1
        System.out.println(new NumberOfWaysOfCuttingAPizza().ways(new String[]{"A..","A..","..."}, 1)); // 1
    }

}
