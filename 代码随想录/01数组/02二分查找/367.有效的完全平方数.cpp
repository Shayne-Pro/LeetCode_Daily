class Solution {
 public:
  bool isPerfectSquare(int num) {
    long long left = 0;
    long long right = num;
    while (left <= right) {
      long long mid = left + (right - left) / 2;
      long long squ = mid * mid;
      if (num < squ) {
        right = mid - 1;
      } else if (num > squ) {
        left = mid + 1;
      } else if (num == squ) {
        return true;
      }
    }
    return false;
  }
};
