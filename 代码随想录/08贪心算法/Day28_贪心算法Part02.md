## 122.买卖股票的最佳时机 II

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0122.%E4%B9%B0%E5%8D%96%E8%82%A1%E7%A5%A8%E7%9A%84%E6%9C%80%E4%BD%B3%E6%97%B6%E6%9C%BAII.html)
视频讲解：[贪心算法也能解决股票问题！LeetCode：122.买卖股票最佳时机 II\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1ev4y1C7na)

```java
class Solution {
    public int maxProfit(int[] prices) {
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            int benifit = prices[i] - prices[i - 1];
            if (benifit > 0) {
                result += benifit;
            }
        }
        return result;
    }
}
```

## 55. 跳跃游戏

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0055.%E8%B7%B3%E8%B7%83%E6%B8%B8%E6%88%8F.html)
视频讲解：[贪心算法，怎么跳跃不重要，关键在覆盖范围 | LeetCode：55.跳跃游戏\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1VG4y1X7kB)

```java
class Solution {
    public boolean canJump(int[] nums) {
        int maxReach = 0; // 初始化能够到达的最远位置

        for (int i = 0; i < nums.length; i++) {
            if (i > maxReach) {
                // 如果当前位置超过了能够到达的最远位置，说明无法到达当前位置
                return false;
            }
            // 更新能够到达的最远位置
            maxReach = Math.max(maxReach, i + nums[i]);

            // 如果能够到达或超过最后一个位置，返回true
            if (maxReach >= nums.length - 1) {
                return true;
            }
        }

        // 如果遍历完数组，说明能够到达最后一个位置
        return true;
    }
}
```

## 45.跳跃游戏 II

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0045.%E8%B7%B3%E8%B7%83%E6%B8%B8%E6%88%8FII.html)
视频讲解：[贪心算法，最少跳几步还得看覆盖范围 | LeetCode： 45.跳跃游戏 II\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1Y24y1r7XZ)

```java
class Solution {
    public int jump(int[] nums) {
        int maxReach = 0; // 当前能够到达的最远位置
        int end = 0; // 当前跳跃的边界
        int count = 0; // 跳跃次数

        for (int i = 0; i < nums.length - 1; i++) {
            // 更新当前能够到达的最远位置
            maxReach = Math.max(maxReach, i + nums[i]);

            // 如果当前位置到达了当前跳跃的边界，则进行一次跳跃
            if (i == end) {
                count++;
                end = maxReach; // 更新跳跃的边界为当前能够到达的最远位置

                // 如果当前能够到达的最远位置已经超过了数组的最后一个位置
                if (end >= nums.length - 1) {
                    break; // 直接跳出循环
                }
            }
        }

        return count;
    }
}

```

## 1005.K 次取反后最大化的数组和

题目链接/文章讲解：[代码随想录](https://programmercarl.com/1005.K%E6%AC%A1%E5%8F%96%E5%8F%8D%E5%90%8E%E6%9C%80%E5%A4%A7%E5%8C%96%E7%9A%84%E6%95%B0%E7%BB%84%E5%92%8C.html)
视频讲解：[贪心算法，这不就是常识？还能叫贪心？LeetCode：1005.K 次取反后最大化的数组和\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV138411G7LY)

```java
class Solution {
    public int largestSumAfterKNegations(int[] nums, int k) {
        // 第一步：对数组进行排序
        Arrays.sort(nums);

        // 第二步：尽量将负数取反，最多进行 K 次
        for (int i = 0; i < nums.length && k > 0; i++) {
            if (nums[i] < 0) { // 如果当前元素是负数
                nums[i] = -nums[i]; // 取反
                k--; // 减少 K 的计数
            }
        }

        // 第三步：如果 K 还有剩余，并且 K 是奇数
        if (k > 0 && k % 2 == 1) {
            // 再次排序以找到当前数组中最小的元素
            Arrays.sort(nums);
            nums[0] = -nums[0]; // 对最小的元素取反
        }

        // 第四步：计算结果
        int result = 0; // 初始化结果
        for (int num : nums) {
            result += num; // 累加数组中的每个元素
        }

        return result; // 返回结果
    }
}

```
