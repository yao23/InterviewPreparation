
public class NumbersWithRank {
	public static void main(String[] args) {
		RankNode n0 = new RankNode(20);
		RankNode n1 = new RankNode(15);
		RankNode n2 = new RankNode(25);
		RankNode n3 = new RankNode(10);
		RankNode n4 = new RankNode(23);
		RankNode n5 = new RankNode(5);
		RankNode n6 = new RankNode(13);
		RankNode n7 = new RankNode(24);
		n0.insertNode(n1.GetVal());
		n0.insertNode(n2.GetVal());
		n0.insertNode(n3.GetVal());
		n0.insertNode(n4.GetVal());
		n0.insertNode(n5.GetVal());
		n0.insertNode(n6.GetVal());
		n0.insertNode(n7.GetVal());
		
		System.out.println("Test case 1:");
		System.out.println("Node " + n7.GetVal() + " rank is: " + 
				n0.getRankOfNumber(n7.GetVal()));
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
		if (n < this.val) {
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
