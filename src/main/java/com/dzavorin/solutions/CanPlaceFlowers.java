package com.dzavorin.solutions;

public class CanPlaceFlowers {

    public boolean canPlaceFlowers2(int[] flowerbed, int n) {
        int i = 0;
        int count = 0;
        int len = flowerbed.length;
        while (i < len && count < n) {
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) &&
                    (i == len - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i++] = 1;
                count++;
            }
            i++;
        }
        return count >= n;
    }

    ////

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed.length == 0) return false;
        if (flowerbed.length == 1 && flowerbed[0] == 0) return true;
        if (flowerbed.length == 2 && flowerbed[0] == 0 && flowerbed[1] == 0 && n == 1) return true;

        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0) {
                int c = 0;
                int j = i;
                for (; j < flowerbed.length; j++) {
                    if (flowerbed[j] == 1) {
                        j--;
                        break;
                    } else {
                        c++;
                    }
                }
                if (c == flowerbed.length) {
                    return n <= (c % 2 == 0 ? c / 2 : c / 2 + 1);
                }
                if ((j == flowerbed.length || i == 0) && c >= 2) {
                    n -= c / 2;
                } else if (c >= 3) {
                    n -= c % 2 == 0 ? c / 2 - 1 : c / 2;
                }
                i = j;

            }
        }
        return n <= 0;
    }

    public static void main(String[] args) {
        System.out.println(new CanPlaceFlowers().canPlaceFlowers(new int[]{1, 0, 0, 0, 0, 1}, 2));
        System.out.println(new CanPlaceFlowers().canPlaceFlowers(new int[]{0}, 1));
        System.out.println(new CanPlaceFlowers().canPlaceFlowers(new int[]{0, 0, 0}, 2));
    }

}
