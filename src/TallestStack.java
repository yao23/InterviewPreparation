import java.util.ArrayList;
import java.util.HashMap;

public class TallestStack {
	//public static int MaxHeight = 0;
	
	private static ArrayList<box> CalTallestStack(HashMap<box, ArrayList<box>> StackMap, 
													ArrayList<box> boxes, box bottom) {
		if( boxes == null || boxes.size() == 0 )
			return boxes;
		
		if( bottom != null && StackMap.containsKey(bottom) ) {
			System.out.println("Get cached in bottom: " + bottom.GetId());
			for( int i = 0; i < StackMap.get(bottom).size(); i++ )
			System.out.print(StackMap.get(bottom).get(i).GetId() + " ");
			System.out.println();
			return StackMap.get(bottom);
		}
		
		//int TmpHeight = 0;
		int MaxHeight = 0, TmpHeight = 0;
		ArrayList<box> res = null;
		for( int i = 0; i < boxes.size(); i++ ) {			
			if( boxes.get(i).CanBeAbove(bottom) ) {
				ArrayList<box> TmpRes = CalTallestStack(StackMap, boxes, boxes.get(i));
				for( int j = 0; j < TmpRes.size(); j++ )
					TmpHeight += TmpRes.get(j).GetHeight();
				if( TmpHeight > MaxHeight ) {
					MaxHeight = TmpHeight;
					res = TmpRes;
					if( bottom == null )
						System.out.println("Bottom is null!");
					else 
						System.out.println("Bottom: " + bottom.GetId());
					for( int j = 0; j < res.size(); j++ )
						System.out.print(res.get(j).GetId() + " ");
					System.out.println();
				}
				TmpHeight = 0;
			}			
		}
		
		if( res == null )
			res = new ArrayList<box>();
		
		if( bottom != null )
			res.add(0, bottom);
		else 
			System.out.println("The max height: " + MaxHeight);
		
		StackMap.put(bottom, res);
		//return res;
		//return (ArrayList<box>)res.clone();
		return new ArrayList<box>(res);
	}
	
	public static void main(String[] args) {
		box tmp = new box();
		HashMap<box, ArrayList<box>> map = new HashMap<box, ArrayList<box>>();
		ArrayList<box> res = new ArrayList<box>();
		ArrayList<box> boxes = new ArrayList<box>();
		box b0 = new box(0, 10, 10, 10);
		box b1 = new box(1, 9, 9, 9);
		box b2 = new box(2, 8, 9, 8);
		box b3 = new box(3, 7, 7, 7);
		box b4 = new box(4, 6, 6, 6);
		box b5 = new box(5, 5, 5, 5);
		boxes.add(b0); boxes.add(b1);
		boxes.add(b2); boxes.add(b3);
		boxes.add(b4); boxes.add(b5);
		res = CalTallestStack(map, boxes, null);
		System.out.println("The tallest stack is build with: ");
		for( int i = 0; i < res.size(); i++ ) {
			tmp = res.get(i);
			System.out.print( tmp.GetId() + " ");
		}
	}
}

class box {
	private int id;
	private int length;
	private int width;
	private int height;
	
	public box() {
		
	}
	
	public box(int i, int l, int w, int h) {
		id = i;
		length = l;
		width = w;
		height = h;
	}
	
	public boolean CanBeAbove(box b) {
		if( b == null )
			return true;
		if( this.length < b.GetLength() && this.width < b.GetWidth() &&
				this.height < b.GetHeight() )
			return true;
		else
			return false;
	}
	
	public int GetId() {
		return id;
	}
	
	public int GetLength() {
		return length;
	}
	
	public int GetWidth() {
		return width;
	}
	
	public int GetHeight() {
		return height;
	}
}
