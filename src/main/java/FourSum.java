import java.util.*;

public class FourSum {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        kSum(4, nums, target, 0, new LinkedList<>());
        return res;
    }

    public void kSum(int k, int[] nums, int target, int idx, LinkedList<Integer> list) {
        if (k == 2) {
            int lo = idx;
            int hi = nums.length - 1;
            while (lo < hi) {
                int sum = nums[hi] + nums[lo];

                if (target == sum) {
                    var listNext = new ArrayList<>(list);
                    listNext.add(nums[lo]);
                    listNext.add(nums[hi]);
                    res.add(listNext);
                    while (lo < hi && nums[lo] == nums[lo + 1]) lo++;
                    while (lo < hi && nums[hi] == nums[hi - 1]) hi--;
                    lo++;
                    hi--;
                } else if (sum > target) {
                    hi--;
                } else {
                    lo++;
                }
            }
        } else {
            for (int i = idx; i < nums.length; i++) {

                if (i > idx && nums[i] == nums[i - 1]) continue;

                list.add(nums[i]);
                kSum(k - 1, nums, target - nums[i], i + 1, list);
                list.removeLast();
            }
        }

    }

    /////////// HashMap

    public List<List<Integer>> fourSum2(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 0, 4);
    }

    public List<List<Integer>> kSum(int[] nums, int target, int start, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (start == nums.length || nums[start] * k > target || target > nums[nums.length - 1] * k)
            return res;
        if (k == 2)
            return twoSum(nums, target, start);
        for (int i = start; i < nums.length; ++i)
            if (i == start || nums[i - 1] != nums[i])
                for (var set : kSum(nums, target - nums[i], i + 1, k - 1)) {
                    res.add(new ArrayList<>(Arrays.asList(nums[i])));
                    res.get(res.size() - 1).addAll(set);
                }
        return res;
    }

    public List<List<Integer>> twoSum(int[] nums, int target, int start) {
        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> s = new HashSet<>();
        for (int i = start; i < nums.length; ++i) {
            if (res.isEmpty() || res.get(res.size() - 1).get(1) != nums[i])
                if (s.contains(target - nums[i]))
                    res.add(Arrays.asList(target - nums[i], nums[i]));
            s.add(nums[i]);
        }
        return res;
    }

}
