import java.math.BigInteger;

public class InsertInteger {
	private static int insertInteger(int n, int m, int i, int j) {
		int mask = ~( ((1 << (j - i + 1)) - 1) << i);
		//System.out.println("Mask: " + mask);
		//BigInteger tmpN = n.and(new BigInteger(Integer.toString(mask)));
		int tmpN = n & mask;
		return (tmpN | (m << i));
	}
	
	public static void main(String[] args) {
		//BigInteger N = new BigInteger("10000000000");
		int M = 19; // 10011
		int i = 2;
		int j = 6;
		int N = 1 << 10;
		
		System.out.println("Test case 1:");
		System.out.println("Before inserting: " + Integer.toBinaryString(N));
		System.out.println("After inserting: " + Integer.toBinaryString(insertInteger(N, M, i, j)));
	}
}
