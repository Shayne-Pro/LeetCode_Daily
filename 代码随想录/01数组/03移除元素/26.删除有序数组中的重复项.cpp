class Solution {
 public:
  int removeDuplicates(vector<int>& nums) {
    int i = 1;
    int k = 0;
    int n = nums.size();
    if (nums.empty()) return 0;
    while (i < n) {
      if (nums[k] != nums[i]) {
        k++;
        nums[k] = nums[i];
      }
      i++;
    }
    return k + 1;
  }
};
