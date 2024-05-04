class Solution {
    public int findChampion(int[][] grid) {
        int n = grid.length;
        int[] wincnt = new int[n];
        int win = 0;
        // 战胜其他所有队伍才是冠军，所以直接计数，看哪只队伍赢了n-1次
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (grid[i][j] == 1) {
                    wincnt[i]++;
                } else {
                    wincnt[j]++;
                }
            }
        }
        for (int c = 0; c < n; c++) {
            if (wincnt[c] == n - 1) {
                win = c;
            }
        }
        return win;
    }
}