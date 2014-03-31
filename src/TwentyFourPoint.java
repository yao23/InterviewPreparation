
public class TwentyFourPoint {
	public static void main(String[] args) {
	    int[] a = {2, 3, 4};
	    int[] a1 = {3, 3, 5, 1};
	    StringBuilder sb = new StringBuilder();
	    if (twentyFourPoint(a, 0, 0, sb)) {
	        System.out.println("Has a way for 24 points!");        
	        System.out.println(sb.delete(0,3).toString());
	    } else {
	        System.out.println("No solution for 24 points!");        
	    }
	    
	    // test case 2
	    sb.delete(0, sb.length());
	    System.out.println("Test case 2:");
	    if (twentyFourPoint(a1, 0, 0, sb)) {
	        System.out.println("Has a way for 24 points!");        
	        System.out.println(sb.delete(0,3).toString());
	    } else {
	        System.out.println("No solution for 24 points!");        
	    }
	}

	public static boolean twentyFourPoint(int[] a, int curRes, int depth, StringBuilder sb) {
	    if (depth == a.length) {
	        if (curRes == 24) {
	            return true;
	        } else {
	            return false;
	        }
	    }
	    	    
	    if (twentyFourPoint(a, (curRes + a[depth]), depth + 1, sb.append(new String(" + " + a[depth])))) {
	        return true;
	    } 
	    sb.delete(sb.length() - 4, sb.length());
	    if (twentyFourPoint(a, (curRes - a[depth]), depth + 1, sb.append(new String(" - " + a[depth])))) {
	        return true;
	    }
	    sb.delete(sb.length() - 4, sb.length());
	    if (twentyFourPoint(a, (curRes * a[depth]), depth + 1, sb.append(new String(" * " + a[depth])))) {
	        return true;
	    } 
	    sb.delete(sb.length() - 4, sb.length());
	    if (twentyFourPoint(a, (curRes / a[depth]), depth + 1, sb.append(new String(" / " + a[depth])))) {
	    	return true;
	    } 
	    sb.delete(sb.length() - 4, sb.length());
	        
	    return false;	         
	    
	}
}
