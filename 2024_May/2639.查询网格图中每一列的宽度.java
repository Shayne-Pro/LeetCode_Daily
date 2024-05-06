class Solution {
    public int[] findColumnWidth(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] max = new int[n];
        for (int j = 0; j < n; j++) { // j索引列，i索引行
            for (int i = 0; i < m; i++) {
                max[j] = Math.max(max[j], getlength(grid[i][j]));
            }
        }
        return max;

    }

    public int getlength(int num) {
        String numberAsString = Integer.toString(num); // 将int转换为String
        return numberAsString.length(); // 求字符串长度
    }
}