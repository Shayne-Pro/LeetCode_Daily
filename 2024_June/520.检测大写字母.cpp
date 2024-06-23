class Solution {
 public:
  bool detectCapitalUse(string word) {
    int n = word.size();
    if (islower(word[0])) {  // 第一个小写，后面全小写
      for (int i = 1; i < n; i++) {
        if (!islower(word[i])) {
          return false;
        }
      }
    } else {  // 第一个大写，后面全大写或全小写
      bool allUpper = true;
      bool allLower = true;
      for (int i = 1; i < n; i++) {
        if (islower(word[i])) {
          allUpper = false;
        } else {
          allLower = false;
        }
      }
      if (!allUpper && !allLower) {
        return false;
      }
    }
    return true;
  }
};
