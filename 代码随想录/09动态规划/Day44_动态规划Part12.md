# Day44 · 动态规划Part12

> 作者：[夜雨翦春韭（Shayne-Pro）](https://blog.csdn.net/weixin_43724673) · 原文发布：2024-10-16
> CSDN 原文：[《【代码随想录Day44】动态规划Part12》](https://blog.csdn.net/weixin_43724673/article/details/142991876) · [返回目录](../README.md)

## 115.不同的子序列

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0115.%E4%B8%8D%E5%90%8C%E7%9A%84%E5%AD%90%E5%BA%8F%E5%88%97.html#%E7%AE%97%E6%B3%95%E5%85%AC%E5%BC%80%E8%AF%BE)
视频讲解：[动态规划之子序列，为了编辑距离做铺垫 | LeetCode：115.不同的子序列\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1fG4y1m75Q/)

```java
class Solution {
    public int numDistinct(String s, String t) {
        // 将字符串s和t转换为字符数组
        char[] nums1 = s.toCharArray();
        char[] nums2 = t.toCharArray();

        // 创建一个二维数组dp，其中dp[i][j]表示s的前i个字符中包含t的前j个字符的不同子序列的数量
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];

        // 初始化第一列，dp[i][0] = 1表示空字符串t可以由s的前i个字符形成一种子序列
        for (int i = 0; i <= nums1.length; i++) {
            dp[i][0] = 1;
        }

        // 填充dp数组
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                // 如果s的第i个字符等于t的第j个字符
                if (nums1[i - 1] == nums2[j - 1]) {
                    // dp[i][j]为dp[i-1][j-1]（包括当前字符的情况）和dp[i-1][j]（不包括当前字符的情况）之和
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    // 如果不相等，dp[i][j]只能取dp[i-1][j]的值（不包括当前字符s[i-1]）
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // 返回s的所有字符中包含t的不同子序列的数量
        return dp[nums1.length][nums2.length];
    }
}
```

## 583. 两个字符串的删除操作

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0583.%E4%B8%A4%E4%B8%AA%E5%AD%97%E7%AC%A6%E4%B8%B2%E7%9A%84%E5%88%A0%E9%99%A4%E6%93%8D%E4%BD%9C.html)
视频讲解：[动态规划之子序列，还是为了编辑距离做铺垫 | LeetCode：583.两个字符串的删除操作\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1we4y157wB/)

```java
class Solution {
    // 方法 minDistance 计算将一个字符串转换为另一个字符串所需的最小编辑距离
    public int minDistance(String word1, String word2) {
        // 获取两个字符串的长度
        int len1 = word1.length();
        int len2 = word2.length();

        // 创建一个二维数组 dp，用于存储子问题的解
        // dp[i][j] 表示 word1 的前 i 个字符与 word2 的前 j 个字符的最长公共子序列的长度
        int[][] dp = new int[len1 + 1][len2 + 1];

        // 遍历 word1 的每个字符
        for (int i = 1; i <= len1; i++) {
            // 遍历 word2 的每个字符
            for (int j = 1; j <= len2; j++) {
                // 如果当前字符相同，最长公共子序列长度加 1
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 如果当前字符不同，取左边或上边的较大值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // 计算最小编辑距离
        // 编辑距离等于两个字符串的长度之和减去它们的最长公共子序列的长度的两倍
        return len1 + len2 - dp[len1][len2] * 2;
    }
}
```

## 72. 编辑距离

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0072.%E7%BC%96%E8%BE%91%E8%B7%9D%E7%A6%BB.html)
视频讲解：[动态规划终极绝杀！ LeetCode：72.编辑距离\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1qv4y1q78f/)

```java
class Solution {
    public int minDistance(String word1, String word2) {
        // 获取两个字符串的长度
        int len1 = word1.length();
        int len2 = word2.length();

        // 创建动态规划表，dp[i][j]表示将word1的前i个字符转换为word2的前j个字符所需的最小操作数
        int[][] dp = new int[len1 + 1][len2 + 1];

        // 初始化第一列，表示将word1的前i个字符转换为空字符串所需的操作数（即删除操作）
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = i;
        }

        // 初始化第一行，表示将空字符串转换为word2的前j个字符所需的操作数（即插入操作）
        for (int j = 1; j <= len2; j++) {
            dp[0][j] = j;
        }

        // 填充动态规划表
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                // 如果当前字符相同，则不需要额外操作，继承左上角的值
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 如果字符不同，计算三种操作的最小值
                    // 1. 替换操作：dp[i-1][j-1] + 1
                    // 2. 删除操作：dp[i-1][j] + 1
                    // 3. 插入操作：dp[i][j-1] + 1
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 1,
                                        Math.min(dp[i - 1][j] + 1,
                                                 dp[i][j - 1] + 1));
                }
            }
        }
        // 返回将word1转换为word2所需的最小操作数
        return dp[len1][len2];
    }
}
```

## 编辑距离总结篇

文章讲解：[代码随想录](https://programmercarl.com/%E4%B8%BA%E4%BA%86%E7%BB%9D%E6%9D%80%E7%BC%96%E8%BE%91%E8%B7%9D%E7%A6%BB%EF%BC%8C%E5%8D%A1%E5%B0%94%E5%81%9A%E4%BA%86%E4%B8%89%E6%AD%A5%E9%93%BA%E5%9E%AB.html)
