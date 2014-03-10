package com.Maxeler;

public class PrintSVG {
	private enum Direction {
		U, D, L, R;
	}
	
	private String mode(int m) {
		String s = "";
		switch (m) {
			case 1: 
				s += "RULURULU"; // vertical mode 1
				break;
			case 2:
				s += "LURULURU"; // vertical mode 2
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
	
	public static void main(String[] args) {
		String s = "";
		String m1 = "RULURULU";
		String m2 = "DLDRDLDR";//"LURULURU";
		//String m3 = 
		s += (m1 + "P" + space(39, Direction.R) + "P" + m2 + "X");
		System.out.println(s);
	}
}
