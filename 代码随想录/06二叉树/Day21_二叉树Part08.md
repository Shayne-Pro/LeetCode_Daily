## 669. 修剪二叉搜索树

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0669.%E4%BF%AE%E5%89%AA%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91.html)
视频讲解：[你修剪的方式不对，我来给你纠正一下！| LeetCode：669. 修剪二叉搜索树\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV17P41177ud)

```java
class Solution {
    // 定义函数trimBST，用于修剪二叉搜索树，使其只包含值在[low, high]范围内的节点
    public TreeNode trimBST(TreeNode root, int low, int high) {
        // 如果当前节点为空，直接返回null
        if (root == null) {
            return null;
        }
        // 如果当前节点的值大于high，说明当前节点及其右子树的所有节点都不在范围内
        // 因此只需要修剪左子树并返回修剪后的左子树
        if (root.val > high) {
            return trimBST(root.left, low, high);
        }
        // 如果当前节点的值小于low，说明当前节点及其左子树的所有节点都不在范围内
        // 因此只需要修剪右子树并返回修剪后的右子树
        if (root.val < low) {
            return trimBST(root.right, low, high);
        }
        // 如果当前节点的值在[low, high]范围内，则需要修剪左右子树
        // 递归修剪左子树，并将返回的节点赋值给root.left
        root.left = trimBST(root.left, low, high);
        // 递归修剪右子树，并将返回的节点赋值给root.right
        root.right = trimBST(root.right, low, high);
        // 返回修剪后的当前节点
        return root;
    }
}

```

## 108.将有序数组转换为二叉搜索树

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0108.%E5%B0%86%E6%9C%89%E5%BA%8F%E6%95%B0%E7%BB%84%E8%BD%AC%E6%8D%A2%E4%B8%BA%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91.html)
视频讲解：[构造平衡二叉搜索树！| LeetCode：108.将有序数组转换为二叉搜索树\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1uR4y1X7qL)

```java
class Solution {
    // 定义一个方法，将有序数组转换为高度平衡的二叉搜索树（BST）
    public TreeNode sortedArrayToBST(int[] nums) {
        // 如果数组为空，返回null，表示空树
        if (nums.length == 0)
            return null;

        // 找到数组的中间元素，作为树的根节点
        int mid = nums.length / 2;
        // 创建根节点，值为数组中间元素的值
        TreeNode root = new TreeNode(nums[mid]); // 初始化 root 对象

        // 将数组分为左右两部分，左半部分为根节点的左子树，右半部分为根节点的右子树
        int[] left = Arrays.copyOfRange(nums, 0, mid); // 复制数组左半部分
        int[] right = Arrays.copyOfRange(nums, mid + 1, nums.length); // 复制数组右半部分

        // 递归地将左半部分数组转换为左子树
        TreeNode left_node = sortedArrayToBST(left);
        // 递归地将右半部分数组转换为右子树
        TreeNode right_node = sortedArrayToBST(right);

        // 将递归返回的左右子树节点分别设置为根节点的左右子节点
        root.left = left_node;
        root.right = right_node;

        // 返回构建好的树的根节点
        return root;
    }
}

```

## 538.把二叉搜索树转换为累加树

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0538.%E6%8A%8A%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91%E8%BD%AC%E6%8D%A2%E4%B8%BA%E7%B4%AF%E5%8A%A0%E6%A0%91.html)
视频讲解：[普大喜奔！二叉树章节已全部更完啦！| LeetCode：538.把二叉搜索树转换为累加树\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1d44y1f7wP)

```java
class Solution {
    private int sum = 0; // 用于记录累加值

    // 将给定的二叉搜索树（BST）转换为累加树
    public TreeNode convertBST(TreeNode root) {
        // 使用反向中序遍历（右 -> 中 -> 左）
        reverseInOrderTraversal(root);
        return root;
    }

    // 反向中序遍历
    private void reverseInOrderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }
        // 先遍历右子树
        reverseInOrderTraversal(node.right);
        // 更新当前节点的值
        sum += node.val;
        node.val = sum;
        // 然后遍历左子树
        reverseInOrderTraversal(node.left);
    }
}
```

## 总结

二叉树总结：[代码随想录](https://programmercarl.com/%E4%BA%8C%E5%8F%89%E6%A0%91%E6%80%BB%E7%BB%93%E7%AF%87.html)
