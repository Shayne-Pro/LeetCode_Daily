## 完全背包

题目链接/文章讲解：[代码随想录](https://programmercarl.com/%E8%83%8C%E5%8C%85%E9%97%AE%E9%A2%98%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80%E5%AE%8C%E5%85%A8%E8%83%8C%E5%8C%85.html)
视频讲解：[带你学透完全背包问题！ 和 01 背包有什么差别？遍历顺序上有什么讲究？\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1uK411o7c9)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 创建一个Scanner对象，用于从标准输入读取数据
        Scanner sc = new Scanner(System.in);

        // 读取物品的数量N
        int N = sc.nextInt();

        // 读取背包的容量bagSize
        int bagSize = sc.nextInt();

        // 创建一个数组来存储每个物品的重量
        int[] weight = new int[N];

        // 创建一个数组来存储每个物品的价值
        int[] value = new int[N];

        // 读取每个物品的重量和价值
        for (int i = 0; i < N; i++) {
            weight[i] = sc.nextInt();
            value[i] = sc.nextInt();
        }

        // 创建一个数组dp，用于存储在不同背包容量下的最大价值
        // dp[j]表示背包容量为j时的最大价值
        int[] dp = new int[bagSize + 1];

        // 动态规划求解背包问题
        for (int i = 0; i < N; i++) {
            // 从当前物品的重量开始，遍历到背包的最大容量
            for (int j = weight[i]; j <= bagSize; j++) {
                // 更新dp[j]，取当前dp[j]和放入当前物品后的价值的最大值
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }

        // 输出背包容量为bagSize时的最大价值
        System.out.println(dp[bagSize]);
    }
}
```

## 518. 零钱兑换 II

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0518.%E9%9B%B6%E9%92%B1%E5%85%91%E6%8D%A2II.html)
视频讲解：[动态规划之完全背包，装满背包有多少种方法？组合与排列有讲究！| LeetCode：518.零钱兑换 II\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1KM411k75j)

```java
class Solution {
    public int change(int amount, int[] coins) {
        // 创建一个数组 dp，用于存储不同金额的组合数
        // dp[i] 表示组成金额 i 的不同硬币组合的数量
        int[] dp = new int[amount + 1];

        // 初始化 dp[0] 为 1，表示组成金额 0 的方法只有一种，即不使用任何硬币
        dp[0] = 1;

        // 遍历每种硬币
        for (int i = 0; i < coins.length; i++) {
            // 从当前硬币的面值开始，更新 dp 数组
            // j 表示当前要组成的金额
            for (int j = coins[i]; j <= amount; j++) {
                // 更新 dp[j]，增加当前硬币 coins[i] 的组合方式
                // dp[j - coins[i]] 表示使用当前硬币后的剩余金额的组合数
                dp[j] += dp[j - coins[i]];
            }
        }

        // 返回组成目标金额 amount 的不同组合数
        return dp[amount];
    }
}
```

## 377. 组合总和 Ⅳ

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0377.%E7%BB%84%E5%90%88%E6%80%BB%E5%92%8C%E2%85%A3.html)
视频讲解：[动态规划之完全背包，装满背包有几种方法？求排列数？| LeetCode：377.组合总和 IV\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1V14y1n7B6)

```java
class Solution {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1; // 初始条件：目标值为0时，只有一种组合方式（什么都不选）

        for (int j = 1; j <= target; j++) {
            for (int i = 0; i < nums.length; i++) {
                if (j >= nums[i]) {
                    dp[j] += dp[j - nums[i]];
                }
            }
        }

        return dp[target];
    }
}
```
