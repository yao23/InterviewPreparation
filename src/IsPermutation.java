import java.util.Arrays;


public class IsPermutation {
	public static boolean CheckPermutation(String s1, String s2) {
		if( s1.length() != s2.length() )
			return false;
		
		char[] TmpStr2 = s2.toCharArray();
		Arrays.sort(TmpStr2);
		String TmpS2 = new String(TmpStr2); 
		return s1.equals(TmpS2);
	}
	
	public static void main(String[] args) {
		String s1_1 = "123456";
		String s1_2 = "132654";
		System.out.println(s1_1 + " and " + s1_2 + 
				(CheckPermutation(s1_1, s1_2) ? " are " : " are not ") + "permutations");
		
		String s2_1 = "123456";
		String s2_2 = "13265";
		System.out.println(s2_1 + " and " + s2_2 + 
				(CheckPermutation(s2_1, s2_2) ? " are " : " are not ") + "permutations");
		
		String s3_1 = "123456";
		String s3_2 = "132657";
		System.out.println(s3_1 + " and " + s3_2 + 
				(CheckPermutation(s3_1, s3_2) ? " are " : " are not ") + "permutations");
	}
}
