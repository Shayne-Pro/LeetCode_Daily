# Day48 · 图论Part01

> 作者：[夜雨翦春韭（Shayne-Pro）](https://blog.csdn.net/weixin_43724673) · 原文发布：2024-10-22
> CSDN 原文：[《【代码随想录Day48】图论Part01》](https://blog.csdn.net/weixin_43724673/article/details/143155361) · [返回目录](../README.md)

## 图论理论基础

题目链接/文章讲解：[图论理论基础 | 代码随想录](https://www.programmercarl.com/kamacoder/%E5%9B%BE%E8%AE%BA%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80.html)

## 深搜理论基础

题目链接/文章讲解：[深度优先搜索理论基础 | 代码随想录](https://www.programmercarl.com/kamacoder/%E5%9B%BE%E8%AE%BA%E6%B7%B1%E6%90%9C%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80.html)

## 98. 所有可达路径

题目链接/文章讲解：[代码随想录](https://www.programmercarl.com/kamacoder/0098.%E6%89%80%E6%9C%89%E5%8F%AF%E8%BE%BE%E8%B7%AF%E5%BE%84.html)

```java
class Solution {
    // 存储所有从源节点到目标节点的路径
    List<List<Integer>> result = new ArrayList<>();
    // 存储当前路径的节点
    List<Integer> path = new LinkedList<>();

    // 主函数，接收图的邻接矩阵并开始深度优先搜索
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        // 从源节点（节点0）开始，添加到当前路径中
        path.add(0);
        // 调用DFS函数，开始搜索路径
        dfs(graph, 0);
        // 返回所有找到的路径
        return result;
    }

    // 深度优先搜索函数
    public void dfs(int[][] graph, int startIndex) {
        // 如果当前节点是目标节点（最后一个节点），则将当前路径添加到结果中
        if (startIndex == graph.length - 1) {
            result.add(new ArrayList<>(path)); // 将当前路径的副本添加到结果
            return; // 结束当前递归
        }
        // 遍历当前节点的所有邻接节点
        for (int i = 0; i < graph[startIndex].length; i++) {
            // 将邻接节点添加到当前路径
            path.add(graph[startIndex][i]);
            // 递归调用DFS，继续搜索下一个节点
            dfs(graph, graph[startIndex][i]);
            // 回溯：从当前路径中移除最后添加的节点
            path.removeLast();
        }
    }
}
```

## 广搜理论基础

题目链接/文章讲解：[广度优先搜索理论基础 | 代码随想录](https://www.programmercarl.com/kamacoder/%E5%9B%BE%E8%AE%BA%E5%B9%BF%E6%90%9C%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80.html)
