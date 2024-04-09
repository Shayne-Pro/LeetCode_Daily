class Solution {
    public int minimumSum(int[] nums) {
        int n = nums.length;
        int min = 1000;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] < nums[j] && nums[k] < nums[j]) {
                        min = Math.min(min, nums[i] + nums[j] + nums[k]);
                    }
                }
            }
        }
        // 如果没有找到满足条件的三元组，则min=1000，返回-1
        return min < 1000 ? min : -1;
    }
}