
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
/*				
			if( nums[i] % 2 == 0 ) {
				if( EvenIdx == 0 )
					EvenIdx = i;
				
				while( i < nums.length - 1 && nums[i] % 2 == 0 ) 
					i++;
				
				if( i < nums.length - 1 ) {
					int OddNum = nums[i];
					for( int j = i - 1; j >= EvenIdx; j-- ) 
						nums[j + 1] = nums[j];
					nums[EvenIdx] = OddNum;
					EvenIdx++;
				}
				else
					return;					
			}
*/			
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
	}
}
