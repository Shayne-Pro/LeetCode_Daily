# Day53 · 图论Part05

> 作者：[夜雨翦春韭（Shayne-Pro）](https://blog.csdn.net/weixin_43724673) · 原文发布：2024-10-27
> CSDN 原文：[《【代码随想录Day53】图论Part05》](https://blog.csdn.net/weixin_43724673/article/details/143268817) · [返回目录](../README.md)

## 并查集理论基础

题目链接/文章讲解：[并查集理论基础 | 代码随想录](https://www.programmercarl.com/kamacoder/%E5%9B%BE%E8%AE%BA%E5%B9%B6%E6%9F%A5%E9%9B%86%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80.html)

## 寻找存在的路径

题目链接/文章讲解：[代码随想录](https://www.programmercarl.com/kamacoder/0107.%E5%AF%BB%E6%89%BE%E5%AD%98%E5%9C%A8%E7%9A%84%E8%B7%AF%E5%BE%84.html)

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int numberOfElements, numberOfConnections;
        Scanner scanner = new Scanner(System.in);

        // 读取元素数量和连接数量
        numberOfElements = scanner.nextInt();
        numberOfConnections = scanner.nextInt();

        // 初始化并查集
        DisjointSetUnion disjointSet = new DisjointSetUnion(numberOfElements + 1);

        // 读取每对连接并合并
        for (int i = 0; i < numberOfConnections; ++i) {
            int elementA = scanner.nextInt();
            int elementB = scanner.nextInt();
            disjointSet.union(elementA, elementB);
        }

        // 检查两个元素是否在同一集合中
        int queryElementA = scanner.nextInt();
        int queryElementB = scanner.nextInt();
        if (disjointSet.isConnected(queryElementA, queryElementB)) {
            System.out.println("1"); // 在同一集合
        } else {
            System.out.println("0"); // 不在同一集合
        }
    }
}

// 并查集实现
class DisjointSetUnion {
    private int[] parent;

    public DisjointSetUnion(int size) {
        parent = new int[size];
        // 初始化每个元素的父节点为自己
        for (int i = 0; i < size; ++i) {
            parent[i] = i;
        }
    }

    // 查找元素的根节点，并进行路径压缩
    public int find(int element) {
        if (element != parent[element]) {
            parent[element] = find(parent[element]); // 路径压缩
        }
        return parent[element];
    }

    // 合并两个元素的集合
    public void union(int elementA, int elementB) {
        int rootA = find(elementA);
        int rootB = find(elementB);
        if (rootA != rootB) {
            parent[rootB] = rootA; // 将B的根节点指向A的根节点
        }
    }

    // 检查两个元素是否在同一集合中
    public boolean isConnected(int elementA, int elementB) {
        return find(elementA) == find(elementB);
    }
}
```
