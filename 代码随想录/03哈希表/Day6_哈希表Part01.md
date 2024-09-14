## 哈希表理论基础
文章讲解：[哈希表理论基础](https://programmercarl.com/%E5%93%88%E5%B8%8C%E8%A1%A8%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80.html)

要快速判断一个元素是否出现集合里的时候，就要考虑哈希法。

## 242. 有效的字母异位词
题目链接/文章讲解/视频讲解：[有效的字母异位词](https://programmercarl.com/0242.%E6%9C%89%E6%95%88%E7%9A%84%E5%AD%97%E6%AF%8D%E5%BC%82%E4%BD%8D%E8%AF%8D.html)

定义一个哈希表 `record`，

遍历 `s`，记录 `s` 中每个字母出现的次数，

遍历 `t`，减去 `t` 中每个字母出现的次数，

遍历 `record`，看是否都为 0

```java
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] record = new int[26];
        for (int i = 0; i < s.length(); i++) {
            record[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            record[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (record[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
```

## 349. 两个数组的交集
题目链接/文章讲解/视频讲解：[两个数组的交集](https://programmercarl.com/0349.%E4%B8%A4%E4%B8%AA%E6%95%B0%E7%BB%84%E7%9A%84%E4%BA%A4%E9%9B%86.html)

1. 标记 `nums1` 中的元素：
   ○ 遍历 `nums1`，将每个唯一元素标记为 `hash[num] = 1`。
2. 检查 `nums2` 中的元素：
   ○ 遍历 `nums2`，检查元素是否已经在 `hash` 中标记（即 `hash[num] == 1`）。
   ○ 如果是，将 `hash[num] = 2` 并增加 `count` 以跟踪交集元素的数量。
3. 收集交集元素：
   ○ 创建一个大小为 `count` 的结果数组。
   ○ 遍历 `hash` 数组，将 `hash[i] == 2` 的元素收集到结果数组中。

```java
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        int[] hash = new int[1001];
        int count = 0;
        // 标记nums1中的元素
        for (int i = 0; i < nums1.length; i++) {
            if (hash[nums1[i]] == 0) {
                hash[nums1[i]] = 1;
            }
        }
        // 检查nums2中的元素并标记如果它们在两个数组中都存在
        for (int i = 0; i < nums2.length; i++) {
            if (hash[nums2[i]] == 1) {
                hash[nums2[i]] = 2;
                count++;
            }
        }
        // 收集交集元素
        int[] result = new int[count];
        int j = 0;
        for (int i = 0; i < 1001; i++) {
            if (hash[i] == 2) {
                result[j++] = i;
            }
        }
        return result;
    }
}
```

## 202. 快乐数
题目链接/文章讲解：[快乐数](https://programmercarl.com/0202.%E5%BF%AB%E4%B9%90%E6%95%B0.html)

1. `while (n != 1 && !record.contains(n))`：该循环条件确保程序要么找到幸福数（即 n 变为 1），要么发现循环（即 n 重复出现）。
2. `record.add(n)`：添加当前数到记录集合。
3. `n = getNext(n)`：获取下一次平方和。
4. `getNext` 方法负责计算一个数各个位上数字的平方和。

```java
class Solution {
    public boolean isHappy(int n) {
        Set<Integer> record = new HashSet<>();
        while (n != 1 && !record.contains(n)) {
            record.add(n);
            n = getNext(n);
        }
        return n == 1;
    }

    private int getNext(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }
}
```

## 1. 两数之和
题目链接/文章讲解/视频讲解：[两数之和](https://programmercarl.com/0001.%E4%B8%A4%E6%95%B0%E4%B9%8B%E5%92%8C.html)

1. **初始化HashMap**:
    - 创建一个`HashMap`对象`indexMap`，用于存储数组中的元素及其对应的索引。
2. **遍历数组**:
    - 使用`for`循环遍历数组中的每一个元素。
3. **计算差值**:
    - 对于每个元素，计算目标值`target`与当前元素`nums[i]`的差值`balance`。
4. **检查HashMap**:
    - 检查`indexMap`中是否存在键为`balance`的元素。
    - 如果存在，说明找到了两个数，它们的和等于目标值。
        * 返回这两个数的索引，即当前索引`i`和`balance`对应的索引。
5. **更新HashMap**:
    - 如果`indexMap`中不存在键为`balance`的元素，则将当前元素`nums[i]`及其索引`i`存入`indexMap`。
6. **返回结果**:
    - 如果在遍历结束后仍未找到符合条件的两个数，则返回`null`。

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int balance = target - nums[i];
            if (indexMap.containsKey(balance)) {
                return new int[] { i, indexMap.get(balance) };
            } else {
                indexMap.put(nums[i], i);
            }
        }
        return null;
    }
}
```