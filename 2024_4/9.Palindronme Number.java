class Solution {
	public boolean isPalindrome(int x) {
		int new_v = 0;
		int old = x;
		int t = 0;
		if(x < 0) {
			return false;
		}
		while(x != 0) {
			t = x % 10;
			new_v = new_v * 10 + t;
			x = (x - t) / 10;
		}
		if(new_v == old) {
			return true;
		}
		else return false;
	}
}