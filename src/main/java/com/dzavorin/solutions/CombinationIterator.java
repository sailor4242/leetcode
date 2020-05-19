package com.dzavorin.solutions;

class CombinationIterator {

    int combinationLength;
    String characters;
    int[] indexArray;
    boolean hasnext;

    public CombinationIterator(String characters, int combinationLength) {
        this.characters = characters;
        this.combinationLength = combinationLength;
        this.hasnext = preparenext();
    }

    boolean preparenext() {
        if (indexArray == null) {
            // initialize array
            indexArray = new int[combinationLength];
            for (int i = 0; i < indexArray.length; i++) {
                indexArray[i] = i;
            }
            return true;
        } else {
            // from last to first, seeking for position can increase
            for (int i = combinationLength - 1; i >= 0; i--) {
                int max = characters.length() - combinationLength + i;
                if (indexArray[i] < max) {
                    // find the one
                    indexArray[i]++;
                    // fill remaining
                    for (int j = i + 1; j < combinationLength; j++) {
                        indexArray[j] = indexArray[i] + (j - i);
                    }
                    return true;
                }
            }
            // not able to find one can increse, return false
            return false;
        }
    }

    public String next() {
        // build the string
        char[] ans = new char[combinationLength];
        for (int i = 0; i < combinationLength; i++) {
            ans[i] = characters.charAt(indexArray[i]);
        }
        // prepare for next
        this.hasnext = preparenext();
        return new String(ans);
    }

    public boolean hasNext() {
        return this.hasnext;
    }

    public static void main(String[] args) {
        CombinationIterator ci = new CombinationIterator("abc", 2);
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.hasNext());
    }

}
