## 150.逆波兰表达式求值

题目链接/文章讲解/视频讲解：[逆波兰表达式求值](https://programmercarl.com/0150.%E9%80%86%E6%B3%A2%E5%85%B0%E8%A1%A8%E8%BE%BE%E5%BC%8F%E6%B1%82%E5%80%BC.html)

**思路：** 遇到数字就入栈，遇到算数运算符就从栈中`pop`​两个数字进行运算，再将运算结果入栈。

```java
public class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) { // 注意这里的长度访问方式
            String token = tokens[i];
            if (isOperator(token)) { // 判断是否为运算符
                int tmp2 = stack.pop(); // 弹出栈顶的值作为右操作数
                int tmp1 = stack.pop(); // 再次弹出一个值作为左操作数
                switch (token) {
                    case "+":
                        stack.push(tmp1 + tmp2);
                        break;
                    case "-":
                        stack.push(tmp1 - tmp2);
                        break;
                    case "*":
                        stack.push(tmp1 * tmp2);
                        break;
                    case "/":
                        stack.push(tmp1 / tmp2); // 注意这里执行的是整数除法
                        break;
                }
            } else { // 如果不是运算符，则将字符串转换为整数并压入栈中
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop(); // 返回栈中的最后一个值
    }

    private boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }
}
```

‍

## 239.滑动窗口最大值

题目链接/文章讲解/视频讲解：[滑动窗口最大值](https://programmercarl.com/0239.%E6%BB%91%E5%8A%A8%E7%AA%97%E5%8F%A3%E6%9C%80%E5%A4%A7%E5%80%BC.html)

1. **初始化双端队列**：使用 `ArrayDeque`​ 作为双端队列，存储数组中元素的索引。
2. **遍历数组**：

    * 如果队列非空且队列头部的索引已经不在当前窗口范围内，则移除队列头部。
    * 如果队列非空且队列尾部的元素小于当前元素，则移除队列尾部，直到队列为空或队列尾部的元素大于等于当前元素。
    * 将当前元素的索引加入队列尾部。
    * 如果当前窗口已经形成（即当前索引大于等于 `k-1`​），则将队列头部的索引对应的元素加入结果数组。
3. **返回结果数组**。

```java
class Solution {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            // 移除不在当前窗口范围内的元素
            if (!deque.isEmpty() && deque.peekFirst() == i - k) {
                deque.pollFirst();
            }

            // 移除所有小于当前元素的元素
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // 将当前元素的索引加入队列
            deque.offerLast(i);

            // 如果窗口已经形成，记录当前窗口的最大值
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        return result;
    }
}
```

‍

## 347.前 K 个高频元素

题目链接/文章讲解/视频讲解：[前 K 个高频元素](https://programmercarl.com/0347.%E5%89%8DK%E4%B8%AA%E9%AB%98%E9%A2%91%E5%85%83%E7%B4%A0.html)

因为要统计最大前k个元素，只有小顶堆每次将最小的元素弹出，最后小顶堆里积累的才是前k个最大元素。


​![image](attachment/image-20240908184144-6907p2c.png)​

```java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // 使用Lambda表达式和Comparator来定义优先队列的比较逻辑
        PriorityQueue<int[]> pq = new PriorityQueue<>((pair1, pair2) -> Integer.compare(pair1[1], pair2[1]));

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (pq.size() < k) {
                pq.add(new int[] { entry.getKey(), entry.getValue() });
            } else if (entry.getValue() > pq.peek()[1]) {
                pq.poll();
                pq.add(new int[] { entry.getKey(), entry.getValue() });
            }
        }

        int[] ans = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            ans[i] = pq.poll()[0];
        }
        return ans;
    }
}
```

‍

## 栈与队列总结

总结：[总结](https://programmercarl.com/%E6%A0%88%E4%B8%8E%E9%98%9F%E5%88%97%E6%80%BB%E7%BB%93.html)
