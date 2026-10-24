# Day41 · 动态规划Part10

> 作者：[夜雨翦春韭（Shayne-Pro）](https://blog.csdn.net/weixin_43724673) · 原文发布：2024-10-13
> CSDN 原文：[《【代码随想录Day41】动态规划Part10》](https://blog.csdn.net/weixin_43724673/article/details/142899180) · [返回目录](../README.md)

## 300.最长递增子序列

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0300.%E6%9C%80%E9%95%BF%E4%B8%8A%E5%8D%87%E5%AD%90%E5%BA%8F%E5%88%97.html)
视频讲解：[动态规划之子序列问题，元素不连续！| LeetCode：300.最长递增子序列\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1ng411J7xP)

```java
public int lengthOfLIS(int[] nums) {
    // 获取数组的长度
    int n = nums.length;

    // 创建一个用于存储以每个元素结尾的最长递增子序列的长度的数组
    int[] dp = new int[n + 1];

    // 初始化dp数组，默认每个位置的最长递增子序列长度为1
    for (int j = 0; j <= n; j++) {
        dp[j] = 1;
    }

    // 初始化结果变量，记录最长递增子序列的最大长度
    int result = 1;

    // 遍历每个元素
    for (int i = 1; i < nums.length; i++) {
        // 对于当前元素，遍历其之前的所有元素
        for (int j = 0; j < i; j++) {
            // 如果当前元素大于之前的元素，可能形成递增子序列
            if (nums[i] > nums[j]) {
                // 更新dp[i]为以nums[i]结尾的最长递增子序列长度
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        // 更新结果，保持最长递增子序列的最大长度
        result = Math.max(result, dp[i]);
    }

    // 返回最长递增子序列的长度
    return result;
}
```

## 674. 最长连续递增序列

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0674.%E6%9C%80%E9%95%BF%E8%BF%9E%E7%BB%AD%E9%80%92%E5%A2%9E%E5%BA%8F%E5%88%97.html)
视频讲解：[动态规划之子序列问题，重点在于连续！| LeetCode：674.最长连续递增序列\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1bD4y1778v)

```java
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        // 获取输入数组的长度
        int n = nums.length;
        // 创建一个 dp 数组，用于存储以每个元素结尾的 LCIS（最长连续递增子序列）的长度
        int[] dp = new int[n];
        // 初始化第一个元素的 LCIS 长度为 1
        dp[0] = 1;
        // 用于记录当前找到的最长连续递增子序列的长度
        int result = 1;

        // 从第二个元素开始遍历数组
        for (int i = 1; i < n; i++) {
            // 如果当前元素大于前一个元素，说明可以延续递增序列
            if (nums[i] > nums[i - 1]) {
                // 更新 dp[i] 为前一个元素的 dp 值加 1
                dp[i] = Math.max(dp[i], dp[i - 1] + 1);
            } else {
                // 如果当前元素不大于前一个元素，重新开始新的 LCIS，长度为 1
                dp[i] = 1;
            }
            // 更新 result 为当前找到的最大值
            result = Math.max(result, dp[i]);
        }
        // 返回最长连续递增子序列的长度
        return result;
    }
}
```

## 718. 最长重复子数组

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0718.%E6%9C%80%E9%95%BF%E9%87%8D%E5%A4%8D%E5%AD%90%E6%95%B0%E7%BB%84.html)
视频讲解：[动态规划之子序列问题，想清楚 DP 数组的定义 | LeetCode：718.最长重复子数组\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV178411H7hV)

```java
class Solution {
    public int findLength(int[] nums1, int[] nums2) {
        // 获取两个数组的长度
        int m = nums1.length;
        int n = nums2.length;

        // 创建一个二维数组 dp，用于存储公共子数组的长度
        // dp[i][j] 表示 nums1 的前 i 个元素和 nums2 的前 j 个元素的公共子数组的长度
        int[][] dp = new int[m + 1][n + 1];

        // 用于记录找到的最大公共子数组的长度
        int result = 0;

        // 遍历 nums1 和 nums2 的每个元素
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 如果 nums1 的第 i-1 个元素等于 nums2 的第 j-1 个元素
                if (nums1[i - 1] == nums2[j - 1]) {
                    // 更新 dp[i][j]，表示当前公共子数组长度
                    // dp[i][j] 由 dp[i-1][j-1] 加 1 得到
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
                // 更新 result，记录当前找到的最大公共子数组长度
                result = Math.max(result, dp[i][j]);
            }
        }
        // 返回找到的最大公共子数组长度
        return result;
    }
}
```
