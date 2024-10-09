## 理论基础

题目链接/文章讲解：[代码随想录](https://programmercarl.com/%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80.html)
视频讲解：[从此再也不怕动态规划了，动态规划解题方法论大曝光 ！| 理论基础 |力扣刷题总结| 动态规划入门\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV13Q4y197Wg)

## 509. 斐波那契数

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0509.%E6%96%90%E6%B3%A2%E9%82%A3%E5%A5%91%E6%95%B0.html)
视频讲解：[手把手带你入门动态规划 | LeetCode：509.斐波那契数\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1f5411K7mo)
**普通递归：**

```java
class Solution {
    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }
}
```

**动态规划：**

```java
class Solution {
    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        int a = 0, b = 1, sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return sum;
    }
}
```

## 70. 爬楼梯

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0070.%E7%88%AC%E6%A5%BC%E6%A2%AF.html)
视频讲解：[带你学透动态规划-爬楼梯（对应力扣 70.爬楼梯）| 动态规划经典入门题目\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV17h411h7UH)
**dp 数组：**

```java
class Solution {
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
```

**状态压缩：**

```java
class Solution {
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int first = 1;  // dp[i-2]
        int second = 2; // dp[i-1]
        int current = 0; // dp[i]

        for (int i = 3; i <= n; i++) {
            current = first + second;
            first = second;
            second = current;
        }

        return second; // dp[n]
    }
}
```

## 746. 使用最小花费爬楼梯

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0746.%E4%BD%BF%E7%94%A8%E6%9C%80%E5%B0%8F%E8%8A%B1%E8%B4%B9%E7%88%AC%E6%A5%BC%E6%A2%AF.html)
视频讲解：[动态规划开更了！| LeetCode：746. 使用最小花费爬楼梯\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV16G411c7yZ)

```java
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        if (n <= 2) {
            return Math.min(cost[0], cost[1]);
        }
        int first = 0;
        int second = 0;
        int current = 0;
        for (int i = 2; i <= n; i++) {
            current = Math.min(first + cost[i - 2], second + cost[i - 1]);
            first = second;
            second = current;
        }
        return current;
    }
}
```
