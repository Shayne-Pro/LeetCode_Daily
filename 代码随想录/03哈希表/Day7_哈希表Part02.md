## 454. 四数相加 II

**题目链接/文章讲解/视频讲解：** [四数相加 II](https://programmercarl.com/0454.%E5%9B%9B%E6%95%B0%E7%9B%B8%E5%8A%A0II.html)

**知识点来源：** [Java HashMap getOrDefault() 方法 | 菜鸟教程](https://www.runoob.com/java/java-hashmap-getordefault.html)

> `getOrDefault()` 方法：
> 
> `getOrDefault()` 方法获取指定 `key` 对应的 `value`，如果找不到 `key`，则返回设置的默认值。
> 
> **语法：**
> ```java
> hashmap.getOrDefault(Object key, V defaultValue)
> ```
> 
> **参数说明：**
> - `key` - 键
> - `defaultValue` - 当指定的 `key` 不存在于映射关系中时，返回的默认值
> 
> **返回值：**
> 返回 `key` 对应的 `value`，如果 `key` 不存在，则返回指定的默认值。

```java
class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        
        for (int i : nums1) {
            for (int j : nums2) {
                int sum = i + j;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        
        for (int i : nums3) {
            for (int j : nums4) {
                res += map.getOrDefault(0 - i - j, 0);
            }
        }
        
        return res;
    }
}
```

---

## 383. 赎金信

**题目链接/文章讲解：** [赎金信](https://programmercarl.com/0383.%E8%B5%8E%E9%87%91%E4%BF%A1.html)

**方法：** 与 [有效的字母异位词](https://programmercarl.com/0242.%E6%9C%89%E6%95%88%E7%9A%84%E5%AD%97%E6%AF%8D%E5%BC%82%E4%BD%8D%E8%AF%8D.html) 类似。为了练习 `HashMap`，这里使用了 `map` 的写法。

```java
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for (char i : magazine.toCharArray()) {
            map.put(i - 'a', map.getOrDefault(i - 'a', 0) + 1);
        }
        
        for (char j : ransomNote.toCharArray()) {
            map.put(j - 'a', map.getOrDefault(j - 'a', 0) - 1);
            if (map.get(j - 'a') < 0) {
                return false;
            }
        }
        
        return true;
    }
}
```

---

## 15. 三数之和

**题目链接/文章讲解/视频讲解：** [三数之和](https://programmercarl.com/0015.%E4%B8%89%E6%95%B0%E4%B9%8B%E5%92%8C.html)

### 双指针法

![双指针法示意图](https://cdn.nlark.com/yuque/0/2024/png/32698236/1725539460935-47ee4276-b155-4cb0-91fe-9b23209356a6.png)

1. 先对数组进行排序。
2. 使用 `i` 遍历数组。
3. 计算 `sum = nums[i] + nums[left] + nums[right]`，若 `sum < 0`，则 `left` 指针右移；若 `sum > 0`，则 `right` 指针左移。
4. 在移动过程中进行判重，若 `nums[left] == nums[left + 1]` 则跳过 `left + 1`，若 `nums[right] == nums[right - 1]` 则跳过 `nums[right - 1]`。
5. `i` 在移动过程中也要判重，如果 `nums[i] == nums[i - 1]`，则跳过 `i`。

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) return result;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                }
            }
        }
        
        return result;
    }
}
```

---

## 18. 四数之和

**题目链接/文章讲解/视频讲解：** [四数之和](https://programmercarl.com/0018.%E5%9B%9B%E6%95%B0%E4%B9%8B%E5%92%8C.html)

### 双指针法

**思路：** 结合了 `两数之和` 和 `三数之和` 的思想。

1. 使用 `i` 和 `j` 两个循环遍历数组，计算 `balance = target - nums[i] - nums[j]`。
2. 定义两个指针 `left = j + 1` 和 `right = length - 1`，计算 `sum2 = nums[left] + nums[right]`。
3. 比较 `sum2` 和 `balance`，若 `sum2 < balance`，则 `left` 指针右移；若 `sum2 > balance`，则 `right` 指针左移。
4. 判重的思路与 `三数之和` 类似。

![双指针法示意图](https://cdn.nlark.com/yuque/0/2024/png/32698236/1725540486766-e05786b6-b1cb-486a-9f31-82f819f1f82e.png)

```java
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int length = nums.length;
        
        for (int i = 0; i < length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            
            for (int j = i + 1; j < length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                
                long balance = (long) target - nums[i] - nums[j];
                int left = j + 1, right = length - 1;
                
                while (left < right) {
                    long sum2 = (long) nums[left] + nums[right];
                    if (sum2 < balance) {
                        left++;
                    } else if (sum2 > balance) {
                        right--;
                    } else {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        left++;
                        right--;
                    }
                }
            }
        }
        
        return result;
    }
}
```