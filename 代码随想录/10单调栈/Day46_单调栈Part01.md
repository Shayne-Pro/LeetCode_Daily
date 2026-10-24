# Day46 · 单调栈Part01

> 作者：[夜雨翦春韭（Shayne-Pro）](https://blog.csdn.net/weixin_43724673) · 原文发布：2024-10-19
> CSDN 原文：[《【代码随想录Day46】单调栈Part01》](https://blog.csdn.net/weixin_43724673/article/details/143086102) · [返回目录](../README.md)

## 739. 每日温度

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0739.%E6%AF%8F%E6%97%A5%E6%B8%A9%E5%BA%A6.html)
视频讲解：[单调栈，你该了解的，这里都讲了！LeetCode:739.每日温度\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1my4y1Z7jj/)
使用暴力方法会超出时间限制：

```java
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        int[] answer = new int[len];
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (temperatures[j] > temperatures[i]) {
                    answer[i] = j - i;
                    break;
                }
            }
        }
        return answer;
    }
}
```

使用单调栈：

```java
class Solution {
    // 定义一个方法 dailyTemperatures，输入一个整数数组 temperatures，返回一个整数数组
    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;  // 获取温度数组的长度
        Stack<Integer> stack = new Stack<>();  // 创建一个栈，用于存储温度的索引
        int[] answer = new int[len];  // 创建一个答案数组，用于存储每一天等待的天数

        // 遍历温度数组
        for (int i = 0; i < len; i++) {
            // 当栈不为空且当前温度大于栈顶索引对应的温度时
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int idx = stack.pop();  // 弹出栈顶索引
                answer[idx] = i - idx;  // 计算当前温度比 idx 天的温度高的天数，并存入答案数组
            }
            stack.push(i);  // 将当前索引压入栈中
        }
        return answer;  // 返回答案数组
    }
}
```

## 496.下一个更大元素 I

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0496.%E4%B8%8B%E4%B8%80%E4%B8%AA%E6%9B%B4%E5%A4%A7%E5%85%83%E7%B4%A0I.html)
视频讲解：[单调栈，套上一个壳子就有点绕了| LeetCode:496.下一个更大元素\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1jA411m7dX/)

```java
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] count = new int[len1];
        Arrays.fill(count, -1);

        // 使用一个栈来帮助找到下一个更大元素
        Stack<Integer> stack = new Stack<>();
        // 用一个映射来存储 nums2 中每个元素的下一个更大元素
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int j = 0; j < len2; j++) {
            // 当栈不为空且当前元素大于栈顶元素时，说明找到了下一个更大元素
            while (!stack.isEmpty() && nums2[j] > stack.peek()) {
                map.put(stack.pop(), nums2[j]); // 将该元素与下一个更大元素映射
            }
            stack.push(nums2[j]); // 将当前元素入栈
        }

        // 遍历 nums1，使用映射找到其下一个更大元素
        for (int i = 0; i < len1; i++) {
            if (map.containsKey(nums1[i])) {
                count[i] = map.get(nums1[i]); // 更新下一个更大元素
            }
        }

        return count;
    }
}
```

## 503.下一个更大元素 II

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0503.%E4%B8%8B%E4%B8%80%E4%B8%AA%E6%9B%B4%E5%A4%A7%E5%85%83%E7%B4%A0II.html)
视频讲解：[单调栈，成环了可怎么办？LeetCode：503.下一个更大元素 II\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV15y4y1o7Dw/)

```java
public class Solution {
    // 方法：寻找数组中每个元素的下一个更大元素
    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length; // 获取数组长度
        int[] result = new int[len]; // 创建结果数组，存储每个元素的下一个更大元素
        Arrays.fill(result, -1); // 初始化结果数组，默认为-1，表示没有更大元素

        // 创建一个栈来存储索引
        Stack<Integer> stack = new Stack<>();

        // 将数组扩展为两倍长度，以便于处理循环数组的情况
        int[] extendNums = new int[len * 2];
        for (int i = 0; i < len; i++) {
            extendNums[i] = nums[i]; // 复制原数组到扩展数组的前半部分
            extendNums[i + len] = nums[i]; // 复制原数组到扩展数组的后半部分
        }

        // 遍历扩展后的数组
        for (int i = 0; i < 2 * len - 1; i++) {
            // 当栈不为空且当前元素大于栈顶索引对应的元素时
            while (!stack.isEmpty() && extendNums[i] > extendNums[stack.peek()]) {
                int idx = stack.pop(); // 弹出栈顶元素的索引
                if (idx < len) { // 只有当弹出的索引属于原始数组时，才更新结果数组
                    result[idx] = extendNums[i]; // 更新结果数组，记录下一个更大元素
                }
            }
            stack.push(i); // 将当前索引压入栈
        }
        return result; // 返回结果数组
    }
}
```
