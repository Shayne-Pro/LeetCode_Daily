class Solution {
    public String maximumBinaryString(String binary) {
        int len = binary.length();
        char[] s = binary.toCharArray();
        int j = 0;
        for (int i = 0; i < len; i++) {
            // 找第一个0
            if (s[i] == '0') {
                while (j <= i || (j < len && s[j] == '1')) {
                    // 找第二个0
                    j++;
                }
                if (j < len) {
                    /*
                     * 1. 0和0中间有1
                     * 找到第二个0，一定可以通过10->01，和第一个0组成00
                     * 再00->10
                     * 2. 00相连 00->01
                     */
                    s[i] = '1';
                    s[j] = '1';
                    s[i + 1] = '0';
                }
            }
        }
        return new String(s);
    }
}