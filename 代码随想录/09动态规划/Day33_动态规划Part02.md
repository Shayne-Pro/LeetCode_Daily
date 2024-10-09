## 62.不同路径

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0062.%E4%B8%8D%E5%90%8C%E8%B7%AF%E5%BE%84.html)
视频讲解：[动态规划中如何初始化很重要！| LeetCode：62.不同路径\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1ve4y1x7Eu)

```java
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
```

## 63. 不同路径 II

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0063.%E4%B8%8D%E5%90%8C%E8%B7%AF%E5%BE%84II.html)
视频讲解：[动态规划，这次遇到障碍了| LeetCode：63. 不同路径 II\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1Ld4y1k7c6)

```java
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        // 如果起点或终点是障碍物，返回0
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }

        // 初始化第一行
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                break; // 遇到障碍物，后面的路径都不能到达
            }
            dp[0][i] = 1; // 通过该位置的路径数为1
        }

        // 初始化第一列
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                break; // 遇到障碍物，后面的路径都不能到达
            }
            dp[i][0] = 1; // 通过该位置的路径数为1
        }

        // 填充dp数组
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) { // 如果当前格子不是障碍物
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1]; // 上方和左方路径的和
                } else {
                    dp[i][j] = 0; // 如果是障碍物，路径数为0
                }
            }
        }

        return dp[m - 1][n - 1]; // 返回终点的路径数
    }
}
```
