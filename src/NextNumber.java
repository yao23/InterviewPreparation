
public class NextNumber {
	public static int getNext(int n) {
		int c = n;
		int c0 = 0; 
		int c1 = 0;
		while (((c & 1) == 0) && (c != 0)) {
			c0++;
			c >>= 1;
		}
		
		while ((c & 1) == 1) {
			c1++;
			c >>= 1;
		}
		
		if (c0 + c1 == 31 || c0 + c1 == 0) {
			return -1;
		}
		
		int p = c0 + c1;
		n |= (1 << p);
		n &= ~((1 << p) - 1);
		n |= ((1 << (c1 - 1)) - 1);
		return n;
	}
	
	public static int getPrev(int n) {
		int tmp = n;
		int c0 = 0;
		int c1 = 0;
		while (((tmp & 1) == 1) && (tmp != 0)) {
			c1++;
			tmp >>= 1;
		}
		
		while (((tmp & 1) == 0) && (tmp != 0)) {
			c0++;
			tmp >>= 1;
		}
		
		int p = c0 + c1;
		n &= ((~0) << (p + 1));
		
		int mask = (1 << (c1 + 1)) - 1;
		n |= (mask << (c0 - 1));
		
		return n;
	}
	
	public static int getNext2(int n) {
		int c = n;
		int c0 = 0; 
		int c1 = 0;
		while (((c & 1) == 0) && (c != 0)) {
			c0++;
			c >>= 1;
		}
		
		while ((c & 1) == 1) {
			c1++;
			c >>= 1;
		}
		
		if (c0 + c1 == 31 || c0 + c1 == 0) {
			return -1;
		}
		
		return (n + (1 << c0) + (1 << (c1 - 1)) - 1);
	}
	
	public static int getPrev2(int n) {
		int tmp = n;
		int c0 = 0;
		int c1 = 0;
		while (((tmp & 1) == 1) && (tmp != 0)) {
			c1++;
			tmp >>= 1;
		}
		
		while (((tmp & 1) == 0) && (tmp != 0)) {
			c0++;
			tmp >>= 1;
		}
		
		return (n - (1 << c1) - (1 << (c0 -1)) + 1);
	}
	
	public static void main(String[] args) {
		int N1 = Integer.parseInt("11011001111100", 2); // binary to decimal 
		int N2 = Integer.parseInt("10011110000011", 2); // 
		System.out.println("Test case 1");
		System.out.println("Number: " + Integer.toBinaryString(N1) + ", largest smaller one:" + 
				Integer.toBinaryString(getPrev(N1)) + 
				", smallest larger one:" + Integer.toBinaryString(getNext(N1)));
		
		System.out.println("Test case 1 in 2nd solution");
		System.out.println("Number: " + Integer.toBinaryString(N1) + ", largest smaller one:" + 
				Integer.toBinaryString(getPrev2(N1)) + 
				", smallest larger one:" + Integer.toBinaryString(getNext2(N1)));
		
		System.out.println("Test case 2");
		System.out.println("Number: " + Integer.toBinaryString(N2) + ", largest smaller one:" + 
				Integer.toBinaryString(getPrev(N2)) + 
				", smallest larger one:" + Integer.toBinaryString(getNext(N2)));
		
		System.out.println("Test case 2 in 2nd solution");
		System.out.println("Number: " + Integer.toBinaryString(N2) + ", largest smaller one:" + 
				Integer.toBinaryString(getPrev2(N2)) + 
				", smallest larger one:" + Integer.toBinaryString(getNext2(N2)));
	}
}
