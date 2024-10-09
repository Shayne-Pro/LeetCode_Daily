## 1049. 最后一块石头的重量 II

题目链接/文章讲解：[代码随想录](https://programmercarl.com/1049.%E6%9C%80%E5%90%8E%E4%B8%80%E5%9D%97%E7%9F%B3%E5%A4%B4%E7%9A%84%E9%87%8D%E9%87%8FII.html)
视频讲解：[动态规划之背包问题，这个背包最多能装多少？LeetCode：1049.最后一块石头的重量 II\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV14M411C7oV)

```java
class Solution {
    public int lastStoneWeightII(int[] stones) {
        // 计算所有石头的总重量
        int sum = 0;
        for (int stone : stones) {
            sum += stone; // 累加每个石头的重量
        }

        // 设定目标为总重量的一半
        int target = sum / 2;

        // 创建一个动态规划数组 dp，dp[j] 表示可以达到重量 j 的最大石头重量
        int[] dp = new int[target + 1];

        // 初始化 dp 数组，填充第一个石头的重量
        // 从 stones[0] 开始到 target，dp[j] 设为 stones[0]
        for (int j = stones[0]; j <= target; j++) {
            dp[j] = stones[0];
        }

        // 遍历每个石头
        for (int i = 1; i < stones.length; i++) { // 从 1 开始，因为 0 号石头已经处理过了
            // 从后往前更新 dp 数组，确保每个石头只能使用一次
            for (int j = target; j >= stones[i]; j--) {
                // 更新 dp[j] 为能达到的最大重量
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }

        // 返回最终的结果，sum - 2 * dp[target] 计算出两堆石头的重量差
        return sum - 2 * dp[target];
    }
}
```

## 494. 目标和

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0494.%E7%9B%AE%E6%A0%87%E5%92%8C.html)
视频讲解：[动态规划之背包问题，装满背包有多少种方法？| LeetCode：494.目标和\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1o8411j73x)

```java
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }

        // 如果目标绝对值大于总和，返回 0
        if (Math.abs(target) > sum) {
            return 0;
        }
        // 如果 (sum + target) 不是偶数，返回 0
        if ((sum + target) % 2 == 1) {
            return 0;
        }

        // 计算背包大小
        int bagSize = (sum + target) / 2;

        // dp[i] 表示能够组成和为 i 的子集的数量
        int[] dp = new int[bagSize + 1];
        dp[0] = 1; // 初始化：只有一个方式组成和为 0，即选择不任何元素

        // 填充 dp 数组
        for (int num : nums) {
            for (int j = bagSize; j >= num; j--) {
                dp[j] += dp[j - num]; // 更新 dp[j]，加上选择当前 num 的方式
            }
        }

        // 返回能够组成目标和的方式数量
        return dp[bagSize];
    }
}
```

## 474.一和零

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0474.%E4%B8%80%E5%92%8C%E9%9B%B6.html)
视频讲解：[动态规划之背包问题，装满这个背包最多用多少个物品？| LeetCode：474.一和零\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1rW4y1x7ZQ)

```java
class Solution {
    // 定义方法findMaxForm，输入参数为字符串数组strs和两个整数m,n
    public int findMaxForm(String[] strs, int m, int n) {
        // 获取字符串数组的长度
        int len = strs.length;
        // 初始化一个二维数组cnt来记录每个字符串中0和1的数量
        int[][] cnt = new int[len][2];

        // 遍历字符串数组，统计每个字符串中0和1的数量
        for (int i = 0; i < len; i++) {
            int zero = 0, one = 0;
            // 遍历字符串中的每一个字符
            for (char c : strs[i].toCharArray()) {
                // 如果字符是'0'，则零计数器加一
                if (c == '0') zero++;
                // 否则，如果字符是'1'，则一计数器加一
                else one++;
            }
            // 将当前字符串中0和1的数量存储到cnt数组中
            cnt[i] = new int[]{zero, one};
        }

        // 初始化一个二维数组f用于动态规划，m+1和n+1是因为需要考虑边界情况
        int[][] f = new int[m + 1][n + 1];

        // 外层循环遍历所有字符串
        for (int k = 0; k < len; k++) {
            // 获取当前字符串中0和1的数量
            int zero = cnt[k][0], one = cnt[k][1];
            // 从m递减到当前字符串中0的数量
            for (int i = m; i >= zero; i--) {
                // 从n递减到当前字符串中1的数量
                for (int j = n; j >= one; j--) {
                    // 动态规划状态转移方程
                    f[i][j] = Math.max(f[i][j], f[i - zero][j - one] + 1);
                }
            }
        }
        // 返回最大形式字符串的数量
        return f[m][n];
    }
}
```
