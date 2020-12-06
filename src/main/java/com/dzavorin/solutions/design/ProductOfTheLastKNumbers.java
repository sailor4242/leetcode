package com.dzavorin.solutions.design;

import java.util.ArrayList;
import java.util.List;

public class ProductOfTheLastKNumbers {

    List<Integer> list = new ArrayList<>();

    public ProductOfTheLastKNumbers() {

    }

    public void add(int num) {
        if (num == 0) {
            list = new ArrayList<>();
        } else if (list.size() == 0) {
            list.add(num);
        } else {
            list.add(num * list.get(list.size() - 1));
        }

    }

    public int getProduct(int k) {
        int len = list.size();
        if (k < len) {
            return (list.get(len - 1) / list.get(len - 1 - k));
        } else if (k == len) {
            return list.get(len - 1);
        }
        return 0;
    }

    public static void main(String[] args) {
        ProductOfTheLastKNumbers p = new ProductOfTheLastKNumbers();
        p.add(2);
        p.add(3);
        p.add(4);
        p.add(5);
        p.getProduct(2);
        p.getProduct(3);
    }
}
