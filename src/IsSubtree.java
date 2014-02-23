
public class IsSubtree {
	public boolean TreeMatch(MyTreeNode t1, MyTreeNode t2) {
		if( t1 == null && t2 == null )
			return true;
		else if( t1 == null || t2 == null )
			return false;			
		else if( t1.name == t2.name )
			return (TreeMatch(t1.left, t2.right) && TreeMatch(t1.right, t2.right));
		else // t1.name != t2.name
			return false;
	}
	public boolean subtree(MyTreeNode t1, MyTreeNode t2) {
		if( t2 == null )
			return true;
		if( t1 == null )
			return false;
		if( t1.name == t2.name ) {
			if( TreeMatch(t1, t2) )
				return true;
		}
		return (subtree(t1.left, t2) || subtree(t1.right, t2));
	}
}
