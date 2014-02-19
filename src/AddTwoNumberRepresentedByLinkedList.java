
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
	
	public static ListNode AddTwoNumber(ListNode l1, ListNode l2) {
		if( l1 == null ) {
			int NodeVal = (l2.data + carry) % 10; 
			carry = (l2.data + carry) / 10;
			return new ListNode(NodeVal); 			
		}
		if( l2 == null ) {
			int NodeVal = (l1.data + carry) % 10; 
			carry = (l1.data + carry) / 10;
			return new ListNode(NodeVal);
		}
		
		if( l1.next == null && l2.next == null ) {
			int NodeVal = (l1.data + l2.data + carry) % 10; 
			carry = (l1.data + l2.data + carry) / 10;
			return new ListNode(NodeVal);
		}
		else {
			int len1 = CalLength(l1);
			int len2 = CalLength(l2);
			if( len1 < len2 ) {
				for( int i = 0; i < (len2 - len1); i++ )
					l2 = l2.next;
			}
			else if( len2 < len1 ) {
				for( int i = 0; i < (len1 - len2); i++ )
					l1 = l1.next;
			}
			
			return AddTwoNumber(l1, l2);
		}	
	}
	
	public static void main(String[] args) {
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
		
		
		System.out.println("List 1: ");
		l1_1.PrintList();
		System.out.println("List 2: ");
		l2_1.PrintList();
		
		System.out.println("Sum: ");
		ListNode res = AddTwoNumber(l1_1, l2_1);
		res.PrintList();
	}
}
