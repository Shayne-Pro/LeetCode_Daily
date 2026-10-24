# Day59 · 图论Part10

> 作者：[夜雨翦春韭（Shayne-Pro）](https://blog.csdn.net/weixin_43724673) · 原文发布：2024-10-30
> CSDN 原文：[《【代码随想录Day59】图论Part10》](https://blog.csdn.net/weixin_43724673/article/details/143369398) · [返回目录](../README.md)

## Bellman_ford 队列优化算法（又名 SPFA）

题目链接/文章讲解：[代码随想录](https://www.programmercarl.com/kamacoder/0094.%E5%9F%8E%E5%B8%82%E9%97%B4%E8%B4%A7%E7%89%A9%E8%BF%90%E8%BE%93I-SPFA.html)

```java
import java.util.*;

public class Main {

    // 定义一个内部类 Edge，用于表示图中的边
    static class Edge {
        int from; // 边的起点
        int to;   // 边的终点
        int val;  // 边的权重
        public Edge(int from, int to, int val) {
            this.from = from;
            this.to = to;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        // 输入处理
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 节点数量
        int m = sc.nextInt(); // 边的数量
        List<List<Edge>> graph = new ArrayList<>(n + 1);

        // 初始化图的邻接表
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // 读取每一条边的信息并添加到图中
        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int val = sc.nextInt();
            graph.get(from).add(new Edge(from, to, val));
        }

        // 定义 minDist 数组以记录从起始节点到每个节点的最小距离
        int[] minDist = new int[n + 1];
        Arrays.fill(minDist, Integer.MAX_VALUE); // 初始化为无穷大
        minDist[1] = 0; // 起始节点到自身的距离为0

        // 使用队列来存储待处理的节点
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1); // 将起始节点加入队列

        // 定义一个布尔数组以记录节点是否在队列中
        boolean[] isInQueue = new boolean[n + 1];
        isInQueue[1] = true; // 起始节点已在队列中

        // 进行节点的松弛操作
        while (!queue.isEmpty()) {
            int curNode = queue.poll(); // 取出队首节点
            isInQueue[curNode] = false; // 标记为不在队列中

            // 遍历当前节点的所有边
            for (Edge edge : graph.get(curNode)) {
                // 如果通过当前节点到达边的终点的距离更小，则松弛边
                if (minDist[edge.to] > minDist[curNode] + edge.val) {
                    minDist[edge.to] = minDist[curNode] + edge.val;

                    // 如果终点不在队列中，则加入队列
                    if (!isInQueue[edge.to]) {
                        queue.offer(edge.to);
                        isInQueue[edge.to] = true; // 标记为在队列中
                    }
                }
            }
        }

        // 输出结果
        if (minDist[n] == Integer.MAX_VALUE) {
            System.out.println("unconnected"); // 如果终点不可达
        } else {
            System.out.println(minDist[n]); // 输出终点的最小距离
        }
    }
}
```

## bellman_ford 之判断负权回路

题目链接/文章讲解：[代码随想录](https://www.programmercarl.com/kamacoder/0095.%E5%9F%8E%E5%B8%82%E9%97%B4%E8%B4%A7%E7%89%A9%E8%BF%90%E8%BE%93II.html)

```java
import java.util.*;

public class Main {
    // 定义一个内部类 Edge 用于表示图中的边
    static class Edge {
        int from; // 边的起始节点
        int to;   // 边的结束节点
        int val;  // 边的权重
        public Edge(int from, int to, int val) {
            this.from = from;
            this.to = to;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        // 输入处理
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 节点数量
        int m = sc.nextInt(); // 边的数量

        // 创建邻接表表示图
        List<List<Edge>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // 读取边的信息并填充图
        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int val = sc.nextInt();
            graph.get(from).add(new Edge(from, to, val));
        }

        // 初始化最小距离数组，记录从起点到各个节点的最小距离
        int[] minDist = new int[n + 1];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[1] = 0; // 起点到自身的距离为0

        // 使用队列进行节点的松弛操作
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);

        // 记录每个节点被加入队列的次数
        int[] count = new int[n + 1];
        count[1]++;

        // 记录节点是否在队列中，避免重复入队
        boolean[] isInQueue = new boolean[n + 1];

        // 标志位用于检测是否存在负权环
        boolean hasNegativeCycle = false;

        // 进行 SPFA 算法
        while (!queue.isEmpty()) {
            int curNode = queue.poll();
            isInQueue[curNode] = false; // 当前节点出队，不在队列中

            // 遍历当前节点的所有边
            for (Edge edge : graph.get(curNode)) {
                // 如果找到更短的路径，进行松弛操作
                if (minDist[edge.to] > minDist[edge.from] + edge.val) {
                    minDist[edge.to] = minDist[edge.from] + edge.val;

                    // 如果目标节点不在队列中，则入队
                    if (!isInQueue[edge.to]) {
                        queue.offer(edge.to);
                        count[edge.to]++; // 记录入队次数
                        isInQueue[edge.to] = true; // 标记为在队列中
                    }

                    // 如果某节点入队次数超过 n，说明存在负权环
                    if (count[edge.to] == n) {
                        hasNegativeCycle = true;
                        queue.clear(); // 清空队列
                        break;
                    }
                }
            }
        }

        // 输出结果
        if (hasNegativeCycle) {
            System.out.println("circle"); // 存在负权环
        } else if (minDist[n] == Integer.MAX_VALUE) {
            System.out.println("unconnected"); // 不可达
        } else {
            System.out.println(minDist[n]); // 输出从起点到终点的最小距离
        }
    }
}
```

## bellman_ford 之单源有限最短路

题目链接/文章讲解：[代码随想录](https://www.programmercarl.com/kamacoder/0096.%E5%9F%8E%E5%B8%82%E9%97%B4%E8%B4%A7%E7%89%A9%E8%BF%90%E8%BE%93III.html)

```java
import java.util.*;

public class Main {
    // 定义边的内部类
    static class Edge {
        int from; // 边的起点
        int to;   // 边的终点
        int val;  // 边的权重

        public Edge(int from, int to, int val) {
            this.from = from;
            this.to = to;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        // 输入处理
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 顶点数量
        int m = sc.nextInt(); // 边的数量

        List<Edge> graph = new ArrayList<>(); // 存储图的边

        // 读取每条边的信息
        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int val = sc.nextInt();
            graph.add(new Edge(from, to, val));
        }

        int src = sc.nextInt(); // 源点
        int dst = sc.nextInt(); // 目标点
        int k = sc.nextInt();   // 最大中转次数

        // 初始化最小距离数组
        int[] minDist = new int[n + 1];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[src] = 0; // 源点到自身的距离为0

        // Bellman-Ford算法的主循环
        for (int i = 0; i < k + 1; i++) { // 进行k+1次松弛操作
            int[] minDistCopy = Arrays.copyOf(minDist, n + 1); // 复制当前的最小距离

            // 遍历图中的每一条边
            for (Edge edge : graph) {
                int from = edge.from;
                int to = edge.to;
                int val = edge.val;

                // 如果from的最小距离是有限的并且to的最小距离可以被更新
                if (minDistCopy[from] != Integer.MAX_VALUE && minDist[to] > minDistCopy[from] + val) {
                    minDist[to] = minDistCopy[from] + val; // 更新to的最小距离
                }
            }
        }

        // 输出结果
        if (minDist[dst] == Integer.MAX_VALUE) {
            System.out.println("unreachable"); // 如果dst不可达
        } else {
            System.out.println(minDist[dst]); // 输出从src到dst的最小距离
        }

        sc.close(); // 关闭扫描器
    }
}
```
