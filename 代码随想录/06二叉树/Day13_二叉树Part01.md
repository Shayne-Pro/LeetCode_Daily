## 理论基础
文章讲解：[代码随想录](https://programmercarl.com/%E4%BA%8C%E5%8F%89%E6%A0%91%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80.html)
二叉树节点的定义：
```java
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
```

## 递归遍历
题目链接/文章讲解/视频讲解：[代码随想录](https://programmercarl.com/%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E9%80%92%E5%BD%92%E9%81%8D%E5%8E%86.html)
### 144.二叉树的前序遍历
```java
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        preorder(root, list);
        return list;

    }

    public void preorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        preorder(root.left, list);
        preorder(root.right, list);
    }
}
```

### 145.二叉树的后序遍历
```java
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        postorder(root, list);
        return list;
    }

    public void postorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        postorder(root.left, list);
        postorder(root.right, list);
        list.add(root.val);
    }
}
```

### 94.二叉树的中序遍历
```java
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        inorder(root, list);
        return list;
    }

    public void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }
}
```

## 迭代遍历
题目链接/文章讲解/视频讲解：[代码随想录](https://programmercarl.com/%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E8%BF%AD%E4%BB%A3%E9%81%8D%E5%8E%86.html)
### 144.二叉树的前序遍历
```java
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();// 存放结果
        if (root == null) {
            return result;// 二叉树为空直接返回
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();// 定义栈
        stack.push(root);// 根节点入栈
        while (!stack.isEmpty()) {
            // 出栈的时候将右孩子和左孩子入栈
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return result;
    }
}
```

### 145.二叉树的后序遍历
刚才前序遍历的入栈顺序是`中-右-左`，出栈顺序是`中-左-右`，
现在修改入栈顺序为`中-左-右`，则出栈顺序为`中-右-左`，逆序输出结果，就是`左-右-中`
```java
import java.util.Collections;

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();// 存放结果
        if (root == null) {
            return result;// 二叉树为空直接返回
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();// 定义栈
        stack.push(root);// 根节点入栈
        while (!stack.isEmpty()) {
            // 出栈的时候将左孩子和右孩子入栈
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }

        }
        Collections.reverse(result);
        return result;
    }
}
```

### 94.二叉树的中序遍历
```java
class Solution {
    // 中序遍历二叉树，返回遍历结果
    public List<Integer> inorderTraversal(TreeNode root) {
        // 创建一个ArrayList来存储遍历结果
        List<Integer> result = new ArrayList<>();
        // 如果根节点为空，返回空列表
        if (root == null) {
            return result;
        }
        // 创建一个栈来辅助遍历
        Stack<TreeNode> stack = new Stack<>();
        // 当前节点初始化为根节点
        TreeNode cur = root;
        // 当当前节点不为空或栈不为空时，执行循环
        while (cur != null || !stack.isEmpty()) {
            // 如果当前节点不为空，将其压入栈，并移动到左子节点
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                // 如果当前节点为空，从栈中弹出一个节点，添加到结果列表
                // 并将当前节点设置为该节点的右子节点
                cur = stack.pop();
                result.add(cur.val);
                cur = cur.right;
            }
        }
        // 返回遍历结果
        return result;
    }
}

```

## 层序遍历
题目链接/文章讲解/视频讲解：[代码随想录](https://programmercarl.com/0102.%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E5%B1%82%E5%BA%8F%E9%81%8D%E5%8E%86.html)
### 102.二叉树的层序遍历
```java
public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        if (root != null)
            queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
            result.add(level);
        }
        return result;
    }
}
```
