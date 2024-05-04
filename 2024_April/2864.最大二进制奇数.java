class Solution {
    public String maximumOddBinaryNumber(String s) {
        int len = s.length();
        int cnt = 0;
        for (char c : s.toCharArray()) {
            if (c - '0' == 1)
                cnt++;
        }
        String ans = "1".repeat(cnt - 1) + "0".repeat(len - cnt) + "1";
        return ans;
    }
}