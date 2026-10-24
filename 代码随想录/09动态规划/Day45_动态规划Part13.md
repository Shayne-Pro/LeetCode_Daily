# Day45 · 动态规划Part13

> 作者：[夜雨翦春韭（Shayne-Pro）](https://blog.csdn.net/weixin_43724673) · 原文发布：2024-10-18
> CSDN 原文：[《【代码随想录Day45】动态规划Part13》](https://blog.csdn.net/weixin_43724673/article/details/143061860) · [返回目录](../README.md)

## 647. 回文子串

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0647.%E5%9B%9E%E6%96%87%E5%AD%90%E4%B8%B2.html)
视频讲解：[动态规划，字符串性质决定了 DP 数组的定义 | LeetCode：647.回文子串\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV17G4y1y7z9/)

```java
class Solution {
    public int countSubstrings(String s) {
        // 将字符串转换为字符数组，便于后续处理
        char[] chars = s.toCharArray();
        int len = chars.length;  // 获取字符串的长度

        // 创建一个二维布尔数组，用于记录子串是否为回文
        boolean[][] dp = new boolean[len][len];
        int result = 0;  // 用于计数回文子串的数量

        // 从字符串的最后一个字符开始向前遍历
        for (int i = len - 1; i >= 0; i--) {
            // 从当前字符位置向后遍历
            for (int j = i; j < len; j++) {
                // 如果两个字符相等
                if (chars[i] == chars[j]) {
                    // 情况一：字符间隔为0（相同的字符）或情况二：字符间隔为1（两个相同字符组成的子串）
                    if (j - i <= 1) {
                        result++;  // 计数加一
                        dp[i][j] = true;  // 标记为回文
                    }
                    // 情况三：判断子串中间部分是否为回文
                    else if (dp[i + 1][j - 1]) {
                        result++;  // 计数加一
                        dp[i][j] = true;  // 标记为回文
                    }
                }
            }
        }
        return result;  // 返回回文子串的总数量
    }
}
```

## 516.最长回文子序列

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0516.%E6%9C%80%E9%95%BF%E5%9B%9E%E6%96%87%E5%AD%90%E5%BA%8F%E5%88%97.html)
视频讲解：[动态规划再显神通，LeetCode：516.最长回文子序列\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1d8411K7W6/)

```java
class Solution {
    public int longestPalindromeSubseq(String s) {
        // 将字符串转换为字符数组
        char[] chars = s.toCharArray();
        int len = chars.length;

        // 创建一个二维数组 dp，用于存储子问题的解
        int[][] dp = new int[len][len];

        // 初始化 dp 数组，所有单字符的回文子序列长度为 1
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;  // 单个字符的回文子序列长度为 1
        }

        // 从后向前遍历字符串
        for (int i = len - 1; i >= 0; i--) {
            // 从 i+1 开始遍历，确保 j 始终大于 i
            for (int j = i + 1; j < len; j++) {
                // 如果当前字符相同，则找到一个回文子序列
                if (chars[i] == chars[j]) {
                    // 当前字符相同，回文长度为 dp[i + 1][j - 1] + 2
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    // 如果字符不同，取去掉当前字符后得到的最大回文子序列长度
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }

        // 返回从字符串开始到结束的最长回文子序列长度
        return dp[0][len - 1];
    }
}
```

## 动态规划总结篇

总结：[代码随想录](https://programmercarl.com/%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92%E6%80%BB%E7%BB%93%E7%AF%87.html#%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92%E5%9F%BA%E7%A1%80)
