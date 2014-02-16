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
	
	public static boolean CheckPermutation2(String s1, String s2) {
		if( s1.length() != s2.length() )
			return false;
		
		int[] CharsInS1 = new int[26];
		int[] CharsInS2 = new int[26];
		
		for( int i = 0; i < s1.length(); i++ ) {
			CharsInS1[s1.charAt(i) - 'a']++;
			CharsInS2[s2.charAt(i) - 'a']++;
		}
		for( int i = 0; i < CharsInS1.length; i++ ) {
			if( CharsInS1[i] != CharsInS2[i] )
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		String s1_1 = "abcdef";
		String s1_2 = "acbfed";
		System.out.println(s1_1 + " and " + s1_2 + 
				(CheckPermutation(s1_1, s1_2) ? " are " : " are not ") + "permutations");
		
		String s2_1 = "abcdef";
		String s2_2 = "acbfe";
		System.out.println(s2_1 + " and " + s2_2 + 
				(CheckPermutation(s2_1, s2_2) ? " are " : " are not ") + "permutations");
		
		String s3_1 = "abcdef";
		String s3_2 = "acbfeg";
		System.out.println(s3_1 + " and " + s3_2 + 
				(CheckPermutation(s3_1, s3_2) ? " are " : " are not ") + "permutations");
		
		System.out.println("\n" + "Check Permutation with extra space: ");
		
		System.out.println(s1_1 + " and " + s1_2 + 
				(CheckPermutation2(s1_1, s1_2) ? " are " : " are not ") + "permutations");
		
		System.out.println(s2_1 + " and " + s2_2 + 
				(CheckPermutation2(s2_1, s2_2) ? " are " : " are not ") + "permutations");
		
		System.out.println(s3_1 + " and " + s3_2 + 
				(CheckPermutation2(s3_1, s3_2) ? " are " : " are not ") + "permutations");
	}
}
