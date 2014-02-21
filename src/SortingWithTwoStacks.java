import java.util.Stack;


public class SortingWithTwoStacks {
	public static Stack<Integer> sort(Stack<Integer> nums) {
		Stack<Integer> res = new Stack<Integer>();
		if( nums == null || nums.isEmpty() )
			return res;
		int tmp;
		res.push(nums.pop());
		
		while( !nums.isEmpty() ) {
			if( nums.peek() <= res.peek() )
				res.push(nums.pop());
			else {
				tmp = nums.pop();
				while( !res.isEmpty() && res.peek() < tmp ) 
					nums.push(res.pop());
				res.push(tmp);
			}				
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		int n = 10;
		Stack<Integer> nums = new Stack<Integer>();
		System.out.print("Befor sorting: ");
		for( int i = 0; i < n; i++ ) {
			nums.push(1 + (int)(Math.random() * ((10 - 1) + 1)));
			System.out.print(nums.peek() + " ");
		}
		
		System.out.println();
		
		Stack<Integer> res = sort(nums);
		System.out.print("After sorting: ");
		for( int i : res )
			System.out.print(i + " ");
	}
}
