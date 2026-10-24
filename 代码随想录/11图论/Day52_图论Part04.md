# Day52 · 图论Part04

> 作者：[夜雨翦春韭（Shayne-Pro）](https://blog.csdn.net/weixin_43724673) · 原文发布：2024-10-27
> CSDN 原文：[《【代码随想录Day52】图论Part04》](https://blog.csdn.net/weixin_43724673/article/details/143268743) · [返回目录](../README.md)

## 字符串接龙

题目链接/文章讲解：[代码随想录](https://www.programmercarl.com/kamacoder/0110.%E5%AD%97%E7%AC%A6%E4%B8%B2%E6%8E%A5%E9%BE%99.html)

```java
import java.util.*;

public class Main {
    // 使用广度优先搜索（BFS）方法计算从beginWord到endWord的最短转换序列长度
    public static int findLadderLength(String beginWord, String endWord, List<String> wordList) {
        // 将单词列表转换为集合以提高查询效率
        HashSet<String> wordSet = new HashSet<>(wordList);

        // 创建队列以存储当前待处理的单词
        Queue<String> wordQueue = new LinkedList<>();

        // 创建映射以记录每个单词的路径长度
        HashMap<String, Integer> visitedWords = new HashMap<>();
        wordQueue.offer(beginWord);
        visitedWords.put(beginWord, 1);

        // 开始BFS遍历
        while (!wordQueue.isEmpty()) {
            String currentWord = wordQueue.poll();
            int currentPathLength = visitedWords.get(currentWord);

            // 遍历当前单词的每一个字符
            for (int i = 0; i < currentWord.length(); i++) {
                char[] charArray = currentWord.toCharArray();

                // 尝试用每个字母替换当前字符
                for (char letter = 'a'; letter <= 'z'; letter++) {
                    charArray[i] = letter;
                    String newWord = new String(charArray);

                    // 如果新单词是目标单词，返回路径长度
                    if (newWord.equals(endWord)) {
                        return currentPathLength + 1; // 返回当前路径长度加一
                    }

                    // 如果新单词存在于集合中且未被访问过
                    if (wordSet.contains(newWord) && !visitedWords.containsKey(newWord)) {
                        visitedWords.put(newWord, currentPathLength + 1);
                        wordQueue.offer(newWord); // 将新单词加入队列
                    }
                }
            }
        }

        // 如果没有找到转换路径，返回0
        return 0;
    }

    public static void main(String[] args) {
        // 接收输入
        Scanner scanner = new Scanner(System.in);
        int numberOfWords = scanner.nextInt();
        scanner.nextLine(); // 读取行结束符
        String[] inputWords = scanner.nextLine().split(" ");

        List<String> wordList = new ArrayList<>();
        for (int i = 0; i < numberOfWords; i++) {
            wordList.add(scanner.nextLine()); // 添加单词到列表中
        }

        // 计算从beginWord到endWord的最短转换序列长度
        int result = findLadderLength(inputWords[0], inputWords[1], wordList);
        System.out.println(result); // 打印结果
    }
}
```

## 有向图的完全可达性

题目链接/文章讲解：[代码随想录](https://www.programmercarl.com/kamacoder/0105.%E6%9C%89%E5%90%91%E5%9B%BE%E7%9A%84%E5%AE%8C%E5%85%A8%E5%8F%AF%E8%BE%BE%E6%80%A7.html)

```java
import java.util.*;

public class Main {
    // 邻接表，用于存储图的结构
    public static List<List<Integer>> adjacencyList = new ArrayList<>();

    // 深度优先搜索 (DFS) 方法
    public static void depthFirstSearch(boolean[] visited, int currentVertex) {
        // 如果当前顶点已经被访问过，直接返回
        if (visited[currentVertex]) {
            return;
        }
        // 标记当前顶点为已访问
        visited[currentVertex] = true;
        // 获取当前顶点的邻接顶点
        List<Integer> adjacentVertices = adjacencyList.get(currentVertex);
        // 对所有邻接顶点进行深度优先搜索
        for (int nextVertex : adjacentVertices) {
            depthFirstSearch(visited, nextVertex);
        }
    }

    // 广度优先搜索 (BFS) 方法
    public static void breadthFirstSearch(boolean[] visited, int startingVertex) {
        // 使用队列来实现广度优先搜索
        Queue<Integer> queue = new LinkedList<>();
        // 将起始顶点加入队列并标记为已访问
        queue.add(startingVertex);
        visited[startingVertex] = true;

        // 当队列不为空时，继续处理
        while (!queue.isEmpty()) {
            int currentVertex = queue.poll(); // 取出队列中的当前顶点
            List<Integer> adjacentVertices = adjacencyList.get(currentVertex);
            // 对所有邻接顶点进行处理
            for (int nextVertex : adjacentVertices) {
                // 如果邻接顶点未被访问过，则添加到队列，并标记为已访问
                if (!visited[nextVertex]) {
                    queue.add(nextVertex);
                    visited[nextVertex] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfVertices = scanner.nextInt(); // 顶点数量
        int numberOfEdges = scanner.nextInt(); // 边的数量

        // 初始化邻接表
        for (int i = 0; i < numberOfVertices; i++) {
            adjacencyList.add(new LinkedList<>());
        }

        // 构建邻接表
        for (int i = 0; i < numberOfEdges; i++) {
            int sourceVertex = scanner.nextInt() - 1; // 输入的源顶点
            int targetVertex = scanner.nextInt() - 1; // 输入的目标顶点
            adjacencyList.get(sourceVertex).add(targetVertex);
        }

        boolean[] visited = new boolean[numberOfVertices]; // 访问记录数组
        depthFirstSearch(visited, 0); // 从顶点0开始进行深度优先搜索
//        breadthFirstSearch(visited, 0); // 如果需要使用广度优先搜索，可以取消注释

        // 检查图是否是连通的
        for (int i = 0; i < numberOfVertices; i++) {
            if (!visited[i]) {
                System.out.println(-1); // 如果有未访问的顶点，输出-1
                return;
            }
        }
        System.out.println(1); // 如果所有顶点都被访问，输出1
    }
}
```

## 岛屿的周长

题目链接/文章讲解：[代码随想录](https://www.programmercarl.com/kamacoder/0106.%E5%B2%9B%E5%B1%BF%E7%9A%84%E5%91%A8%E9%95%BF.html)

```java
import java.util.*;

public class Main {
    // 定义四个方向的移动
    static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    // 用于记录当前1的周长
    static int perimeterCount;

    // 计算当前位置周围的周长
    public static void calculatePerimeter(int[][] grid, int x, int y) {
        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];

            // 如果遇到边界或水（0），周长增加1
            if (newX < 0 || newX >= grid.length || newY < 0 || newY >= grid[0].length
                || grid[newX][newY] == 0) {
                perimeterCount++;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 接受网格的尺寸输入
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();

        int[][] grid = new int[rows][columns];
        // 读取网格数据
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }

        int totalPerimeter = 0; // 初始化总周长
        // 遍历整个网格
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                // 如果当前单元格为1，则计算其周长
                if (grid[i][j] == 1) {
                    perimeterCount = 0; // 重置周长计数
                    calculatePerimeter(grid, i, j); // 计算周长
                    totalPerimeter += perimeterCount; // 累加到总周长
                }
            }
        }

        // 输出结果
        System.out.println(totalPerimeter);
    }
}
```
