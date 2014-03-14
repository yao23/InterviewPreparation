import java.util.ArrayList;

public class FindMissingNumber {
	public int findMissing(ArrayList<BitInteger> array) {
		/* BitInteger.INTEGER_SIZE - 1 corresponds to the Least Significant Bit.
		 * Start from there, and work our way through the bigger bits.
		 */
		return findMissing(array, BitInteger.INTEGER_SIZE - 1);
	}
	
	public int findMissing(ArrayList<BitInteger> input, int column) {
		if (column < 0) { // base case and error condition
			return 0;
		}
		ArrayList<BitInteger> oneBits = new ArrayList<BitInteger>(input.size() / 2);
		ArrayList<BitInteger> zeroBits = new ArrayList<BitInteger>(input.size() / 2);
		for (BitInteger t : input) {
			if (t.fetch(column) == 0) {
				zeroBits.add(t);
			} else {
				oneBits.add(t);
			}
		}
		if (zeroBits.size() <= oneBits.size()) {
			int v = findMissing(zeroBits, column + 1);
			return (v << 1) | 0;
		} else {
			int v = findMissing(oneBits, column + 1);
			return (v << 1) | 1;
		}
	}
}

class BitInteger {
	private static int data;
	public static int INTEGER_SIZE = (Integer.toBinaryString(data)).length();
	BitInteger(int i) {
		data = i;
	}
	public int fetch(int i) {
		int d = 1;
		for (int j = 0; j < i; j++) {
			d <<= 1;
		}
		return (data & d) >> i;
	}
}