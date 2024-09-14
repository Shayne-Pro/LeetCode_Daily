##  找树左下角的值
题目链接/文章讲解/视频讲解：[代码随想录](https://programmercarl.com/0513.%E6%89%BE%E6%A0%91%E5%B7%A6%E4%B8%8B%E8%A7%92%E7%9A%84%E5%80%BC.html)
**从右向左**层序遍历，最后一个输出的就是左下角的节点。
```java
class Solution {
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode temp = null;
        while (!queue.isEmpty()) {
            temp = queue.poll();
            if (temp.right != null) {
                queue.offer(temp.right);
            }
            if (temp.left != null) {
                queue.offer(temp.left);
            }
        }
        return temp.val;
    }
}
```

## 路径总和
题目链接/文章讲解/视频讲解：[代码随想录](https://programmercarl.com/0112.%E8%B7%AF%E5%BE%84%E6%80%BB%E5%92%8C.html)
```java
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        // 如果当前节点为空，返回false
        if (root == null) {
            return false;
        }
        // 如果当前节点是叶子节点，并且节点的值等于目标和，返回true
        if (root.left == null && root.right == null && root.val == targetSum) {
            return true;
        }
        // 递归检查左子树和右子树
        boolean left = hasPathSum(root.left, targetSum - root.val);
        boolean right = hasPathSum(root.right, targetSum - root.val);
        // 只要左子树或右子树中有一条路径满足条件，就返回true
        return left || right;
    }
}
```

## 从中序与后序遍历序列构造二叉树(难)
题目链接/文章讲解/视频讲解：[代码随想录](https://programmercarl.com/0106.%E4%BB%8E%E4%B8%AD%E5%BA%8F%E4%B8%8E%E5%90%8E%E5%BA%8F%E9%81%8D%E5%8E%86%E5%BA%8F%E5%88%97%E6%9E%84%E9%80%A0%E4%BA%8C%E5%8F%89%E6%A0%91.html)
```java
class Solution {
    // 根据中序遍历和后序遍历构建二叉树
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = postorder.length;
        if (n == 0) { // 如果后序遍历数组为空，返回空节点
            return null;
        }
        // 后序遍历的最后一个节点为根节点
        int leftSize = indexOf(inorder, postorder[n - 1]); // 计算左子树的大小
        int[] in1 = Arrays.copyOfRange(inorder, 0, leftSize); // 左子树的中序遍历
        int[] in2 = Arrays.copyOfRange(inorder, leftSize + 1, n); // 右子树的中序遍历
        int[] post1 = Arrays.copyOfRange(postorder, 0, leftSize); // 左子树的后序遍历
        int[] post2 = Arrays.copyOfRange(postorder, leftSize, n - 1); // 右子树的后序遍历
        TreeNode left = buildTree(in1, post1); // 递归构建左子树
        TreeNode right = buildTree(in2, post2); // 递归构建右子树
        return new TreeNode(postorder[n - 1], left, right); // 构建当前节点并返回
    }

    // 返回 x 在 a 中的下标，保证 x 一定在 a 中
    private int indexOf(int[] a, int x) {
        for (int i = 0;; i++) {
            if (a[i] == x) {
                return i;
            }
        }
    }
}
```