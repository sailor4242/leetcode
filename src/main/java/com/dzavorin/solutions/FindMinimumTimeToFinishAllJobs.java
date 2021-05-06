package com.dzavorin.solutions;

import java.util.Arrays;

public class FindMinimumTimeToFinishAllJobs {

    int min = Integer.MAX_VALUE;

    public int minimumTimeRequired(int[] jobs, int k) {
        //sort the jobs so that the worker with the maximum workload will be taken first
        // this will help to terminate early
        Arrays.sort(jobs); // sorting is an optimization to consider
        // recurse from the worker who is having maximum work load
        recurse(jobs, jobs.length - 1, new int[k]);
        // return the minimum working time
        return min;
    }

    protected void recurse(int[] jobs, int index, int[] sum) {

        // get the maximum total from the sum
        int max = getMax(sum);
        // if the sum of work time exceeds then return
        if (max >= min)
            return;

        // if the index is going negative, update the minimum maximum from the sum
        if (index < 0) {
            min = Math.min(min, max);
            return;
        }

        for (int i = 0; i < sum.length; i++) {
            //if the sum is equal then continue and do not recurse
            // recurse only for distinct sum
            if (i > 0 && sum[i] == sum[i - 1])
                continue;
            // choose
            sum[i] += jobs[index];
            // explore further
            recurse(jobs, index - 1, sum);
            //unchoose
            sum[i] -= jobs[index];
        }
    }

    protected int getMax(int[] sum) {
        int max = Integer.MIN_VALUE;
        for (int s : sum) {
            max = Math.max(max, s);
        }
        return max;
    }

}
