## 235. 二叉搜索树的最近公共祖先

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0235.%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91%E7%9A%84%E6%9C%80%E8%BF%91%E5%85%AC%E5%85%B1%E7%A5%96%E5%85%88.html)
视频讲解：[二叉搜索树找祖先就有点不一样了！| 235. 二叉搜索树的最近公共祖先\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1Zt4y1F7ww)
之前的`236. 二叉树的最近公共祖先`思路同样适用。

```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null && right == null) {
            return null;
        } else if (left == null && right != null) {
            return right;
        } else if (left != null && right == null) {
            return left;
        } else {
            return root;
        }
    }
}
```

本题是二叉搜索树，二叉搜索树是有序的，可以好好利用一下这个特点。那么只要从上到下去遍历，遇到 cur 节点是数值在[p, q]区间中则一定可以说明该节点 cur 就是 p 和 q 的公共祖先。
如图，我们从根节点搜索，第一次遇到 cur 节点是数值在[q, p]区间中，即 节点 5，此时可以说明 q 和 p 一定分别存在于 节点 5 的左子树，和右子树中。
![[Pasted image 20240917170858.png]]

```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        // p,q在root的两侧
        if ((root.val > p.val && root.val < q.val) || (root.val < p.val && root.val > q.val)) {
            return root;
        }
        if (root.val > p.val && root.val > q.val) {
            // root.val大于p,q
            return lowestCommonAncestor(root.left, p, q);
        }
        if (root.val < p.val && root.val < q.val) {
            // root.val小于p,q
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }
}
```

如果递归函数有返回值，如何区分要搜索一条边，还是搜索整个树。
搜索一条边的写法：

```java
if (递归函数(root->left)) return ;
if (递归函数(root->right)) return ;
```

搜索整个树写法：

```java
left = 递归函数(root->left);
right = 递归函数(root->right);
left与right的逻辑处理;
```

## 701.二叉搜索树中的插入操作

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0701.%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91%E4%B8%AD%E7%9A%84%E6%8F%92%E5%85%A5%E6%93%8D%E4%BD%9C.html)
视频讲解：[原来这么简单？ | LeetCode：701.二叉搜索树中的插入操作\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1Et4y1c78Y)

```java
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            root = new TreeNode(val);
            return root;
        }
        if (root.val > val && root.left == null) {
            root.left = new TreeNode(val);
        }
        if (root.val < val && root.right == null) {
            root.right = new TreeNode(val);
        }
        if (root.val < val && root.right != null) {
            insertIntoBST(root.right, val);
        }
        if (root.val > val && root.left != null) {
            insertIntoBST(root.left, val);
        }
        return root;
    }
}

```

## 450.删除二叉搜索树中的节点

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0450.%E5%88%A0%E9%99%A4%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91%E4%B8%AD%E7%9A%84%E8%8A%82%E7%82%B9.html)
视频讲解：[调整二叉树的结构最难！| LeetCode：450.删除二叉搜索树中的节点\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1tP41177us)

```java
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (root.val == key) {
            // 节点被找到
            if (root.left == null) {
                return root.right; // 只有右子树或无子树的情况
            } else if (root.right == null) {
                return root.left; // 只有左子树的情况
            } else {
                // 两个子树的情况
                // 找到右子树的最小节点
                TreeNode minNode = findMin(root.right);
                root.val = minNode.val; // 用右子树中的最小节点替换当前节点
                root.right = deleteNode(root.right, minNode.val); // 删除右子树中的最小节点
            }
        } else if (key < root.val) {
            root.left = deleteNode(root.left, key); // 在左子树中删除
        } else {
            root.right = deleteNode(root.right, key); // 在右子树中删除
        }

        return root; // 返回更新后的树
    }

    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left; // 找到最左节点
        }
        return node;
    }
}
```
