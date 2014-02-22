
public class InorderNextNodeinBST {
	public static MyTreeNode2 NextNode(MyTreeNode2 root, MyTreeNode2 target) {
		if( root == null )
			return null;
		if( root.name.charAt(0) < target.name.charAt(0) ) 
			return NextNode(root.GetRight(), target);
		else if( root.name.charAt(0) > target.name.charAt(0) )
			return NextNode(root.GetLeft(), target);
		else {
			if( root.GetRight() == null ) { // no right subtree
				MyTreeNode2 tmp = root;
				
				while( tmp.GetParent() != null && tmp != tmp.GetParent().GetLeft() )
					tmp = tmp.GetParent();

				return tmp.GetParent();
			}
			else { // result is the most left node in right subtree
				MyTreeNode2 tmp = root.GetRight();
				
				while( tmp.GetLeft() != null )
					tmp = tmp.GetLeft();
				
				return tmp;
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		MyTreeNode2 root = new MyTreeNode2("D");
		MyTreeNode2 n1 = new MyTreeNode2("B");
		MyTreeNode2 n2 = new MyTreeNode2("F");
		MyTreeNode2 n3 = new MyTreeNode2("A");
		MyTreeNode2 n4 = new MyTreeNode2("C");
		MyTreeNode2 n5 = new MyTreeNode2("E");
		root.SetParentChild(null, n1, n2);
		n1.SetParentChild(root, n3, n4);
		n2.SetParentChild(root, n5, null);
		n3.SetParent(n1); n4.SetParent(n1);
		n5.SetParent(n2);
		
		MyTreeNode2 res = NextNode(root, n1);
		System.out.println("Test case 1: ");
		System.out.println("Next node of " + n1.name + " is " + 
							( res == null ? "null" : res.name) );
		
		res = NextNode(root, root);
		System.out.println("Test case 2: ");
		System.out.println("Next node of " + root.name + " is " + 
							( res == null ? "null" : res.name) );
		
		res = NextNode(root, n2);
		System.out.println("Test case 3: ");
		System.out.println("Next node of " + n2.name + " is " + 
							( res == null ? "null" : res.name) );
		
		res = NextNode(root, n4);
		System.out.println("Test case 4: ");
		System.out.println("Next node of " + n4.name + " is " + 
							( res == null ? "null" : res.name) );
	}
}

class MyTreeNode2 extends MyTreeNode {
	// MyTreeNode is defined in BinaryTreeSumPath
	private MyTreeNode2 parent;
	private MyTreeNode2 left;
	private MyTreeNode2 right;
	MyTreeNode2(String s) {
		super(s);
	}
	MyTreeNode2(String s, MyTreeNode2 p) {
		super(s);
		parent = p;
	}
	public void SetParentChild(MyTreeNode2 p, MyTreeNode2 l, 
								MyTreeNode2 r) {
		parent = p;
		left = l;
		right = r;
	}
	public MyTreeNode2 GetLeft() {
		return left;
	}
	public MyTreeNode2 GetRight() {
		return right;
	}
	public MyTreeNode2 GetParent() {
		return parent;
	}
	public void SetParent(MyTreeNode2 p) {
		parent = p;
	}
}