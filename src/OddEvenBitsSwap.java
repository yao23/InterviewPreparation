
public class OddEvenBitsSwap {
	public static int swap(int i) {
		return (((i & 0x55555555) << 1) | ((i & 0xaaaaaaaa) >> 1));
	}
	public static void main(String[] args) {
		int d1 = 10;
		int d2 = 5;
		System.out.println("Test case 1:");
		System.out.println("Number: " + Integer.toBinaryString(d1) + 
				", after swap: " + Integer.toBinaryString(swap(d1)));
		
		System.out.println("Test case 2:");
		System.out.println("Number: " + Integer.toBinaryString(d2) + 
				", after swap: " + Integer.toBinaryString(swap(d2)));
	}
}
