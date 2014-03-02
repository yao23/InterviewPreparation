import java.math.BigInteger;

public class InsertInteger {
	private static BigInteger insertInteger(BigInteger n, int m, int i, int j) {
		int mask = ~( ((1 << (j - i + 1)) - 1) << i);
		BigInteger tmpN = n.and(new BigInteger(Integer.toString(mask)));
		return (tmpN.or(new BigInteger(Integer.toString(m << i))));
	}
	
	public static void main(String[] args) {
		BigInteger N = new BigInteger("10000000000");
		int M = 10011;
		int i = 2;
		int j = 6;
		
		System.out.println("Test case 1:");
		System.out.println("Before inserting: " + N);
		System.out.println("After inserting: " + insertInteger(N, M, i, j));
	}
}
