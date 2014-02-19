
public class RemoveMidNode {
	public static void RemoveMiddleNode(ListNode l) {
		ListNode next = l.next;
		
		while( next != null ) {
			l.i = next.i;
			l.next = next.next;
			l = l.next;
			next = l.next;
		}
		
	}
	
	public static void main(String[] args) {
		ListNode l1 = new ListNode(1);	ListNode l2 = new ListNode(2);
		ListNode l3 = new ListNode(3);	ListNode l4 = new ListNode(4);
		ListNode l5 = new ListNode(5);	
		l1.next = l2; l2.next = l3; l3.next = l4; l4.next = l5;
		l5.next = null;
		
		System.out.println("Before removing: ");
		l1.PrintList();
		System.out.println();
		System.out.println("After removing: ");
		RemoveMiddleNode(l3);
		l1.PrintList();
	}
}
