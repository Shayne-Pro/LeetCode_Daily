class Solution {
    // 二分查找
    public int maximumCount(int[] nums) {
        int pos1 = lowerBound(nums, 0);
        int pos2 = lowerBound(nums, 1);
        return pos1 > (nums.length - pos2) ? pos1 : (nums.length - pos2);
    }

    public int lowerBound(int[] nums, int val) {
        int left = 0;
        int rigth = nums.length;
        while (left < rigth) {
            int mid = (left + rigth) / 2;
            if (nums[mid] < val)
                left = mid + 1;
            else
                rigth = mid;
        }
        return left;
    }
}