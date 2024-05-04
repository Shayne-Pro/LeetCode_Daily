class Solution {
	public int reverse(int x) {
		int new_v = 0;
		int t = 0;
		while (x != 0) {
			t = x % 10;
			// 提前判断是否溢出
			if (new_v > (Integer.MAX_VALUE / 10) || new_v < (Integer.MIN_VALUE / 10))
				return 0;
			new_v = new_v * 10 + t;
			x = (x - t) / 10;
		}
		return new_v;
	}
}