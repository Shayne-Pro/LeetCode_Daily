# Day55 · 图论Part07

> 作者：[夜雨翦春韭（Shayne-Pro）](https://blog.csdn.net/weixin_43724673) · 原文发布：2024-10-27
> CSDN 原文：[《【代码随想录Day55】图论Part07》](https://blog.csdn.net/weixin_43724673/article/details/143269105) · [返回目录](../README.md)

## prim 算法精讲

题目链接/文章讲解：[代码随想录](https://www.programmercarl.com/kamacoder/0053.%E5%AF%BB%E5%AE%9D-prim.html)

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取顶点数和边数
        int vertexCount = scanner.nextInt();
        int edgeCount = scanner.nextInt();

        // 初始化邻接矩阵，所有值初始化为一个大值，表示无穷大
        int[][] adjacencyMatrix = new int[vertexCount + 1][vertexCount + 1];
        for (int i = 0; i <= vertexCount; i++) {
            Arrays.fill(adjacencyMatrix[i], Integer.MAX_VALUE);
        }

        // 读取边的信息并填充邻接矩阵
        for (int i = 0; i < edgeCount; i++) {
            int startVertex = scanner.nextInt();
            int endVertex = scanner.nextInt();
            int weight = scanner.nextInt();
            adjacencyMatrix[startVertex][endVertex] = weight;
            adjacencyMatrix[endVertex][startVertex] = weight; // 无向图
        }

        // 距离数组，记录每个节点到生成树的最小距离
        int[] minimumDistances = new int[vertexCount + 1];
        Arrays.fill(minimumDistances, Integer.MAX_VALUE);

        // 记录节点是否在生成树中
        boolean[] isInMST = new boolean[vertexCount + 1];

        // Prim算法主循环
        minimumDistances[1] = 0; // 从第一个节点开始
        for (int i = 1; i < vertexCount; i++) {
            int closestVertex = -1;
            int smallestDistance = Integer.MAX_VALUE;

            // 选择距离生成树最近的节点
            for (int j = 1; j <= vertexCount; j++) {
                if (!isInMST[j] && minimumDistances[j] < smallestDistance) {
                    smallestDistance = minimumDistances[j];
                    closestVertex = j;
                }
            }

            // 将最近的节点加入生成树
            isInMST[closestVertex] = true;

            // 更新非生成树节点到生成树的距离
            for (int j = 1; j <= vertexCount; j++) {
                if (!isInMST[j] && adjacencyMatrix[closestVertex][j] < minimumDistances[j]) {
                    minimumDistances[j] = adjacencyMatrix[closestVertex][j];
                }
            }
        }

        // 统计生成树的总权重
        int totalWeight = 0;
        for (int i = 2; i <= vertexCount; i++) {
            totalWeight += minimumDistances[i];
        }

        // 输出结果
        System.out.println(totalWeight);
        scanner.close();
    }
}
```

## kruskal 算法精讲

题目链接/文章讲解：[代码随想录](https://www.programmercarl.com/kamacoder/0053.%E5%AF%BB%E5%AE%9D-Kruskal.html)

```java
import java.util.*;

class Edge {
    int vertex1, vertex2, weight;

    // Edge构造函数，初始化边的两个顶点和权重
    Edge(int vertex1, int vertex2, int weight) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }
}

public class Main {
    private static final int MAX_NODES = 10001; // 最大节点数
    private static int[] parent = new int[MAX_NODES]; // 存储每个节点的父节点

    // 初始化并查集
    public static void initializeUnionFind() {
        for (int i = 0; i < MAX_NODES; i++) {
            parent[i] = i; // 每个节点的父节点初始化为自身
        }
    }

    // 查找操作，寻找节点的根节点
    public static int find(int node) {
        if (node == parent[node]) {
            return node; // 如果节点是根节点，直接返回
        }
        // 路径压缩，优化查找效率
        return parent[node] = find(parent[node]);
    }

    // 合并两个集合
    public static void union(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);
        if (root1 != root2) {
            parent[root2] = root1; // 将一个集合的根节点指向另一个集合的根节点
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int vertexCount = scanner.nextInt(); // 顶点数量
        int edgeCount = scanner.nextInt(); // 边的数量
        List<Edge> edgeList = new ArrayList<>(); // 存储所有边
        int totalWeight = 0; // 最小生成树的权重总和

        // 读取边的信息
        for (int i = 0; i < edgeCount; i++) {
            int vertex1 = scanner.nextInt();
            int vertex2 = scanner.nextInt();
            int weight = scanner.nextInt();
            edgeList.add(new Edge(vertex1, vertex2, weight)); // 添加边到边列表
        }

        // 按照边的权重进行排序
        edgeList.sort(Comparator.comparingInt(edge -> edge.weight));

        // 初始化并查集
        initializeUnionFind();

        // 遍历所有边，执行Kruskal算法
        for (Edge edge : edgeList) {
            int root1 = find(edge.vertex1);
            int root2 = find(edge.vertex2);

            // 如果两个顶点的根节点不同，说明它们不在同一个集合
            if (root1 != root2) {
                totalWeight += edge.weight; // 加入当前边的权重
                union(root1, root2); // 合并两个集合
            }
        }

        // 输出最小生成树的权重总和
        System.out.println(totalWeight);
        scanner.close(); // 关闭扫描器
    }
}
```
