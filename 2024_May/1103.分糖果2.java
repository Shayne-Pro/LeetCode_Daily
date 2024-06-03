class Solution {
    public int[] distributeCandies(int candies, int num_people) {
        int[] ans = new int[num_people]; // 创建数组记录每个小朋友获得的糖果数
        int i = 0, candyGiven = 0; // 记录当前小朋友的索引和已经分发的糖果数

        while (candies > 0) {
            candyGiven++; // 下一个小朋友应得的糖果数
            // 如果当前糖果数不足以给下一个小朋友比上一次多的糖果数，则给剩余所有糖果
            if (candies <= candyGiven) {
                ans[i] += candies; // 给当前小朋友剩余所有糖果
                break;
            } else {
                ans[i] += candyGiven; // 给当前小朋友当前轮的糖果数
                candies -= candyGiven; // 减去已分配的糖果数
            }
            i = (i + 1) % num_people; // 移动到下一个小朋友的位置
        }

        return ans; // 返回分配结果
    }
}

class Solution {
    public int[] distributeCandies(int candies, int num_people) {
        int[] result = new int[num_people];
        int index = 0;
        int give = 1;

        while (candies > 0) {
            result[index % num_people] += Math.min(give, candies);
            candies -= give;
            give++;
            index++;
        }

        return result;
    }
}