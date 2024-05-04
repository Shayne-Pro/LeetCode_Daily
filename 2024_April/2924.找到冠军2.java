
// 智谱清言
class Solution {
    // 主函数：找到冠军
    public int findChampion(int n, int[][] edges) {
        int win = -1; // 初始化胜者为-1，表示尚未找到
        int[] indegree = new int[n]; // 初始化入度数组，大小为n

        // 统计每个节点的入度
        for (int[] edge : edges) {
            // 假设edge[0]是赢家，edge[1]是输家
            indegree[edge[1]]++; // 输家的入度加1
        }

        // 遍历所有节点，找到入度为0的节点，即没有输过的节点
        int count = 0; // 用于计数没有输过的选手数量
        for (int k = 0; k < n; k++) {
            if (indegree[k] == 0) {
                win = k; // 如果找到入度为0的节点，赋值给win
                count++; // 计数加1
                if (count > 1) {
                    // 如果找到第二个没有输过的选手，则返回-1
                    return -1;
                }
            }
        }
        return win; // 返回找到的冠军节点
    }
}

// 文心一言
import java.util.Arrays;

class Solution {
    public int findChampion(int n, int[][] edges) {
        int[] indegree = new int[n];
        Arrays.fill(indegree, 0);

        // 统计每个节点的入度
        for (int[] edge : edges) {
            int to = edge[1]; // 假设edges[i] = [from, to]，表示from指向to的边
            indegree[to]++; // 增加to节点的入度
        }

        int championCount = 0; // 记录入度为0的节点数量
        int champion = -1; // 存储入度为0的节点的编号，初始化为-1

        // 查找入度为0的节点
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                championCount++;
                if (championCount == 1) {
                    champion = i; // 如果只找到一个入度为0的节点，则记录其编号
                } else {
                    // 如果找到多个入度为0的节点，则设置champion为-1
                    champion = -1;
                    break; // 不需要继续查找，因为已经确定有多个冠军节点
                }
            }
        }

        // 返回结果，如果有多个冠军节点则返回-1，否则返回冠军节点的编号
        return champion;
    }
}