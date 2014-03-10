package com.Maxeler;

public class PrintSVG {
	private static int len = 400; // image length (pixel)
	private static int len0 = 10; // basic mode length
	private static int len1 = 40; // mode 1 length
/*	private static int p = 0; // point index
	private static Point[] points = new Point[] {
			new Point(0,0), new Point(0, 40), new Point(0, 360), new Point(0, 400), 
			new Point(400, 400), new Point(400, 360), new Point(220, 350), new Point(180, 350), 
			new Point(150, 330), new Point(110, 330), new Point(95, 280), new Point(55, 280), 
			new Point(50, 205), new Point(10, 205), new Point(55, 120), new Point(95, 120), 
			new Point(110, 70), new Point(150, 70), new Point(180, 50), new Point(220, 50), 
			new Point(400, 0), new Point(400, 40), new Point(290, 70), new Point(250, 70), 
			new Point(305, 120), new Point(345, 120), new Point(280, 170), new Point(120, 170), 
			new Point(140, 210), new Point(260, 210), new Point(350, 205), new Point(390, 205), 
			new Point(240, 240), new Point(160, 240), new Point(180, 260), new Point(220, 260), 
			new Point(205, 270), new Point(185, 270), new Point(305, 280), new Point(345, 280), 
			new Point(250, 330), new Point(290, 330)
	};

	private static Point[] points = new Point[] {
		new Point(0,0), new Point(0, 40), new Point(0, 360), new Point(0, 400), 
		new Point(400, 400), new Point(400, 360), new Point(220, 350), new Point(180, 350), 
		new Point(110, 330), new Point(150, 330), new Point(95, 280), new Point(55, 280), 
		new Point(50, 205), new Point(10, 205), new Point(55, 120), new Point(95, 120), 
		new Point(110, 70), new Point(150, 70), new Point(180, 50), new Point(220, 50), 
		new Point(400, 0), new Point(400, 40), new Point(290, 70), new Point(250, 70), 
		new Point(305, 120), new Point(345, 120), new Point(280, 170), new Point(120, 170), 
		new Point(140, 210), new Point(260, 210), new Point(350, 205), new Point(390, 205), 
		new Point(240, 240), new Point(160, 240), new Point(180, 260), new Point(220, 260), 
		new Point(205, 270), new Point(185, 270), new Point(305, 280), new Point(345, 280), 
		new Point(250, 330), new Point(290, 330)
	};
*/
	
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
/*	
	private static String proceed() {
		String s = "";
		int xDis = points[p + 1].getX() -  points[p + 2].getX();
		int yDis = points[p + 1].getY() -  points[p + 2].getY();
		if (xDis < 0) { // right
			s += space(Math.abs(xDis), Direction.R);
		} else if (xDis > 0) { // left
			s += space(xDis, Direction.L);
		}
		if (yDis < 0) { // up
			s += space(Math.abs(yDis), Direction.U);
		} else if (yDis > 0) { // down
			s += space(yDis, Direction.D);
		}
		p += 2; // next point
		return s;
	}
	
	private static String draw() {
		String s = "";
		s += mode(1);
		s += proceed();
		s += mode(1);
		s += proceed();
		return s;
	}
*/	
	private static String draw2() {
		String s = "";
		int col1Space = (len - len1 * 2) / len0; 
		String col1 = mode(1) + addP(space(col1Space, Direction.U)) + mode(1); // left column 1
		int botSpace = len / len0; // bottom row 1 
		String bot = addP(space(botSpace, Direction.R)) + mode(2);
		String bot1 = addP(space(40 - 22, Direction.L) + space(36 - 35, Direction.D)) + "LULDLULD"; // regual
		String bot2 = addP(space(18 - 15, Direction.L) + space(35 - 33, Direction.D)) + "LULDLULD" + 
					  addP(space(25 - 11, Direction.R)) + "RURDRURD";
		String bot3 = addP(space(34 - 29, Direction.R) + space(33 - 28, Direction.D)) + "ULDLULDL" + 
				      addP(space(31 - 23, Direction.L) + space(29 - 28, Direction.U)) + "JJJJJLMMMMMDLULDLUKKKKKNNNNN" + // small
				      addP(space(18 - 10, Direction.L) + space(28 - 27, Direction.D)) + "LULDLULD";
		String bot4 = addP(space(18 - 7, Direction.R) + "JJJJJRKKKKK" + space(28 - 28, Direction.D)) + "DRURDRUR" + // regular, adjust from 6 in first space() to 7
			          addP(space(24 - 22, Direction.R) + space(26 - 24, Direction.D)) + "LLDDLLUULLDDLLUU"; // 2x large
		String mid = addP(space(12 - 1, Direction.L) + space(24 - 21, Direction.D)) + "DRURDRUR" + // adjust from 16 in first space() to 12
			         addP(space(14 - 5, Direction.R) + space(21 - 20, Direction.U)) + "DRURDRUR" + // 3x large
			         addP(space(35 - 26, Direction.R) + space(21 - 20, Direction.D)) + "RDRURDRU";
		String top4 = addP(space(39 - 26, Direction.L) + space(20 - 17, Direction.D)) + "LDLULDLU"; // 4x large, adjust from 28 to 26
		String top3 = addP(space(17 - 5, Direction.L) + space(17 - 12, Direction.D)) + "DRURDRUR" + // adjust from 12 in first space() to 17
				      addP(space(30 - 9, Direction.R)) + "RDRURDRU";
		String top2 = addP(space(35 - 29, Direction.L) + space(12 - 7, Direction.D)) + "DLULDLUL" + 
				      addP(space(25 - 15, Direction.L)) + "LDLULDLU";
		String top1 = addP(space(18 - 11, Direction.R) + space(7 - 5, Direction.D)) + "DRURDRUR";
		String top = addP(space(40- 22, Direction.R) + space(5 - 4, Direction.D)) + "DLDRDLDR"; 
		
		s += (col1 + bot + bot1 + bot2 + bot3 + bot4 + mid + top4 + top3 + top2 + top1 + top);

		return s;
	}
	
	public static void main(String[] args) {
		String s = "";
		
//		s += draw();
		s += draw2();
		s += "X"; // terminate drawing
		System.out.println(s);
	}
}
/*
class Point {
	private int x;
	private int y;
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
*/