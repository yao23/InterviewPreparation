
public class AddTwoNumberRepresentedByLinkedList {
	private static int carry = 0;
	
	public static int CalLength(ListNode l) {
		if( l == null )
			return 0;
		int len = 0;
		
		while( l != null ) {
			len++;
			l = l.next;
		}
		
		return len;
	}
	
	public static ListNode AddTwoNumber(ListNode l1, ListNode l2, 
							int len1, int len2, int CurLen1, int CurLen2) {
		if( l1.next == null && l2.next == null ) {
			int NodeVal = (l1.data + l2.data + carry) % 10; 
			carry = (l1.data + l2.data + carry) / 10;
			return new ListNode(NodeVal);
		}
		else {
			ListNode Next = new ListNode(0);
			int NodeVal = 0;
			if( CurLen1 < CurLen2 ) {
				Next = AddTwoNumber(l1, l2.next, len1, len2, CurLen1, CurLen2 - 1);
				NodeVal = (l2.data + carry) % 10;
				carry = (l2.data + carry) / 10;
			}
			else if( CurLen2 < CurLen1 ) {
				Next = AddTwoNumber(l1.next, l2, len1, len2, CurLen1 - 1, CurLen2);
				NodeVal = (l1.data + carry) % 10;
				carry = (l1.data + carry) / 10;
			}
			else {
				Next = AddTwoNumber(l1.next, l2.next, len1, len2, CurLen1 - 1, CurLen2 -1);
				NodeVal = (l1.data + l2.data + carry) % 10; 
				carry = (l1.data + l2.data + carry) / 10;
			}
			ListNode Cur = new ListNode(NodeVal);
			Cur.next = Next;
			if( CurLen1 < len1 || CurLen2 < len2 )
				return Cur;
			else {
				if( carry == 0)
					return Cur;
				else {
					ListNode head = new ListNode(carry);
					head.next = Cur;
					return head;
				}
			}
		}	
	}
	
	public static void main(String[] args) {
		// ListNode definition in RemoveDuplicatedFromUnsortedLinkedList
		ListNode l1_1 = new ListNode(1);	ListNode l1_2 = new ListNode(2);
		ListNode l1_3 = new ListNode(3);	ListNode l1_4 = new ListNode(4);
		ListNode l1_5 = new ListNode(5);	
		l1_1.next = l1_2; l1_2.next = l1_3; l1_3.next = l1_4; l1_4.next = l1_5;
		l1_5.next = null;
		
		ListNode l2_1 = new ListNode(1);	ListNode l2_2 = new ListNode(2);
		ListNode l2_3 = new ListNode(1);	ListNode l2_4 = new ListNode(3);
		ListNode l2_5 = new ListNode(4);	ListNode l2_6 = new ListNode(5);
		ListNode l2_7 = new ListNode(2);	ListNode l2_8 = new ListNode(3);
		l2_1.next = l2_2; l2_2.next = l2_3; l2_3.next = l2_4; l2_4.next = l2_5;
		l2_5.next = l2_6; l2_6.next = l2_7; l2_7.next = l2_8; l2_8.next = null;
		
		int len1 = CalLength(l1_1);
		int len2 = CalLength(l2_1);
		
		System.out.println("List 1: ");
		l1_1.PrintList();
		System.out.println("List 2: ");
		l2_1.PrintList();
		
		System.out.println("Sum: ");
		ListNode res = AddTwoNumber(l1_1, l2_1, len1, len2, len1, len2);
		res.PrintList();
		
		System.out.println("Test case 2: ");
		
		ListNode l3_1 = new ListNode(6);	ListNode l3_2 = new ListNode(1);
		ListNode l3_3 = new ListNode(7);	
		l3_1.next = l3_2; l3_2.next = l3_3; l3_3.next = null; 
		
		ListNode l4_1 = new ListNode(2);	ListNode l4_2 = new ListNode(9);
		ListNode l4_3 = new ListNode(5);	
		l4_1.next = l4_2; l4_2.next = l4_3; l4_3.next = null; 
		
		int len2_1 = CalLength(l3_1);
		int len2_2 = CalLength(l4_1);
		
		System.out.println("List 1: ");
		l3_1.PrintList();
		System.out.println("List 2: ");
		l4_1.PrintList();
		
		System.out.println("Sum: ");
		ListNode res2 = AddTwoNumber(l3_1, l4_1, len2_1, len2_2, len2_1, len2_2);
		res2.PrintList();
	}
}
