
public class MagicIndex {
	private static int search(int[] nums, int l, int r) {
		if( l > r || l < 0 || r >= nums.length  )
			return -1;
		int mid = l + ((r - l) >> 1);
		//int mid = l + (r - l) / 2;
		//int mid = (r + l) / 2;
		
		if( nums[mid] == mid )
			return mid;
		else if( nums[mid] < mid ) 
			return search(nums, mid + 1, r);
		else
			return search(nums, l, mid - 1);
	}
	public static int FindMagicIndex(int[] nums) {
		return search(nums, 0, nums.length - 1);
	}
	
	public static void main(String[] args) {
		int[] nums = new int[]{-40, -20, -1, 1, 2, 3, 
								5, 7, 9, 12, 13};
		System.out.println("Find a magic index whose " +
							"value equals array element: ");
		for( int i = 0; i < nums.length; i++ )
			System.out.println("index: " + i + " " + "element: " + nums[i]);
		
		int res = FindMagicIndex(nums);
		System.out.println("Result: ");
		if( res >= 0)
			System.out.println("index: " + res + " " + "element: " + nums[res]);
		else
			System.out.println("No Magic Index!");
		
		int[] nums2 = new int[]{-40, -20, -1, 1, 2, 3, 
				5, 8, 9, 12, 13};
		System.out.println("Find a magic index whose " +
					"value equals array element in 2nd array: ");
		for( int i = 0; i < nums2.length; i++ )
		System.out.println("index: " + i + " " + "element: " + nums2[i]);
		
		int res2 = FindMagicIndex(nums2);
		System.out.println("Result: ");
		if( res2 >= 0)
			System.out.println("index: " + res2 + " " + "element: " + nums2[res2]);
		else
			System.out.println("No Magic Index!");
	}
}
