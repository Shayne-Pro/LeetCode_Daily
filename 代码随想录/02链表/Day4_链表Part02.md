## 24. 两两交换链表中的节点  
题目链接/文章讲解/视频讲解：[24. 两两交换链表中的节点](https://programmercarl.com/0024.%E4%B8%A4%E4%B8%A4%E4%BA%A4%E6%8D%A2%E9%93%BE%E8%A1%A8%E4%B8%AD%E7%9A%84%E8%8A%82%E7%82%B9.html)  
  
**使用虚拟头节点**  

![](https://cdn.nlark.com/yuque/0/2024/png/32698236/1725185616459-f70e23b9-faf0-4da2-9856-2ac24722c7f5.png)  

```java
/**  
 * Definition for singly-linked list.  
 * public class ListNode {  
 * int val;  
 * ListNode next;  
 * ListNode() {}  
 * ListNode(int val) { this.val = val; }  
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }  
 * }  
 */  
class Solution {  
    public ListNode swapPairs(ListNode head) {  
        ListNode dummy = new ListNode(0);  
        dummy.next = head;  
        ListNode cur = dummy;  
        while (cur.next != null && cur.next.next != null) {  
            ListNode temp = cur.next;  
            cur.next = cur.next.next;  
            temp.next = cur.next.next;  
            cur.next.next = temp;  
            cur = cur.next.next;  
        }  
        return dummy.next;  
    }  
}  
```  
  
## 19. 删除链表的倒数第N个节点  
题目链接/文章讲解/视频讲解：[删除链表的倒数第N个节点](https://programmercarl.com/0019.%E5%88%A0%E9%99%A4%E9%93%BE%E8%A1%A8%E7%9A%84%E5%80%92%E6%95%B0%E7%AC%ACN%E4%B8%AA%E8%8A%82%E7%82%B9.html)  
  
### 思路一：  
第一次 while 循环，计算总节点数；  
第二次 for 循环移动到要删除节点的前一个位置；  
`cur.next=cur.next.next` 删除目标节点  

```java
/**  
 * Definition for singly-linked list.  
 * public class ListNode {  
 * int val;  
 * ListNode next;  
 * ListNode() {}  
 * ListNode(int val) { this.val = val; }  
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }  
 * }  
 */  
class Solution {  
    public ListNode removeNthFromEnd(ListNode head, int n) {  
        ListNode dummy = new ListNode(0);  
        dummy.next = head;  
        ListNode cur = dummy;  
        int count = 0;  
        while (cur.next != null) {  
            cur = cur.next;  
            count++;  
        }  
        cur = dummy;  
        for (int j = 0; j < count - n; j++) {  
            cur = cur.next;  
        }  
        cur.next = cur.next.next;  
        return dummy.next;  
    }  
}  
```  
  
### 思路二：双指针  
`fast` 指针先向后移动 n 位；  
然后 `slow` 指针和 `fast` 指针同时开始移动，直到 `fast` 移动到末尾；  
此时 `slow` 指针的位置是要删除节点的前一位  

```java
/**  
 * Definition for singly-linked list.  
 * public class ListNode {  
 * int val;  
 * ListNode next;  
 * ListNode() {}  
 * ListNode(int val) { this.val = val; }  
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }  
 * }  
 */  
class Solution {  
    public ListNode removeNthFromEnd(ListNode head, int n) {  
        ListNode dummy = new ListNode(0);  
        dummy.next = head;  
        ListNode fast = dummy;  
        ListNode slow = dummy;  
        for (int i = 0; i < n; i++) {  
            fast = fast.next;  
        }  
        while (fast.next != null) {  
            fast = fast.next;  
            slow = slow.next;  
        }  
        slow.next = slow.next.next;  
        return dummy.next;  
    }  
}  
```  
  
## 面试题 0207. 链表相交  
题目链接/文章讲解：[链表相交](https://programmercarl.com/%E9%9D%A2%E8%AF%95%E9%A2%9802.07.%E9%93%BE%E8%A1%A8%E7%9B%B8%E4%BA%A4.html)  
  
我们求出两个链表的长度，并求出长度的差值，然后让 `curA` 移动到和 `curB` 末尾对齐的位置，如图：  

![](https://cdn.nlark.com/yuque/0/2024/png/32698236/1725188950652-379d6301-f074-46d8-ba6d-4e38526c3823.png)  
  
此时可以比较 `curA` 和 `curB` 是否相同，如果不相同，同时向后移动 `curA` 和 `curB`，如果遇到 `curA == curB`，则找到交点。  
  
**注意：最后判断是 `curA.next != curB.next`，而不是 `curA.next.val != curB.next.val`**  

```java
/**  
 * Definition for singly-linked list.  
 * public class ListNode {  
 * int val;  
 * ListNode next;  
 * ListNode(int x) {  
 * val = x;  
 * next = null;  
 * }  
 * }  
 */  
public class Solution {  
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {  
        ListNode dummyA = new ListNode(0);  
        ListNode dummyB = new ListNode(0);  
        dummyA.next = headA;  
        dummyB.next = headB;  
        ListNode curA = dummyA;  
        ListNode curB = dummyB;  
        int countA = 0, countB = 0;  
        while (curA.next != null) {  
            curA = curA.next;  
            countA++;  
        }  
        while (curB.next != null) {  
            curB = curB.next;  
            countB++;  
        }  
        curA = dummyA;  
        curB = dummyB;  
        if (countA - countB > 0) {  
            for (int i = 0; i < Math.abs(countA - countB); i++) {  
                curA = curA.next;  
            }  
        } else {  
            for (int i = 0; i < Math.abs(countA - countB); i++) {  
                curB = curB.next;  
            }  
        }  
        while (curA.next != null) {  
            if (curA.next != curB.next) {  
                curA = curA.next;  
                curB = curB.next;  
            } else {  
                return curA.next;  
            }  
        }  
        return null;  
    }  
}  
```  
  
## 142. 环形链表II   
题目链接/文章讲解/视频讲解：[环形链表II](https://programmercarl.com/0142.%E7%8E%AF%E5%BD%A2%E9%93%BE%E8%A1%A8II.html)  
  
![](https://cdn.nlark.com/yuque/0/2024/png/32698236/1725195003792-08fe8453-79e7-4a5c-a278-d25be16d65e3.png)  
  
**双指针**  
用 `slow` 指针（每次移动一个节点）和 `fast` 指针（每次移动两个节点）找到相遇点。  
**关键点：**头节点到环入口的距离 = 相遇点到环入口的距离（即：x=z）  
`index1` 从头节点出发，`index2` 从相遇点出发，`index1` 和 `index2` 在环入口相遇。  

```java
/**  
 * Definition for singly-linked list.  
 * class ListNode {  
 * int val;  
 * ListNode next;  
 * ListNode(int x) {  
 * val = x;  
 * next = null;  
 * }  
 * }  
 */  
public class Solution {  
    public ListNode detectCycle(ListNode head) {  
        ListNode slow = head;  
        ListNode fast = head;  
        while (fast != null && fast.next != null) {  
            slow = slow.next;  
            fast = fast.next.next;  
            if (slow == fast) {  
                ListNode index1 = head;  
                ListNode index2 = fast;  
                while (index1 != index2) {  
                    index1 = index1.next;  
                    index2 = index2.next;  
                }  
                return index1;  
            }  
        }  
        return null;  
    }  
}  
```  
  
## 链表总结  
[链表总结](https://www.programmercarl.com/%E9%93%BE%E8%A1%A8%E6%80%BB%E7%BB%93%E7%AF%87.html)  