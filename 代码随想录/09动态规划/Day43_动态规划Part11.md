# Day43 · 动态规划Part11

> 作者：[夜雨翦春韭（Shayne-Pro）](https://blog.csdn.net/weixin_43724673) · 原文发布：2024-10-15
> CSDN 原文：[《【代码随想录Day43】动态规划Part11》](https://blog.csdn.net/weixin_43724673/article/details/142964077) · [返回目录](../README.md)

## 1143.最长公共子序列

题目链接/文章讲解：[代码随想录](https://programmercarl.com/1143.%E6%9C%80%E9%95%BF%E5%85%AC%E5%85%B1%E5%AD%90%E5%BA%8F%E5%88%97.html)
视频讲解：[动态规划子序列问题经典题目 | LeetCode：1143.最长公共子序列\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1ye4y1L7CQ)

```java
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        // 将输入字符串转换为字符数组
        char[] nums1 = text1.toCharArray();
        char[] nums2 = text2.toCharArray();

        // 创建一个二维数组 dp，用于存储子问题的解
        // dp[i][j] 表示 text1 的前 i 个字符与 text2 的前 j 个字符的最长公共子序列长度
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];

        // 遍历字符数组，用 i 和 j 作为索引
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                // 如果当前字符相等，则最长公共子序列长度加1
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 如果不相等，取之前的最大值
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        // 返回最长公共子序列的长度
        return dp[nums1.length][nums2.length];
    }
}
```

## 1035.不相交的线

题目链接/文章讲解：[代码随想录](https://programmercarl.com/1035.%E4%B8%8D%E7%9B%B8%E4%BA%A4%E7%9A%84%E7%BA%BF.html)
视频讲解：[动态规划之子序列问题，换汤不换药 | LeetCode：1035.不相交的线\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1h84y1x7MP)

```java
class Solution {
    // 方法：计算两个数组中未交叉的最大连线数
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        // 创建一个二维数组 dp，用于存储子问题的解
        // dp[i][j] 表示 nums1 的前 i 个元素和 nums2 的前 j 个元素的最大未交叉线数
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];

        // 遍历 nums1 和 nums2 的每一个元素
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                // 如果 nums1 的当前元素与 nums2 的当前元素相等
                if (nums1[i - 1] == nums2[j - 1]) {
                    // 则可以在当前元素之间画一条线，增加一条连线
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 如果不相等，则取前一个状态的最大值
                    // dp[i][j] = max(dp[i-1][j], dp[i][j-1])
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        // 返回 dp 数组中最后一个元素，代表 nums1 和 nums2 的最大未交叉线数
        return dp[nums1.length][nums2.length];
    }
}
```

## 53. 最大子序和

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0053.%E6%9C%80%E5%A4%A7%E5%AD%90%E5%BA%8F%E5%92%8C%EF%BC%88%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92%EF%BC%89.html)
视频讲解：[看起来复杂，其实是简单动态规划 | LeetCode：53.最大子序和\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV19V4y1F7b5)

```java
class Solution {
    public int maxSubArray(int[] nums) {
        // 初始化dp数组和maxSum变量
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int maxSum = dp[0]; // 初始最大值为第一个元素

        for (int i = 1; i < nums.length; i++) {
            // 计算以 nums[i] 结尾的最大子数组和
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            // 更新最大子数组和
            maxSum = Math.max(maxSum, dp[i]);
        }

        return maxSum;
    }
}
```

## 392.判断子序列

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0392.%E5%88%A4%E6%96%AD%E5%AD%90%E5%BA%8F%E5%88%97.html)
视频讲解：[动态规划，用相似思路解决复杂问题 | LeetCode：392.判断子序列\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1tv4y1B7ym/)

```java
class Solution {
    // 定义一个方法，检查字符串 s 是否为字符串 t 的子序列
    public boolean isSubsequence(String s, String t) {
        // 将字符串 s 和 t 转换为字符数组
        char[] nums1 = s.toCharArray();
        char[] nums2 = t.toCharArray();

        // 创建一个二维数组 dp，用于动态规划，行数为 nums1.length + 1，列数为 nums2.length + 1
        // dp[i][j] 表示 nums1 的前 i 个字符与 nums2 的前 j 个字符的最长公共子序列的长度
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];

        // 遍历 nums1 和 nums2 的每个字符
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {

                // 如果当前字符相等，则最长公共子序列的长度加一
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1; // 继承之前的值并加一
                } else {
                    // 如果不相等，取前一行或前一列的最大值
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        // 如果 dp[nums1.length][nums2.length] 等于 nums1.length，说明 s 是 t 的子序列
        if (dp[nums1.length][nums2.length] == nums1.length) {
            return true; // 是子序列
        } else {
            return false; // 不是子序列
        }
    }
}
```
