
public class ArrayToThreeStacks {
	public static void main(String[] args) {
		int[] nums = new int[10];
		int StackNum = 3;
		StackElem s1 = new StackElem(1);
		StackElem s2 = new StackElem(2);
		StackElem s3 = new StackElem(3);
		s1.push(1, nums, StackNum);
		s1.push(2, nums, StackNum);
		s1.push(3, nums, StackNum);
		s1.peek(nums, StackNum);
		s1.pop(nums, StackNum);
		s1.push(4, nums, StackNum);
		s2.pop(nums, StackNum);
		s3.peek(nums, StackNum);
	}
}

class StackElem {
	private int TopIdx;
	private int StackId;
	public StackElem(int id){
		StackId = id;
		TopIdx = -1;
	}
	
	public void push(int elem, int[] nums, int StackNum) {
		int idx = (StackId - 1) * (nums.length / StackNum) + TopIdx + 1;
		int capacity = StackId * (nums.length / StackNum);
		if( idx == capacity ) { // stack is full
			System.out.println("Stack " + StackId + " is full! Cannot push " + elem);
			return;
		}			
		nums[idx] = elem;
		TopIdx++;
		System.out.println("Push " + elem + " into Stack " + StackId + "!");
	}
	
	public int pop(int[] nums, int StackNum) {
		if( TopIdx == -1 ) { // stack is empty
			System.out.println("Stack " + StackId + " is empty! Cannot pop!");
			return Integer.MIN_VALUE;
		}
		else {
			int idx = (StackId - 1) * (nums.length / StackNum) + TopIdx;
			TopIdx--;
			System.out.println("Pop " + nums[idx] + " out of Stack " + StackId + "!");
			return nums[idx];
		}
	}
	
	public int peek(int[] nums, int StackNum) {
		if( TopIdx == -1 ) { // stack is empty
			System.out.println("Stack " + StackId + " is empty! Cannot peek!");
			return Integer.MIN_VALUE;
		}
		int idx = (StackId - 1) * (nums.length / StackNum) + TopIdx;
		System.out.println("Peek " + nums[idx] + " in Stack " + StackId + "!");
		return nums[idx];
	}
}