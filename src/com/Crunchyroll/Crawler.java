package com.Crunchyroll;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.HashSet;
import java.util.LinkedList;

public class Crawler {
	public static final long STARTING_PAGE_NUM = 64738; 
	public static URL BASE_URL; 
	public static long goal = 0;
	public static int nodeCount = 0;
	public static boolean isFirstPath = true;
	public static ArrayList<Long> shortestPath = new ArrayList<Long>();
	public static ArrayList<Long> curPath = new ArrayList<Long>();
	public static LinkedList<Long> pageQueue = new LinkedList<Long>(); // BFS iterates graph
	public static HashSet<Long> nodes = new HashSet<Long>(); // unique pages
	public static int directedCycleCount = 0;
	
	public static void output() {
		System.out.println("{");
		System.out.println(" \"goal\": " + goal + ",");
		System.out.println(" \"node_count\": " + nodeCount + ",");
		System.out.print(" \"shortest_path\": [");
		for (int i = 0; i < shortestPath.size() - 1; i++) {
			System.out.print(shortestPath.get(i) + ", ");
		}
		System.out.print(shortestPath.get(shortestPath.size() - 1)); // last number without comma later
		System.out.println("],");
		System.out.println(" \"directed_cycle_count\": " + directedCycleCount); 
		System.out.println("}");
	}
	
	public static long evaluateExpression(String s) {
		int idx = 0;
		long res = 0;
		Stack<String> operator = new Stack<String>();
		Stack<Long> operand = new Stack<Long>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if ( c == '(') {
				operator.push(s.substring(idx, i)); 
				idx = i + 1;
			} else if (c == ')') {
				long a = 0;
				if (s.charAt(i - 1) != ')') { 
					a = Long.parseLong(s.substring(idx, i)); // 2nd operand
				} else { // 2nd operand already in stack when previous one is ')'
					a = operand.pop();
				}
				String opt = operator.pop();
				if (opt.equals("abs")) {
					operand.push(Math.abs(a));
				} else {					
					long b = operand.pop();
					if (opt.equals("add")) {
						operand.push(a + b);
					} else if (opt.equals("subtract")) {
						operand.push(b - a);
					} else if (opt.equals("multiply")) {
						operand.push(a * b);
					} else {
						System.out.println("Invalid operation");
					}
				}
				idx = i + 1;
			} else if ( c == ',') { 
				if (s.charAt(i - 1) != ')') { // 1st integer already in operand when previous one is ')'
					operand.push(Long.parseLong(s.substring(idx, i))); // push 1st integer in operand
				}
				idx = i + 1;				
			}
		}
		res = operand.pop();
		return res;
	}

	public static void parsePage(URL page) throws IOException {
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(page.openStream()));
		String inputLine;
        while ((inputLine = in.readLine()) != null) {
        	if (inputLine.equals("DEADEND")) {
        		System.out.println("DEADEND"); // add DEADEND process here?
        		curPath.remove(curPath.size() - 1); // remove dead end node for next path
        	} else if (inputLine.equals("GOAL")) {
        		System.out.println("GOAL"); // add GOAL process here?
        		if (isFirstPath) {
        			shortestPath.addAll(curPath);
        			isFirstPath = false;
        		} else {
        			if (curPath.size() < shortestPath.size()) {
        				shortestPath.addAll(curPath);
        			}
        		}
        		curPath.remove(curPath.size() - 1); // remove goal node for next path        		
        	} else { // list page
        		long exp = evaluateExpression(inputLine);
        		if (!nodes.add(exp)) { // BFS meet directed cycle
        			directedCycleCount++;
        			curPath.remove(curPath.size() - 1); // remove cycle node for next path
        		} else {
        			curPath.add(exp);
        		}
        		//System.out.println(inputLine);
        		//System.out.println(exp);
        	}
        }
        in.close();
	}	
	
	public static void iterateGraph() throws IOException {
		while (!pageQueue.isEmpty()) { // BFS iterate graph
			String relativeURL = String.valueOf(pageQueue.removeFirst());
			URL link = new URL(BASE_URL, relativeURL);
			parsePage(link);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BASE_URL = new URL(
					"http://www.crunchyroll.com/tech-challenge/roaming-math/myspiritcrazy@gmail.com/");
		pageQueue.add(STARTING_PAGE_NUM);
		iterateGraph();
		
		//output();
	}
}
