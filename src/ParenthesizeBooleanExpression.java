import java.util.HashMap;


public class ParenthesizeBooleanExpression {
	public static int WaysToParenthesize(String exp, boolean res, int l, int r, 
											HashMap<String, Integer> map) {
		String key = "" + res + l + r; 
		if( map.containsKey(key) )
			return map.get(key);
		if( l == r ) {
			if( exp.charAt(l) == '1' && res )
				return 1;
			else if( exp.charAt(l) == '0' && !res )
				return 1;
			return 0;
		}
		int ways = 0;
		if( res ) {
			for( int i = l + 1; i <= r; i++ ) {
				char op = exp.charAt(i);
				switch(op) {
					case '&':
						ways += WaysToParenthesize(exp, true, l, i - 1, map) * 
								WaysToParenthesize(exp, true, i + 1, r, map);
						break;
					case '|':
						ways += WaysToParenthesize(exp, true, l, i - 1, map) * 
								WaysToParenthesize(exp, true, i + 1, r, map);
						ways += WaysToParenthesize(exp, true, l, i - 1, map) * 
								WaysToParenthesize(exp, false, i + 1, r, map);
						ways += WaysToParenthesize(exp, false, l, i - 1, map) * 
								WaysToParenthesize(exp, true, i + 1, r, map);
						break;
					case '^':
						ways += WaysToParenthesize(exp, true, l, i - 1, map) * 
								WaysToParenthesize(exp, false, i + 1, r, map);
						ways += WaysToParenthesize(exp, false, l, i - 1, map) * 
								WaysToParenthesize(exp, true, i + 1, r, map);
						break;
				}
			}
		}
		else {
			for( int i = l + 1; i <= r; i++ ) {
				char op = exp.charAt(i);
				switch(op) {
					case '&':
						ways += WaysToParenthesize(exp, false, l, i - 1, map) * 
								WaysToParenthesize(exp, false, i + 1, r, map);
						ways += WaysToParenthesize(exp, true, l, i - 1, map) * 
								WaysToParenthesize(exp, false, i + 1, r, map);
						ways += WaysToParenthesize(exp, false, l, i - 1, map) * 
								WaysToParenthesize(exp, true, i + 1, r, map);
						break;
					case '|':
						
						ways += WaysToParenthesize(exp, false, l, i - 1, map) * 
								WaysToParenthesize(exp, false, i + 1, r, map);
						break;
					case '^':
						ways += WaysToParenthesize(exp, true, l, i - 1, map) * 
								WaysToParenthesize(exp, true, i + 1, r, map);
						ways += WaysToParenthesize(exp, false, l, i - 1, map) * 
								WaysToParenthesize(exp, false, i + 1, r, map);
						break;
				}
			}
		}
		map.put(key,  ways);
		return ways;
	}
	
	public static void main(String[] args) {
		String s1 = "1^0|0";
		boolean b1 = true;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		System.out.println(s1 + " has " + WaysToParenthesize(s1, b1, 0, s1.length() - 1, map) 
							+ " ways to parenthesize to be " + (b1 ? "true" : "false"));
		
		map.clear();
		String s2 = "1^0|0|1";
		boolean b2 = true;
		System.out.println(s2 + " has " + WaysToParenthesize(s2, b2, 0, s2.length() - 1, map) 
				+ " ways to parenthesize to be " + (b1 ? "true" : "false"));
	}
}
