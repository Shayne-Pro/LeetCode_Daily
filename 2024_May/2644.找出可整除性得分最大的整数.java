class Solution {
    public int maxDivScore(int[] nums, int[] divisors) {
        int maxScore = 0; // 最大得分
        int minDivisor = Integer.MAX_VALUE; // 具有最大得分的最小除数

        for (int divisor : divisors) {
            int score = 0; // 当前除数的得分
            for (int num : nums) {
                if (num % divisor == 0) {
                    score++;
                }
            }
            // 更新最大得分和对应的除数
            if (score > maxScore || (score == maxScore && divisor < minDivisor)) {
                maxScore = score;
                minDivisor = divisor;
            }
        }

        return minDivisor;
    }
}