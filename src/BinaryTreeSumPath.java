import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BinaryTreeSumPath {
	public static ArrayList<Pair> AppendRootToPath(MyTreeNode root, 
			MyTreeNode child, HashMap<String, Integer> result) {
		ArrayList<Pair> ret = new ArrayList<Pair>(); // base case for upper level 
		if( root == null || child == null ) 
		return ret; 
		ArrayList<Pair> subResult = GetPath(child, result);
		
		for( int i = 0; i < subResult.size(); i++ ) {
		Pair tmp = subResult.get(i);
		String key = root.name + tmp.GetName();
		int value = root.val + tmp.GetVal();
		result.put(key, value);
		ret.add(new Pair(key, value));
		}
		
		return ret;
	}

	public static ArrayList<Pair> GetPath(MyTreeNode root, HashMap<String, Integer> result) {
		ArrayList<Pair> ret = new ArrayList<Pair>(); // base case for upper level
		if( root == null ) 
			return ret;
		if( root.left == null && root.right == null ) { // leaf node
			ret.add(new Pair(root.name, root.val));
			return ret;
		}
		ArrayList<Pair> left = new ArrayList<Pair>();
		ArrayList<Pair> right = new ArrayList<Pair>();
		if( root.left != null ) {
			left = AppendRootToPath(root, root.left, result);
			ret.addAll(left);
		}
		if( root.right != null ) {
			right = AppendRootToPath(root, root.right, result);
			ret.addAll(right);
		}
		if( !left.isEmpty() && !right.isEmpty() ) {
			for( int i = 0; i < left.size(); i++ ) {
				Pair tmp = left.get(i);
				for( int j = 0; j < right.size(); j++ ) {
					Pair tmp2 = right.get(j);
					String reverseStr = tmp.GetName();
					reverseStr = new StringBuilder(reverseStr).reverse().toString();
					reverseStr = reverseStr.substring(0, reverseStr.length() - 1);
					result.put(reverseStr + tmp2.GetName(), tmp.GetVal() - root.val + tmp2.GetVal());
				}
			}
		}
		
		return ret;
	}

	public static void main(String[] args) {
		MyTreeNode root = new MyTreeNode("A", 0, null, null);
		MyTreeNode n1 = new MyTreeNode("B", 1, null, null);
		MyTreeNode n2 = new MyTreeNode("C", 2, null, null);
		MyTreeNode n3 = new MyTreeNode("D", 3, null, null);
		MyTreeNode n4 = new MyTreeNode("E", 4, null, null);
		MyTreeNode n5 = new MyTreeNode("F", 5, null, null);
		root.left = n1;	root.right = n2; n1.left = n3;
		n1.right = n4;	n2.left = n5;
		
		HashMap<String, Integer> res = new HashMap<String, Integer>();
		
		int target = 5;
		GetPath(root, res);
		
		if( res.containsValue(target) ) {			
			Iterator<Map.Entry<String, Integer>> it = res.entrySet().iterator();
			
			while (it.hasNext()) {
				Map.Entry<String, Integer> pairs = it.next();
				if( pairs.getValue() == target ) {
					String tmp = pairs.getKey();
					for( int i = 0; i < (tmp.length() - 1); i++ )
						System.out.print(tmp.charAt(i) + "->");
					System.out.println(tmp.charAt(tmp.length() - 1));
				}
				it.remove(); // avoids a ConcurrentModificationException
			}
		}
	}
}

class MyTreeNode {
	String name;
	int val;
	MyTreeNode left;
	MyTreeNode right;
	MyTreeNode(String n) {
		name = n;
	}
	MyTreeNode(String n, int v, MyTreeNode l, MyTreeNode r) {
		name = n;
		val = v; 
		left = l;
		right = r;
	}
}
	
class Pair {
	private String name;
	private int val;
	Pair(String s, int i) {
		name = s;
		val = i;
	}
	public String GetName() {
		return name;
	}
	public int GetVal() {
		return val;
	}
}
	
