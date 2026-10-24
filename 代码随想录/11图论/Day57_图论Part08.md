# Day57 · 图论Part08

> 作者：[夜雨翦春韭（Shayne-Pro）](https://blog.csdn.net/weixin_43724673) · 原文发布：2024-10-30
> CSDN 原文：[《【代码随想录Day57】图论Part08》](https://blog.csdn.net/weixin_43724673/article/details/143369331) · [返回目录](../README.md)

## 拓扑排序精讲

题目链接/文章讲解：[代码随想录](https://www.programmercarl.com/kamacoder/0117.%E8%BD%AF%E4%BB%B6%E6%9E%84%E5%BB%BA.html)

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取文件数量 n 和依赖关系数量 m
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // 初始化记录文件依赖关系的列表和每个文件的入度数组
        List<List<Integer>> umap = new ArrayList<>(n); // 记录文件依赖关系
        int[] inDegree = new int[n]; // 记录每个文件的入度

        // 初始化 umap，每个文件的依赖列表
        for (int i = 0; i < n; i++) {
            umap.add(new ArrayList<>());
        }

        // 读取依赖关系
        for (int i = 0; i < m; i++) {
            int s = scanner.nextInt(); // 依赖的源文件
            int t = scanner.nextInt(); // 依赖的目标文件
            umap.get(s).add(t); // 记录s指向哪些文件
            inDegree[t]++; // t的入度加一，表示有一个文件依赖于t
        }

        // 使用 ArrayDeque 作为队列来进行拓扑排序
        Deque<Integer> queue = new ArrayDeque<>();

        // 将所有入度为0的文件加入队列
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i); // 入度为0的文件可以作为开头
            }
        }

        // 存储拓扑排序结果
        List<Integer> result = new ArrayList<>();

        // 拓扑排序过程
        while (!queue.isEmpty()) {
            int cur = queue.poll(); // 当前选中的文件
            result.add(cur); // 将当前文件加入结果列表

            // 遍历当前文件指向的所有文件
            for (int file : umap.get(cur)) {
                inDegree[file]--; // 当前文件指向的文件入度-1
                if (inDegree[file] == 0) {
                    queue.add(file); // 如果入度为0，则加入队列
                }
            }
        }

        // 检查是否完成了拓扑排序
        if (result.size() == n) {
            // 如果结果列表的大小等于文件数量，说明拓扑排序成功
            StringBuilder output = new StringBuilder();
            for (int i = 0; i < result.size(); i++) {
                output.append(result.get(i)); // 添加文件到输出
                if (i < result.size() - 1) {
                    output.append(" "); // 添加空格分隔文件
                }
            }
            System.out.println(output); // 输出最终结果
        } else {
            // 如果结果列表的大小不等于文件数量，说明存在循环依赖
            System.out.println(-1);
        }
    }
}
```

## dijkstra（朴素版）精讲

题目链接/文章讲解：[代码随想录](https://www.programmercarl.com/kamacoder/0047.%E5%8F%82%E4%BC%9Adijkstra%E6%9C%B4%E7%B4%A0.html)

```java
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取节点数量 n 和边的数量 m
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // 初始化图的邻接矩阵，使用 Integer.MAX_VALUE 表示无穷大
        int[][] grid = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(grid[i], Integer.MAX_VALUE);
        }

        // 读取边的信息，建立邻接矩阵
        for (int i = 0; i < m; i++) {
            int p1 = scanner.nextInt();
            int p2 = scanner.nextInt();
            int val = scanner.nextInt();
            // 存储边的权值，假设图是有向图
            grid[p1][p2] = Math.min(grid[p1][p2], val); // 保证边的权值最小
        }

        int start = 1; // 起始点
        int end = n;   // 终点

        // 存储从起始点到每个节点的最短距离
        int[] minDist = new int[n + 1];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[start] = 0; // 起始点到自身的距离为0

        // 记录每个节点是否被访问过
        boolean[] visited = new boolean[n + 1];

        // Dijkstra 算法主循环
        for (int i = 1; i <= n; i++) {
            // 1. 找到未访问的节点中最小距离的节点
            int cur = -1;
            int minVal = Integer.MAX_VALUE;

            for (int v = 1; v <= n; ++v) {
                if (!visited[v] && minDist[v] < minVal) {
                    minVal = minDist[v];
                    cur = v; // 记录当前节点
                }
            }

            // 如果所有节点都已访问，或剩下的节点不可达，则退出循环
            if (cur == -1) break;

            visited[cur] = true; // 2. 标记当前节点已被访问

            // 3. 更新未访问节点到起始点的距离
            for (int v = 1; v <= n; v++) {
                // 如果有边且未访问，更新最短距离
                if (!visited[v] && grid[cur][v] != Integer.MAX_VALUE) {
                    minDist[v] = Math.min(minDist[v], minDist[cur] + grid[cur][v]);
                }
            }
        }

        // 输出结果
        System.out.println(minDist[end] == Integer.MAX_VALUE ? -1 : minDist[end]);
    }
}
```
