package com.dzavorin.solutions.design;

import java.math.BigInteger;
import java.util.*;

public class FancySequence {

    Map<Integer, int[]> map = new HashMap<>();
    List<int[]> ops = new ArrayList<>();
    int MOD = 1000000007;
    int counter = -1;

    public void append(int val) {
        map.put(++counter, new int[]{val, ops.size()});
    }


    public void addAll(int inc) {
        ops.add(new int[]{0, inc});
    }

    public void multAll(int m) {
        ops.add(new int[]{1, m});
    }

    public int getIndex(int idx) {
        if (!map.containsKey(idx))
            return -1;

        long val = map.get(idx)[0];

        for (int i = map.get(idx)[1]; i < ops.size(); i++) {
            int[] op = ops.get(i);

            if (op[0] == 0)
                val += op[1];
            else
                val *= op[1];

            if (val > MOD)
                val %= MOD;
        }

        map.put(idx, new int[]{(int) val, ops.size()});

        return (int) val;
    }

    static class FermatLittleTheorem {
        private ArrayList<Long> lst;
        private ArrayList<Long> add;
        private ArrayList<Long> mult;
        private final long MOD = 1000000007;

        public FermatLittleTheorem() {
            lst = new ArrayList<>();
            add = new ArrayList<>();
            mult = new ArrayList<>();
            add.add(0L);
            mult.add(1L);
        }

        public void append(int val) {
            lst.add((long) val);
            int l = add.size();
            add.add(add.get(l - 1));
            mult.add(mult.get(l - 1));
        }

        public void addAll(int inc) {
            int l = add.size();
            add.set(l - 1, add.get(l - 1) + inc);
        }

        public void multAll(int m) {
            int l = add.size();
            add.set(l - 1, (add.get(l - 1) * m) % MOD);
            mult.set(l - 1, (mult.get(l - 1) * m) % MOD);
        }

        public int getIndex(int idx) {
            if (idx >= lst.size()) return -1;

            int l = add.size();
            long m = (mult.get(l - 1) * inverse(mult.get(idx))) % MOD;
            long a = (add.get(l - 1) - (add.get(idx) * m) % MOD + MOD) % MOD;
            return (int) (((lst.get(idx) * m) % MOD + a) % MOD);
        }

        long inverse(long a) {
            return pow(a, MOD - 2);
        }

        long pow(long a, long n) {
            if (n == 0) return 1;
            if (n % 2 == 0) {
                long t = pow(a, n / 2);
                return (t * t) % MOD;
            } else {
                return (pow(a, n - 1) * a) % MOD;
            }
        }
    }

    static public class FancyVovaBigBrain {
        private final int p = 1_000_000_007;
        private final BigInteger bigP = BigInteger.valueOf(p);
        private final ArrayList<BigInteger> arr = new ArrayList<>();
        private BigInteger a = BigInteger.ONE;
        private BigInteger b = BigInteger.ZERO;
        private BigInteger revA = a;

        public void append(int val) {
            if (revA == null) revA = a.modInverse(bigP);
            arr.add(BigInteger.valueOf(val).add(bigP).subtract(b).multiply(revA).mod(bigP));
        }

        public void addAll(int inc) {
            b = b.add(BigInteger.valueOf(inc)).mod(bigP);
        }

        public void multAll(int m) {
            a = a.multiply(BigInteger.valueOf(m)).mod(bigP);
            b = b.multiply(BigInteger.valueOf(m).mod(bigP));
            revA = null;
        }

        public int getIndex(int idx) {
            if (idx >= arr.size()) return -1;
            return arr.get(idx).multiply(a).add(b).mod(bigP).intValue();
        }
    }

    public static void main(String[] args) {
        FancySequence fs = new FancySequence();
        fs.append(2);
        fs.addAll(3);
        fs.append(7);
        fs.multAll(2);
        System.out.println(fs.getIndex(0));
        fs.addAll(3);
        fs.append(10);
        fs.multAll(2);
        System.out.println(fs.getIndex(0));
        System.out.println(fs.getIndex(1));
        System.out.println(fs.getIndex(2));

        FancySequence fs2 = new FancySequence();
        fs2.append(1);
        fs2.addAll(1);
        fs2.append(4);
        fs2.addAll(1);
        System.out.println(fs2.getIndex(0));
        System.out.println(fs2.getIndex(0));
        System.out.println(fs2.getIndex(0));
        System.out.println(fs2.getIndex(0));

        System.out.println(Math.pow(4, -1));

    }

}
