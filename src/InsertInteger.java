
public class InsertInteger {
	private static int insertInteger(int n, int m, int i, int j) {
		int mask = ~( ((1 << (j - i + 1)) - 1) << i);
		int tmpN = n & mask;
		return (tmpN | (m << i));
	}
	
	public static void main(String[] args) {
		int i = 2;
		int j = 6;
		int N = Integer.parseInt("10000000000", 2); // binary to decimal 
		int M = Integer.parseInt("10011", 2); 
		
		System.out.println("Test case 1:");
		System.out.println("Before inserting: " + Integer.toBinaryString(N));
		System.out.println("After inserting:  " + Integer.toBinaryString(insertInteger(N, M, i, j)));
	}
}
