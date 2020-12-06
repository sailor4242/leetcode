package com.dzavorin.solutions.dynamic;

import com.dzavorin.solutions.tree.MaximumWidthOfBinaryTree.Pair;

import java.util.*;

public class ShoppingOffers {

    // with memo

    public int shoppingOffers2(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return shoppingOffers(price, special, needs, new HashMap<>());
    }

    public int shoppingOffers(List<Integer> price, List<List<Integer>> specials, List<Integer> needs, HashMap<List<Integer>, Integer> memo) {
        Integer cachedPrice = memo.get(needs);
        if (cachedPrice != null) return cachedPrice;

        int total = plainPrice(price, needs); // calculate price if every item is bought individually
        for (List<Integer> special : specials) {
            if (isOfferValid(special, needs)) { // check if offer is valid

                List<Integer> cloned = new ArrayList<>(); // remaining needs after taking this special offer
                for (int k = 0; k < needs.size(); k++) {
                    //  update the
                    cloned.add(needs.get(k) - special.get(k)); // update remaining needs
                }
                // calculate price for rest of needs recursively
                int priceWithThisOffer = shoppingOffers(price, specials, cloned, memo) + special.get(needs.size());
                total = Math.min(priceWithThisOffer, total); // total would be minimum of total or price with this offer
            }
        }

        memo.put(needs, total);
        return total;
    }

    public int plainPrice(List<Integer> price, List<Integer> needs) {
        int cost = 0;
        for (int i = 0; i < needs.size(); i++) {
            cost += (needs.get(i) * price.get(i));
        }
        return cost;
    }

    // Offer is only valid if each item's count is <= needs
    boolean isOfferValid(List<Integer> offer, List<Integer> needs) {
        for (int i = 0; i < needs.size(); i++) {
            if (needs.get(i) < offer.get(i)) return false;
        }
        return true;
    }

    ////// Brute-force dp

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {

//         Map<Integer, int[]> map = new HashMap<>();
//         for (List<Integer> list : special) {
//             int diff = 0;
//             for (int i = 0; i < needs.size() - 1; i++) {
//                 int d = needs.get(i) - list.get(i);
//                 if (d < 0) {
//                     continue;
//                 } else {
//                     diff += d;
//                 }
//             }
//             map.put(list.hashCode(), new int[]{diff, list.get(list.size() - 1)});
//         }

//         Collections.sort(special, (a, b) -> {
//             int[] aa = map.get(a.hashCode());
//             int[] bb = map.get(b.hashCode());

//             return aa[0] - bb[0] == 0
//                 ? aa[1] - bb[1]
//                 : aa[0] - bb[0];
//         });


        return dfs(price, special, needs, new int[6], 0);
    }

    public int dfs(List<Integer> price, List<List<Integer>> specials, List<Integer> needs, int[] cur, int curPrice) {
        boolean finished = true;
        int i = 0;
        int plainPrice = 0;
        for (int n : needs) {
            if (cur[i] != n) {
                finished = false;
                plainPrice += price.get(i) * (n - cur[i]);
            }
            i++;
        }
        if (finished) return curPrice;

        int res = Integer.MAX_VALUE;
        for (List<Integer> special : specials) {

            Pair<Boolean, int[]> check = checkSpecial(special, needs, cur);
            if (check.getKey()) {
                res = Math.min(res, dfs(price, specials, needs, check.getValue(), curPrice + special.get(special.size() - 1)));
            }

        }

        res = Math.min(res, curPrice + plainPrice);

        return res;
    }

    private Pair<Boolean, int[]> checkSpecial(List<Integer> special, List<Integer> needs, int[] cur) {
        int[] next = new int[6];

        for (int i = 0; i < special.size() - 1; i++) {
            int v = cur[i] + special.get(i);
            if (needs.get(i) >= v) {
                next[i] = v;
            } else {
                return new Pair<>(false, null);
            }
        }

        return new Pair<>(true, next);
    }

    //// djikstra greedy

    public int shoppingOffers3(List<Integer> prices, List<List<Integer>> specials, List<Integer> needs) {
        if (prices == null || specials == null || needs == null || prices.size() != needs.size()) {
            return -1;
        }
        for (List<Integer> special : specials) {
            if (special.size() < 2) {
                return -1;
            }
        }
        if (prices.isEmpty()) {
            return 0;
        }
        PriorityQueue<Tuple> minHeap = new PriorityQueue<>(prices.size(), new Comparator<>() {
            @Override
            public int compare(Tuple t1, Tuple t2) {
                return Integer.compare(t1.w, t2.w);
            }
        });
        addToMinHeap(needs, minHeap, 0, prices, specials);
        int finalCost = -1;
        while (!minHeap.isEmpty()) {
            Tuple t = minHeap.poll();
            if (t.isAllZero) {
                finalCost = t.w;
                break;
            }
            addToMinHeap(t.rem, minHeap, t.w, prices, specials);
        }
        return finalCost;
    }

    private void addToMinHeap(List<Integer> needs, PriorityQueue<Tuple> minHeap, int costSoFar,
                              List<Integer> prices, List<List<Integer>> specials) {
        for (List<Integer> special : specials) {
            int cost = special.get(special.size() - 1);
            List<Integer> rem = new ArrayList<>();
            boolean isAllZero = true;
            for (int i = 0; i < special.size() - 1; i++) {
                int r = needs.get(i) - special.get(i);
                if (r < 0) {
                    rem = null;
                } else if (rem != null) {
                    rem.add(r);
                }
                if (r != 0) {
                    isAllZero = false;
                }
            }
            if (rem != null) {
                minHeap.add(new Tuple(rem, cost + costSoFar, isAllZero));
            }
        }
        List<Integer> rem = new ArrayList<>();
        int cost = 0;
        for (int i = 0; i < needs.size(); i++) {
            rem.add(0);
            cost += needs.get(i) * prices.get(i);
        }
        minHeap.add(new Tuple(rem, cost + costSoFar, true));
    }

    private static class Tuple {
        public List<Integer> rem;
        public int w;
        public boolean isAllZero;

        public Tuple(List<Integer> rem, int w, boolean isAllZero) {
            this.rem = rem;
            this.w = w;
            this.isAllZero = isAllZero;
        }
    }

}
