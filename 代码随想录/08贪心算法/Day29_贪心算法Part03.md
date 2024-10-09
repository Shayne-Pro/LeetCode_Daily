## 134. 加油站

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0134.%E5%8A%A0%E6%B2%B9%E7%AB%99.html)
视频讲解：[贪心算法，得这么加油才能跑完全程！LeetCode ：134.加油站\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1jA411r7WX)

```java
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // 创建一个数组来存储每个加油站的剩余油量
        int[] rest = new int[gas.length];
        // 当前累计的剩余油量
        int curSum = 0;
        // 总剩余油量
        int totalSum = 0;
        // 起始加油站的索引
        int start = 0;

        // 遍历每个加油站
        for (int i = 0; i < gas.length; i++) {
            // 计算当前加油站的剩余油量
            rest[i] = gas[i] - cost[i];
            // 更新当前累计的剩余油量
            curSum += rest[i];
            // 更新总剩余油量
            totalSum += rest[i];

            // 如果当前累计的剩余油量小于0，说明从当前起始加油站出发无法完成环路
            if (curSum < 0) {
                // 将起始加油站更新为下一个加油站
                start = (i + 1) % gas.length;
                // 重置当前累计的剩余油量
                curSum = 0;
            }
        }

        // 如果总剩余油量小于0，说明无法完成环路，返回-1
        if (totalSum < 0) {
            return -1;
        }

        // 返回起始加油站的索引
        return start;
    }
}
```

## 135. 分发糖果

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0135.%E5%88%86%E5%8F%91%E7%B3%96%E6%9E%9C.html)
视频讲解：[贪心算法，两者兼顾很容易顾此失彼！LeetCode：135.分发糖果\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1ev4y1r7wN)

```java
class Solution {
    public int candy(int[] ratings) {
        // 如果ratings为空，直接返回0
        if (ratings == null || ratings.length == 0) {
            return 0;
        }

        int n = ratings.length;
        int[] candyVec = new int[n];

        // 每个孩子至少分1个糖果
        candyVec[0] = 1; // 第一个孩子至少有1个糖果
        for (int i = 1; i < n; i++) {
            // 如果当前孩子的评分高于前一个孩子
            if (ratings[i] > ratings[i - 1]) {
                candyVec[i] = candyVec[i - 1] + 1; // 给当前孩子更多的糖果
            } else {
                candyVec[i] = 1; // 否则，当前孩子只能得到1个糖果
            }
        }

        // 从右到左遍历ratings
        for (int i = n - 2; i >= 0; i--) {
            // 如果当前孩子的评分高于下一个孩子
            if (ratings[i] > ratings[i + 1]) {
                // 更新当前孩子的糖果数，确保满足条件
                candyVec[i] = Math.max(candyVec[i], candyVec[i + 1] + 1);
            }
        }

        // 计算总糖果数
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += candyVec[i];
        }

        return sum;
    }
}

```

## 860.柠檬水找零

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0860.%E6%9F%A0%E6%AA%AC%E6%B0%B4%E6%89%BE%E9%9B%B6.html)
视频讲解：[贪心算法，看上去复杂，其实逻辑都是固定的！LeetCode：860.柠檬水找零\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV12x4y1j7DD)

```java
class Solution {
    public boolean lemonadeChange(int[] bills) {
        int[] count = new int[3]; // count[0]: $5 钞票, count[1]: $10 钞票, count[2]: $20 钞票

        for (int bill : bills) {
            if (bill == 5) {
                count[0]++;
            } else if (bill == 10) {
                if (count[0] == 0) {
                    return false; // 没有足够的 $5 钞票来找零
                }
                count[0]--; // 找出一张 $5 钞票作为找零
                count[1]++;
            } else if (bill == 20) {
                // 优先找出一张 $10 和一张 $5 钞票（如果有的话）
                if (count[1] > 0 && count[0] > 0) {
                    count[1]--;
                    count[0]--;
                } else if (count[0] >= 3) {
                    count[0] -= 3; // 找出三张 $5 钞票作为找零
                } else {
                    return false; // 没有足够的钞票来找零
                }
                count[2]++; // 接受一张 $20 钞票
            }
        }

        return true; // 成功为所有钞票找零
    }
}

```

## 406.根据身高重建队列

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0406.%E6%A0%B9%E6%8D%AE%E8%BA%AB%E9%AB%98%E9%87%8D%E5%BB%BA%E9%98%9F%E5%88%97.html)
视频讲解：[贪心算法，不要两边一起贪，会顾此失彼 | LeetCode：406.根据身高重建队列\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1EA411675Y)

```java
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        // 首先对数组进行排序
        // 排序规则：按照高度降序排列，如果高度相同则按照k值升序排列
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    // 如果高度相同，按照k值升序排列
                    return o1[1] - o2[1];
                }
                // 否则按照高度降序排列
                return o2[0] - o1[0];
            }
        });

        // 使用LinkedList来存储结果，因为LinkedList的插入操作效率较高
        LinkedList<int[]> que = new LinkedList<>();

        // 遍历排序后的数组，按照每个元素的k值插入到LinkedList中
        for (int[] p : people) {
            que.add(p[1], p);
        }

        // 将LinkedList转换为二维数组并返回
        return que.toArray(new int[people.length][]);
    }
}
```
