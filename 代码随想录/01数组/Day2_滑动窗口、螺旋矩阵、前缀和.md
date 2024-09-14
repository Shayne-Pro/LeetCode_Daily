## 209. 长度最小的子数组
题目链接：[. - 力扣（LeetCode）](https://leetcode.cn/problems/minimum-size-subarray-sum/)

文章讲解：[代码随想录](https://programmercarl.com/0209.%E9%95%BF%E5%BA%A6%E6%9C%80%E5%B0%8F%E7%9A%84%E5%AD%90%E6%95%B0%E7%BB%84.html)

视频讲解：[拿下滑动窗口！ | LeetCode 209 长度最小的子数组_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1tZ4y1q7XE)

### 滑动窗口
- **外层循环（遍历数组）：**
  使用 `for (int right = 0; right < n; right++)` 来遍历数组，每次将右边界指向的元素添加到 `sum` 中。

- **内层循环（缩小窗口）：**
  使用 `while (sum >= target)` 判断当前的子数组和是否达到目标值。
  如果达到目标值，执行更新最小长度的代码，并移除左边界的元素。
  `sum -= nums[left];` 表示移除 `left` 指向的元素，避免计算时累加。
  `left++` 表示将左指针右移一位，以缩小这个窗口。

- **返回结果：**
  `return min == Integer.MAX_VALUE ? 0 : min;`：如果未找到满足条件的子数组，返回 `0`；否则返回 `min`，也就是找到的最小子数组长度。

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int n = nums.length;
        int sum = 0;
        int min = Integer.MAX_VALUE;

        for (int right = 0; right < n; right++) {
            sum += nums[right];  // 将当前元素加到 sum 中

            // 只要 sum 大于等于 target，就尝试缩小窗口
            while (sum >= target) {
                min = Math.min(min, right - left + 1);  // 更新最小长度
                sum -= nums[left];  // 移除左边界的元素
                left++;  // 移动左指针
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;  // 检查是否找到满足条件的子数组
    }
}
```

## 59. 螺旋矩阵 II
题目链接：[. - 力扣（LeetCode）](https://leetcode.cn/problems/spiral-matrix-ii/)

文章讲解：[代码随想录](https://programmercarl.com/0059.%E8%9E%BA%E6%97%8B%E7%9F%A9%E9%98%B5II.html)

视频讲解：[一入循环深似海 | LeetCode：59.螺旋矩阵II_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1SL4y1N7mV/)

### 思路一：一路走到底
通过定义四个边界（`top`, `bottom`, `left`, `right`）来控制螺旋遍历的方向。每次遍历完一个方向后，相应的边界会向内收缩，直到填充完整个矩阵。这样可以确保矩阵按照螺旋顺序填充。

```java
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int cnt = 1;
        int top = 0, bottom = n - 1, left = 0, right = n - 1;

        while (cnt <= n * n) {
            // Traverse from left to right
            for (int i = left; i <= right; i++) {
                matrix[top][i] = cnt++;
            }
            top++;

            // Traverse from top to bottom
            for (int i = top; i <= bottom; i++) {
                matrix[i][right] = cnt++;
            }
            right--;

            // Traverse from right to left
            for (int i = right; i >= left; i--) {
                matrix[bottom][i] = cnt++;
            }
            bottom--;

            // Traverse from bottom to top
            for (int i = bottom; i >= top; i--) {
                matrix[i][left] = cnt++;
            }
            left++;
        }

        return matrix;
    }
}
```

### 思路二：左闭右开
```java
class Solution {
    public int[][] generateMatrix(int n) {
        int startX = 0, startY = 0; // 起始位置
        int setoff = 1; // 控制边界
        int loop = n / 2; // 循环次数
        int i, j;
        int cnt = 1;
        int[][] mat = new int[n][n];
        while ((loop--) > 0) {
            for (j = startY; j < n - setoff; j++) {
                mat[startX][j] = cnt++;
            }
            for (i = startX; i < n - setoff; i++) {
                mat[i][j] = cnt++;
            }
            for (; j > startY; j--) {
                mat[i][j] = cnt++;
            }
            for (; i > startX; i--) {
                mat[i][j] = cnt++;
            }
            startX++; // 改变起始位置
            startY++;
            setoff++; // 收缩边界
        }
        if (n % 2 == 1) {
            // n为奇数，对中间的数单独赋值
            mat[n / 2][n / 2] = cnt;
        }
        return mat;
    }
}
```

## 区间和
### 前缀和
数组 `p` 用于存放前缀和，`p[i]` 的位置存放的是从 `vec[0]` 到 `vec[i]` 的和。求从 `a` 到 `b` 的和，只需要计算 `p[b] - p[a-1]`。

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt(); // 读n个数
        int[] vec = new int[n]; // 存放数组
        int[] p = new int[n]; // 存放前缀和

        int presum = 0;
        for (int i = 0; i < n; i++) {
            vec[i] = scanner.nextInt();
            presum += vec[i];
            p[i] = presum;
        }

        while (scanner.hasNextInt()) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            int sum;
            if (a == 0) {
                sum = p[b];
            } else {
                sum = p[b] - p[a - 1];
            }
            System.out.println(sum);
        }

        scanner.close();
    }
}
```

## 数组总结
文章链接：[数组总结篇](https://programmercarl.com/%E6%95%B0%E7%BB%84%E6%80%BB%E7%BB%93%E7%AF%87.html)

![](https://cdn.nlark.com/yuque/0/2024/png/32698236/1724980909500-9a82d923-d02d-4ec7-b926-d944fe788add.png)