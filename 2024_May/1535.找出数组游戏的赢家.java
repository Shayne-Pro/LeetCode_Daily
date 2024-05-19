class Solution {
    public int getWinner(int[] arr, int k) {
        // 当前连胜次数
        int curWinCount = 0;
        // 当前连胜的数字
        int curWinner = arr[0];

        for (int i = 1; i < arr.length; i++) {
            // 如果当前数字比上一个连胜的数字大，则更新连胜次数和当前连胜数字
            if (arr[i] > curWinner) {
                curWinCount = 1;
                curWinner = arr[i];
            } else {
                // 如果当前数字小于或等于上一个连胜的数字，则增加连胜次数
                curWinCount++;
            }

            // 如果连胜次数达到k，则返回当前连胜数字
            if (curWinCount == k) {
                return curWinner;
            }
        }

        // 如果没有达到k连胜，则返回最后一个获胜的数字
        return curWinner;
    }
}