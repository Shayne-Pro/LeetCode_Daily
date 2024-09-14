## 232. 用栈实现队列
题目链接/文章讲解/视频讲解：[用栈实现队列](https://programmercarl.com/0232.%E7%94%A8%E6%A0%88%E5%AE%9E%E7%8E%B0%E9%98%9F%E5%88%97.html)

![](https://cdn.nlark.com/yuque/0/2024/png/32698236/1725716367144-32c4e186-c460-4dc6-a970-bd2835cefa37.png)

```java
class MyQueue {
    Stack<Integer> stackIn;
    Stack<Integer> stackOut;

    public MyQueue() {
        stackIn = new Stack<>();
        stackOut = new Stack<>();
    }

    public void push(int x) {
        stackIn.push(x);
    }

    public int pop() {
        dumpstackIn();
        return stackOut.pop();
    }

    public int peek() {
        dumpstackIn();
        return stackOut.peek();
    }

    public boolean empty() {
        return stackIn.isEmpty() && stackOut.isEmpty();
    }

    private void dumpstackIn() {
        if (!stackOut.isEmpty())
            return;
        while (!stackIn.isEmpty()) {
            stackOut.push(stackIn.pop());
        }
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
```

## 225. 用队列实现栈
题目链接/文章讲解/视频讲解：[用队列实现栈](https://programmercarl.com/0225.%E7%94%A8%E9%98%9F%E5%88%97%E5%AE%9E%E7%8E%B0%E6%A0%88.html)

![](https://cdn.nlark.com/yuque/0/2024/png/32698236/1725719397386-d11ee053-b09e-4ad7-93f6-1dead0608136.png)

```java
class MyStack {
    Queue<Integer> queue;

    public MyStack() {
        queue = new LinkedList<>();
    }

    public void push(int x) {
        queue.offer(x);
    }

    public int pop() {
        for (int i = 0; i < queue.size() - 1; i++) {
            queue.offer(queue.poll());
        }
        return queue.poll(); // 返回最后一个出队的元素
    }

    public int top() {
        for (int i = 0; i < queue.size() - 1; i++) {
            queue.offer(queue.poll());
        }
        int temp = queue.poll(); // 获取顶部元素
        queue.offer(temp); // 再次放入队列以保持栈的状态不变
        return temp;
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
```

## 20. 有效的括号
题目链接/文章讲解/视频讲解：[有效的括号](https://programmercarl.com/0020.%E6%9C%89%E6%95%88%E7%9A%84%E6%8B%AC%E5%8F%B7.html)

使用一个栈来存储左括号，当遇到右括号时，检查栈顶的左括号是否与之匹配，如果匹配则弹出栈顶元素，否则返回`false`。最后检查栈是否为空，如果为空则表示所有括号都匹配，返回`true`，否则返回`false`。

```java
class Solution {
    public boolean isValid(String s) {
        // 创建一个字符类型的栈，用于存储左括号
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            // 如果当前字符是左括号，将其压入栈中
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                stack.push(s.charAt(i));
            } else {
                // 如果栈为空，说明没有与之匹配的左括号，直接返回 false
                if (stack.isEmpty()) {
                    return false;
                }
                // 如果当前字符是右括号，且与栈顶的左括号匹配，则弹出栈顶元素
                if (s.charAt(i) == ')' && stack.peek() == '(') {
                    stack.pop();
                } else if (s.charAt(i) == ']' && stack.peek() == '[') {
                    stack.pop();
                } else if (s.charAt(i) == '}' && stack.peek() == '{') {
                    stack.pop();
                } else {
                    // 如果当前右括号与栈顶左括号不匹配，返回 false
                    return false;
                }
            }
        }
        // 循环结束后，如果栈为空，说明所有括号都匹配，返回 true；否则返回 false
        return stack.isEmpty();
    }
}
```

## 1047. 删除字符串中的所有相邻重复项
题目链接/文章讲解/视频讲解：[删除字符串中的所有相邻重复项](https://programmercarl.com/1047.%E5%88%A0%E9%99%A4%E5%AD%97%E7%AC%A6%E4%B8%B2%E4%B8%AD%E7%9A%84%E6%89%80%E6%9C%89%E7%9B%B8%E9%82%BB%E9%87%8D%E5%A4%8D%E9%A1%B9.html)

+ **数据结构**：
    - 创建了一个`Stack<Character>`（字符栈）来辅助处理字符串。
+ **遍历字符串**：
    - 通过一个`for`循环遍历输入字符串`s`中的每个字符。
    - 在每次循环中，首先检查栈是否为空，如果不为空且当前字符与栈顶字符相同，就将栈顶字符弹出，这一步实现了对相邻重复字符的消除。
    - 如果栈为空或者当前字符与栈顶字符不同，则将当前字符压入栈中。
+ **构建结果字符串**：
    - 使用`StringBuilder`来高效地构建最终的字符串。
    - 通过一个`while`循环，当栈不为空时，将栈中的字符逐个取出，并插入到`StringBuilder`的开头（因为栈是后进先出的，这样可以保证字符顺序的正确性）。
    - 最后，将`StringBuilder`转换为`String`类型并返回，得到消除相邻重复字符后的字符串。

```java
class Solution {
    public String removeDuplicates(String s) {
        // 创建一个字符类型的栈，用于存储字符
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            // 在进行 peek 操作前，先判断栈是否为空，因为对空栈执行 peek 会抛出异常
            if (!stack.isEmpty() && s.charAt(i) == stack.peek()) {
                // 如果当前字符和栈顶字符相同，则弹出栈顶字符
                stack.pop();
            } else {
                // 如果不同，则将当前字符压入栈
                stack.push(s.charAt(i));
            }
        }
        // 使用 StringBuilder 来高效地构建字符串
        StringBuilder str = new StringBuilder();
        while (!stack.isEmpty()) {
            // 从栈中取出字符并添加到 StringBuilder 的开头，因为栈是后进先出的
            str.insert(0, stack.pop());
        }
        // 将 StringBuilder 转换为 String 并返回
        return str.toString();
    }
}
```
