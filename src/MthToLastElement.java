
public class MthToLastElement {
	public static int FindMthLastToEnd(int[] nums, int m) {
		int res = 0, end = 0, diff = 0;
		
		while( end < nums.length ) {
			if( diff == m )
				res++;
			else
				diff++;
			end++;
		}
		
		return nums[res-1];
	}
	
	public static void main(String args[]) {
		int[] nums = new int[]{1, 2, 3, 4, 5};
		int[] nums2 = new int[]{5, 6, 7, 8, 9};
		System.out.println("4th last to end: " + FindMthLastToEnd(nums, 4));
		System.out.println("2nd last to end: " + FindMthLastToEnd(nums2, 2));
	}
}
