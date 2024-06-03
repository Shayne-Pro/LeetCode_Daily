class Solution {
    public int distributeCandies(int[] candyType) {
        int n = candyType.length / 2;
        Set<Integer> uniqueNumbers = new HashSet<>();
        // 将数组中的所有元素添加到Set中
        for (int number : candyType) {
            uniqueNumbers.add(number);
        }
        return Math.min(uniqueNumbers.size(), n);
    }
}