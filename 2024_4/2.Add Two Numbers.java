/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		return add(l1, l2, 0);
	}
	public ListNode add(ListNode l1, ListNode l2, int bit) {
		if(l1 == null && l2 == null && bit == 0) {
			return null;
		}
		int val = bit;
		if(l1 != null) {
			val += l1.val;
			l1 = l1.next;
		}
		if(l2 != null) {
			val += l2.val;
			l2 = l2.next;
		}
		ListNode node = new ListNode(val % 10);
		node.next = add(l1, l2, val / 10);
		return node;
	}
}