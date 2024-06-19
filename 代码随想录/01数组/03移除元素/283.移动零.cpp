class Solution {
 public:
  void moveZeroes(vector<int>& nums) {
    int k = 0;  // k表示新的非零元素的位置
    int n = nums.size();
    for (int i = 0; i < n; i++) {  // 将所有非零元素移到前面
      if (nums[i] != 0) {
        nums[k] = nums[i];
        k++;
      }
    }
    for (int t = k; t < n; t++) {  // 将剩余的位置填充为0
      nums[t] = 0;
    }
  }
};
