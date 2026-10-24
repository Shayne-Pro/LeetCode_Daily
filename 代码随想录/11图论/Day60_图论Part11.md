# Day60 · 图论Part11

> 作者：[夜雨翦春韭（Shayne-Pro）](https://blog.csdn.net/weixin_43724673) · 原文发布：2024-10-31
> CSDN 原文：[《【代码随想录Day60】图论Part11》](https://blog.csdn.net/weixin_43724673/article/details/143393054) · [返回目录](../README.md)

## Floyd 算法精讲

题目链接/文章讲解：[代码随想录](https://www.programmercarl.com/kamacoder/0097.%E5%B0%8F%E6%98%8E%E9%80%9B%E5%85%AC%E5%9B%AD.html)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 创建 Scanner 对象以读取输入
        Scanner scanner = new Scanner(System.in);

        // 读取图的顶点数 n 和边的数 m
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int p1, p2, val;

        // 创建一个三维数组 grid，用于存储最短路径的距离
        // grid[i][j][k] 表示在考虑前 k 个顶点的情况下，从 i 到 j 的最短路径长度
        int[][][] grid = new int[n + 1][n + 1][n + 1];

        // 初始化 grid 数组，所有的距离设置为一个很大的值 10005（代表无穷大）
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= n; k++) {
                    grid[i][j][k] = 10005; // 初始化为一个很大的值
                }
            }
        }

        // 读取边的信息
        for (int i = 0; i < m; i++) {
            p1 = scanner.nextInt(); // 读取起点
            p2 = scanner.nextInt(); // 读取终点
            val = scanner.nextInt(); // 读取边的权重

            // 因为是无向图，设置两条边的距离
            grid[p1][p2][0] = val; // 从 p1 到 p2 的距离
            grid[p2][p1][0] = val; // 从 p2 到 p1 的距离
        }

        // 开始执行 Floyd-Warshall 算法
        for (int k = 1; k <= n; k++) { // 考虑第 k 个顶点
            for (int i = 1; i <= n; i++) { // 从顶点 i
                for (int j = 1; j <= n; j++) { // 到顶点 j
                    // 更新从 i 到 j 的最短路径，考虑经过第 k 个顶点
                    grid[i][j][k] = Math.min(grid[i][j][k - 1], grid[i][k][k - 1] + grid[k][j][k - 1]);
                }
            }
        }

        // 读取查询的数量 z
        int z = scanner.nextInt();
        while (z-- > 0) { // 对每一个查询进行处理
            int start = scanner.nextInt(); // 读取起始节点
            int end = scanner.nextInt();   // 读取结束节点

            // 检查从 start 到 end 的最短路径长度
            if (grid[start][end][n] == 10005) {
                System.out.println(-1); // 若无路径则输出 -1
            } else {
                System.out.println(grid[start][end][n]); // 输出最短路径的长度
            }
        }

        // 关闭 Scanner 对象
        scanner.close();
    }
}
```

## A \* 算法精讲 （A star 算法）

题目链接/文章讲解：[A \* 算法精讲 （A star 算法） | 代码随想录](https://www.programmercarl.com/kamacoder/0126.%E9%AA%91%E5%A3%AB%E7%9A%84%E6%94%BB%E5%87%BBastar.html)

```java
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    // 定义一个存储移动步数的数组
    static int[][] moves = new int[1001][1001];
    // 定义骑士的移动方向
    static int[][] dir = {{-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}};
    static int b1, b2; // 目标位置

    // 定义骑士的状态
    static class Knight implements Comparable<Knight> {
        int x, y; // 当前坐标
        int g, h, f; // G, H, F 值

        @Override
        public int compareTo(Knight k) { // 重载比较方法，以便优先队列可以排序
            return Integer.compare(this.f, k.f);
        }
    }

    // 估算函数，使用欧几里得距离的平方
    static int Heuristic(Knight k) {
        return (k.x - b1) * (k.x - b1) + (k.y - b2) * (k.y - b2); // 省略开根号以提高精度
    }

    // A* 算法实现
    static void astar(Knight k) {
        PriorityQueue<Knight> que = new PriorityQueue<>(); // 创建优先队列
        que.add(k); // 将起始节点加入队列

        while (!que.isEmpty()) {
            Knight cur = que.poll(); // 取出队列中优先级最高的节点

            // 如果到达目标位置，则结束搜索
            if (cur.x == b1 && cur.y == b2) {
                break;
            }

            // 遍历所有可能的骑士移动
            for (int i = 0; i < 8; i++) {
                Knight next = new Knight(); // 创建下一个节点
                next.x = cur.x + dir[i][0]; // 更新 x 坐标
                next.y = cur.y + dir[i][1]; // 更新 y 坐标

                // 检查下一个位置是否在有效范围内
                if (next.x < 1 || next.x > 1000 || next.y < 1 || next.y > 1000) {
                    continue;
                }

                // 检查该位置是否已经访问过
                if (moves[next.x][next.y] == 0) {
                    moves[next.x][next.y] = moves[cur.x][cur.y] + 1; // 更新步数

                    // 计算 G, H 和 F 值
                    next.g = cur.g + 5; // 统一不开根号以提高精度
                    next.h = Heuristic(next);
                    next.f = next.g + next.h;
                    que.add(next); // 将下一个节点加入队列
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 读取测试用例的数量

        while (n-- > 0) {
            int a1 = scanner.nextInt(); // 起始 x 坐标
            int a2 = scanner.nextInt(); // 起始 y 坐标
            b1 = scanner.nextInt(); // 目标 x 坐标
            b2 = scanner.nextInt(); // 目标 y 坐标

            // 清空步数数组
            for (int i = 0; i < moves.length; i++) {
                for (int j = 0; j < moves[i].length; j++) {
                    moves[i][j] = 0;
                }
            }

            // 初始化起始节点
            Knight start = new Knight();
            start.x = a1;
            start.y = a2;
            start.g = 0;
            start.h = Heuristic(start);
            start.f = start.g + start.h;

            // 执行 A* 算法
            astar(start);

            // 输出到达目标位置的步数
            System.out.println(moves[b1][b2]);
        }
        scanner.close(); // 关闭扫描器
    }
}
```

## 最短路算法总结篇

题目链接/文章讲解：[最短路算法总结篇 | 代码随想录](https://www.programmercarl.com/kamacoder/%E6%9C%80%E7%9F%AD%E8%B7%AF%E9%97%AE%E9%A2%98%E6%80%BB%E7%BB%93%E7%AF%87.html)

## 图论总结

题目链接/文章讲解：[图论总结篇 | 代码随想录](https://www.programmercarl.com/kamacoder/%E5%9B%BE%E8%AE%BA%E6%80%BB%E7%BB%93%E7%AF%87.html)
