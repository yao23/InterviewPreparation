
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
				return "ERROR";
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
		System.out.println("Test case 1");
		System.out.println("Number: " + d + " binary:" + 
				decimalToBinary(d));
	}
}
