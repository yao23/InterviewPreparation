
public class NumbersWithRank {
	public static void main(String[] args) {
		RankNode n0 = new RankNode(20);
		n0.insertNode(15);
		n0.insertNode(25);
		n0.insertNode(10);
		n0.insertNode(23);
		n0.insertNode(5);
		n0.insertNode(13);
		n0.insertNode(24);
		
		System.out.println("Test case 1:");
		System.out.println("Node " + 24 + " rank is: " + 
				n0.getRankOfNumber(24));
		
		RankNode n1 = new RankNode(5);
		n1.insertNode(1);
		n1.insertNode(4);
		n1.insertNode(4);
		n1.insertNode(5);
		n1.insertNode(9);
		n1.insertNode(7);
		n1.insertNode(13);
		n1.insertNode(3);
		
		System.out.println("Test case 2:");
		System.out.println("Node " + 1 + " rank is: " + 
				n1.getRankOfNumber(1));
		System.out.println("Node " + 3 + " rank is: " + 
				n1.getRankOfNumber(3));
		System.out.println("Node " + 4 + " rank is: " + 
				n1.getRankOfNumber(4));
	}
}

class RankNode {
	private int val;
	RankNode left;
	RankNode right;
	private int leftSize;
	RankNode(int i) {
		val = i;
		leftSize = 0;
	}
	
	public int GetVal() {
		return val;
	}
	
	public void insertNode(int n) {
		if (n <= this.val) {
			if (this.left == null) {
				this.left = new RankNode(n);
			} else {
				this.left.insertNode(n);
			}
			this.leftSize++;
		} else {
			if (this.right == null) {
				this.right = new RankNode(n);
			} else {
				this.right.insertNode(n);
			}
		}
	}
	
	public int getRankOfNumber(int v) {
		if (this.val == v) {
			return this.leftSize;
		} else if (v < this.val) {
			if (this.left == null) {
				return -1;
			} else {
				return this.left.getRankOfNumber(v);
			}			
		} else {
			if (this.right == null) {
				return -1;
			} else {
				return (this.leftSize + 1 + this.right.getRankOfNumber(v));
			}
		}
	}
}
