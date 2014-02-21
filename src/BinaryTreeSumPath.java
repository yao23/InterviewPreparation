import java.util.ArrayList;

public class BinaryTreeSumPath {
	public static ArrayList<ArrayList<Integer>> FindSumPathRecur(int CurSum, int ExpSum, 
			MyTree root, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> paths) {
		if(  CurSum > ExpSum )
			return paths;
		else if( CurSum == ExpSum ) {
			ArrayList<Integer> TmpPath = new ArrayList<Integer>();
			TmpPath.addAll(path);
			paths.add(TmpPath);
			path.remove(path.size() - 1);
			return paths;
		}
		else {
			if( root == null )
				return paths;
			path.add(root.data);
			CurSum += root.data;
			FindSumPathRecur(CurSum, ExpSum, root.left, path, paths);
			FindSumPathRecur(CurSum, ExpSum, root.right, path, paths);
			path.remove(path.size() - 1);
			return paths;
		}
	}
	
	public static ArrayList<ArrayList<Integer>> FindSumPath(MyTree root, int target) {
		ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
		if( root == null )
			return paths;
		ArrayList<Integer> path = new ArrayList<Integer>();
		FindSumPathRecur(0, target, root, path, paths);
		return paths;
	}
	public static void main(String[] args) {
		MyTree t1 = new MyTree(1); MyTree t2 = new MyTree(2);
		MyTree t3 = new MyTree(3); MyTree t4 = new MyTree(4); 
		MyTree t5 = new MyTree(5); MyTree t6 = new MyTree(6);
		t1.left = t2; t1.right = t3; t2.left = t4; t3.left = t6;
		t4.right = t5;
		ArrayList<ArrayList<Integer>> paths = FindSumPath(t1, 10);
		for( int i = 0; i < paths.size(); i++ ) {
			System.out.print("{");
			for( int j = 0; j < paths.get(i).size(); j++ ) 
				System.out.print(paths.get(i).get(j) + " ");
			System.out.println("}");
		}
	}
}

class MyTree {
	int data;
	MyTree left;
	MyTree right;
	MyTree(int i) {
		data = i;
	}
}
