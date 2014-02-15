
public class UniqueCharacter {
	public static boolean CheckUniqueCharacter(String s) {
		int[] chars = new int[26];
		for( int i = 0; i < chars.length; i++ )
			chars[i] = -1;
		for( int i = 0; i < s.length(); i++ ) {
			if( chars[s.charAt(i) - 'a'] >= 0 )
				return false;
			else
				chars[s.charAt(i) - 'a']++;
		}
		return true;
	}
	public static void main(String args[]) {
		String s1 = "abc";
		String s2 = "missipi";
		System.out.println("All unique characters in s1? " + 
				(CheckUniqueCharacter(s1) ? "true" : "false" ));
		System.out.println("All unique characters in s2? " + 
				(CheckUniqueCharacter(s2) ? "true" : "false" ));
	}
}
