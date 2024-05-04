class Solution {
    public String finalString(String s) {
        StringBuilder ans = new StringBuilder();
        char[] ch = s.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == 'i') {
                ans.reverse();
            } else {
                ans.append(ch[i]); // 使用append方法添加字符到StringBuilder
            }
        }
        return ans.toString(); // 最后将StringBuilder转换为String返回
    }
}