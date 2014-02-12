import java.util.ArrayList;

public class PrintMultipleChildrenTree {
	private static void PrintTree(ArrayList<TNode> nodes, int ParentId, int depth)  {
	  for( TNode node : nodes ) {
	     if( node.id == 0 ) // no node in current position
	       return;
	     if( node.p_id == ParentId ) {
	       for( int i = 0; i < depth; i++ )
	          System.out.print("-");
	       System.out.println(node.data);
	       PrintTree(nodes, node.id, depth + 1);
	     }
	  }	  
	}
	
	public static void PrintTrees(ArrayList<TNode> nodes) {
		   int ParentId = 0;
		   int depth = 0;
		   PrintTree(nodes, ParentId, depth);
	}
		
	public static void main(String[] args) {
		TNode a = new TNode(); TNode b = new TNode(); TNode c = new TNode();
		TNode d = new TNode(); TNode e = new TNode(); TNode f = new TNode();
		TNode g = new TNode(); TNode h = new TNode(); 
		a.p_id = 0; a.id = 1; a.data = "a";	b.p_id = 1; b.id = 2; b.data = "b";
		c.p_id = 1; c.id = 3; c.data = "c";	d.p_id = 2; d.id = 4; d.data = "d";	
		e.p_id = 2; e.id = 5; e.data = "e";	f.p_id = 3; f.id = 6; f.data = "f";
		g.p_id = 3; g.id = 7; g.data = "g";	h.p_id = 3; h.id = 8; c.data = "h";
		
		ArrayList<TNode> nodes = new ArrayList<TNode>();
		nodes.add(a); nodes.add(b); nodes.add(c); nodes.add(d);
		nodes.add(e); nodes.add(f); nodes.add(g); nodes.add(h);
		
		PrintTrees(nodes);
	}
}

class TNode {
	  int id;
	  int p_id;
	  String data;
}
