# Day51 · 图论Part03

> 作者：[夜雨翦春韭（Shayne-Pro）](https://blog.csdn.net/weixin_43724673) · 原文发布：2024-10-25
> CSDN 原文：[《【代码随想录Day51】图论Part03》](https://blog.csdn.net/weixin_43724673/article/details/143231070) · [返回目录](../README.md)

## 孤岛的总面积

题目链接/文章讲解：[代码随想录](https://www.programmercarl.com/kamacoder/0101.%E5%AD%A4%E5%B2%9B%E7%9A%84%E6%80%BB%E9%9D%A2%E7%A7%AF.html)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取矩阵的行数和列数
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        // 读取矩阵
        int[][] grid = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }

        // 计算所有孤岛的总面积
        int totalArea = totalAreaOfIslands(grid);

        // 输出结果
        System.out.println(totalArea);
    }

    // 计算所有孤岛的总面积
    public static int totalAreaOfIslands(int[][] grid) {
        int total_area = 0; // 初始化孤岛总面积为0

        // 遍历整个二维网格
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 如果当前格子是陆地（值为1）
                if (grid[i][j] == 1) {
                    // 使用深度优先搜索计算当前岛屿的面积，并检查是否为孤岛
                    int area = dfs(grid, i, j);
                    // 如果返回的面积大于0，说明是孤岛，累加到总面积中
                    if (area > 0) {
                        total_area += area;
                    }
                }
            }
        }

        return total_area; // 返回孤岛总面积
    }

    // 深度优先搜索方法，用于计算岛屿的面积并检查是否为孤岛
    public static int dfs(int[][] grid, int i, int j) {
        // 检查当前格子是否越界或是否是水域（值为0）
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return 0; // 如果是越界或水域，返回面积为0
        }

        // 检查当前格子是否接触到矩阵的边缘
        if (i == 0 || i == grid.length - 1 || j == 0 || j == grid[0].length - 1) {
            return -1; // 如果接触到边缘，返回-1表示该岛屿不是孤岛
        }

        grid[i][j] = 0; // 将当前格子标记为已访问（值设为0）

        // 递归计算当前格子上、下、左、右四个方向的岛屿面积
        int up = dfs(grid, i - 1, j); // 上
        int down = dfs(grid, i + 1, j); // 下
        int left = dfs(grid, i, j - 1); // 左
        int right = dfs(grid, i, j + 1); // 右

        // 如果任意一个方向的面积为-1，说明该岛屿不是孤岛
        if (up == -1 || down == -1 || left == -1 || right == -1) {
            return -1; // 返回-1表示该岛屿不是孤岛
        }

        // 返回当前格子的面积（1）加上四个方向的面积之和
        return 1 + up + down + left + right;
    }
}
```

## 沉没孤岛

题目链接/文章讲解：[代码随想录](https://www.programmercarl.com/kamacoder/0102.%E6%B2%89%E6%B2%A1%E5%AD%A4%E5%B2%9B.html)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取矩阵的行数和列数
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        // 读取矩阵
        int[][] grid = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }

        // 沉没所有孤岛
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 如果当前格子是陆地（值为1）
                if (grid[i][j] == 1) {
                    // 使用深度优先搜索检查是否为孤岛，并沉没孤岛
                    if (dfs(grid, i, j, new boolean[N][M])) {
                        sinkIsland(grid, i, j);
                    }
                }
            }
        }

        // 输出沉没后的矩阵
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    // 深度优先搜索方法，用于检查岛屿是否为孤岛
    public static boolean dfs(int[][] grid, int i, int j, boolean[][] visited) {
        // 检查当前格子是否越界或是否是水域（值为0）
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return true; // 如果是越界或水域，返回true
        }

        // 检查当前格子是否接触到矩阵的边缘
        if (i == 0 || i == grid.length - 1 || j == 0 || j == grid[0].length - 1) {
            return false; // 如果接触到边缘，返回false表示该岛屿不是孤岛
        }

        // 如果当前格子已经访问过，返回true
        if (visited[i][j]) {
            return true;
        }

        visited[i][j] = true; // 将当前格子标记为已访问

        // 递归检查当前格子上、下、左、右四个方向的岛屿是否为孤岛
        boolean up = dfs(grid, i - 1, j, visited); // 上
        boolean down = dfs(grid, i + 1, j, visited); // 下
        boolean left = dfs(grid, i, j - 1, visited); // 左
        boolean right = dfs(grid, i, j + 1, visited); // 右

        // 如果任意一个方向的岛屿不是孤岛，返回false
        return up && down && left && right;
    }

    // 沉没孤岛的方法
    public static void sinkIsland(int[][] grid, int i, int j) {
        // 检查当前格子是否越界或是否是水域（值为0）
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return; // 如果是越界或水域，返回
        }

        grid[i][j] = 0; // 将当前格子标记为水域（值设为0）

        // 递归沉没当前格子上、下、左、右四个方向的岛屿
        sinkIsland(grid, i - 1, j); // 上
        sinkIsland(grid, i + 1, j); // 下
        sinkIsland(grid, i, j - 1); // 左
        sinkIsland(grid, i, j + 1); // 右
    }
}
```

## 水流问题

题目链接/文章讲解：[代码随想录](https://www.programmercarl.com/kamacoder/0103.%E6%B0%B4%E6%B5%81%E9%97%AE%E9%A2%98.html)

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取矩阵的行数和列数
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        // 读取矩阵
        int[][] grid = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }

        // 初始化两个布尔矩阵
        boolean[][] canReachFirst = new boolean[N][M];
        boolean[][] canReachSecond = new boolean[N][M];

        // 从第一组边界开始进行 BFS
        bfs(grid, canReachFirst, true);

        // 从第二组边界开始进行 BFS
        bfs(grid, canReachSecond, false);

        // 找出在两个标记矩阵中都被标记为 True 的单元格
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (canReachFirst[i][j] && canReachSecond[i][j]) {
                    result.add(new int[]{i, j});
                }
            }
        }

        // 输出结果
        for (int[] cell : result) {
            System.out.println(cell[0] + " " + cell[1]);
        }
    }

    // 广度优先搜索方法
    public static void bfs(int[][] grid, boolean[][] canReach, boolean isFirstGroup) {
        int N = grid.length;
        int M = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();

        // 初始化队列，添加第一组或第二组边界的单元格
        if (isFirstGroup) {
            // 第一组边界：左边界和上边界
            for (int i = 0; i < N; i++) {
                queue.add(new int[]{i, 0});
                canReach[i][0] = true;
            }
            for (int j = 1; j < M; j++) {
                queue.add(new int[]{0, j});
                canReach[0][j] = true;
            }
        } else {
            // 第二组边界：右边界和下边界
            for (int i = 0; i < N; i++) {
                queue.add(new int[]{i, M - 1});
                canReach[i][M - 1] = true;
            }
            for (int j = 0; j < M - 1; j++) {
                queue.add(new int[]{N - 1, j});
                canReach[N - 1][j] = true;
            }
        }

        // 方向数组，表示上下左右四个方向
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // 开始 BFS
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0];
            int y = cell[1];

            // 遍历四个方向
            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                // 检查新位置是否越界
                if (newX >= 0 && newX < N && newY >= 0 && newY < M) {
                    // 检查新位置是否可以到达，并且高度不高于当前位置
                    if (!canReach[newX][newY] && grid[newX][newY] >= grid[x][y]) {
                        canReach[newX][newY] = true;
                        queue.add(new int[]{newX, newY});
                    }
                }
            }
        }
    }
}
```

## 建造最大岛屿

题目链接/文章讲解：[代码随想录](https://www.programmercarl.com/kamacoder/0104.%E5%BB%BA%E9%80%A0%E6%9C%80%E5%A4%A7%E5%B2%9B%E5%B1%BF.html)

```java
import java.util.*;

public class Main {
    // 记录每个岛屿的面积
    static int islandArea;
    // 用于标记不同岛屿的编号
    static int islandMark;
    // 定义二维数组表示四个方位（上下左右）
    static int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    // 使用深度优先搜索（DFS）来标记岛屿并计算其面积
    public static void depthFirstSearch(int[][] grid, int x, int y, boolean[][] visited) {
        // 边界条件检查
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) return;
        // 如果已经访问过或者是海水，直接返回
        if (visited[x][y] || grid[x][y] == 0) return;

        visited[x][y] = true; // 标记为已访问
        islandArea++; // 增加当前岛屿的面积计数
        grid[x][y] = islandMark; // 将岛屿标记为当前编号

        // 继续向四个方向搜索
        for (int[] direction : directions) {
            depthFirstSearch(grid, x + direction[0], y + direction[1], visited);
        }
    }

    public static void main(String[] args) {
        // 接收输入
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();

        int[][] grid = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }

        // 初始化岛屿标记，从2开始（0表示海水，1表示岛屿）
        islandMark = 2;

        // 创建一个boolean数组来记录每个位置是否被访问过
        boolean[][] visited = new boolean[rows][cols];

        // 创建一个HashMap来记录每个岛屿的标记号和对应面积
        HashMap<Integer, Integer> islandSizes = new HashMap<>();

        // 创建一个HashSet用于判断某一水域周围是否存在不同标记的岛屿
        HashSet<Integer> adjacentIslandMarks = new HashSet<>();

        // 检查是否全是岛屿
        boolean isAllIsland = true;

        // 遍历二维数组进行DFS搜索，标记每个岛屿并记录其面积
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) isAllIsland = false; // 发现海水
                if (grid[i][j] == 1) {
                    islandArea = 0; // 重置岛屿面积计数
                    depthFirstSearch(grid, i, j, visited); // 执行DFS
                    islandSizes.put(islandMark, islandArea); // 记录岛屿面积
                    islandMark++; // 更新岛屿标记
                }
            }
        }

        // 初始化结果变量
        int maxConnectedArea = 0;
        if (isAllIsland) maxConnectedArea = rows * cols; // 如果全是岛屿，直接设置为总面积

        // 计算每个水域周围相邻岛屿的面积之和
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) { // 只检查水域
                    adjacentIslandMarks.clear(); // 清空临时存储的岛屿标记
                    int currentSize = 1; // 将当前位置视为岛屿开始，初始面积为1

                    // 检查四个方向
                    for (int[] direction : directions) {
                        int adjacentRow = i + direction[0];
                        int adjacentCol = j + direction[1];

                        // 边界条件检查
                        if (adjacentRow < 0 || adjacentRow >= rows || adjacentCol < 0 || adjacentCol >= cols) continue;
                        int adjacentMark = grid[adjacentRow][adjacentCol];

                        // 跳过已访问或未记录的岛屿
                        if (adjacentIslandMarks.contains(adjacentMark) || !islandSizes.containsKey(adjacentMark)) continue;

                        adjacentIslandMarks.add(adjacentMark); // 记录相邻岛屿
                        currentSize += islandSizes.get(adjacentMark); // 增加面积
                    }

                    maxConnectedArea = Math.max(maxConnectedArea, currentSize); // 更新最大面积
                }
            }
        }

        // 输出结果
        System.out.println(maxConnectedArea);
    }
}
```
