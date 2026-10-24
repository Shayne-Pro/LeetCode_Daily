# Day40 · 动态规划Part09

> 作者：[夜雨翦春韭（Shayne-Pro）](https://blog.csdn.net/weixin_43724673) · 原文发布：2024-10-12
> CSDN 原文：[《【代码随想录Day40】动态规划Part09》](https://blog.csdn.net/weixin_43724673/article/details/142876605) · [返回目录](../README.md)

## 188.买卖股票的最佳时机 IV

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0188.%E4%B9%B0%E5%8D%96%E8%82%A1%E7%A5%A8%E7%9A%84%E6%9C%80%E4%BD%B3%E6%97%B6%E6%9C%BAIV.html)
视频讲解：[动态规划来决定最佳时机，至多可以买卖 K 次！| LeetCode：188.买卖股票最佳时机 4\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV16M411U7XJ)

```java
class Solution {
    public int maxProfit(int k, int[] prices) {
        // 如果价格数组为空，则没有交易，利润为0
        if (prices.length == 0) return 0;

        // 获取价格数组的长度
        int len = prices.length;

        // 创建dp数组，维度为[天数][股票状态]
        // 股票状态:
        // - 奇数表示第 k 次交易持有/买入 (如 1, 3, ..., 2k-1)
        // - 偶数表示第 k 次交易不持有/卖出 (如 0, 2, ..., 2k)
        // - 0 表示没有操作
        int[][] dp = new int[len][k * 2 + 1];

        // 初始化dp数组的第一天状态
        // 在第一天，如果进行奇数次交易（买入），则利润为负的股票价格
        for (int i = 1; i < k * 2; i += 2) {
            dp[0][i] = -prices[0]; // 第一天买入股票的成本
        }

        // 遍历每一天
        for (int i = 1; i < len; i++) {
            // 遍历每个可能的交易状态
            for (int j = 0; j < k * 2 - 1; j += 2) {
                // 更新持有状态: 选择不变持有状态或在今天买入
                dp[i][j + 1] = Math.max(dp[i - 1][j + 1], dp[i - 1][j] - prices[i]);

                // 更新不持有状态: 选择不变不持有状态或在今天卖出
                dp[i][j + 2] = Math.max(dp[i - 1][j + 2], dp[i - 1][j + 1] + prices[i]);
            }
        }

        // 返回最后一天不持有状态的最大利润，即完成k次交易后的最大利润
        return dp[len - 1][k * 2];
    }
}
```

## 309.最佳买卖股票时机含冷冻期

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0309.%E6%9C%80%E4%BD%B3%E4%B9%B0%E5%8D%96%E8%82%A1%E7%A5%A8%E6%97%B6%E6%9C%BA%E5%90%AB%E5%86%B7%E5%86%BB%E6%9C%9F.html)
视频讲解：[动态规划来决定最佳时机，这次有冷冻期！| LeetCode：309.买卖股票的最佳时机含冷冻期\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1rP4y1D7ku)

```java
class Solution {
    public int maxProfit(int[] prices) {
        // 如果价格数组为空或长度小于2，无法进行交易，直接返回0
        if (prices == null || prices.length < 2) {
            return 0;
        }

        // 创建一个二维数组dp，dp[i][0]表示第i天不持有股票的最大利润，dp[i][1]表示第i天持有股票的最大利润
        int[][] dp = new int[prices.length][2];

        // 初始化边界条件
        // 第0天不持有股票的最大利润为0
        dp[0][0] = 0;
        // 第0天持有股票的最大利润为买入股票的负值，即-prices[0]
        dp[0][1] = -prices[0];

        // 第1天不持有股票的最大利润为前一天不持有股票的利润和前一天持有股票并在今天卖出的利润中的较大值
        dp[1][0] = Math.max(dp[0][0], dp[0][1] + prices[1]);
        // 第1天持有股票的最大利润为前一天持有股票的利润和今天买入股票的利润中的较大值
        dp[1][1] = Math.max(dp[0][1], -prices[1]);

        // 从第2天开始遍历价格数组
        for (int i = 2; i < prices.length; i++) {
            // 第i天不持有股票的最大利润为前一天不持有股票的利润和前一天持有股票并在今天卖出的利润中的较大值
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 第i天持有股票的最大利润为前一天持有股票的利润和前两天不持有股票并在今天买入股票的利润中的较大值
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
        }

        // 返回最后一天不持有股票的最大利润
        return dp[prices.length - 1][0];
    }
}
```

## 714.买卖股票的最佳时机含手续费

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0714.%E4%B9%B0%E5%8D%96%E8%82%A1%E7%A5%A8%E7%9A%84%E6%9C%80%E4%BD%B3%E6%97%B6%E6%9C%BA%E5%90%AB%E6%89%8B%E7%BB%AD%E8%B4%B9%EF%BC%88%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92%EF%BC%89.html)
视频讲解：[动态规划来决定最佳时机，这次含手续费！| LeetCode：714.买卖股票的最佳时机含手续费\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1z44y1Z7UR)

```java
class Solution {
    public int maxProfit(int[] prices, int fee) {
        // 获取价格数组的长度
        int n = prices.length;

        // 创建一个二维数组 dp，其中 dp[i][0] 表示第 i 天结束时不持有股票的最大利润
        // dp[i][1] 表示第 i 天结束时持有股票的最大利润
        int[][] dp = new int[n][2];

        // 初始化第 0 天的状态
        dp[0][0] = 0; // 第 0 天不持有股票时的利润为 0
        dp[0][1] = -prices[0]; // 第 0 天持有股票时的利润为 -prices[0]（买入股票的成本）

        // 遍历每一天，从第 1 天到第 n-1 天
        for (int i = 1; i < n; ++i) {
            // 第 i 天不持有股票的最大利润
            // 选择不变（保持前一天的状态）或者卖出股票（从持有状态转为不持有，并扣除交易费用）
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);

            // 第 i 天持有股票的最大利润
            // 选择不变（保持前一天的状态）或者买入股票（从不持有状态转为持有）
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        // 返回最后一天不持有股票时的最大利润
        return dp[n - 1][0];
    }
}
```

## 股票总结

文章讲解：[代码随想录](https://programmercarl.com/%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92-%E8%82%A1%E7%A5%A8%E9%97%AE%E9%A2%98%E6%80%BB%E7%BB%93%E7%AF%87.html)
