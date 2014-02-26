
public class SearchString {
	private static int binarySearchStr(String[] ss, String target, 
		int l, int r) {
		int m = (l + r) / 2;
		if (ss[m] == target) {
			return m;
		}
		if (ss[m].equals(" ")) {
			int left = m - 1;
			int right = m + 1;
			while (left >= l && right <= r) {
				if (left < l || right > r) {
					return -1;
				}
				else if (!ss[right].equals(" ")) {
					m = right;
					break;
				}
				else if (!ss[left].equals(" ")) {
					m = left;
					break;
				}
				left--;
				right++;
			}
		}
		
		while (l <= r) {
			if (ss[m] == target) {
				return m;
			}
			else if (ss[m].compareToIgnoreCase(target) < 0) {
				binarySearchStr(ss, target, m + 1, r);
			}
			else {
				binarySearchStr(ss, target, l, m - 1);
			}
		}
		
		return -1;
	}
	private static int search(String[] ss, String target) {
		if (target == null || target.isEmpty() || 
				target.equals(" ")) {
			return -1;
		}
		int res = binarySearchStr(ss, target, 0, ss.length - 1);
		return res;
	}
	public static void main(String[] args) {
		String[] s = {"abc", "cba", "dog", "apple", "google"};
		System.out.println("Test case 1: ");
		System.out.println("Original string array: ");
		for (int i = 0; i < s.length; i++) {
			System.out.print(s[i] + " ");
		}
		System.out.println();
		System.out.println("Target found at index: " + search(s, "apple"));
	}
}
