package com.dzavorin.solutions.strings;

import java.util.LinkedList;

public class SimplifyPath {

    public String simplifyPath(String path) {
        String[] tokens = path.split("/");
        LinkedList<String> list = new LinkedList<>();

        for (String t : tokens) {
            if ("..".equals(t)) {
                if (!list.isEmpty()) {
                    list.removeLast();
                }
            } else if (!("".equals(t) || ".".equals(t))){
                list.add(t);
            }
        }

        StringBuilder res = new StringBuilder("/");

        while (!list.isEmpty()) {
            res.append(list.removeFirst());
            if (!list.isEmpty()) {
                res.append("/");
            }
        }

        return res.toString();
    }

}
