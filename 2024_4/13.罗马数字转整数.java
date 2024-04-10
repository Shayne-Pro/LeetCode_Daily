class Solution {
    Map<Character, Integer> map = new HashMap<Character, Integer>() {
        {
            // 添加映射
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }
    };

    public int romanToInt(String s) {
        char[] ch = s.toCharArray();
        int len = s.length();
        int ans = 0;
        for (int i = 0; i < len; i++) {
            if (i == len - 1) {
                // 最后一个数，直接加
                ans += map.get(ch[i]);
                break;
            }
            // 比较第i个数和第i+1个数的大小，若大于，则加ch[i]，否则减ch[i]
            if (map.get(ch[i]) >= map.get(ch[i + 1]))
                ans += map.get(ch[i]);
            else
                ans -= map.get(ch[i]);
        }
        return ans;
    }
}