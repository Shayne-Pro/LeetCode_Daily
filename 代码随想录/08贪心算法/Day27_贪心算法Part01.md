## 理论基础

题目链接/文章讲解：[代码随想录](https://programmercarl.com/%E8%B4%AA%E5%BF%83%E7%AE%97%E6%B3%95%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80.html)
视频讲解：[贪心算法理论基础！\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1WK4y1R71x/)

## 455.分发饼干

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0455.%E5%88%86%E5%8F%91%E9%A5%BC%E5%B9%B2.html)
视频讲解：[贪心算法，你想先喂哪个小孩？| LeetCode：455.分发饼干\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1MM411b7cq)

一开始使用了双重循环，时间复杂度为 $(O(n \times m))$，其中 $(n)$ 是 `g` 的长度，$(m)$ 是 `s` 的长度，会超出时间限制。我们可以通过使用双指针的方法将时间复杂度优化到 $(O(n + m))$。

```java
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        // 首先对两个数组进行排序
        Arrays.sort(g);
        Arrays.sort(s);

        int count = 0;
        int i = 0; // 指向孩子的指针
        int j = 0; // 指向饼干的指针

        // 使用双指针遍历两个数组
        while (i < g.length && j < s.length) {
            if (s[j] >= g[i]) {
                // 如果当前饼干可以满足当前孩子，则计数加一，并且两个指针都向前移动
                count++;
                i++;
                j++;
            } else {
                // 如果当前饼干不能满足当前孩子，则只移动饼干的指针
                j++;
            }
        }

        return count;
    }
}
```

## 376. 摆动序列

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0376.%E6%91%86%E5%8A%A8%E5%BA%8F%E5%88%97.html)
视频讲解：[贪心算法，寻找摆动有细节！| LeetCode：376.摆动序列\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV17M411b7NS)

```java
class Solution {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }

        int prevDiff = nums[1] - nums[0];
        int count = prevDiff != 0 ? 2 : 1; // 如果前两个数相同，则摆动序列长度为1，否则为2

        for (int i = 2; i < nums.length; i++) {
            int currDiff = nums[i] - nums[i - 1];
            if ((currDiff > 0 && prevDiff <= 0) || (currDiff < 0 && prevDiff >= 0)) {
                count++;
                prevDiff = currDiff;
            }
        }

        return count;
    }
}
```

## 53. 最大子序和

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0053.%E6%9C%80%E5%A4%A7%E5%AD%90%E5%BA%8F%E5%92%8C.html)
视频讲解：[贪心算法的巧妙需要慢慢体会！LeetCode：53. 最大子序和\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1aY4y1Z7ya)

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE; // 初始化最大和为最小整数值
        int sum = 0; // 初始化当前子数组的和为0

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i]; // 累加当前元素到当前子数组的和

            // 更新最大和
            if (sum > maxSum) {
                maxSum = sum;
            }

            // 如果当前子数组的和为负数，重置为0
            if (sum < 0) {
                sum = 0;
            }
        }

        return maxSum;
    }
}
```
