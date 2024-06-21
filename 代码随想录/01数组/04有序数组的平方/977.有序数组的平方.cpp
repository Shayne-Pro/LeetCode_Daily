class Solution {
 public:
  vector<int> sortedSquares(vector<int>& nums) {
    int n = nums.size();
    int k = n - 1;
    int left = 0;
    int right = n - 1;
    vector<int> ans(n);
    while (left <= right) {
      if (nums[left] + nums[right] <= 0) {
        ans[k] = nums[left] * nums[left];
        left++;
      } else {
        ans[k] = nums[right] * nums[right];
        right--;
      }
      k--;
    }
    return ans;
  }
};