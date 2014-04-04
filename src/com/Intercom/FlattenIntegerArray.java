package com.Intercom;

import java.util.ArrayList;

public class FlattenIntegerArray {
	
	// I don't think you mean this one
	public static String flattenSimple(String arr) {
		StringBuilder res = new StringBuilder();
		res.append("[");
		for (int i = 0; i < arr.length(); i++) {
			char c = arr.charAt(i);
			if ('0' <= c && c <= '9') {
				res.append(c + ",");
			}
		}
		res.deleteCharAt(res.length() - 1);
		res.append("]");
		return res.toString();
	}
	
	// I guess you want this one 
	public static void flattenComplex(NestedInteger n, ArrayList<Integer> res) {		
		if(n.isNestedListAhead == false && n.isInteger()) {
			res.add(n.getInteger());
		} else if (n.isNestedListAhead == true) {
			for (int i = 0; i < n.getList().size(); i++) {
				flattenComplex(n.getList().get(i), res);
			}
		}
	}
	
	public static void printResult(ArrayList<Integer> arr) {
		StringBuilder res = new StringBuilder();
		res.append("[");
		for (int i = 0; i < arr.size(); i++) {
			res.append(arr.get(i) + ",");
		}
		res.deleteCharAt(res.length() - 1);
		res.append("]");
		System.out.print(res.toString());
	}
	
	public static void main(String[] args) {
		String s0 = "[[1,2,[3]],4]";
		System.out.println("Test case for simple senario: ");
		System.out.println("Origin: " + s0);
		System.out.println("Result: " + flattenSimple(s0));
		
		NestedInteger n0 = new NestedInteger(4);
		NestedInteger n1 = new NestedInteger(1);
		NestedInteger n2 = new NestedInteger(2);
		NestedInteger n3 = new NestedInteger(3);
		ArrayList<NestedInteger> l0 = new ArrayList<NestedInteger>();
		l0.add(n3);
		NestedInteger n4 = new NestedInteger(l0);
		ArrayList<NestedInteger> l1 = new ArrayList<NestedInteger>();
		l1.add(n1);
		l1.add(n2);
		l1.add(n4);
		n0.nestedList = l1;
		n0.isNestedListAhead = true;
		ArrayList<Integer> res = new ArrayList<Integer>();
		flattenComplex(n0, res);
		System.out.println("Test case for complex senario: ");
		printResult(res);
	}
}

class NestedInteger {
	boolean isNestedListAhead;
	int val;
	ArrayList<NestedInteger> nestedList;
	NestedInteger(int v) {
		isNestedListAhead = false;
		val = v;
	}
	NestedInteger(ArrayList<NestedInteger> l) {
		isNestedListAhead = true;
		nestedList = new ArrayList<NestedInteger>(l);
	}
	NestedInteger(int v, ArrayList<NestedInteger> nestedl) {
		isNestedListAhead = false;
		val = v;
		nestedList = new ArrayList<NestedInteger>(nestedl);
	}
	// returns true if this NestedInteger holds a single integer, rather than a nested list
	public boolean isInteger() {
		return (nestedList == null);
	}
 
	// returns the single integer that this NestedInteger holds, if it holds a single integer
	// returns null if this NestedInteger holds a nested list
	public Integer getInteger() {
		if (isInteger()) {
			return val;
		} else {
			return null;
		}
	}
 
	// returns the nested list that this NestedInteger holds, if it holds a nested list
	// returns null if this NestedInteger holds a single integer
	public ArrayList<NestedInteger> getList() {
		if (isInteger()) {
			return null;
		} else {
			return nestedList;
		} 
	}
}
