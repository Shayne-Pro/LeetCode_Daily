class Solution {
    public int wateringPlants(int[] plants, int capacity) {
        int len = plants.length;
        int steps = 0;
        int ori_cap = capacity;
        for (int i = 0; i < len; i++) {
            if (capacity >= plants[i]) {
                // 足够浇水
                capacity -= plants[i];
                steps++;
            } else {
                // 不够浇水
                steps += i * 2;
                i--;
                capacity = ori_cap;
            }
        }
        return steps;
    }
}