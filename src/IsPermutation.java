import java.util.Arrays;


public class IsPermutation {
	public static boolean CheckPermutation(String s1, String s2) {
		char[] TmpStr2 = s2.toCharArray();
		Arrays.sort(TmpStr2);
		String TmpS2 = new String(TmpStr2); 
		return s1.equals(TmpS2);
	}
	
	public static void main(String[] args) {
		String s1_1 = "123456";
		String s1_2 = "132654";
		System.out.println(s1_1 + " and " + s1_2 + 
				(CheckPermutation(s1_1, s1_2) ? " is " : " is not ") + "permutation");
	}
}
