## 93.复原 IP 地址

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0093.%E5%A4%8D%E5%8E%9FIP%E5%9C%B0%E5%9D%80.html)
视频讲解：[回溯算法如何分割字符串并判断是合法 IP？| LeetCode：93.复原 IP 地址\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1XP4y1U73i/)

```java
class Solution {
    List<String> result = new ArrayList<>();
    LinkedList<String> path = new LinkedList<>();

    public List<String> restoreIpAddresses(String s) {
        if (s.length() > 12 || s.length() < 4) {
            return result; // IP 地址长度不合法
        }
        backtracing(s, 0);
        return result;
    }

    public void backtracing(String s, int startIndex) {
        if (path.size() == 4 && startIndex == s.length()) {
            // 如果已经分割成 4 段并且已经遍历完字符串，则添加到结果中
            result.add(String.join(".", path));
            return;
        }

        for (int i = startIndex; i < s.length(); i++) {
            if (i - startIndex >= 3) {
                break; // 每个段最多 3 个字符
            }
            String segment = s.substring(startIndex, i + 1);
            if (isValid(segment)) {
                path.add(segment);
                backtracing(s, i + 1);
                path.removeLast(); // 回溯
            }
        }
    }

    public boolean isValid(String s) {
        if (s.length() == 0 || s.length() > 3) {
            return false;
        }
        if (s.charAt(0) == '0' && s.length() > 1) {
            return false; // 不能有前导零
        }
        int num = Integer.parseInt(s);
        return num >= 0 && num <= 255;
    }
}
```

## 78.子集

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0078.%E5%AD%90%E9%9B%86.html)
视频讲解：[回溯算法解决子集问题，树上节点都是目标集和！ | LeetCode：78.子集\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1U84y1q7Ci)

```java
class Solution {
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        backtracing(nums, 0);
        return result;
    }

    public void backtracing(int[] nums, int startIndex) {
        result.add(new ArrayList(path));
        for (int i = startIndex; i < nums.length; i++) {
            if (i > startIndex && nums[i] == nums[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            backtracing(nums, i + 1);
            path.removeLast();
        }
    }
}
```

## 90.子集 II

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0090.%E5%AD%90%E9%9B%86II.html)
视频讲解：[回溯算法解决子集问题，如何去重？| LeetCode：90.子集 II\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1vm4y1F71J)

```java
class Solution {
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        backtracing(nums, 0);
        return result;
    }

    public void backtracing(int[] nums, int startIndex) {
        result.add(new ArrayList(path));
        for (int i = startIndex; i < nums.length; i++) {
            if (i > startIndex && nums[i] == nums[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            backtracing(nums, i + 1);
            path.removeLast();
        }
    }
}
```
