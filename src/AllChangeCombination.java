
public class AllChangeCombination {
	public static int CalChangeCombination(int[] changes, 
									int idx, int target) {
		if( changes[idx] == 1 )
			return 1;
		int NextIdx = 0, res = 0;
		switch (changes[idx]) {
			case 25:
				NextIdx = 1;
				break;
			case 10:
				NextIdx = 2;
				break;
			case 5:
				NextIdx = 3;
				break;
			case 1:
				NextIdx = 4;
				break;				
		}
		for( int i = 0; (i * changes[idx]) <= target; i++ )
			res += CalChangeCombination(changes, NextIdx, 
										target - (i * changes[idx]));
		return res;		
	}
	
	public static void main(String[] args) {
		int[] changes = new int[]{25, 10, 5, 1};
		int target = 100, target2 = 25;
		System.out.println("Number of change combination for " + 
							target + " is " + CalChangeCombination(changes, 0, target));
							
		System.out.println("Number of change combination for " + 
				target + " is " + CalChangeCombination(changes, 0, target2));
	}
}
