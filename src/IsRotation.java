
public class IsRotation {
	private static boolean IsSubstring(String s1, String s2) {
		for( int i = 0; i < (s1.length() - s2.length()); i++ )
			if( s2.equals(s1.substring(i, i + s2.length() - 1)) )
				return true;
		return false;
	}
	
	public static boolean CheckRotation(String s1, String s2) {
		String tmp = s1 + s1;
		return IsSubstring(tmp, s2);
	}
	
	public static void main(String[] args) {
		String s1 = "waterbottle";
		String s2 = "erbottlewat";
		System.out.println(s1 + " and " + s2 + 
				(CheckRotation(s1, s2) ? " are " : " are not ") + "rotation");
	}
}
