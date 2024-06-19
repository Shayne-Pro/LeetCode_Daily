class Solution {
 public:
  bool backspaceCompare(string s, string t) {
    string finalS = processString(s, s.length());
    string finalT = processString(t, t.length());

    return finalS == finalT;
  }

 private:
  string processString(const string& str, int length) {
    string result;
    for (char ch : str) {
      if (ch != '#') {
        result.push_back(ch);
      } else {
        if (!result.empty()) {
          result.pop_back();
        }
      }
    }
    return result;
  }
};
