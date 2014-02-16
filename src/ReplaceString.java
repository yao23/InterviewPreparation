
public class ReplaceString {
	public static char[] Replace(char[] ch) {
		int EndSpaceIdx = -1; // index of space in the end
		for(int i = ch.length - 1; i >= 0; i-- ) {
			if( ch[i] != ' ' ) {
				EndSpaceIdx = (i + 1);
				System.out.println("End Space Index: " + EndSpaceIdx);
				break;
			}			
		}
		for( int i = 0; i < EndSpaceIdx; i++ ) {
			if( ch[i] == ' ' ) {
				for( int j = EndSpaceIdx - 1; j > i; j-- )
					ch[j + 2] = ch[j];
				ch[i] = '%';
				ch[i + 1] = '2';
				ch[i + 2] = '0';					
			}
		}
		return ch;
	}
	
	public static void main(String[] args) {
		char[] ch1 = {'M', 'r', ' ', 'J', 'o', 'h', 'n', ' ', 'S', 
				       	'm', 'i', 't', 'h', ' ', ' ', ' ', ' '};
		System.out.println(ch1.length + "Before replacing space with %20: ");
		for( int i = 0; i < ch1.length; i++ )
			System.out.print(ch1[i] + " ");
		System.out.println();
		ch1 = Replace(ch1);
		System.out.println("After replacing space with %20: ");
		for( int i = 0; i < ch1.length; i++ )
			System.out.print(ch1[i] + " ");
	}
}
