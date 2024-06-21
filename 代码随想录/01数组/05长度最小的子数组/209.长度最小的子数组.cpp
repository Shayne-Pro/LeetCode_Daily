class Solution {
 public:
  int minSubArrayLen(int target, vector<int>& nums) {
    int i = 0, j = 0;
    int n = nums.size();
    int ans = INT_MAX;
    int sum = 0;
    while (j < n) {
      sum += nums[j++];
      while (sum >= target) {
        ans = min(ans, j - i);
        sum -= nums[i++];
      }
    }
    return ans == INT_MAX ? 0 : ans;
  }
};
