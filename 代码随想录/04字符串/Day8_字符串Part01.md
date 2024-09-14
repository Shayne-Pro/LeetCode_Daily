## 344. 反转字符串
题目链接/文章讲解/视频讲解：[反转字符串](https://programmercarl.com/0344.%E5%8F%8D%E8%BD%AC%E5%AD%97%E7%AC%A6%E4%B8%B2.html)

### 双指针

```java
class Solution {
    public void reverseString(char[] s) {
        int len = s.length;
        int left = 0;
        int right = len - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }
}
```

## 541. 反转字符串 II
题目链接/文章讲解/视频讲解：[反转字符串 II](https://programmercarl.com/0541.%E5%8F%8D%E8%BD%AC%E5%AD%97%E7%AC%A6%E4%B8%B2II.html)

```java
class Solution {
    public String reverseStr(String s, int k) {
        char[] ch = s.toCharArray();
        int len = ch.length;

        for (int i = 0; i < len; i += 2 * k) {
            int start = i;
            int end = Math.min(i + k - 1, len - 1); // 确保 end 不会超出数组范围
            while (start < end) {
                char temp = ch[start];
                ch[start] = ch[end];
                ch[end] = temp;
                start++;
                end--;
            }
        }

        return new String(ch);
    }
}
```

## 卡码网：54. 替换数字
题目链接/文章讲解：[卡码网：54. 替换数字](https://programmercarl.com/kama54.%E6%9B%BF%E6%8D%A2%E6%95%B0%E5%AD%97.html)

### 双指针：从后向前替换数字字符
也就是双指针法，过程如下：`i` 指向新长度的末尾，`j` 指向旧长度的末尾.

![替换数字示意图](https://cdn.nlark.com/yuque/0/2024/png/32698236/1725550684247-c7ea33dc-6630-4f56-b02e-262ddc339275.png)

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int len = s.length();
        // 计算新字符串长度
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                len += 5;
            }
        }
        // 将旧字符串复制到新数组中
        char[] ret = new char[len];
        for (int i = 0; i < s.length(); i++) {
            ret[i] = s.charAt(i);
        }
        // 遇到数字则开始填充 "number"
        for (int i = s.length() - 1, j = len - 1; i >= 0; i--) {
            if ('0' <= ret[i] && ret[i] <= '9') {
                ret[j--] = 'r';
                ret[j--] = 'e';
                ret[j--] = 'b';
                ret[j--] = 'm';
                ret[j--] = 'u';
                ret[j--] = 'n';
            } else {
                ret[j--] = ret[i];
            }
        }
        System.out.println(ret);
    }
}
```