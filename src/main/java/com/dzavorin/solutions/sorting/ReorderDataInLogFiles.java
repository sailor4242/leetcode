package com.dzavorin.solutions.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReorderDataInLogFiles {

    public String[] reorderLogFiles(String[] logs) {
        List<Log> list = new ArrayList<>();
        for (String str : logs) {
            for (int i = 0 ; i < str.length(); i++) {
                if (str.charAt(i) == ' ') {
                    String id = str.substring(0, i);
                    String content = str.substring(i + 1);
                    Boolean isLetter = !Character.isDigit(content.charAt(0));
                    list.add(new Log(id, content, isLetter, str));
                    break;
                }
            }
        }

        Collections.sort(list, (log1, log2) -> {
            if (log1.isLetter && log2.isLetter) {
                if (log1.content.equals(log2.content)) {
                    return log1.id.compareTo(log2.id);
                } else {
                    return log1.content.compareTo(log2.content);
                }
            } else if (log1.isLetter && !log2.isLetter) {
                return -1;
            } else if (!log1.isLetter && log2.isLetter) {
                return 1;
            } else {
                return 0;
            }
        });

        int i = 0;
        for (Log log : list) {
            logs[i++] = log.str;
        }
        return logs;
    }

    static class Log {
        String content;
        String id;
        Boolean isLetter;
        String str;

        Log(String id, String content, Boolean isLetter, String str) {
            this.content = content;
            this.id = id;
            this.isLetter = isLetter;
            this.str = str;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new ReorderDataInLogFiles()
                .reorderLogFiles(new String[]{"dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"})));
    }

}
