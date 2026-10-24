# Day58 · 图论Part09

> 作者：[夜雨翦春韭（Shayne-Pro）](https://blog.csdn.net/weixin_43724673) · 原文发布：2024-10-30
> CSDN 原文：[《【代码随想录Day58】图论Part09》](https://blog.csdn.net/weixin_43724673/article/details/143369360) · [返回目录](../README.md)

## dijkstra（堆优化版）精讲

题目链接/文章讲解：[代码随想录](https://www.programmercarl.com/kamacoder/0047.%E5%8F%82%E4%BC%9Adijkstra%E5%A0%86.html)

```java
import java.util.*;

class Edge {
    int to;  // 邻接顶点
    int val; // 边的权重

    Edge(int to, int val) {
        this.to = to;
        this.val = val;
    }
}

class Pair<U, V> {
    public final U first;  // 节点
    public final V second; // 从源点到该节点的权重

    public Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取节点数和边数
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // 创建图的邻接表
        List<List<Edge>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // 读取边的信息
        for (int i = 0; i < m; i++) {
            int p1 = scanner.nextInt();
            int p2 = scanner.nextInt();
            int val = scanner.nextInt();
            graph.get(p1).add(new Edge(p2, val));
        }

        int start = 1;  // 起点
        int end = n;    // 终点

        // 存储从源点到每个节点的最短距离
        int[] minDist = new int[n + 1];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[start] = 0;  // 起始点到自身的距离为0

        // 记录顶点是否被访问过
        boolean[] visited = new boolean[n + 1];

        // 优先队列，用于选择当前最短路径的节点
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(pair -> pair.second));

        // 初始化队列，添加源点
        pq.add(new Pair<>(start, 0));

        while (!pq.isEmpty()) {
            // 取出当前距离源点最近的节点
            Pair<Integer, Integer> cur = pq.poll();
            int currentNode = cur.first;

            // 如果该节点已被访问，跳过
            if (visited[currentNode]) continue;

            // 标记该节点为已访问
            visited[currentNode] = true;

            // 更新与当前节点相连的邻接节点的路径
            for (Edge edge : graph.get(currentNode)) {
                // 如果该邻接节点未被访问且新路径更短，则更新最短路径
                if (!visited[edge.to] && minDist[currentNode] + edge.val < minDist[edge.to]) {
                    minDist[edge.to] = minDist[currentNode] + edge.val;
                    pq.add(new Pair<>(edge.to, minDist[edge.to])); // 将新路径加入优先队列
                }
            }
        }

        // 输出结果
        System.out.println(minDist[end] == Integer.MAX_VALUE ? -1 : minDist[end]);
    }
}
```

## Bellman_ford 算法精讲

题目链接/文章讲解：[代码随想录](https://www.programmercarl.com/kamacoder/0094.%E5%9F%8E%E5%B8%82%E9%97%B4%E8%B4%A7%E7%89%A9%E8%BF%90%E8%BE%93I.html)

```java
import java.util.*;

public class Main {

    // 定义边的内部类
    static class Edge {
        int from; // 起始节点
        int to;   // 结束节点
        int val;  // 边的权重

        public Edge(int from, int to, int val) {
            this.from = from;
            this.to = to;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        int numberOfNodes = scanner.nextInt(); // 节点数量
        int numberOfEdges = scanner.nextInt();  // 边的数量
        List<Edge> edges = new ArrayList<>();

        // 读取每一条边的信息
        for (int i = 0; i < numberOfEdges; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int val = scanner.nextInt();
            edges.add(new Edge(from, to, val));
        }

        // 存储从起点到各节点的最小距离
        int[] minDistance = new int[numberOfNodes + 1];
        Arrays.fill(minDistance, Integer.MAX_VALUE); // 初始化最小距离为无穷大
        minDistance[1] = 0; // 起点到自身的距离为0

        // 执行 Bellman-Ford 算法，放松所有边 n-1 次
        for (int i = 1; i < numberOfNodes; i++) {
            for (Edge edge : edges) {
                // 如果起始节点的距离不为无穷大，且通过当前边可以找到更短的路径
                if (minDistance[edge.from] != Integer.MAX_VALUE) {
                    int newDistance = minDistance[edge.from] + edge.val;
                    if (newDistance < minDistance[edge.to]) {
                        minDistance[edge.to] = newDistance; // 更新最小距离
                    }
                }
            }
        }

        // 输出结果
        if (minDistance[numberOfNodes] == Integer.MAX_VALUE) {
            System.out.println("unconnected"); // 如果目标节点不可达
        } else {
            System.out.println(minDistance[numberOfNodes]); // 输出目标节点的最小距离
        }

        scanner.close(); // 关闭扫描器以释放资源
    }
}
```
