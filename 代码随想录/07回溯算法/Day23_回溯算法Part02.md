## 39. 组合总和

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0039.%E7%BB%84%E5%90%88%E6%80%BB%E5%92%8C.html)
视频讲解：[带你学透回溯算法-组合总和（对应「leetcode」力扣题目：39.组合总和）| 回溯法精讲！\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1KT4y1M7HJ)

```java
class Solution {
    // 存储最终结果的列表
    List<List<Integer>> result = new ArrayList<>();
    // 存储当前路径的链表
    LinkedList<Integer> path = new LinkedList<>();
    // 当前路径的和
    int sum = 0;

    // 主函数，用于调用回溯函数并返回结果
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 对候选数组进行排序，以便后续处理
        Arrays.sort(candidates);
        // 调用回溯函数，从第一个元素开始
        backtracing(candidates, target, 0);
        // 返回最终结果
        return result;
    }

    // 回溯函数，用于递归地寻找组合
    public void backtracing(int[] candidates, int target, int startIndex) {
        // 如果当前路径的和大于目标值，直接返回
        if (sum > target) {
            return;
        }
        // 如果当前路径的和等于目标值，将当前路径添加到结果中
        if (sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        // 从startIndex开始遍历候选数组
        for (int i = startIndex; i < candidates.length; i++) {
            // 将当前候选值添加到路径和sum中
            sum += candidates[i];
            // 将当前候选值添加到路径中
            path.add(candidates[i]);
            // 递归调用回溯函数，继续寻找组合
            backtracing(candidates, target, i);
            // 递归返回后，从sum中减去当前候选值
            sum -= candidates[i];
            // 从路径中移除最后一个元素
            path.removeLast();
        }
    }
}
```

## 40.组合总和 II

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0040.%E7%BB%84%E5%90%88%E6%80%BB%E5%92%8CII.html)
视频讲解：[回溯算法中的去重，树层去重树枝去重，你弄清楚了没？| LeetCode:40.组合总和 II\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV12V4y1V73A)

```java
class Solution {
    // 存储最终结果的列表
    List<List<Integer>> result = new ArrayList<>();
    // 存储当前路径的链表
    LinkedList<Integer> path = new LinkedList<>();
    // 当前路径的和
    int sum = 0;

    // 主函数，用于调用回溯函数并返回结果
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 对候选数组进行排序，以便后续处理
        Arrays.sort(candidates);
        // 调用回溯函数，从第一个元素开始
        backtracing(candidates, target, 0);
        // 返回最终结果
        return result;
    }

    // 回溯函数，用于递归地寻找组合
    public void backtracing(int[] candidates, int target, int startIndex) {
        // 如果当前路径的和大于目标值，直接返回
        if (sum > target) {
            return;
        }
        // 如果当前路径的和等于目标值，将当前路径添加到结果中
        if (sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        // 从startIndex开始遍历候选数组
        for (int i = startIndex; i < candidates.length; i++) {
            // 剪枝操作：如果当前候选值与前一个候选值相同，并且前一个候选值还没有被使用过，跳过当前候选值
            if (i > startIndex && candidates[i] == candidates[i - 1]) {
                continue;
            }
            // 将当前候选值添加到路径和sum中
            sum += candidates[i];
            // 将当前候选值添加到路径中
            path.add(candidates[i]);
            // 递归调用回溯函数，从下一个元素开始（避免重复使用同一个数字）
            backtracing(candidates, target, i + 1);
            // 递归返回后，从sum中减去当前候选值
            sum -= candidates[i];
            // 从路径中移除最后一个元素
            path.removeLast();
        }
    }
}
```

在解决组合问题时，如 `combinationSum2` 这类题目，输入的 `candidates` 数组可能会包含重复的数字。当题目要求返回的组合是唯一的（即不能包含重复的组合），我们需要采取一定的措施来避免生成重复的结果。

当对 `candidates` 数组进行排序后，相同的数字会相邻排列。这样，在遍历过程中，如果当前考虑的数字与前一个数字相同，并且前一个数字已经在这个位置被考虑过（即在当前路径中已经被选择过），那么就没有必要再考虑当前这个相同的数字了，因为这样做会导致重复的解。

具体来说，剪枝操作 `if (i > startIndex && candidates[i] == candidates[i - 1])` 的目的是跳过所有与前一个已检查过的元素相同的元素。这里的关键是 `i > startIndex` 这个条件，它确保了只有在当前索引 `i` 不是 `startIndex` 的时候才进行跳过操作。这是因为在第一次进入循环的时候，我们希望检查 `startIndex` 位置的所有可能，包括那些重复的数字。但是在那之后，为了避免重复，我们需要跳过所有与 `startIndex` 位置上数字相同的后续数字。

通过这种方式，我们保证了每种情况下的唯一性，同时减少了不必要的计算，提高了算法效率。

## 131.分割回文串

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0131.%E5%88%86%E5%89%B2%E5%9B%9E%E6%96%87%E4%B8%B2.html)
视频讲解：[代码随想录](https://programmercarl.com/0131.%E5%88%86%E5%89%B2%E5%9B%9E%E6%96%87%E4%B8%B2.html)

```java
class Solution {
    // 存储所有有效的回文分割组合
    List<List<String>> result = new ArrayList<>();
    // 存储当前的回文分割路径
    LinkedList<String> path = new LinkedList<>();

    // 主方法，接收字符串 s 并开始回溯
    public List<List<String>> partition(String s) {
        backtracing(s, 0); // 从索引 0 开始进行回溯
        return result; // 返回所有有效的分割组合
    }

    // 回溯方法，负责生成回文分割
    public void backtracing(String s, int startIndex) {
        // 如果开始索引超过字符串的长度，说明已生成一个有效组合
        if (startIndex >= s.length()) {
            result.add(new ArrayList<>(path)); // 将当前路径的副本添加到结果中
            return; // 返回以结束当前递归
        }

        // 遍历从 startIndex 到字符串的每个可能结束索引 i
        for (int i = startIndex; i < s.length(); i++) {
            // 获取子串
            String substring = s.substring(startIndex, i + 1);
            // 检查该子串是否为回文
            if (check(substring)) {
                path.add(substring); // 如果是回文，将其加入当前路径
                backtracing(s, i + 1); // 递归调用，处理下一个子串的起始索引
                path.removeLast(); // 回溯，移除最后一个添加的子串，恢复路径
            }
        }
    }

    // 检查一个字符串是否为回文
    public boolean check(String s) {
        // 对称比较字符串的字符
        for (int i = 0; i < s.length() / 2; i++) {
            // 如果发现不相等的字符，返回 false
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        // 如果全部字符相同，返回 true，表示是回文
        return true;
    }
}

```
