import java.util.Arrays;


public class StringSorting {
	private static boolean isAnagram(String s1, String s2) {
		char[] cs = s2.toCharArray();
		Arrays.sort(cs);
		String tmp = new String(cs);
		return (s1.equals(tmp));
	}
	public static void merge(String[] s, int l, int m, int r) {
		int rIdx = m + 1;
		int idx = 0;
		String[] tmp = new String[s.length];
		for (int i = l; i <= r ; i++) {
			tmp[i] = s[i];
		}
		
		while (l <= m && rIdx <= r) {
			if (isAnagram(tmp[l], tmp[rIdx])) {
				s[idx] = tmp[l];
				l++;
				idx++;
				s[idx] = tmp[rIdx];
				rIdx++;
			}
			else if (tmp[l].compareToIgnoreCase(tmp[rIdx]) < 0) {
				s[idx] = tmp[l];
				while ((l + 1 <= m) && isAnagram(tmp[l], tmp[l + 1])) { 
					s[++idx] = tmp[++l]; // copy anagram
				}
				l++;
			}
			else {
				s[idx] = tmp[rIdx];
				while ((rIdx + 1 <= r) && isAnagram(tmp[rIdx], tmp[rIdx + 1])) { 
					s[++idx] = tmp[++rIdx]; // copy anagram
				}
				rIdx++;
			}
			idx++;
		}
		
		while (l <= m) { // left half remained, right half is already in place
			s[idx++] = tmp[l++];
		}
	}
	
	public static void mergeSort(String[] s, int l, int r) {
		if (l < r) {
			int m = (l + r) / 2;
			mergeSort(s, l, m);
			mergeSort(s, m + 1, r);
			merge(s, l, m, r);
		}
	}
	
	public static void sortStrings(String[] s) {
		int l = 0; 
		int r = s.length - 1;
		mergeSort(s, l, r);
	}
	
	public static void main(String[] args) {
		String[] s = {"abc", "cba", "dog", "apple", "google"};
		System.out.println("Test case 1: ");
		System.out.println("Before sorting: ");
		for (int i = 0; i < s.length; i++) {
			System.out.print(s[i] + " ");
		}
		sortStrings(s);
		System.out.println();
		System.out.println("After sorting: ");
		for (int i = 0; i < s.length; i++) {
			System.out.print(s[i] + ", ");
		}
	}
}
