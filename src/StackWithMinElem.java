import java.util.Stack;


public class StackWithMinElem {
	private static Stack<Integer> MinStack = new Stack<Integer>();
	
	public static void push(int elem, Stack<Integer> st) {
		if( MinStack.isEmpty() || elem <= MinStack.peek() )
			MinStack.push(elem);
		st.push(elem);
		System.out.println("Push " + elem + " into Stack!");
	}
	
	public static int pop(Stack<Integer> st) {
		if( st.isEmpty() ) { // stack is empty
			System.out.println("Stack is empty! Cannot pop!");
			return Integer.MIN_VALUE;
		}
		else {
			if( st.peek() == MinStack.peek() )
				MinStack.pop();
			System.out.println("Pop " + st.peek() + " out of Stack!");
			return st.pop();
		}
	}
	
	public static int peek(Stack<Integer> st) {
		if( st.isEmpty() ) { // stack is empty
			System.out.println("Stack is empty! Cannot peek!");
			return Integer.MIN_VALUE;
		}
		System.out.println("Peek " + st.peek() + " in Stack!");
		return st.peek();
	}
	
	public static boolean IsEmpty(Stack<Integer> st) {
		return st.isEmpty();
	}
	
	public static int min(Stack<Integer> st) {
		if( st.isEmpty() ) { // stack is empty
			System.out.println("Stack is empty! No min value!");
			return Integer.MIN_VALUE;
		}
		System.out.println("Stack min value is " + MinStack.peek() + "!");
		return MinStack.peek();
	}
	
	public static void main(String[] args) {
		Stack<Integer> st = new Stack<Integer>();
		pop(st);
		peek(st);
		min(st);
		push(11, st);
		peek(st);
		min(st);
		
		for( int i = 0; i < 9; i++ )
			push(1 + (int)(Math.random() * ((10 - 1) + 1)), st);
		
		min(st);
	}
}
