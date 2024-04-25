class Solution {
    public int distanceTraveled(int mainTank, int additionalTank) {
        int dis = 0;
        while (mainTank >= 5) {
            dis += 50;
            mainTank -= 5;
            if (additionalTank >= 1) {
                mainTank += 1;
                additionalTank -= 1;
            }
        }
        return dis += mainTank * 10;
    }
}