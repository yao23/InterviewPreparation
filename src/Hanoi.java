import java.util.Stack;


public class Hanoi {
	public static void main(String[] args) {
		int n = 3;
		Tower[] towers = new Tower[3];
		for( int i = 0; i < n; i++ )
			towers[i] = new Tower(i + 1);
		towers[0].MoveWithSupport(n, towers[2], towers[1]);
	}
}

class Tower {
	int idx;
	Stack<Integer> st;
	Tower(int i) {
		idx = i;
		st.push(idx);
	}
	public void MoveWithSupport(int n, Tower dest, Tower sup) {
		if( n <= 0 )
			return;
		this.MoveWithSupport(n - 1, sup, dest);
		this.MoveDirectly(dest);
		sup.MoveWithSupport(n - 1, dest, this);
	}
	
	public void MoveDirectly(Tower t) {
		int data = this.st.pop();
		t.st.push(data);
		System.out.println("Move disk from " + this.idx + " to " +t.idx);
	}
}