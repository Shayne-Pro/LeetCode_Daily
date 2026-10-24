# Day50 · 图论Part02

> 作者：[夜雨翦春韭（Shayne-Pro）](https://blog.csdn.net/weixin_43724673) · 原文发布：2024-10-23
> CSDN 原文：[《【代码随想录Day50】图论Part02》](https://blog.csdn.net/weixin_43724673/article/details/143192974) · [返回目录](../README.md)

## 岛屿数量 深搜

题目链接/文章讲解：[代码随想录](https://www.programmercarl.com/kamacoder/0099.%E5%B2%9B%E5%B1%BF%E7%9A%84%E6%95%B0%E9%87%8F%E6%B7%B1%E6%90%9C.html)

```java
class Solution {
    // 计算网格中岛屿的数量
    public int numIslands(char[][] grid) {
        int sum = 0;  // 初始化岛屿数量为0

        // 遍历整个网格
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 如果当前单元格是陆地（'1'），则表示发现了一个新的岛屿
                if (grid[i][j] == '1') {
                    sum++;  // 增加岛屿计数
                    dfs(grid, i, j);  // 使用深度优先搜索（DFS）标记整个岛屿
                }
            }
        }

        return sum;  // 返回岛屿的总数
    }

    // 深度优先搜索，用于标记连接的陆地
    public void dfs(char[][] grid, int i, int j) {
        // 如果索引越界或当前单元格是水（'0'），则返回
        if (i < 0 || i > grid.length - 1 || j < 0 || j > grid[0].length - 1 || grid[i][j] == '0') {
            return;
        }

        // 将当前单元格标记为水（'0'），表示已经访问过
        grid[i][j] = '0';

        // 递归地检查四个方向（上、下、左、右）的相邻单元格
        dfs(grid, i - 1, j);  // 上
        dfs(grid, i + 1, j);  // 下
        dfs(grid, i, j - 1);  // 左
        dfs(grid, i, j + 1);  // 右
    }
}
```

## 岛屿数量 广搜

题目链接/文章讲解：[代码随想录](https://www.programmercarl.com/kamacoder/0099.%E5%B2%9B%E5%B1%BF%E7%9A%84%E6%95%B0%E9%87%8F%E5%B9%BF%E6%90%9C.html)

```java
class Solution {
    public int numIslands(char[][] grid) {
        // 创建一个队列用于BFS
        Queue<int[]> queue = new LinkedList<>();
        // 初始化岛屿数量
        int sum = 0;

        // 遍历整个网格
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 如果当前位置是陆地（'1'）
                if (grid[i][j] == '1') {
                    // 发现一个新的岛屿，岛屿数量加一
                    sum++;
                    // 将当前位置加入队列，并标记为已访问（'0'）
                    queue.add(new int[]{i, j});
                    grid[i][j] = '0';

                    // 开始BFS
                    while (!queue.isEmpty()) {
                        // 从队列中取出一个位置
                        int[] cur = queue.poll();
                        int x = cur[0];
                        int y = cur[1];

                        // 检查上方的位置
                        if (x - 1 >= 0 && grid[x - 1][y] == '1') {
                            queue.add(new int[]{x - 1, y});
                            grid[x - 1][y] = '0'; // 标记为已访问
                        }
                        // 检查下方的位置
                        if (x + 1 < grid.length && grid[x + 1][y] == '1') {
                            queue.add(new int[]{x + 1, y});
                            grid[x + 1][y] = '0'; // 标记为已访问
                        }
                        // 检查左边的位置
                        if (y - 1 >= 0 && grid[x][y - 1] == '1') {
                            queue.add(new int[]{x, y - 1});
                            grid[x][y - 1] = '0'; // 标记为已访问
                        }
                        // 检查右边的位置
                        if (y + 1 < grid[0].length && grid[x][y + 1] == '1') {
                            queue.add(new int[]{x, y + 1});
                            grid[x][y + 1] = '0'; // 标记为已访问
                        }
                    }
                }
            }
        }
        // 返回岛屿的总数量
        return sum;
    }
}
```

## 岛屿的最大面积

题目链接/文章讲解：[代码随想录](https://www.programmercarl.com/kamacoder/0100.%E5%B2%9B%E5%B1%BF%E7%9A%84%E6%9C%80%E5%A4%A7%E9%9D%A2%E7%A7%AF.html)

```java
class Solution {
    // 主方法，用于计算二维网格中最大的岛屿面积
    public int maxAreaOfIsland(int[][] grid) {
        int max_area = 0; // 初始化最大岛屿面积为0

        // 遍历整个二维网格
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 如果当前格子是岛屿（值为1）
                if (grid[i][j] == 1) {
                    // 使用深度优先搜索计算当前岛屿的面积
                    int area = dfs(grid, i, j);
                    // 更新最大岛屿面积
                    max_area = Math.max(max_area, area);
                }
            }
        }

        return max_area; // 返回最大岛屿面积
    }

    // 深度优先搜索方法，用于计算岛屿的面积
    public int dfs(int[][] grid, int i, int j) {
        // 检查当前格子是否越界或是否是水域（值为0）
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return 0; // 如果是越界或水域，返回面积为0
        }

        grid[i][j] = 0; // 将当前格子标记为已访问（值设为0）

        // 递归计算当前格子上、下、左、右四个方向的岛屿面积
        int up = dfs(grid, i - 1, j); // 上
        int down = dfs(grid, i + 1, j); // 下
        int left = dfs(grid, i, j - 1); // 左
        int right = dfs(grid, i, j + 1); // 右

        // 返回当前格子的面积（1）加上四个方向的面积之和
        return 1 + up + down + left + right;
    }
}
```
