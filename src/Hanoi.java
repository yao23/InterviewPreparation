import java.util.Stack;


public class Hanoi {
	public static void main(String[] args) {
		int n = 3;
		Tower[] towers = new Tower[3];
		for( int i = 0; i < n; i++ )
			towers[i] = new Tower(i + 1); // initialize towers
		for( int i = n; i > 0; i-- ) 
			towers[0].add(i); // add all disks to tower 1
		towers[0].MoveWithSupport(n, towers[2], towers[1]);
	}
}

class Tower {
	private int idx;
	private Stack<Integer> disks;
	Tower(int i) {
		idx = i;
		disks = new Stack<Integer>();
	}
	
	public int GetIndex() {
		return idx;
	}
	
	public void add(int i) {
		if( !disks.isEmpty() && disks.peek() <= i )
			return;
		else
			disks.push(i);
	}
	
	public void MoveWithSupport(int n, Tower dest, Tower sup) {
		if( n > 0 ) {
			MoveWithSupport(n - 1, sup, dest);
			MoveDirectlyTo(dest);
			sup.MoveWithSupport(n - 1, dest, this);
		}
	}
	
	public void MoveDirectlyTo(Tower t) {
		int data = this.disks.pop();
		t.disks.push(data);
		System.out.println("Move disk " + data + " from tower " 
							+ this.idx + " to tower " + t.idx);
	}
}