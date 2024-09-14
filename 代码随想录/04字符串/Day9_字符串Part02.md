## 151. 翻转字符串里的单词

解题思路如下：

1. 移除多余空格
2. 将整个字符串反转
3. 将每个单词反转

举个例子，源字符串为：`"the sky is blue "`

- 移除多余空格 : `"the sky is blue"`
- 字符串反转：`"eulb si yks eht"`
- 单词反转：`"blue is sky the"`

题目链接/文章讲解/视频讲解：[代码随想录](https://programmercarl.com/0151.%E7%BF%BB%E8%BD%AC%E5%AD%97%E7%AC%A6%E4%B8%B2%E9%87%8C%E7%9A%84%E5%8D%95%E8%AF%8D.html)

```java
public class Solution {
    public String reverseWords(String s) {
        // 移除多余空格
        StringBuilder sb = removeExtraSpaces(s);
        // 反转整个字符串
        reverseString(sb, 0, sb.length() - 1);
        // 反转每个单词
        reverseEachWord(sb);
        return sb.toString();
    }

    private StringBuilder removeExtraSpaces(String s) {
        int start = 0, end = s.length() - 1;
        // 移除前导空格
        while (start <= end && s.charAt(start) == ' ') start++;
        // 移除尾随空格
        while (start <= end && s.charAt(end) == ' ') end--;
        StringBuilder sb = new StringBuilder();
        while (start <= end) {
            char c = s.charAt(start);
            if (c != ' ' || sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }
            start++;
        }
        return sb;
    }

    private void reverseString(StringBuilder sb, int start, int end) {
        while (start < end) {
            char temp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, temp);
            start++;
            end--;
        }
    }

    private void reverseEachWord(StringBuilder sb) {
        int start = 0, end = 1, n = sb.length();
        while (start < n) {
            while (end < n && sb.charAt(end) != ' ') end++;
            reverseString(sb, start, end - 1);
            start = end + 1;
            end = start + 1;
        }
    }
}
```

## 卡码网：55.右旋转字符串

题目链接/文章讲解：[卡码网：55.右旋转字符串](https://programmercarl.com/kama55.%E5%8F%B3%E6%97%8B%E5%AD%97%E7%AC%A6%E4%B8%B2.html)

![右旋转字符串示例](https://cdn.nlark.com/yuque/0/2024/png/32698236/1725692332610-33221cd1-75f8-44b6-b42a-1735facb17db.png)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        String s = in.nextLine();

        int len = s.length();  //获取字符串长度
        char[] chars = s.toCharArray();
        reverseString(chars, 0, len - 1);  //反转整个字符串
        reverseString(chars, 0, n - 1);  //反转前一段字符串，此时的字符串首尾尾是0,n - 1
        reverseString(chars, n, len - 1);  //反转后一段字符串，此时的字符串首尾尾是n,len - 1
          
        System.out.println(chars);
    }

    public static void reverseString(char[] ch, int start, int end) {
        //异或法反转字符串，参照题目 344.反转字符串的解释
        while (start < end) {
            char temp;
            temp = ch[start];
            ch[start] = ch[end];
            ch[end] = temp;
            start++;
            end--;
        }
    }
}
```
