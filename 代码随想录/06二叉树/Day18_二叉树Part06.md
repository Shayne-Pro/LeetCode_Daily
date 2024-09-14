## 530.二叉搜索树的最小绝对差
题目链接/文章讲解：[代码随想录](https://programmercarl.com/0530.%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91%E7%9A%84%E6%9C%80%E5%B0%8F%E7%BB%9D%E5%AF%B9%E5%B7%AE.html)
视频讲解：[二叉搜索树中，需要掌握如何双指针遍历！| LeetCode：530.二叉搜索树的最小绝对差\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1DD4y11779)
二叉搜索树是有序的，遇到在二叉搜索树上求什么最值啊，差值之类的，就把它想成在一个有序数组上求最值，求差值。
```java
class Solution {
    private List<Integer> list = new ArrayList<>();

    public int getMinimumDifference(TreeNode root) {
        traversal(root);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < list.size(); i++) {
            min = Math.min(min, list.get(i) - list.get(i - 1));
        }
        return min;
    }

    private void traversal(TreeNode root) {
        if (root == null) {
            return;
        }
        traversal(root.left);
        list.add(root.val);
        traversal(root.right);
    }
}

```

## 501.二叉搜索树中的众数
题目链接/文章讲解：[代码随想录](https://programmercarl.com/0501.%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91%E4%B8%AD%E7%9A%84%E4%BC%97%E6%95%B0.html)
视频讲解：[不仅双指针，还有代码技巧可以惊艳到你！ | LeetCode：501.二叉搜索树中的众数\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1fD4y117gp)
我们首先进行中序遍历，因为中序遍历可以按升序访问二叉搜索树中的所有节点。然后，我们使用一个哈希表来记录每个元素的出现次数。最后，我们找出出现次数最多的元素，并将它们作为众数返回。
```java
class Solution {
    private List<Integer> list = new ArrayList<>();
    private HashMap<Integer, Integer> map = new HashMap<>();

    public int[] findMode(TreeNode root) {
        if (root == null)
            return new int[0]; // 如果根节点为空，直接返回空数组
        traversal(root); // 先进行遍历
        int maxCount = 0; // 用于记录最大出现次数
        for (int num : map.keySet()) {
            maxCount = Math.max(maxCount, map.get(num)); // 更新最大出现次数
        }
        List<Integer> modes = new ArrayList<>(); // 用于存放众数
        for (int num : map.keySet()) {
            if (map.get(num) == maxCount) {
                modes.add(num); // 如果当前元素的出现次数等于最大次数，则添加到众数列表中
            }
        }
        int[] result = new int[modes.size()]; // 创建结果数组
        for (int i = 0; i < modes.size(); i++) {
            result[i] = modes.get(i); // 将众数列表转换为数组
        }
        return result; // 返回结果数组
    }

    private void traversal(TreeNode root) {
        if (root == null) {
            return;
        }
        traversal(root.left); // 遍历左子树
        list.add(root.val); // 访问当前节点
        map.put(root.val, map.getOrDefault(root.val, 0) + 1); // 更新当前节点值的出现次数
        traversal(root.right); // 遍历右子树
    }
}
```

## 236. 二叉树的最近公共祖先
题目链接/文章讲解：[代码随想录](https://programmercarl.com/0236.%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E6%9C%80%E8%BF%91%E5%85%AC%E5%85%B1%E7%A5%96%E5%85%88.html)
视频讲解：[自底向上查找，有点难度！ | LeetCode：236. 二叉树的最近公共祖先\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1jd4y1B7E2)
![](https://pic.leetcode.cn/1681546069-BZfraI-236.png)

1. 如果当前节点为空或本身就是 `p` 或 `q` 中的一个，那么当前节点就是最近公共祖先。
2. 否则，递归地在左子树中查找最近公共祖先，并将结果赋值给 `left`。
3. 同样递归地在右子树中查找最近公共祖先，并将结果赋值给 `right`。
4. 如果 `left` 和 `right` 都为空，则说明 `p` 和 `q` 都不在当前节点的子树中。
5. 如果 `left` 为空而 `right` 不为空，则说明 `p` 和 `q` 都在右子树中，因此 `right` 即为最近公共祖先。
6. 如果 `left` 不为空而 `right` 为空，则说明 `p` 和 `q` 都在左子树中，因此 `left` 即为最近公共祖先。
7. 如果 `left` 和 `right` 都不为空，则说明 `p` 和 `q` 分别位于当前节点的两侧，因此当前节点即为最近公共祖先。
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