
public class BitsNumberToFlip {
	public static int convert(int a, int b) {
		int count = 0;
		for (int c = a ^ b; c != 0; c >>= 1) {
			count += (c & 1);
		}
		return count;
	}
	public static int convert2(int a, int b) {
		int count = 0; 
		for (int c = a ^ b; c != 0; c = (c & (c - 1))) {
			count++;
		}
		return count;
	}
	public static void main(String[] args) {
		int a1 = 31;
		int b1 = 14;
		int a2 = 29;
		int b2 = 15;
		System.out.println("Test case 1 in method 1: ");
		System.out.println("a: " + Integer.toBinaryString(a1) + 
			", b: " + Integer.toBinaryString(b1) + ", " + convert(a1, b1) + "bit(s) need to flip!");
		
		System.out.println("Test case 2 in method 1: ");
		System.out.println("a: " + Integer.toBinaryString(a2) + 
			", b: " + Integer.toBinaryString(b2) + ", " + convert(a2, b2) + "bit(s) need to flip!");
		
		System.out.println("Test case 1 in method 2: ");
		System.out.println("a: " + Integer.toBinaryString(a1) + 
			", b: " + Integer.toBinaryString(b1) + ", " + convert2(a1, b1) + "bit(s) need to flip!");
		
		System.out.println("Test case 2 in method 2: ");
		System.out.println("a: " + Integer.toBinaryString(a2) + 
				", b: " + Integer.toBinaryString(b2) + ", " + convert2(a2, b2) + "bit(s) need to flip!");
	}
}
