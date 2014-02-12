import java.util.Random;


public class MergeSort {
    public static void Merge(int [] nums, int left, 
    		 int right) {
      int [] temp = new int[nums.length];
      int mid = (left + right) >> 1; 
      int i = left, l = left, r = mid + 1;
      //int len = (right - left + 1);
  
      while( (l <= mid) && (r <= right) ) {
          if( nums[l] <= nums[r] )
              temp[i++] = nums[l++];
          else
              temp[i++] = nums[r++];
      }
  
      while( l <= mid )
          temp[i++] = nums[l++];

      while( r <= right )
          temp[i++] = nums[r++];

      for( i = left; i <= right; i++ ) {
          // nums[right] = temp[right];
          // right--;
    	  nums[i] = temp[i];
      }
  }

  public static void MergeSort_Recursive(int [] numbers, 
		  int left, int right) {
    int mid;
  
    if( left < right  ) {
      mid = (right + left) / 2;
      MergeSort_Recursive(numbers, left, mid);
      MergeSort_Recursive(numbers, (mid + 1), right);
  
      Merge(numbers, left, right);
    }
  }


  public static void main(String[] args) {
      int[] nums = { 3, 8, 7, 5, 2, 1, 9, 6, 4 };
      int[] nums2 = { 3, 5, 2, 1, 4 };

      System.out.println("MergeSort By Recursive Method: ");

      MergeSort_Recursive(nums, 0, nums.length - 1);
      for( int i = 0; i < nums.length; i++ )
          System.out.print(nums[i] + " ");
     
      System.out.println();
      
      MergeSort_Recursive(nums2, 0, nums2.length - 1);
      for( int i = 0; i < nums2.length; i++ )
          System.out.print(nums2[i] + " ");
      
      System.out.println();
		
      Random r = new Random();
      int[] nums3 = new int[10];
      System.out.print("Origin: ");
      for( int i = 0; i < 10; i++ ) {
    	  nums3[i] = r.nextInt(10-1) + 1;
    	  System.out.print(nums3[i] + " ");
      }
      
      System.out.println();
      
      MergeSort_Recursive(nums3, 0, nums3.length - 1);
      System.out.print("Changed: ");
      for( int i = 0; i < nums3.length; i++ )
          System.out.print(nums3[i] + " ");
  }
}
