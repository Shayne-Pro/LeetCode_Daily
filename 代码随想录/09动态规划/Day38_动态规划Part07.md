## 198.打家劫舍

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0198.%E6%89%93%E5%AE%B6%E5%8A%AB%E8%88%8D.html)
视频讲解：[动态规划，偷不偷这个房间呢？| LeetCode：198.打家劫舍\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1Te411N7SX)

```java
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        // 特殊情况处理
        if (n == 0)
            return 0;
        if (n == 1)
            return nums[0];
        if (n == 2)
            return Math.max(nums[0], nums[1]);

        int[] dp = new int[n + 1];
        dp[0] = 0; // dp[0]表示不偷任何房子的最大金额
        dp[1] = nums[0]; // dp[1]表示只偷第一个房子的最大金额

        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }

        return dp[n]; // 返回偷取所有房子中的最大金额
    }
}
```

## 213.打家劫舍 II

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0213.%E6%89%93%E5%AE%B6%E5%8A%AB%E8%88%8DII.html)
视频讲解：[动态规划，房间连成环了那还偷不偷呢？| LeetCode：213.打家劫舍 II\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1oM411B7xq)

```java
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;

        // 特殊情况处理
        if (n == 0)
            return 0;
        if (n == 1)
            return nums[0];
        if (n == 2)
            return Math.max(nums[0], nums[1]);

        // 定义一个方法来计算一维情况下的最大偷取金额
        return Math.max(robLinear(Arrays.copyOfRange(nums, 0, n - 1)), // 偷取第一个房子，不偷最后一个
                robLinear(Arrays.copyOfRange(nums, 1, n))); // 不偷第一个房子，偷取最后一个
    }

    private int robLinear(int[] nums) {
        int n = nums.length;
        if (n == 0)
            return 0;
        if (n == 1)
            return nums[0];

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = nums[0];

        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }

        return dp[n];
    }
}
```

## 337.打家劫舍 III

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0337.%E6%89%93%E5%AE%B6%E5%8A%AB%E8%88%8DIII.html)
视频讲解：[动态规划，房间连成树了，偷不偷呢？| LeetCode：337.打家劫舍 3\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1H24y1Q7sY)

```java
class Solution {
    public int rob(TreeNode root) {
        int[] result = dfs(root);
        return Math.max(result[0], result[1]);
    }

    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[2]; // 返回 {0, 0}，表示偷和不偷的金额都为0
        }

        int[] left = dfs(node.left);   // 左子树的结果
        int[] right = dfs(node.right); // 右子树的结果

        // dp[0]表示偷取当前节点的最大金额，dp[1]表示不偷取当前节点的最大金额
        int[] dp = new int[2];
        dp[0] = node.val + left[1] + right[1]; // 偷当前节点，左右子节点不能偷
        dp[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]); // 不偷当前节点，左右子节点可以选择偷或不偷

        return dp;
    }
}
```
