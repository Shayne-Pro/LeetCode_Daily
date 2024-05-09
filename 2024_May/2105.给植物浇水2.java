class Solution {
    public int minimumRefill(int[] plants, int capacityA, int capacityB) {
        int len = plants.length;
        int num = 0;
        int pos_a = 0, pos_b = len - 1;
        int val_a = capacityA, val_b = capacityB;
        while (pos_a < pos_b) {
            // A浇水
            if (val_a < plants[pos_a]) {
                num++;
                val_a = capacityA - plants[pos_a];
            } else {
                val_a -= plants[pos_a];
            }
            pos_a++;
            // B浇水
            if (val_b < plants[pos_b]) {
                num++;
                val_b = capacityB - plants[pos_b];
            } else {
                val_b -= plants[pos_b];
            }
            pos_b--;
        }
        if (pos_a == pos_b) {
            // A，B到同一个植物时，水多的浇水
            if (val_a >= val_b && val_a < plants[pos_a]) {
                num++;
            }
            if (val_a < val_b && val_b < plants[pos_b]) {
                num++;
            }
        }
        return num;
    }
}