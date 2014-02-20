import java.util.ArrayList;


public class StackSets {
	public static void main(String[] args) {
		NewStack ns1 = new NewStack(3);
		NewStack ns2 = new NewStack(3);
		NewStack ns3 = new NewStack(3);
		SetOfStacks s = new SetOfStacks(3);
		s.stacks.add(ns1); s.stacks.add(ns2); s.stacks.add(ns3);
		s.push(0); s.push(1);
		s.pop();
	}
}

class SetOfStacks {
	ArrayList<NewStack> stacks = new ArrayList<NewStack>();
	public int capacity;
	public SetOfStacks(int capacity) {
		this.capacity = capacity;
	}
	public NewStack getLastStack() {
		if( stacks.size() == 0 )
			return null;
		return stacks.get(stacks.size() - 1);
	}
	
	public void push(int elem) {
		NewStack last = getLastStack();
		if( last != null || !(last.isFull()) ) // add to last stack
			last.push(elem);
		else {
			NewStack st = new NewStack(capacity);
			st.push(elem);
			stacks.add(st);
		}			
		System.out.println("Push " + elem + " into Stack!");
	}
	
	public int pop() {
		NewStack last = getLastStack();
		int elem = last.pop();
		if( last.size == 0 ) 
			stacks.remove(stacks.size() - 1);
		System.out.println("Pop " + elem + " out of Stack!");
		return elem;
	}
	
	public boolean isEmpty() {
		NewStack last = getLastStack();
		return (last == null || last.isEmpty());
	}
	
	public int leftShift(int index, boolean removeTop) {
		NewStack st = stacks.get(index);
		int removed_item;
		if( removeTop )
			removed_item = st.pop();
		else
			removed_item = st.removeBottom();
		if( st.isEmpty() )
			stacks.remove(index);
		else if( stacks.size() > index + 1 ) {
			int elem = leftShift(index + 1, false);
			st.push(elem);
		}
		return removed_item;
	}
	
	public int popAt(int index) {
		return leftShift(index, true);
	}
	
	
}

class NewStack {
	private int capacity;
	public StackNode top, bottom; 
	int size;
	
	public NewStack(int capacity) {
		this.capacity = capacity;
	}
	
	public boolean isFull() {
		return (capacity == size);
	}
	
	public void join(StackNode above, StackNode below) {
		if( below != null )
			below.above = above;
		if( above != null ) 
			above.below = below;
	}
	
	public boolean push(int elem) {
		if( size >= capacity )
			return false;
		size++;
		StackNode n = new StackNode(elem);
		if( size == 1 )
			bottom = n;
		join(n, top);
		top = n;
		return true;
	}
	
	public int pop() {
		StackNode t = top;
		top = top.below;
		size--;
		return t.data;
	}
	
	public boolean isEmpty() {
		return (size == 0);
	}
	
	public int removeBottom() {
		StackNode b = bottom;
		bottom = bottom.above;
		if( bottom != null )
			bottom.below = null;
		size--;
		return b.data;
	}
}

class StackNode {
	int data;
	StackNode above;
	StackNode below;
	StackNode(int i) {
		data = i;
	}
}
