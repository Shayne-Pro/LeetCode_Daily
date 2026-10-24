# Day54 · 图论Part06

> 作者：[夜雨翦春韭（Shayne-Pro）](https://blog.csdn.net/weixin_43724673) · 原文发布：2024-10-27
> CSDN 原文：[《【代码随想录Day54】图论Part06》](https://blog.csdn.net/weixin_43724673/article/details/143268938) · [返回目录](../README.md)

## 冗余连接

题目链接/文章讲解：[代码随想录](https://www.programmercarl.com/kamacoder/0108.%E5%86%97%E4%BD%99%E8%BF%9E%E6%8E%A5.html)

```java
import java.util.Scanner;

public class Main {
    private int numberOfNodes; // 节点数量
    private int[] parent; // 存储每个节点的父节点

    // 构造函数初始化并查集
    public Main(int size) {
        numberOfNodes = size;
        parent = new int[size + 1]; // 根据节点数量初始化数组
        initializeUnionFind();
    }

    // 初始化并查集，将每个节点的父节点设为自身
    private void initializeUnionFind() {
        for (int i = 0; i <= numberOfNodes; i++) {
            parent[i] = i;
        }
    }

    // 查找节点node的根节点，并进行路径压缩
    private int find(int node) {
        if (node == parent[node]) {
            return node;
        }
        // 路径压缩
        parent[node] = find(parent[node]);
        return parent[node];
    }

    // 判断节点nodeU和节点nodeV是否属于同一集合
    public boolean isSameSet(int nodeU, int nodeV) {
        return find(nodeU) == find(nodeV);
    }

    // 将节点nodeV连接到节点nodeU
    public void union(int nodeU, int nodeV) {
        int rootU = find(nodeU); // 寻找nodeU的根
        int rootV = find(nodeV); // 寻找nodeV的根
        if (rootU != rootV) {
            parent[rootV] = rootU; // 将nodeV的根节点设为nodeU的根节点
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfEdges = scanner.nextInt(); // 输入边的数量
        Main unionFind = new Main(numberOfEdges); // 初始化并查集

        for (int i = 0; i < numberOfEdges; i++) {
            int nodeU = scanner.nextInt(); // 输入节点U
            int nodeV = scanner.nextInt(); // 输入节点V
            if (unionFind.isSameSet(nodeU, nodeV)) {
                System.out.println(nodeU + " " + nodeV); // 如果nodeU和nodeV已连接，输出并结束
                return;
            } else {
                unionFind.union(nodeU, nodeV); // 否则将nodeU和nodeV连接
            }
        }
        scanner.close(); // 关闭扫描器
    }
}
```

## 冗余连接 II

题目链接/文章讲解：[代码随想录](https://www.programmercarl.com/kamacoder/0109.%E5%86%97%E4%BD%99%E8%BF%9E%E6%8E%A5II.html)

```java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static int numberOfNodes;
    private static int[] parent;

    // 并查集初始化
    private static void initializeUnionFind() {
        for (int i = 1; i <= numberOfNodes; i++) {
            parent[i] = i;
        }
    }

    // 并查集里寻根的过程
    private static int find(int node) {
        if (node != parent[node]) {
            parent[node] = find(parent[node]); // 路径压缩
        }
        return parent[node];
    }

    // 将边 v -> u 加入并查集
    private static void union(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);
        if (rootU != rootV) {
            parent[rootV] = rootU; // 将 v 的根指向 u 的根
        }
    }

    // 判断 u 和 v 是否找到同一个根
    private static boolean areInSameComponent(int u, int v) {
        return find(u) == find(v);
    }

    // 在有向图里找到删除的那条边，使其变成树
    private static void findEdgeToRemove(List<int[]> edges) {
        initializeUnionFind(); // 初始化并查集
        for (int i = 0; i < edges.size(); i++) { // 遍历所有的边
            int[] edge = edges.get(i);
            if (areInSameComponent(edge[0], edge[1])) { // 构成有向环了，就是要删除的边
                System.out.println(edge[0] + " " + edge[1]);
                return;
            } else {
                union(edge[0], edge[1]);
            }
        }
    }

    // 删一条边之后判断是不是树
    private static boolean isTreeAfterRemovingEdge(List<int[]> edges, int edgeToRemoveIndex) {
        initializeUnionFind(); // 初始化并查集
        for (int i = 0; i < edges.size(); i++) {
            if (i == edgeToRemoveIndex) continue; // 跳过要删除的边
            int[] edge = edges.get(i);
            if (areInSameComponent(edge[0], edge[1])) { // 构成有向环了，一定不是树
                return false;
            }
            union(edge[0], edge[1]);
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        numberOfNodes = scanner.nextInt();
        parent = new int[numberOfNodes + 1];
        List<int[]> edges = new ArrayList<>();
        int[] inDegree = new int[numberOfNodes + 1]; // 记录节点入度

        for (int i = 0; i < numberOfNodes; i++) {
            int startNode = scanner.nextInt();
            int endNode = scanner.nextInt();
            inDegree[endNode]++;
            edges.add(new int[]{startNode, endNode});
        }

        List<Integer> edgeIndicesWithInDegreeTwo = new ArrayList<>(); // 记录入度为2的边索引
        // 找入度为2的节点所对应的边，注意要倒序，因为优先删除最后出现的一条边
        for (int i = numberOfNodes - 1; i >= 0; i--) {
            if (inDegree[edges.get(i)[1]] == 2) {
                edgeIndicesWithInDegreeTwo.add(i);
            }
        }

        // 情况一、情况二
        if (!edgeIndicesWithInDegreeTwo.isEmpty()) {
            // 放在 edgeIndicesWithInDegreeTwo 里的边已经按照倒叙放的，所以这里就优先删第一个
            if (isTreeAfterRemovingEdge(edges, edgeIndicesWithInDegreeTwo.get(0))) {
                System.out.println(edges.get(edgeIndicesWithInDegreeTwo.get(0))[0] + " " + edges.get(edgeIndicesWithInDegreeTwo.get(0))[1]);
            } else {
                System.out.println(edges.get(edgeIndicesWithInDegreeTwo.get(1))[0] + " " + edges.get(edgeIndicesWithInDegreeTwo.get(1))[1]);
            }
            return;
        }

        // 处理情况三
        // 明确没有入度为2的情况，那么一定有有向环，找到构成环的边返回
        findEdgeToRemove(edges);
    }
}
```
