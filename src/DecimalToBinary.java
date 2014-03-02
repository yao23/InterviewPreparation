
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
	public static void main(String[] args) {
		double d = 0.72;
		double d1 = 0.75;
		System.out.println("Test case 1");
		System.out.println("Number: " + d + " binary:" + 
				decimalToBinary(d));
		System.out.println("Test case 1");
		System.out.println("Number: " + d1 + " binary:" + 
				decimalToBinary(d1));
	}
}
