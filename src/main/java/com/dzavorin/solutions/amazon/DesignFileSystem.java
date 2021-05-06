package com.dzavorin.solutions.amazon;

import java.util.HashMap;
import java.util.Map;

public class DesignFileSystem {

    Path root;

    public DesignFileSystem() {
        root = new Path();
    }

    public boolean createPath(String path, int value) {
        if ("/".equals(path) || "".equals(path)) return false;
        String[] dirs = path.split("/");
        Path cur = root;
        for (int i = 1; i < dirs.length - 1; i++) {
            String dir = dirs[i];
            if (!cur.dirs.containsKey(dir)) {
                return false;
            }
            cur = cur.dirs.get(dir);
        }
        String lastDir = dirs[dirs.length - 1];
        if (cur.dirs.containsKey(lastDir)) return false;

        cur.dirs.put(lastDir, new Path(value));

        return true;
    }

    public int get(String path) {
        if ("/".equals(path) || "".equals(path)) return -1;
        String[] dirs = path.split("/");
        Path cur = root;
        for (int i = 1; i < dirs.length; i++) {
            String dir = dirs[i];
            cur = cur.dirs.get(dir);
            if (cur == null) return -1;
        }
        return cur.value;
    }

    static class Path {
        Map<String, Path> dirs = new HashMap<>();
        Integer value = null;

        Path() {

        }

        Path(int value) {
            this.value = value;
        }
    }
}
