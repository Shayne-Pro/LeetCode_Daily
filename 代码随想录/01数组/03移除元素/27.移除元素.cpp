class Solution {
 public:
  int removeElement(std::vector<int>& nums, int val) {
    int k = 0;  // k 用于记录不等于 val 的元素数量
    for (int i = 0; i < nums.size(); ++i) {
      if (nums[i] != val) {
        nums[k] = nums[i];  // 将不等于 val 的元素移动到数组的前面
        k++;                // 增加不等于 val 的元素计数
      }
    }
    return k;  // 返回不等于 val 的元素数量
  }
};
