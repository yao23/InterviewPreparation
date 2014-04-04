package com.Intercom;

import java.util.ArrayList;

public class FlattenIntegerArray {
	
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
	
	public static void main(String[] args) {
		NestedInteger n0 = new Node(1);
		Node n1 = new Node(2);
		Node n2 = new Node(3);
		Node n3 = new Node(4);
		n0.next = n1;
		
		String s0 = "[[1,2,[3]],4]";
		System.out.println("Test case for simple senario: ");
		System.out.println("Origin: " + s0);
		System.out.println("Result: " + flattenSimple(s0)); 
		
	}
}

class NestedInteger {
	ArrayList<Integer> curList;
	ArrayList<NestedInteger> nestedList;
	NestedInteger(ArrayList<Integer> l) {
		curList = new c(l);
	}
	NestedInteger(ArrayList<NestedInteger> l) {
		nestedList = new ArrayList<NestedInteger>(l);
	}
	NestedInteger(ArrayList<Integer> curl, ArrayList<NestedInteger> nestedl) {
		curList = new ArrayList<Integer>(curl);
		nestedList = new ArrayList<NestedInteger>(nestedl);
	}
	// returns true if this NestedInteger holds a single integer, rather than a nested list
	public boolean isInteger() {
		return (curList.size() == 1);
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
			return intList;
		} 
	}
}
