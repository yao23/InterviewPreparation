
public class SearchString {
	private static int binarySearchStr(String[] ss, String target, 
		int l, int r) {
		int m = (l + r) / 2;
		int left = m - 1;
		int right = m + 1;
		
		if (ss[m].equals(" ")) {
			while (true) {
				if (left < l || right > r) {
					return -1;
				}
				else if (right <= r && !ss[right].equals(" ")) {
					m = right;
					break;
				}
				else if (left >= l && !ss[left].equals(" ")) {
					m = left;
					break;
				}
				left--;
				right++;
			}
		}
		
		if (ss[m] == target) {
			return m;
		}
		else if (ss[m].compareToIgnoreCase(target) < 0) {
			return binarySearchStr(ss, target, m + 1, r);
		}
		else {
			return binarySearchStr(ss, target, l, m - 1);
		}
	}
	private static int search(String[] ss, String target) {
		if (target == null || target.isEmpty() || 
				target.equals(" ")) {
			return -1;
		}
	
		return binarySearchStr(ss, target, 0, ss.length - 1);
	}
	public static void main(String[] args) {
		String[] s = {"abc", "apple", "cba", "dog", "google"};
		String[] s2 = {"at", " ", " ", " ", "ball", " ", " ", "car", 
				" ", " ", "dad", " ", " "};
		System.out.println("Test case 1: ");
		System.out.println("Original string array: ");
		for (int i = 0; i < s.length; i++) {
			System.out.print(s[i] + " ");
		}
		System.out.println();
		System.out.println("Target found at index: " + search(s, "apple"));
		
		System.out.println("Test case 2: ");
		System.out.println("Original string array: ");
		for (int i = 0; i < s2.length; i++) {
			System.out.print(s2[i] + " ");
		}
		System.out.println();
		System.out.println("Target found at index: " + search(s2, "ball"));
	}
}
