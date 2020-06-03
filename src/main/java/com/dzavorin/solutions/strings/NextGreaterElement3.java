package com.dzavorin.solutions.strings;

import java.util.Arrays;

public class NextGreaterElement3 {

    public int nextGreaterElement(int n) {

        char[] number = String.valueOf(n).toCharArray();
        int i, j;
        for (i = number.length - 1; i > 0; i--) {
            if (number[i - 1] < number[i]) {
                break;
            }
        }
        if (i == 0) {
            return -1;
        }
        char x = number[i - 1];
        int smallestId = i;
        for (j = i + 1; j < number.length; j++) {
            if (number[j] > x && number[j] <= number[smallestId]) {
                smallestId = j;
            }
        }

        number[i - 1] = number[smallestId];
        number[smallestId] = x;
        Arrays.sort(number, i, number.length);
        long val = Long.parseLong(new String(number));

        return (val <= Integer.MAX_VALUE) ? (int) val : -1;
    }

    // Идем с права на лево пока не найдем понижение в числах,
    // затем проверяем есть ли справа от этого числа, число меньше
    // если есть то меняемся с ним (тест 2), если нет то с тем что нашли раньше (тест 1)
    // сортируем остаток справа

    public static void main(String[] args) {
        System.out.println(new NextGreaterElement3().nextGreaterElement(230241)); // 230412
        System.out.println(new NextGreaterElement3().nextGreaterElement(230243)); // 230324
    }

}
