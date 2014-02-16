
public class AllChangeCombination {
	public static int CalChangeCombination(int denom, int target) {
			
		int NextDenom = 0, res = 0;
		switch (denom) {
			case 25:
				NextDenom = 10;
				break;
			case 10:
				NextDenom = 5;
				break;
			case 5:
				NextDenom = 1;
				break;
			case 1:
				return 1;			
		}
		for( int i = 0; (i * denom) <= target; i++ )
			res += CalChangeCombination(NextDenom, target - (i * denom));
		return res;		
	}
	
	public static void main(String[] args) {
		int target = 100, target2 = 25;
		System.out.println("Number of change combination for " + 
							target + " is " + CalChangeCombination(25, target));
							
		System.out.println("Number of change combination for " + 
				target + " is " + CalChangeCombination(25, target2));
	}
}
