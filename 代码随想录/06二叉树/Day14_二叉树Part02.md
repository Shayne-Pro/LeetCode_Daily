## 226.翻转二叉树
题目链接/文章讲解/视频讲解：[代码随想录](https://programmercarl.com/0226.%E7%BF%BB%E8%BD%AC%E4%BA%8C%E5%8F%89%E6%A0%91.html)
遍历二叉树，交换每个节点的左右子树。
```java
class Solution {
    public TreeNode invertTree(TreeNode root) {
        preorder(root);
        return root;
    }

    public static void preorder(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        preorder(root.left);
        preorder(root.right);
    }
}
```

## 101.对称二叉树
题目链接/文章讲解/视频讲解：[代码随想录](https://programmercarl.com/0101.%E5%AF%B9%E7%A7%B0%E4%BA%8C%E5%8F%89%E6%A0%91.html)
![[Pasted image 20240910210811.png]]
外侧和外侧比，内侧和内侧比。后序遍历。
```java
class Solution {
    public boolean isSymmetric(TreeNode root) {
        return compare(root.left, root.right);
    }

    public boolean compare(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if ((left == null && right != null) || (left != null && right == null)) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        boolean outside = compare(left.left, right.right);
        boolean inside = compare(left.right, right.left);
        boolean result = outside && inside;
        return result;

    }
}
```

## 104.二叉树的最大深度
题目链接/文章讲解/视频讲解：[代码随想录](https://programmercarl.com/0104.%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E6%9C%80%E5%A4%A7%E6%B7%B1%E5%BA%A6.html)
```java
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int maxleft = maxDepth(root.left);
        int maxright = maxDepth(root.right);
        int hight = 1 + Math.max(maxleft, maxright);
        return hight;
    }
}
```

## 111.二叉树的最小深度
题目链接/文章讲解/视频讲解：[代码随想录](https://programmercarl.com/0111.%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E6%9C%80%E5%B0%8F%E6%B7%B1%E5%BA%A6.html)
```java
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = minDepth(root.left);
        int rightDepth = minDepth(root.right);
        if (root.left == null) {
            return 1 + rightDepth;
        }
        if (root.right == null) {
            return 1 + leftDepth;
        }
        return 1 + Math.min(leftDepth, rightDepth);
    }
}
```
