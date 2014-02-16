
public class CompressString {
	public static String compress(String s) {
		String res = "";
		int last = 0;
		for( int i = 1; i < s.length(); i++ ) {
			if( s.charAt(i) != s.charAt(last) ) {
				res = res + s.charAt(last) + (i - last);
				last = i;
			}
		}
		if( last < s.length() ) 
			res = res + s.charAt(last) + (s.length() - last);
		return res;
	}
	
	public static void main(String[] args) {
		String s1 = "aabcccccaaa";
		System.out.println(s1 + " is compressed as " + compress(s1));
	}
}
