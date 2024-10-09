## 491.递增子序列

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0491.%E9%80%92%E5%A2%9E%E5%AD%90%E5%BA%8F%E5%88%97.html)
视频讲解：[回溯算法精讲，树层去重与树枝去重 | LeetCode：491.递增子序列\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1EG4y1h78v)

```java
class Solution {
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        backtracing(nums, 0);
        return result;
    }

    public void backtracing(int[] nums, int startIndex) {
        if (path.size() > 1) {
            result.add(new ArrayList<>(path));
        }

        // 使用 HashSet 来记录当前层级已经使用过的数字，避免重复
        Set<Integer> used = new HashSet<>();

        for (int i = startIndex; i < nums.length; i++) {
            // 如果当前数字已经在当前层级使用过，或者它小于 path 中的最后一个元素，跳过
            if (used.contains(nums[i]) || (!path.isEmpty() && nums[i] < path.getLast())) {
                continue;
            }

            // 标记当前数字为已使用
            used.add(nums[i]);
            path.add(nums[i]);
            backtracing(nums, i + 1);
            path.removeLast();
        }
    }
}
```

## 46.全排列

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0046.%E5%85%A8%E6%8E%92%E5%88%97.html)
视频讲解：[组合与排列的区别，回溯算法求解的时候，有何不同？| LeetCode：46.全排列\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV19v4y1S79W)

```java
class Solution {
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    boolean[] used; // 用于记录哪些数字已经被使用过

    public List<List<Integer>> permute(int[] nums) {
        used = new boolean[nums.length]; // 初始化used数组
        backtracing(nums);
        return result;
    }

    public void backtracing(int[] nums) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path)); // 添加当前路径到结果集中
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i])
                continue; // 如果当前数字已经被使用过，跳过
            used[i] = true; // 标记当前数字为已使用
            path.add(nums[i]); // 将当前数字加入路径
            backtracing(nums); // 递归调用
            path.removeLast(); // 回溯，移除最后一个数字
            used[i] = false; // 回溯，标记当前数字为未使用
        }
    }
}
```

## 47.全排列 II

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0047.%E5%85%A8%E6%8E%92%E5%88%97II.html)
视频讲解：[回溯算法求解全排列，如何去重？| LeetCode：47.全排列 II\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1R84y1i7Tm)

```java
class Solution {
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    boolean[] used; // 用于记录哪些数字已经被使用过

    public List<List<Integer>> permuteUnique(int[] nums) {
        used = new boolean[nums.length]; // 初始化used数组
        Arrays.sort(nums);
        backtracing(nums);
        return result;
    }

    public void backtracing(int[] nums) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path)); // 添加当前路径到结果集中
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 如果当前数字已经被使用过，或者当前数字与前一个数字相同且前一个数字未被使用过，跳过
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false)) {
                continue;
            }
            used[i] = true; // 标记当前数字为已使用
            path.add(nums[i]); // 将当前数字加入路径
            backtracing(nums); // 递归调用
            path.removeLast(); // 回溯，移除最后一个数字
            used[i] = false; // 回溯，标记当前数字为未使用
        }
    }
}
```

## 总结

总结：[代码随想录](https://programmercarl.com/%E5%9B%9E%E6%BA%AF%E6%80%BB%E7%BB%93.html)
