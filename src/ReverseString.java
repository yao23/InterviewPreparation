
public class ReverseString {
	public static String ReverseStr(String s) {
		char[] result = new char[s.length()]; 
		for( int i = 0; i <= (s.length() >> 1); i++) {
			result[i] = s.charAt(s.length() - 1 - i);
			result[s.length() - 1 - i] = s.charAt(i);	
		}
		return new String(result);
	}
	public static void main(String args[]) {
		String s1 = "abcdefghi";
		String s2 = "missipi";
		String s3 = "yongyuanziyoudexin";
		System.out.println("Reversed " + s1 + " : " + ReverseStr(s1));
		System.out.println("Reversed " + s2 + " : " + ReverseStr(s2));
		System.out.println("Reversed " + s3 + " : " + ReverseStr(s3));
	}
}
