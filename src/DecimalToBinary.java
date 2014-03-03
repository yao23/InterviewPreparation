
public class DecimalToBinary {
	private static String decimalToBinary(double d) {
		if (d >= 1 || d <= 0) {
			return "ERROR";
		}
		double frac = 0.5;
		StringBuilder res = new StringBuilder();
		res.append(".");
		while (d > 0) {
			if (res.length() > 32) {
				return "Cannot be in binary";
			}
			if (d >= frac) {
				res.append("1");
				d -= frac;
			} else {
				res.append("0");
			}
			frac /= 2;
		}
		return res.toString();
	}
	
	private static String decimalToBinary2(double d) {
		if (d >= 1 || d <= 0) {
			return "ERROR";
		}
		StringBuilder res = new StringBuilder();
		res.append(".");
		while (d > 0) {
			if (res.length() > 32) {
				return "Cannot be in binary";
			}
			if (d * 2 >= 1) {
				res.append("1");
				d = d * 2 - 1;
			} else {
				res.append("0");
				d *= 2;
			}
		}
		return res.toString();
	}
	
	public static void main(String[] args) {
		double d = 0.72;
		double d1 = 0.75;
		System.out.println("Test case 1");
		System.out.println("Number: " + d + " binary:" + 
				decimalToBinary(d));
		
		System.out.println("Test case 1 in 2nd solution");
		System.out.println("Number: " + d + " binary:" + 
				decimalToBinary2(d));
		
		System.out.println("Test case 2");
		System.out.println("Number: " + d1 + " binary:" + 
				decimalToBinary(d1));
		
		System.out.println("Test case 2 in 2nd solution");
		System.out.println("Number: " + d1 + " binary:" + 
				decimalToBinary2(d1));	
	}
}
