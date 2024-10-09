## 0-1 背包问题 二维

题目链接/文章讲解：[代码随想录](https://programmercarl.com/%E8%83%8C%E5%8C%85%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%8001%E8%83%8C%E5%8C%85-1.html)
视频讲解：[带你学透 0-1 背包问题！| 关于背包问题，你不清楚的地方，这里都讲了！| 动态规划经典问题 | 数据结构与算法\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1cg411g7Y6)

```java
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 物品数量
        int bagweight = sc.nextInt(); // 背包容量

        int[] weight = new int[n];
        int[] value = new int[n];

        // 输入物品的重量
        for (int i = 0; i < n; i++) {
            weight[i] = sc.nextInt();
        }

        // 输入物品的价值
        for (int i = 0; i < n; i++) {
            value[i] = sc.nextInt();
        }

        // 动态规划数组
        int[][] dp = new int[n][bagweight + 1];

        // 初始化第一个物品
        for (int j = weight[0]; j <= bagweight; j++) {
            dp[0][j] = value[0];
        }

        // 填充动态规划表
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= bagweight; j++) {
                if (j < weight[i]) {
                    // 当前物品的重量大于当前背包容量
                    dp[i][j] = dp[i - 1][j]; // 不放当前物品
                } else {
                    // 当前物品可以放入背包
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                }
            }
        }

        // 输出结果
        System.out.println(dp[n - 1][bagweight]);
    }
}

```

## 0-1 背包问题 一维

题目链接/文章讲解：[代码随想录](https://programmercarl.com/%E8%83%8C%E5%8C%85%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%8001%E8%83%8C%E5%8C%85-2.html)
视频讲解：[带你学透 01 背包问题（滚动数组篇） | 从此对背包问题不再迷茫！\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1BU4y177kY)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 物品数量
        int bagweight = sc.nextInt(); // 背包容量

        int[] weight = new int[n];
        int[] value = new int[n];

        // 输入物品的重量
        for (int i = 0; i < n; i++) {
            weight[i] = sc.nextInt();
        }

        // 输入物品的价值
        for (int i = 0; i < n; i++) {
            value[i] = sc.nextInt();
        }

        // 使用一维数组进行动态规划
        int[] dp = new int[bagweight + 1];

        // 填充动态规划数组
        for (int i = 0; i < n; i++) {
            for (int j = bagweight; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }

        // 输出结果
        System.out.println(dp[bagweight]);
    }
}
```

## 416. 分割等和子集

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0416.%E5%88%86%E5%89%B2%E7%AD%89%E5%92%8C%E5%AD%90%E9%9B%86.html)
视频讲解：[动态规划之背包问题，这个包能装满吗？| LeetCode：416.分割等和子集\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1rt4y1N7jE)

### **二维数组版：**

```java
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // 如果总和是奇数，无法分成两个相等的子集
        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;
        int n = nums.length;

        // 创建一个 dp 数组，表示是否可以达到特定的和
        boolean[][] dp = new boolean[n + 1][target + 1];

        // 初始化：可以达到和为 0
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        // 填充 dp 数组
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                if (j < nums[i - 1]) {
                    // 当前数字大于目标和，无法选择它
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 选择当前数字或不选择它
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        // 返回能否达到 target
        return dp[n][target];
    }
}
```

代码解释：

1. **计算总和**: 首先通过一个循环计算 `nums` 数组的总和 `sum`。

2. **检查奇偶性**: 如果 `sum` 是奇数，则不可能将数组分成两个相等的子集，直接返回 `false`。

3. **动态规划数组**: 创建一个二维布尔数组 `dp`，其中 `dp[i][j]` 表示前 `i` 个数字是否可以组成和为 `j` 的子集。

4. **初始化**:

   - 任何情况下，和为 0 只需要不选择任何数字，因此 `dp[i][0]` 都是 `true`。

5. **填充 `dp` 数组**:

   - 通过两层循环遍历每个数字和每个可能的和。如果当前数字大于目标和 `j`，则无法选择它，`dp[i][j]` 等于不选择它的结果 `dp[i-1][j]`。
   - 如果可以选择当前数字，则 `dp[i][j]` 等于选择当前数字或不选择它的结果，即 `dp[i][j] = dp[i-1][j] || dp[i-1][j - nums[i - 1]]`。

6. **返回结果**: 最后返回 `dp[n][target]`，表示是否可以用前 `n` 个数字组成和为 `target` 的子集。

### **一维数组版：**

```java
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // 如果总和是奇数，无法分成两个相等的子集
        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;
        int n = nums.length;

        // 创建一个 dp 数组，表示是否可以达到特定的和
        boolean[] dp = new boolean[target + 1];
        dp[0] = true; // 和为 0 是可能的

        // 填充 dp 数组
        for (int i = 0; i < n; i++) {
            // 从后向前遍历，确保每个数字只使用一次
            for (int j = target; j >= nums[i]; j--) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }

        // 返回能否达到 target
        return dp[target];
    }
}
```

代码解释：

1. **计算总和**: 和之前一样，我们计算数组 `nums` 的总和 `sum`。

2. **检查奇偶性**: 如果总和是奇数，返回 `false`。

3. **初始化一维动态规划数组**:

   - 创建一个布尔数组 `dp`，`dp[j]` 表示是否可以用数组中的某些数字组成和为 `j` 的子集。
   - 初始化 `dp[0]` 为 `true`，因为和为 0 总是可以实现（不选任何数字）。

4. **填充 `dp` 数组**:

   - 使用外层循环遍历每个数字 `nums[i]`。
   - 内层循环从 `target` 到 `nums[i]` 反向遍历，更新 `dp[j]` 的值。这样可以确保在更新 `dp[j]` 时，我们只使用当前数字一次。

5. **返回结果**: 最后返回 `dp[target]`，表示是否可以用数组中的数字组成和为 `target` 的子集。
