import java.util.Stack;

public class IsLinkedListPalindromic {
	public static boolean CheckList(ListNode l) {
		if( l == null || l.next == null )
			return true;
		ListNode slow = l;
		ListNode fast = l;
		Stack<ListNode> FirstHalf = new Stack<ListNode>();
		
		while( fast != null && fast.next != null ) {
			FirstHalf.push(slow);
			slow = slow.next;
			fast = fast.next.next;
		}
		
		if( fast == null ) { // list length is even
			// nothing, two elements in the middle, process later
		}
		else { // fast.next == null, list length is odd
			slow = slow.next;
		}
				
		while( slow != null ) {
			if( slow.data != FirstHalf.peek().data )
				return false; 
			FirstHalf.pop();
			slow = slow.next;
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		// ListNode definition in RemoveDuplicatedFromUnsortedLinkedList
		ListNode l1_1 = new ListNode(1);	ListNode l1_2 = new ListNode(2);
		ListNode l1_3 = new ListNode(3);	ListNode l1_4 = new ListNode(2);
		ListNode l1_5 = new ListNode(1);	
		l1_1.next = l1_2; l1_2.next = l1_3; l1_3.next = l1_4; l1_4.next = l1_5;
		l1_5.next = null;
		
		ListNode l2_1 = new ListNode(1);	ListNode l2_2 = new ListNode(2);
		ListNode l2_3 = new ListNode(3);	ListNode l2_4 = new ListNode(3);
		ListNode l2_5 = new ListNode(2);	ListNode l2_6 = new ListNode(1);
		l2_1.next = l2_2; l2_2.next = l2_3; l2_3.next = l2_4; l2_4.next = l2_5;
		l2_5.next = l2_6; l2_6.next = null;
		
		System.out.println("Test case 1: ");
		System.out.print("List 1: ");
		l1_1.PrintList();
		System.out.println("List 1 is palindromic? " +
							(CheckList(l1_1) ? "true" : "false"));
		
		System.out.println("Test case 2: ");
		System.out.print("List 2: ");
		l2_1.PrintList();		
		System.out.println("List 2 is palindromic? " +
				(CheckList(l2_1) ? "true" : "false"));
		
		ListNode l3_1 = new ListNode(6);	ListNode l3_2 = new ListNode(1);
		ListNode l3_3 = new ListNode(7);	
		l3_1.next = l3_2; l3_2.next = l3_3; l3_3.next = null; 
		System.out.println("Test case 3: ");
		System.out.print("List 3: ");
		l3_1.PrintList();
		System.out.println("List 3 is palindromic? " +
							(CheckList(l3_1) ? "true" : "false"));
		
		ListNode l4_1 = new ListNode(2);	ListNode l4_2 = new ListNode(9);
		ListNode l4_3 = new ListNode(5);	ListNode l4_4 = new ListNode(5);
		l4_1.next = l4_2; l4_2.next = l4_3; l4_3.next = l4_4; l4_4.next = null;
		System.out.println("Test case 4: ");
		System.out.print("List 4: ");
		l4_1.PrintList();		
		System.out.println("List 4 is palindromic? " +
				(CheckList(l4_1) ? "true" : "false"));
	}
}
