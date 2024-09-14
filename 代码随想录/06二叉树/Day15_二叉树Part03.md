## 110.平衡二叉树
题目链接/文章讲解/视频讲解：[代码随想录](https://programmercarl.com/0110.%E5%B9%B3%E8%A1%A1%E4%BA%8C%E5%8F%89%E6%A0%91.html)
求高度用后序遍历，因为要向父节点返回结果。求深度用前序遍历。
```java
class Solution {
    public boolean isBalanced(TreeNode root) {
        if (getDepth(root) == -1) {
            return false;
        }
        return true;
    }

    public int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getDepth(root.left);
        if (left == -1) {
            return -1;
        }
        int right = getDepth(root.right);
        if (right == -1) {
            return -1;
        }
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        return 1 + Math.max(left, right);
    }
}
```

## 257.二叉树的所有路径(难)
题目链接/文章讲解/视频讲解：[代码随想录](https://programmercarl.com/0257.%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E6%89%80%E6%9C%89%E8%B7%AF%E5%BE%84.html)

题目要求找到二叉树中所有从根节点到叶子节点的路径，并且将这些路径以字符串的形式返回。这里的路径是指从树的根节点开始一直到某叶子节点所经过的节点序列。

### 解题思路

#### 1. 定义问题
- **输入**：一个二叉树的根节点 `root`。
- **输出**：一个字符串列表，其中每个字符串代表一条从根节点到叶子节点的路径。

#### 2. 分析问题
- 对于一个二叉树来说，叶子节点指的是没有左右子节点的节点。
- 我们需要从根节点出发，沿着每条可能的路径向下探索，直到达到叶子节点。
- 当达到叶子节点时，我们需要记录下这条路径，并把它加入到最终的答案列表中。
- 由于路径需要以字符串形式返回，所以我们需要在递归的过程中构建路径字符串。

#### 3. 设计算法
- 使用深度优先搜索（DFS）策略来遍历二叉树。
- 使用一个辅助函数来进行递归遍历。
- 在递归过程中，维护一个路径列表 `path` 来记录当前路径上的节点值。
- 当到达叶子节点时，将当前路径转换成字符串形式，并添加到结果列表中。

#### 4. 实现细节
- 在递归的过程中，每当访问一个新的节点，就将其值添加到路径列表 `path` 中。
- 如果当前节点是叶子节点，则将路径列表转换成字符串，并添加到结果列表中。
- 如果当前节点不是叶子节点，则递归访问其左右子节点。
- 在递归返回时，需要从路径列表中移除最后一个添加的节点值，以便回溯到上一层，继续构造其他的路径。

### 伪代码

```plaintext
function binaryTreePaths(root):
    results = []
    path = []  # 用来存储当前路径上的节点值
    dfs(root, path, results)
    return results

function dfs(node, path, results):
    if node is null:
        return
    add node's value to the end of path
    if node is a leaf node:  # 如果是叶子节点
        convert path to string and add it to results
    else:
        dfs(node.left, path, results)  # 继续探索左子树
        dfs(node.right, path, results)  # 继续探索右子树
    remove last element from path  # 回溯，移除最后一个节点
```

### 注意事项
- 确保路径转换成字符串时格式正确，如 `"1->2->3"`。
- 在递归调用结束后，要记得回溯，即从路径列表中移除最后一个节点值。
- 当遍历到叶子节点时，才将路径添加到结果列表中。
```java
class Solution {
    // 主方法用于收集所有根节点到叶子节点的路径
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<Integer> path = new ArrayList<>();
        traversal(root, path, result);
        return result;
    }

    // 辅助方法用于递归遍历树
    private void traversal(TreeNode root, List<Integer> path, List<String> result) {
        path.add(root.val); // 添加当前节点值到路径列表中

        // 如果当前节点是叶子节点，则添加路径到结果列表
        if (root.left == null && root.right == null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size() - 1; i++) {
                sb.append(path.get(i)).append("->");
            }
            sb.append(path.get(path.size() - 1));
            result.add(sb.toString());
        } else {
            // 如果有左子树，继续遍历左子树
            if (root.left != null) {
                traversal(root.left, path, result);
                path.remove(path.size() - 1); // 回溯，移除最后一个节点
            }
            // 如果有右子树，继续遍历右子树
            if (root.right != null) {
                traversal(root.right, path, result);
                path.remove(path.size() - 1); // 回溯，移除最后一个节点
            }
        }
    }
}
```

## 404.左叶子之和
题目链接/文章讲解/视频讲解：[代码随想录](https://programmercarl.com/0404.%E5%B7%A6%E5%8F%B6%E5%AD%90%E4%B9%8B%E5%92%8C.html)
```java
class Solution {
    int result = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = sumOfLeftLeaves(root.left);
        int right = sumOfLeftLeaves(root.right);
        if (root.left != null && root.left.left == null && root.left.right == null) {
            result += root.left.val;
        }
        return result;
    }
}
```

## 222.完全二叉树的节点个数
题目链接/文章讲解/视频讲解：[代码随想录](https://programmercarl.com/0222.%E5%AE%8C%E5%85%A8%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E8%8A%82%E7%82%B9%E4%B8%AA%E6%95%B0.html)
### 当成普通二叉树
```java
class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = countNodes(root.left);
        int right = countNodes(root.right);
        return 1 + left + right;
    }
}
```
### 利用完全二叉树
```java
class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = 0;
        int rightDepth = 0;
        TreeNode temp = root;
        while (temp.left != null) {
            temp = temp.left;
            leftDepth++;
        }
        temp = root;
        while (temp.right != null) {
            temp = temp.right;
            rightDepth++;
        }
        if (leftDepth == rightDepth) {
            return (2 << leftDepth) - 1;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}
```