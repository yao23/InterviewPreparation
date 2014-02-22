import java.util.ArrayList;
import java.util.LinkedList;

public class SearchRouteInGraph {
	private static boolean SearchRoute(Vertex s, Vertex e, Graph g) {
		ArrayList<Vertex> vertices = g.GetVertices();
		LinkedList<Vertex> q = new LinkedList<Vertex>();
		for( int i = 0; i < vertices.size(); i++ ) { // find start vertex
			Vertex cur = vertices.get(i);
			if( cur == s ) {
				q.add(cur);
			
				while( !q.isEmpty() ) {
					cur = q.removeFirst();
					System.out.println("Current vertex is: " + cur.GetName()); 
					for( Vertex adj : cur.GetAdjacency() ) {
						System.out.println("Adjacent vertex is: " + adj.GetName()); 
						if( !adj.IsVisited() ) {
							if( adj == e ) // find the route
								return true;
							adj.SetVisited();
							q.add(adj);
						}
					}
				}
				
				System.out.println("Vertex queue is empty!");
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		Vertex v1 = new Vertex("A"); Vertex v2 = new Vertex("B");
		Vertex v3 = new Vertex("C"); Vertex v4 = new Vertex("D");
		Vertex v5 = new Vertex("E"); Vertex v6 = new Vertex("F");
		Edge e1 = new Edge(v1, v2); Edge e2 = new Edge(v1, v3);
		Edge e3 = new Edge(v2, v4); Edge e4 = new Edge(v2, v5);
		Edge e5 = new Edge(v3, v6);
		Graph g = new Graph();
		g.AddVertex(v1); g.AddVertex(v2); g.AddVertex(v3); g.AddVertex(v4);
		g.AddVertex(v5); g.AddVertex(v6);
		g.AddEdge(e1); g.AddEdge(e2); g.AddEdge(e3); g.AddEdge(e4);
		g.AddEdge(e5);
		/*
		System.out.println("Test case 1:");
		System.out.println("A route from " + v1.GetName() + " to " +
							v5.GetName() + " existed? " + 
							(SearchRoute(v1, v5, g) ? "true" : "false"));
		*/
		System.out.println("Test case 2:");
		System.out.println("A route from " + v1.GetName() + " to " +
							v6.GetName() + " existed? " + 
							(SearchRoute(v1, v6, g) ? "true" : "false"));
	}
}

class Vertex {
	private String name;
	private boolean visited;
	private ArrayList<Vertex> adjacency;
	Vertex(String s) {
		name = s;
		visited = false;
		adjacency = new ArrayList<Vertex>();
	}
	public String GetName() {
		return name;
	}
	public boolean IsVisited() {
		return visited;
	}
	public void SetVisited() {
		visited = true;
	}
	public void AddToAdjacency(Vertex v) {
		adjacency.add(v);
	}
	public ArrayList<Vertex> GetAdjacency() {
		return adjacency;
	}
	
}

class Edge {
	private Vertex start;
	private Vertex end;
	Edge(Vertex s, Vertex e) {
		start = s;
		end = e;
		start.AddToAdjacency(end); 
	}
	public Vertex GetStart() {
		return start;
	}
	public Vertex GetEnd() {
		return end;
	}
}

class Graph {
	private ArrayList<Vertex> vertices;
	private ArrayList<Edge> edges;
	Graph() {
		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
	}
	public ArrayList<Vertex> GetVertices() {
		return vertices;
	}
	public ArrayList<Edge> GetEdges() {
		return edges;
	}
	public void AddVertex(Vertex v) {
		vertices.add(v);
	}
	public void AddEdge(Edge e) {
		edges.add(e);
	}
}