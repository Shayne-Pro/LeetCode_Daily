## 654.最大二叉树
题目链接/文章讲解：[代码随想录](https://programmercarl.com/0654.%E6%9C%80%E5%A4%A7%E4%BA%8C%E5%8F%89%E6%A0%91.html)
视频讲解：[又是构造二叉树，又有很多坑！| LeetCode：654.最大二叉树\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1MG411G7ox)
思路和昨天的**从中序与后序遍历序列构造二叉树**很像，那一题是根节点对数组分割，这一题是最大元素对数组分割。
### 代码解释：
1. **基本检查**：如果输入数组 `nums` 为空，直接返回 `null`。
2. **找到最大值的索引**：使用 `getMaxIndex` 方法找到数组中的最大值的索引。
3. **创建根节点**：根据最大值创建根节点。
4. **创建左子树和右子树的数组**：使用 `Arrays.copyOfRange` 方法分别创建左子树和右子树的数组。
5. **递归构建左子树和右子树**：分别递归调用 `constructMaximumBinaryTree` 方法构建左子树和右子树。
6. **连接左子树和右子树**：将构建好的左子树和右子树连接到根节点。
7. **返回根节点**：返回构建好的树的根节点。
```java
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        int index = getMaxIndex(nums);
        TreeNode root = new TreeNode(nums[index]);
        int[] numsLeft = Arrays.copyOfRange(nums, 0, index);
        int[] numsRight = Arrays.copyOfRange(nums, index + 1, nums.length);
        TreeNode rootLeft = constructMaximumBinaryTree(numsLeft);
        TreeNode rootRight = constructMaximumBinaryTree(numsRight);
        root.left = rootLeft;
        root.right = rootRight;
        return root;
    }

    public int getMaxIndex(int[] nums) {
        int max = nums[0];
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
                index = i;
            }
        }
        return index;
    }
}
```
## 617.合并二叉树
题目链接/文章讲解：[代码随想录](https://programmercarl.com/0617.%E5%90%88%E5%B9%B6%E4%BA%8C%E5%8F%89%E6%A0%91.html)
视频讲解：[一起操作两个二叉树？有点懵！| LeetCode：617.合并二叉树\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1m14y1Y7JK)
### 解释：
1. **合并节点值**：我们首先创建一个新的 `TreeNode`，并根据 `root1` 和 `root2` 的情况设置其值。
2. **递归合并左子树**：我们检查 `root1` 和 `root2` 的左子树是否为 `null`，然后递归地合并它们。
3. **递归合并右子树**：我们检查 `root1` 和 `root2` 的右子树是否为 `null`，然后递归地合并它们。
4. **返回合并后的树**：最后返回合并后的树的根节点。
```java
class Solution {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return null;
        }
        TreeNode root = new TreeNode();
        if (root1 == null) {
            root.val = root2.val;
        } else if (root2 == null) {
            root.val = root1.val;
        } else {
            root.val = root1.val + root2.val;
        }

        // 检查 root1 和 root2 的左子树
        TreeNode left1 = (root1 != null) ? root1.left : null;
        TreeNode left2 = (root2 != null) ? root2.left : null;
        root.left = mergeTrees(left1, left2);

        // 检查 root1 和 root2 的右子树
        TreeNode right1 = (root1 != null) ? root1.right : null;
        TreeNode right2 = (root2 != null) ? root2.right : null;
        root.right = mergeTrees(right1, right2);

        return root;
    }
}
```
## 700.二叉搜索树中的搜索
题目链接/文章讲解：[代码随想录](https://programmercarl.com/0700.%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91%E4%B8%AD%E7%9A%84%E6%90%9C%E7%B4%A2.html)
视频讲解：[不愧是搜索树，这次搜索有方向了！| LeetCode：700.二叉搜索树中的搜索\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1wG411g7sF)
```java
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        if (val < root.val) {
            return searchBST(left, val);
        }
        if (val > root.val) {
            return searchBST(right, val);
        }
        return null;
    }
}
```
##  98.验证二叉搜索树
题目链接/文章讲解：[代码随想录](https://programmercarl.com/0098.%E9%AA%8C%E8%AF%81%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91.html)
视频讲解：[你对二叉搜索树了解的还不够！ | LeetCode：98.验证二叉搜索树\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV18P411n7Q4)
```java
class Solution {
    private List<Integer> vec = new ArrayList<>();

    private void traversal(TreeNode root) {
        if (root == null)
            return;
        traversal(root.left);
        vec.add(root.val); // 将二叉搜索树转换为有序数组
        traversal(root.right);
    }

    public boolean isValidBST(TreeNode root) {
        vec.clear(); // 清空列表以备重复使用
        traversal(root);
        for (int i = 1; i < vec.size(); i++) {
            // 注意要小于等于，搜索树里不能有相同元素
            if (vec.get(i) <= vec.get(i - 1))
                return false;
        }
        return true;
    }
}
```