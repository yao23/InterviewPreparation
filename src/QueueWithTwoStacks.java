import java.util.Stack;


public class QueueWithTwoStacks {
	public static void main(String[] args) {
		MyQueue q = new MyQueue();
		q.DeQueue();
		for( int i = 0; i < 3; i++ )
			q.EnQueue(1 + (int)(Math.random() * ((3 - 1) + 1)));
		for( int i = 0; i < 5; i++ )
			q.DeQueue();
	}
}

class MyQueue {
	Stack<Integer> in;
	Stack<Integer> out;
	MyQueue() {
		in = new Stack<Integer>();
		out = new Stack<Integer>();
	}
	public void EnQueue(int i) {
		in.push(i);
		System.out.println("Add " + i + " in queue!");
	}
	public int DeQueue() {
		if( out.isEmpty() ) {
			while( !in.isEmpty() ) 
				out.push(in.pop());
			if( out.isEmpty() ) {
				System.out.println("Queue is empty, cannot remove!");
				return -1;
			}
		}
		System.out.println("Remove " + out.peek() + " from queue!");
		return out.pop();
	}
}