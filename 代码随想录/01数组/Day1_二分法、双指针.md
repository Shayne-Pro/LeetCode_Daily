## 数组理论基础
文章讲解：[代码随想录](https://programmercarl.com/%E6%95%B0%E7%BB%84%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80.html)

## 704. 二分查找
题目链接：[. - 力扣（LeetCode）](https://leetcode.cn/problems/binary-search/)

文章讲解：[代码随想录](https://programmercarl.com/0704.%E4%BA%8C%E5%88%86%E6%9F%A5%E6%89%BE.html)

视频讲解：[手把手带你撕出正确的二分法 | 二分查找法 | 二分搜索法 | LeetCode：704. 二分查找_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1fA4y1o715)

### 重点：边界条件

#### 左闭右闭 [left, right]
- 循环条件 `while(left <= right)`，因为 `left == right` 是有意义的，`[left, right]` 能取到值
- 当 `nums[mid] > target` 时，`right = mid - 1`，因为 `nums[mid]` 一定不是 `target`；同理，当 `nums[mid] < target` 时，`left = mid + 1`，因为 `nums[mid]` 一定不是 `target`

```java
class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) / 2);
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
```

#### 左闭右开 [left, right)
- 循环条件 `while(left < right)`，因为 `left == right` 在区间 `[left, right)` 是没有意义的，`[left, right)` 取不到值
- 当 `nums[mid] > target` 时，`right = mid`，因为 `nums[mid]` 一定不是 `target`，且左闭右开 `[ )`，取不到 `mid`；`left` 和左闭右闭一样，还是更新为 `left = mid + 1`

```java
class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + ((right - left) / 2);
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
```

## 27. 移除元素
题目链接：[. - 力扣（LeetCode）](https://leetcode.cn/problems/remove-element/)

文章讲解：[代码随想录](https://programmercarl.com/0027.%E7%A7%BB%E9%99%A4%E5%85%83%E7%B4%A0.html)

视频讲解：[数组中移除元素并不容易！ | LeetCode：27. 移除元素_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV12A4y1Z7LP)

### 双指针
定义慢指针 `left`，快指针 `right`

`right` 指针负责找出不等于 `val` 的数，找到后赋到 `left` 指针的位置

注意：循环条件是 `<=`，只有一个元素时也要判断

```java
class Solution {
    public int removeElement(int[] nums, int val) {
        int left = 0;
        int right = 0;
        int k = 0;
        while (right <= (nums.length - 1)) {
            if (nums[right] != val) {
                nums[left] = nums[right];
                left++;
                right++;
                k++;
            } else {
                right++;
            }
        }
        return k;
    }
}
```

## 977. 有序数组的平方
题目链接：[. - 力扣（LeetCode）](https://leetcode.cn/problems/squares-of-a-sorted-array/)

文章讲解：[代码随想录](https://programmercarl.com/0977.%E6%9C%89%E5%BA%8F%E6%95%B0%E7%BB%84%E7%9A%84%E5%B9%B3%E6%96%B9.html)

视频讲解：[双指针法经典题目 | LeetCode：977.有序数组的平方_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1QB4y1D7ep)

### 双指针
`left = 0`，`right = n - 1`

比较平方后的大小，**从大到小**，逆序赋到新数组中

```java
class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        int[] result = new int[n];
        int index = n - 1;
        while (left <= right) {
            int leftsquare = nums[left] * nums[left];
            int rightsquare = nums[right] * nums[right];
            if (leftsquare <= rightsquare) {
                result[index] = rightsquare;
                right--;
            } else {
                result[index] = leftsquare;
                left++;
            }
            index--;
        }
        return result;
    }
}
```