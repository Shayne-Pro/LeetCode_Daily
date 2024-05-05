class Solution {
    public int[] decrypt(int[] code, int k) {
        int len = code.length;
        int[] ans = new int[len];
        int[] new_code = new int[len * 2];
        for (int m = 0; m < len; m++) {
            new_code[m] = new_code[m + len] = code[m];
        }
        if (k == 0) {
            return ans;
        } else if (k > 0) {
            for (int j = 0; j < len; j++) {
                for (int t = 1; t <= k; t++) {
                    ans[j] += new_code[j + t];
                }
            }
        } else if (k < 0) {
            for (int n = 0; n < len; n++) {
                for (int t = -1; t >= k; t--) {
                    ans[n] += new_code[n + len + t];
                }
            }
        }
        return ans;
    }
}