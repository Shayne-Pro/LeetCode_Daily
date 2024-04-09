class Solution {
    public int distinctIntegers(int n) {
        // n>1时，n%(n-1)=1，依次有n-2,n-3,...,2
        // n=1时，只有1
        return n == 1 ? 1 : n - 1;
    }
}