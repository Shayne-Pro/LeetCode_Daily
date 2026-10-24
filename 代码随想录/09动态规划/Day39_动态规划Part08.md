# Day39 · 动态规划Part08

> 作者：[夜雨翦春韭（Shayne-Pro）](https://blog.csdn.net/weixin_43724673) · 原文发布：2024-10-11
> CSDN 原文：[《【代码随想录Day39】动态规划Part08》](https://blog.csdn.net/weixin_43724673/article/details/142862926) · [返回目录](../README.md)

## 121. 买卖股票的最佳时机

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0121.%E4%B9%B0%E5%8D%96%E8%82%A1%E7%A5%A8%E7%9A%84%E6%9C%80%E4%BD%B3%E6%97%B6%E6%9C%BA.html)
视频讲解：[动态规划之 LeetCode：121.买卖股票的最佳时机 1\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1Xe4y1u77q)

```java
class Solution {
    public int maxProfit(int[] prices) {
        // 创建一个数组 dp，用于存储到当前日期的最大利润
        int[] dp = new int[prices.length];
        // 初始时，第一天的利润为 0
        dp[0] = 0;

        // 初始化最小价格为第一天的价格
        int minPrice = prices[0];

        // 从第二天开始遍历价格数组
        for (int i = 1; i < prices.length; i++) {
            // 更新当前的最小价格
            minPrice = Math.min(minPrice, prices[i]);
            // 计算到当前日期的最大利润
            dp[i] = Math.max(dp[i - 1], prices[i] - minPrice);
        }
        // 返回最后一天的最大利润
        return dp[prices.length - 1];
    }
}
```

## 122.买卖股票的最佳时机 II

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0122.%E4%B9%B0%E5%8D%96%E8%82%A1%E7%A5%A8%E7%9A%84%E6%9C%80%E4%BD%B3%E6%97%B6%E6%9C%BAII%EF%BC%88%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92%EF%BC%89.html)
视频讲解：[动态规划，股票问题第二弹 | LeetCode：122.买卖股票的最佳时机 II\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1D24y1Q7Ls)
贪心算法：

```java
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            if (prices[i] > prices[i - 1]) {
                sum += prices[i] - prices[i - 1];
            }
        }
        return sum;
    }
}
```

动态规划：

```java
class Solution {
    public int maxProfit(int[] prices) {
        // 获取价格数组的长度
        int n = prices.length;
        // 创建一个二维数组 dp，dp[i][0] 表示第 i 天结束时不持有股票的最大利润
        // dp[i][1] 表示第 i 天结束时持有股票的最大利润
        int[][] dp = new int[n][2];

        // 初始化第 0 天的状态
        dp[0][0] = 0; // 第 0 天不持有股票时的利润为 0
        dp[0][1] = -prices[0]; // 第 0 天持有股票时的利润为 -prices[0]（买入股票的成本）

        // 遍历每一天，从第 1 天到第 n-1 天
        for (int i = 1; i < n; ++i) {
            // 第 i 天不持有股票的最大利润
            // 选择不变（保持前一天的状态）或者卖出股票（从持有状态转为不持有）
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);

            // 第 i 天持有股票的最大利润
            // 选择不变（保持前一天的状态）或者买入股票（从不持有状态转为持有）
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        // 返回最后一天不持有股票时的最大利润
        return dp[n - 1][0];
    }
}
```

## 123.买卖股票的最佳时机 III

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0123.%E4%B9%B0%E5%8D%96%E8%82%A1%E7%A5%A8%E7%9A%84%E6%9C%80%E4%BD%B3%E6%97%B6%E6%9C%BAIII.html)
视频讲解：[动态规划，股票至多买卖两次，怎么求？ | LeetCode：123.买卖股票最佳时机 III\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1WG411K7AR)

```java
class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;

        // 定义 5 种状态:
        // 0: 没有操作, 1: 第一次买入, 2: 第一次卖出, 3: 第二次买入, 4: 第二次卖出
        int[] dp = new int[5];

        // 初始化第一次买入的状态为 -prices[0]
        dp[1] = -prices[0];

        // 初始化第二次买入的状态为 -prices[0]
        dp[3] = -prices[0];

        // 遍历每一天的价格
        for (int i = 1; i < len; i++) {
            // 更新第一次买入的状态：取当前状态和以当天价格买入的较大值
            dp[1] = Math.max(dp[1], -prices[i]);

            // 更新第一次卖出的状态：取当前状态和以当天价格卖出的较大值
            dp[2] = Math.max(dp[2], dp[1] + prices[i]);

            // 更新第二次买入的状态：取当前状态和以当天价格买入的较大值
            dp[3] = Math.max(dp[3], dp[2] - prices[i]);

            // 更新第二次卖出的状态：取当前状态和以当天价格卖出的较大值
            dp[4] = Math.max(dp[4], dp[3] + prices[i]);
        }

        // 返回第二次卖出后的最大利润
        return dp[4];
    }
}
```
