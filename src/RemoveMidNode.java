
public class RemoveMidNode {
	public static void RemoveMiddleNode(ListNode l) {
		if( l == null )
			return; 
		if( l.next == null ) {
			l = null;
			return;
		}
		ListNode next = l.next;
		l.data = next.data;
		l.next = next.next;		
	}
	
	public static void RemoveMiddleNode2(ListNode l) {
		if( l == null )
			return; 
		if( l.next == null ) {
			l = null;
			return;
		}
		ListNode next = l.next;
		ListNode pre = new ListNode(-1);
		pre.next = l;
		
		while( next != null ) {
			l.data = next.data;
			pre = l;
			l = l.next;
			next = l.next;
		}
		
		pre.next = null;
		
	}
	
	public static void main(String[] args) {
		ListNode l1 = new ListNode(1);	ListNode l2 = new ListNode(2);
		ListNode l3 = new ListNode(3);	ListNode l4 = new ListNode(4);
		ListNode l5 = new ListNode(5);	
		l1.next = l2; l2.next = l3; l3.next = l4; l4.next = l5;
		l5.next = null;
		
		System.out.println("Before removing: ");
		l1.PrintList();
		
		System.out.println("After removing: ");
		RemoveMiddleNode(l3);
		l1.PrintList();
		
		System.out.println("For test case 2: ");
		ListNode l2_1 = new ListNode(1);	ListNode l2_2 = new ListNode(2);
		ListNode l2_3 = new ListNode(1);	ListNode l2_4 = new ListNode(3);
		ListNode l2_5 = new ListNode(4);	ListNode l2_6 = new ListNode(5);
		ListNode l2_7 = new ListNode(2);	ListNode l2_8 = new ListNode(3);
		l2_1.next = l2_2; l2_2.next = l2_3; l2_3.next = l2_4; l2_4.next = l2_5;
		l2_5.next = l2_6; l2_6.next = l2_7; l2_7.next = l2_8; l2_8.next = null;
		
		System.out.println("Before removing: ");
		l2_1.PrintList();
		
		System.out.println("After removing: ");
		RemoveMiddleNode(l2_3);
		l2_1.PrintList();
		
		System.out.println("Test complicated solution: ");
		
		System.out.println("Before removing: ");
		l1.PrintList();
		
		System.out.println("After removing: ");
		RemoveMiddleNode(l3);
		l1.PrintList();
		
		System.out.println("\nFor test case 2: ");
		
		System.out.println("Before removing: ");
		l2_1.PrintList();
		
		System.out.println("After removing: ");
		RemoveMiddleNode(l2_3);
		l2_1.PrintList();
	}
}
