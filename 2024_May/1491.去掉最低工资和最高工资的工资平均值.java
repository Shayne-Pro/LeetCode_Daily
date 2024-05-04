class Solution {
    public double average(int[] salary) {
        int min = salary[0], max = salary[0];
        double sum = salary[0];
        int len = salary.length;
        for (int i = 1; i < len; i++) {
            if (salary[i] < min)
                min = salary[i];
            else if (salary[i] > max)
                max = salary[i];
            sum += salary[i];
        }
        return (sum - min - max) / (len - 2);

    }
}