class Solution {
 public:
  int temperatureTrend(vector<int>& temperatureA, vector<int>& temperatureB) {
    int n = temperatureA.size();
    int same = 0;
    int ans = 0;
    for (int i = 0; i < n - 1; i++) {
      int a = temperatureA[i + 1] - temperatureA[i];
      int b = temperatureB[i + 1] - temperatureB[i];
      if ((a > 0 && b > 0) || (a == 0 && b == 0) || (a < 0 && b < 0)) {
        same++;
        ans = max(ans, same);
      } else {
        same = 0;
      }
    }
    return ans;
  }
};