## 322. 零钱兑换

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0322.%E9%9B%B6%E9%92%B1%E5%85%91%E6%8D%A2.html)
视频讲解：[动态规划之完全背包，装满背包最少的物品件数是多少？| LeetCode：322.零钱兑换\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV14K411R7yv)

```java
class Solution {
    public int coinChange(int[] coins, int amount) {
        // 定义一个变量 max，表示无穷大，用于初始化 dp 数组
        int max = Integer.MAX_VALUE;

        // 创建一个 dp 数组，大小为 amount + 1，用于存储每个金额所需的最少硬币数
        int[] dp = new int[amount + 1];

        // 初始化 dp 数组，除了 dp[0] 为 0 外，其余都设为无穷大
        for (int j = 0; j < dp.length; j++) {
            dp[j] = max;
        }
        dp[0] = 0; // 金额为 0 时，所需硬币数为 0

        // 遍历每一种硬币
        for (int i = 0; i < coins.length; i++) {
            // 从当前硬币的面值开始，更新 dp 数组
            for (int j = coins[i]; j <= amount; j++) {
                // 如果 dp[j - coins[i]] 不是无穷大，说明可以通过使用当前硬币来更新 dp[j]
                if (dp[j - coins[i]] != max) {
                    // 更新 dp[j]，取当前 dp[j] 和 dp[j - coins[i]] + 1 的最小值
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }

        // 如果 dp[amount] 仍然是无穷大，说明无法凑出 amount，返回 -1
        // 否则返回 dp[amount]，即凑出 amount 所需的最少硬币数
        return dp[amount] == max ? -1 : dp[amount];
    }
}
```

## 279.完全平方数

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0279.%E5%AE%8C%E5%85%A8%E5%B9%B3%E6%96%B9%E6%95%B0.html)
视频讲解：[动态规划之完全背包，换汤不换药！| LeetCode：279.完全平方数\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV12P411T7Br)

```java
class Solution {
    public int numSquares(int n) {
        int max = Integer.MAX_VALUE;
        int[] dp = new int[n + 1];

        // 初始化dp数组，将每个位置的值设为最大值
        // 这样在后续的比较中可以确保dp[j]会被更新为较小的值
        for (int j = 0; j <= n; j++) {
            dp[j] = max;
        }

        // 当和为0时，组合的个数为0
        dp[0] = 0;

        // 遍历物品，这里的物品是平方数
        // i * i 表示当前的平方数
        for (int i = 1; i * i <= n; i++) {
            // 遍历背包，j表示当前的背包容量
            // 从当前的平方数开始遍历到n
            for (int j = i * i; j <= n; j++) {
                // 更新dp[j]，表示使用当前的平方数i*i来组成和j
                // dp[j] 表示不使用当前平方数时的最小组合数
                // dp[j - i * i] + 1 表示使用当前平方数时的最小组合数
                dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
            }
        }

        // 返回组成和为n的最小平方数个数
        return dp[n];
    }
}
```

## 139.单词拆分

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0139.%E5%8D%95%E8%AF%8D%E6%8B%86%E5%88%86.html)
视频讲解：[动态规划之完全背包，你的背包如何装满？| LeetCode：139.单词拆分\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1pd4y147Rh)

```java
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // 将字典中的单词放入HashSet中，以便快速查找
        HashSet<String> set = new HashSet<>(wordDict);

        // 创建一个布尔数组valid，长度为s.length() + 1
        // valid[i]表示字符串s的前i个字符是否可以被拆分为字典中的单词
        boolean[] valid = new boolean[s.length() + 1];

        // 初始化valid[0]为true，因为空字符串总是可以被拆分
        valid[0] = true;

        // 遍历字符串s的每个字符
        for (int i = 1; i <= s.length(); i++) {
            // 对于每个字符i，检查从0到i-1的子串是否可以被拆分
            for (int j = 0; j < i && !valid[i]; j++) {
                // 如果s.substring(j, i)在字典中，并且s的前j个字符可以被拆分
                // 那么s的前i个字符也可以被拆分
                if (set.contains(s.substring(j, i)) && valid[j]) {
                    valid[i] = true;
                }
            }
        }

        // 返回valid[s.length()]，表示整个字符串s是否可以被拆分为字典中的单词
        return valid[s.length()];
    }
}
```

## 关于多重背包，你该了解这些！

题目链接/文章讲解：[代码随想录](https://programmercarl.com/%E8%83%8C%E5%8C%85%E9%97%AE%E9%A2%98%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80%E5%A4%9A%E9%87%8D%E8%83%8C%E5%8C%85.html)

```java
import java.util.Scanner;

class multi_pack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        /**
         * bagWeight: 背包容量
         * n: 物品种类数量
         */
        int bagWeight, n;

        // 获取用户输入数据，中间用空格隔开，回车键换行
        bagWeight = sc.nextInt(); // 输入背包容量
        n = sc.nextInt(); // 输入物品种类数量

        // 初始化物品的重量、价值和数量数组
        int[] weight = new int[n]; // 存储每种物品的重量
        int[] value = new int[n];  // 存储每种物品的价值
        int[] nums = new int[n];   // 存储每种物品的数量

        // 输入每种物品的重量
        for (int i = 0; i < n; i++) {
            weight[i] = sc.nextInt();
        }

        // 输入每种物品的价值
        for (int i = 0; i < n; i++) {
            value[i] = sc.nextInt();
        }

        // 输入每种物品的数量
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        // 初始化动态规划数组，dp[j]表示容量为j的背包所能装的最大价值
        int[] dp = new int[bagWeight + 1];

        // 先遍历物品再遍历背包，作为01背包处理
        for (int i = 0; i < n; i++) { // 遍历每种物品
            for (int j = bagWeight; j >= weight[i]; j--) { // 遍历背包容量，从大到小
                // 遍历每种物品的个数
                for (int k = 1; k <= nums[i] && (j - k * weight[i]) >= 0; k++) {
                    // 更新dp数组，取当前容量下的最大价值
                    dp[j] = Math.max(dp[j], dp[j - k * weight[i]] + k * value[i]);
                }
            }
        }

        // 输出背包容量为bagWeight时的最大价值
        System.out.println(dp[bagWeight]);
    }
}
```

## 背包问题总结篇！

题目链接/文章讲解：[代码随想录](https://programmercarl.com/%E8%83%8C%E5%8C%85%E6%80%BB%E7%BB%93%E7%AF%87.html)
