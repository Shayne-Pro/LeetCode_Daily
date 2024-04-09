class Solution {
	public int[] twoSum(int[] nums, int target) {
		for(int i = 0; i < nums.length; i++) {
			for(int j = i + 1; j < nums.length; j++) { // j从i+1开始
				if(nums[i] + nums[j] == target) {
					return new int[] {
						i, j
					};
				}
			}
		}
		return new int[0];
	}
}