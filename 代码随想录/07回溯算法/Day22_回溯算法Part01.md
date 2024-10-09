## 理论基础

题目链接/文章讲解：[代码随想录](https://programmercarl.com/%E5%9B%9E%E6%BA%AF%E7%AE%97%E6%B3%95%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80.html)
视频讲解：[带你学透回溯算法（理论篇）| 回溯法精讲！\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1cy4y167mM)
回溯算法模板框架：

```java
void backtracking(参数) {
    if (终止条件) {
        存放结果;
        return;
    }

    for (选择：本层集合中元素（树中节点孩子的数量就是集合的大小）) {
        处理节点;
        backtracking(路径，选择列表); // 递归
        回溯，撤销处理结果
    }
}
```

回溯算法模板：

```java
public class BacktrackingTemplate {
    // 定义结果集和当前路径为类的成员变量
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> current = new LinkedList<>();

    public List<List<Integer>> someMethod(int[] nums) {
        backtrackHelper(nums, 0);
        return result;
    }

    private void backtrackHelper(int[] nums, int startIndex) {
        // 根据具体问题，决定是否将 current 添加到 result 中
        result.add(new ArrayList<>(current)); // 例如，求子集时需要添加

        // 遍历所有可能的选择
        for (int i = startIndex; i < nums.length; i++) {
            // 做出选择
            current.add(nums[i]);
            // 递归调用，探索下一层
            backtrackHelper(nums, i + 1);
            // 撤销选择，回溯
            current.removeLast();
        }
    }
}

```

## 77. 组合

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0077.%E7%BB%84%E5%90%88.html)
视频讲解：[带你学透回溯算法-组合问题（对应力扣题目：77.组合）| 回溯法精讲！\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1ti4y1L7cv)
剪枝操作：[带你学透回溯算法-组合问题的剪枝操作（对应力扣题目：77.组合）| 回溯法精讲！\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1wi4y157er)
![](https://code-thinking-1253855093.file.myqcloud.com/pics/20201123195242899.png)

```java
class Solution {
    // 存储所有可能的组合结果
    List<List<Integer>> result = new ArrayList<>();

    // 用于记录当前递归路径上的值
    LinkedList<Integer> path = new LinkedList<>();

    // 主方法，接收两个参数：总的元素个数 n 和需要选取的元素个数 k
    public List<List<Integer>> combine(int n, int k) {
        // 调用回溯方法进行组合的生成
        backtracking(n, k, 1);

        // 返回所有可能的组合
        return result;
    }

    // 辅助的递归方法，用于生成所有可能的组合
    private void backtracking(int n, int k, int startIndex) {
        // 当路径中的元素数量达到 k 时，说明找到了一组有效的组合
        if (path.size() == k) {
            // 将当前路径作为一个完整的组合添加到结果列表中
            result.add(new ArrayList<>(path));

            // 结束本次递归
            return;
        }

        // 遍历剩余的元素
        for (int i = startIndex; i <= n; i++) {
            // 将当前元素添加到路径中
            path.add(i);

            // 继续递归，探索以当前元素开头的所有可能组合
            backtracking(n, k, i + 1);

            // 回溯：撤销上一步的选择，以便尝试其他可能性
            path.removeLast();
        }
    }
}
```

剪枝优化：

```java
class Solution {
    // 存储所有可能的组合结果
    List<List<Integer>> result = new ArrayList<>();

    // 用于记录当前递归路径上的值
    LinkedList<Integer> path = new LinkedList<>();

    // 主方法，接收两个参数：总的元素个数 n 和需要选取的元素个数 k
    public List<List<Integer>> combine(int n, int k) {
        // 调用回溯方法进行组合的生成
        backtracking(n, k, 1);

        // 返回所有可能的组合
        return result;
    }

    // 辅助的递归方法，用于生成所有可能的组合
    private void backtracking(int n, int k, int startIndex) {
        // 当路径中的元素数量达到 k 时，说明找到了一组有效的组合
        if (path.size() == k) {
            // 将当前路径作为一个完整的组合添加到结果列表中
            result.add(new ArrayList<>(path));

            // 结束本次递归
            return;
        }

        // 遍历剩余的元素
        for (int i = startIndex; i <= (n - (k - path.size())) + 1; i++) {
            // 将当前元素添加到路径中
            path.add(i);

            // 继续递归，探索以当前元素开头的所有可能组合
            backtracking(n, k, i + 1);

            // 回溯：撤销上一步的选择，以便尝试其他可能性
            path.removeLast();
        }
    }
}
```

## 216.组合总和 III

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0216.%E7%BB%84%E5%90%88%E6%80%BB%E5%92%8CIII.html)
视频讲解：[和组合问题有啥区别？回溯算法如何剪枝？| LeetCode：216.组合总和 III\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1wg411873x)

```java
class Solution {
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    int sum = 0; // 初始化 sum

    public List<List<Integer>> combinationSum3(int k, int n) {
        backtracing(k, n, 1);
        return result;
    }

    public void backtracing(int k, int n, int startIndex) {
        // 如果 path 的长度等于 k 且 sum 等于 n，则找到一个有效组合
        if (path.size() == k && sum == n) {
            result.add(new ArrayList<>(path));
            return;
        }
        // 如果 path 的长度已经等于 k 但 sum 不等于 n，则提前返回
        if (path.size() == k) {
            return;
        }
        // 从 startIndex 开始遍历到 9
        for (int i = startIndex; i <= 9 - (k - path.size()) + 1; i++) {
            path.add(i);
            sum += i;
            backtracing(k, n, i + 1); // 注意这里是 i + 1，而不是 startIndex + 1
            path.removeLast();
            sum -= i;
        }
    }
}
```

## 17.电话号码的字母组合

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0017.%E7%94%B5%E8%AF%9D%E5%8F%B7%E7%A0%81%E7%9A%84%E5%AD%97%E6%AF%8D%E7%BB%84%E5%90%88.html)
视频讲解：[还得用回溯算法！| LeetCode：17.电话号码的字母组合\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1yV4y1V7Ug)

```java
class Solution {
    List<String> result = new ArrayList<>();
    LinkedList<String> path = new LinkedList<>();

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return result;
        }
        String[] numString = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        backtracing(digits, numString, 0);
        return result;
    }

    public void backtracing(String digits, String[] numString, int index) {
        if (index == digits.length()) {
            result.add(String.join("", path));
            return;
        }

        // 获取当前数字对应的字符串
        int digit = digits.charAt(index) - '0';
        String letters = numString[digit];

        // 遍历当前数字对应的字符串中的每个字符
        for (int i = 0; i < letters.length(); i++) {
            path.add(String.valueOf(letters.charAt(i)));
            backtracing(digits, numString, index + 1);
            path.removeLast(); // 回溯，移除最后一个字符
        }
    }
}
```
