package com.Maxeler;

public class PrintSVG {
	private static int len = 400; // image length (pixel)
	private static int len0 = 10; // basic mode length
	private static int len1 = 40; // mode 1 length

	private enum Direction {
		U, D, L, R;
	}
	
	private static String mode(int m) {
		String s = "";
		switch (m) {
			case 1: 
				s += "RULURULU"; // vertical mode 1 
				break;
			case 2:
				s += "DLDRDLDR"; // vertical mode 2
				break;
			case 3:
				s += "DRURDRUR"; // horizontal mode 1
				break;
			case 4:
				s += "RDRURDRU"; // horizontal mode 2
				break;
			case 5:
				s += "URDRURDR"; // horizontal mode 3
				break;
			case 6:
				s += "RURURURU"; // horizontal mode 4
				break;
			default:
				System.out.println("Invalid drawing mode!");
				break;
		}
		return s;
	}
	
	private static String space(int l, Direction d) {
		String s = "";
		for (int i = 0; i < l; i++) {
			s += d;
		}
		return s;
	}
	
	private static String addP(String s) {
		return ("P" + s + "P");
	}
	
	private static String draw() {
		String s = "";
		int col1Space = (len - len1 * 2) / len0; 
		String col1 = mode(1) + addP(space(col1Space, Direction.U)) + mode(1); // left column 1
		int botSpace = len / len0; // bottom row 1 
		String bot = addP(space(botSpace, Direction.R)) + mode(2);
		String bot1 = addP(space(40 - 22, Direction.L) + space(36 - 35, Direction.D)) + "LULDLULD"; // regual
		String bot2 = addP(space(18 - 15, Direction.L) + space(35 - 33, Direction.D)) + "LULDLULD" + 
					  addP(space(25 - 11, Direction.R)) + "RURDRURD";
		String bot3 = addP(space(34 - 29, Direction.R) + space(33 - 28, Direction.D)) + "ULDLULDL" + 
				      addP(space(31 - 22, Direction.L) + space(29 - 28, Direction.U)) + "JJJJJLMMMMMDLULDLUKKKKKNNNNN" + // small
				      addP(space(18 - 9, Direction.L) + space(28 - 27, Direction.D)) + "LULDLULD";
		String bot4 = addP(space(18 - 6, Direction.R) + space(28 - 28, Direction.D)) + "DRURDRUR" + // regular
			          addP(space(24 - 22, Direction.R) + space(26 - 24, Direction.D)) + "LLDDLLUULLDDLLUU"; // 2x large
		String mid = addP(space(16 - 1, Direction.L) + space(24 - 21, Direction.D)) + "DRURDRUR" + 
			         addP(space(14 - 5, Direction.R)) + "DDDRRRUUURRRDDDRRRUUURRR" + // 3x large
			         addP(space(35 - 26, Direction.R)) + "RDRURDRU";
		String top4 = addP(space(39 - 28, Direction.L) + space(20 - 16, Direction.D)) + "LLLLDDDDLLLLUUUULLLLDDDDLLLLUUUU"; // 4x large, adjust from 16 in 2nd space() to 16
		String top3 = addP(space(12 - 6, Direction.L) + space(17 - 12, Direction.D)) + "DRURDRUR" + // adjust 5 in 1st space() to 6 
				      addP(space(30 - 10, Direction.R)) + "RDRURDRU"; // adjust 9 in 1st space() to 10 
		String top2 = addP(space(34 - 29, Direction.L) + space(12 - 7, Direction.D)) + "DLULDLUL" + 
				      addP(space(25 - 15, Direction.L)) + "LDLULDLU";
		String top1 = addP(space(18 - 11, Direction.R) + space(7 - 5, Direction.D)) + "DRURDRUR";
		String top = addP(space(40- 22, Direction.R) + space(7 - 4, Direction.D)) + "DLDRDLDR"; // adjust 5 in 2nd space() to 7 
		
		s += (col1 + bot + bot1 + bot2 + bot3 + bot4 + mid + top4 + top3 + top2 + top1 + top);

		return s;
	}
	
	public static void main(String[] args) {
		String s = "";
		s += draw();
		s += "X"; // terminate drawing
		System.out.println(s);
	}
}