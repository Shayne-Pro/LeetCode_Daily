class NumArray {

    int[] sums;

    public NumArray(int[] nums) {
        int len = nums.length;
        sums = new int[len + 1];
        for (int i = 0; i < len; i++) {
            // 前缀和，nums[0]到nums[i]的和，保存到sums[i+1]中
            sums[i + 1] = sums[i] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        return sums[right + 1] - sums[left];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */