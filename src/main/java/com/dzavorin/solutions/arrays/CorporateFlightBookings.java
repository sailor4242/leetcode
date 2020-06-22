package com.dzavorin.solutions.arrays;

import java.util.Arrays;

public class CorporateFlightBookings {

//    To avoid using loop from i to j in every booking, we only need to keep track of the begin and the end flight in each booking.
//
//    At the first loop, ans[i] means we add ans[i] to all flights from i to n. For example, [5,0,0,0] with ans[0] = 5 means all flights start from 0 will be added by 5, the result will be [5,5,5,5]. We then subtract 5 from position j, where we want to stop. In the case here, i = 1, j = 3 will be [5,0,0,-5] and the end result will be [5,5,5,0].
//
//    Note that we don't calculate the entire array at first. We just mark the begin and end in the first loop and use another loop at the end to calculate the result.

    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] answer = new int[n];

        for (int[] booking : bookings) {
            int i = booking[0];
            int j = booking[1];
            int k = booking[2];

            answer[i - 1] += k;
            if (j < n) {
                answer[j] -= k;
            }
        }

        for (int i = 1; i < n; i++) {
            answer[i] = answer[i] + answer[i - 1];
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new CorporateFlightBookings()
                .corpFlightBookings(new int[][]{{1, 2, 10}, {2, 3, 20}, {2, 5, 25}}, 5)));
    }
}
