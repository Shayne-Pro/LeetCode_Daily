class Solution {
 public:
  int mySqrt(int x) {
    int left = 0;
    int right = x;
    while (left <= right) {
      int mid = (left + right) / 2;
      long long square =
          (long long)mid * mid;  // Cast to long long to avoid overflow
      if (x < square) {
        right = mid - 1;
      } else if (x > square) {
        left = mid + 1;
      } else {
        return mid;
      }
    }
    // Check if left is the exact square root
    if ((long long)left * left == x) {
      return left;
    }
    // Otherwise, return left - 1
    return left - 1;
  }
};
