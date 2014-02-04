import java.util.Random;


public class OddEvenPartition {
	public static void OddEvenPartitioning(int[] nums) {
		int EvenIdx = -1;
		for( int i = 0; i < nums.length; i++ ) {
			if( nums[i] % 2 != 0 ) {
				if( EvenIdx >= 0 ) {
					int OddNum = nums[i];
					for( int j = i - 1; j >= EvenIdx; j-- ) 
						nums[j + 1] = nums[j];
					nums[EvenIdx] = OddNum;
					EvenIdx++;
				}
			}
			else if( EvenIdx == -1 )
				EvenIdx = i;
		}
	}
	
	public static void swap(int m, int n, int[] nums) {
		int tmp = nums[m];
		nums[m] = nums[n];
		nums[n] = tmp;
	}
	
	public static void main(String args[]) {
		int[] nums = new int[]{1, 2, 3, 4, 5, 6};
		System.out.println("Before partition: ");
		for( int i = 0; i < nums.length; i++ )
			System.out.print(nums[i] + " ");
		OddEvenPartitioning(nums);
		System.out.println("\n");
		System.out.println("After partition: ");
		for( int i = 0; i < nums.length; i++ )
			System.out.print(nums[i] + " ");
		
		System.out.println("\n");
		
		int[] nums2 = new int[]{2, 4, 6, 1, 3, 5};
		System.out.println("Before partition: ");
		for( int i = 0; i < nums2.length; i++ )
			System.out.print(nums2[i] + " ");
		OddEvenPartitioning(nums2);
		System.out.println("\n");
		System.out.println("After partition: ");
		for( int i = 0; i < nums2.length; i++ )
			System.out.print(nums2[i] + " ");
		
		System.out.println("\n");
		
		Random r = new Random();
		int[] nums3 = new int[10];
		for( int i = 0; i < 10; i++ )
			nums3[i] = r.nextInt(10-1) + 1;
	
		System.out.println("Before partition: ");
		for( int i = 0; i < nums3.length; i++ )
			System.out.print(nums3[i] + " ");
		OddEvenPartitioning(nums3);
		System.out.println("\n");
		System.out.println("After partition: ");
		for( int i = 0; i < nums3.length; i++ )
			System.out.print(nums3[i] + " ");
	}
}
