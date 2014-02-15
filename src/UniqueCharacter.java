
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
	
	public static boolean CheckUniqueCharacter2(String s) {
		int NumBuf = 0, idx;
		for( int i = 0; i < s.length(); i++ ) {
			idx = s.charAt(i) - 'a';
			if( (NumBuf & 1 << idx)!= 0 )
				return false;
			else
				NumBuf |= (1 << idx);
		}
		return true;
	}
	
	public static void main(String args[]) {
		String s1 = "abc";
		String s2 = "missipi";
		String s3 = "yongyuanziyoudexin";
		System.out.println("All unique characters in s1? " + 
				(CheckUniqueCharacter(s1) ? "true" : "false" ));
		System.out.println("All unique characters in s2? " + 
				(CheckUniqueCharacter(s2) ? "true" : "false" ));
		System.out.println("All unique characters in s3? " + 
				(CheckUniqueCharacter(s3) ? "true" : "false" ));
		
		System.out.println("All unique characters in s1(without extra space)? " + 
				(CheckUniqueCharacter2(s1) ? "true" : "false" ));
		System.out.println("All unique characters in s2(without extra space)? " + 
				(CheckUniqueCharacter2(s2) ? "true" : "false" ));
		System.out.println("All unique characters in s3(without extra space)? " + 
				(CheckUniqueCharacter2(s3) ? "true" : "false" ));
	}
}
