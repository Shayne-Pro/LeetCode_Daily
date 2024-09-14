## 链表理论基础
文章链接：[链表理论基础](https://programmercarl.com/%E9%93%BE%E8%A1%A8%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80.html)

## 203. 移除链表元素
题目链接/文章讲解/视频讲解：[203. 移除链表元素](https://programmercarl.com/0203.%E7%A7%BB%E9%99%A4%E9%93%BE%E8%A1%A8%E5%85%83%E7%B4%A0.html)

### 方法一：在链表前面加一个虚拟头节点，方便处理删除头结点的操作。
![虚拟头节点示意图](https://cdn.nlark.com/yuque/0/2024/png/32698236/1724987512732-12bd5c34-0f9a-4c23-9149-b4768965fed4.png)

**注意**：最后 return 头结点的时候，是 return dummyNode->next

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
```

### 方法二：对删除头节点的情况单独处理
先处理头节点，再处理其他节点

![删除头节点示意图](https://cdn.nlark.com/yuque/0/2024/png/32698236/1724988834754-da55f9bf-ef6a-4737-9349-be384bca3c1f.png)
![删除其他节点示意图](https://cdn.nlark.com/yuque/0/2024/png/32698236/1724988844404-d16b30f9-8e1e-41d1-89db-d719f78694c2.png)

**注意**：记得判断 head 和 cur 是否为 null

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }
}
```

## 707. 设计链表
题目链接/文章讲解/视频讲解：[707. 设计链表](https://programmercarl.com/0707.%E8%AE%BE%E8%AE%A1%E9%93%BE%E8%A1%A8.html)

插入、删除的操作本身不难，难的是准确找到指针要移动到的位置。

```java
// 节点类，用于表示链表中的节点
class ListNode {
    int val;
    ListNode next;

    // 无参构造函数
    ListNode() {}

    // 带一个参数的构造函数
    ListNode(int val) {
        this.val = val;
    }
}

// 链表类
class MyLinkedList {
    int size; // 链表中元素的个数
    ListNode head; // 虚拟头节点

    // 构造函数，初始化链表
    public MyLinkedList() {
        size = 0; // 初始化链表大小为0
        head = new ListNode(0); // 虚拟头节点，不存储有效数据
    }

    // 获取链表中第index个节点的值
    public int get(int index) {
        // 如果索引无效，返回-1
        if (index < 0 || index >= size) {
            return -1;
        }
        ListNode cur = head.next; // 从虚拟头节点的下一个节点开始
        // 遍历链表直到第index个节点
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.val; // 返回第index个节点的值
    }

    // 在链表头添加一个值为val的节点
    public void addAtHead(int val) {
        ListNode temp = new ListNode(val); // 创建新节点
        temp.next = head.next; // 新节点指向原先的第一个节点
        head.next = temp; // 虚拟头节点指向新节点
        size++; // 链表大小增加1
    }

    // 在链表尾添加一个值为val的节点
    public void addAtTail(int val) {
        ListNode cur = head; // 从虚拟头节点开始
        // 遍历到最后一个节点
        while (cur.next != null) {
            cur = cur.next;
        }
        ListNode temp = new ListNode(val); // 创建新节点
        cur.next = temp; // 最后一个节点指向新节点
        size++; // 链表大小增加1
    }

    // 在链表的第index个位置插入值为val的节点
    public void addAtIndex(int index, int val) {
        // 如果index小于0或大于链表长度，直接返回
        if (index < 0 || index > size) {
            return;
        }
        if (index == 0) { // 如果index为0，在头部插入
            addAtHead(val);
        } else if (index == size) { // 如果index等于长度，在尾部插入
            addAtTail(val);
        } else { // 在链表中间插入
            ListNode pre = head; // 从虚拟头节点开始
            // 遍历到第index-1个节点
            for (int i = 0; i < index; i++) {
                pre = pre.next;
            }
            ListNode temp = new ListNode(val); // 创建新节点
            temp.next = pre.next; // 新节点指向第index个节点
            pre.next = temp; // 第index-1个节点指向新节点
            size++; // 链表大小增加1
        }
    }

    // 删除链表中第index个节点
    public void deleteAtIndex(int index) {
        // 如果索引无效，直接返回
        if (index < 0 || index >= size) {
            return;
        }
        ListNode pre = head; // 从虚拟头节点开始
        // 遍历到第index个节点的前一个节点
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        pre.next = pre.next.next; // 前一个节点指向第index+1个节点
        size--; // 链表大小减少1
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
```

## 206. 反转链表
题目链接/文章讲解/视频讲解：[206. 反转链表](https://programmercarl.com/0206.%E7%BF%BB%E8%BD%AC%E9%93%BE%E8%A1%A8.html)

### 方法一：使用头插法，将链表元素依次插入到新链表中。
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode();
        ListNode cur = head;
        while (cur != null) {
            ListNode temp = new ListNode();
            temp.val = cur.val;
            temp.next = dummy.next;
            dummy.next = temp;
            cur = cur.next;
        }
        return dummy.next;
    }
}
```

我自己写的方法，正确，询问 GPT 后提出了一些问题：

1. 使用多余的节点创建：每次循环都新建一个 ListNode，这并没有必要，并且会导致额外的空间复杂度。
2. 无须使用哑结点：dummy 节点在这里并不必要，因为我们可以直接改变指针来达成反转链表的效果。
3. 时间复杂度增大：新建节点和拷贝数据导致的开销，可以更简单直接地通过更改指针来实现。

GPT 建议用双指针法：

### 方法二：双指针
要用 temp 保存 curr.next 的位置，否则无法更新 curr 的位置。

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next; // 暂存当前节点的下一个节点
            curr.next = prev; // 将当前节点的next指向前一个节点
            prev = curr; // 将prev指针移动到当前节点
            curr = nextTemp; // 将curr指针移动到下一个节点
        }
        return prev; // 返回反转后的头结点
    }
}
```

### 方法三：递归
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        // 基本情况：如果链表为空或只有一个节点，直接返回头节点
        if (head == null || head.next == null) {
            return head;
        }

        // 递归反转剩余的链表
        ListNode newHead = reverseList(head.next);

        // 处理当前节点的反转
        head.next.next = head;  // 例如：head -> node2, 变成 node2 -> head
        head.next = null;

        // 返回新的头节点
        return newHead;
    }
}
```