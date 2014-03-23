package com.Crunchyroll;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.HashSet;

public class Crawler {
	public static final long STARTING_PAGE_NUM = 64738; 
	public static URL BASE_URL; 
	public static long goal = 0;
	public static int nodeCount = 0;
	public static boolean isFirstPath = true;
	public static ArrayList<Long> shortestPath = new ArrayList<Long>();
	public static ArrayList<Long> curPath = new ArrayList<Long>();
	public static HashSet<Long> nodes = new HashSet<Long>(); // unique pages
	public static int directedCycleCount = 0;
	public static ArrayList<ArrayList<Long>> directedCycles = new ArrayList<ArrayList<Long>>();
	
	public static void output() {
		System.out.println("{");
		System.out.println(" \"goal\": " + goal + ",");
		System.out.println(" \"node_count\": " + nodes.size() + ",");
		System.out.print(" \"shortest_path\": [");
		if (shortestPath.size() > 1) {
			for (int i = 0; i < shortestPath.size() - 1; i++) {
				System.out.print(shortestPath.get(i) + ", ");
			}
			System.out.print(shortestPath.get(shortestPath.size() - 1)); // last number without comma later
		} else if (shortestPath.size() == 1) { // only one node, starting page is goal
			System.out.print(STARTING_PAGE_NUM + ", " + STARTING_PAGE_NUM);
		} else { // shortestPath.size() == 0, starting page is dead end
			System.out.print(STARTING_PAGE_NUM);
		}
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
        		//System.out.println("DEADEND"); // add DEADEND process here?
        		curPath.remove(curPath.size() - 1); // remove dead end node for next path
        	} else if (inputLine.equals("GOAL")) {
        		//System.out.println("GOAL"); // add GOAL process here?
        		if (isFirstPath) {
        			goal = curPath.get(curPath.size() - 1);
        			shortestPath.addAll(curPath);
        			isFirstPath = false;
        		} else {
        			if (curPath.size() < shortestPath.size()) {
        				shortestPath.clear();
        				shortestPath.addAll(curPath);
        			}
        		}
        		curPath.remove(curPath.size() - 1); // remove goal node for next path        		
        	} else { // list page
        		long exp = evaluateExpression(inputLine);
        		if (!nodes.add(exp)) { // DFS meet directed cycle        			
        			if (exp == goal && isFirstPath == false && 
        					curPath.size() < shortestPath.size()) { // goal is duplicated node   
        				shortestPath.clear();
        				shortestPath.addAll(curPath);
        				shortestPath.add(goal);
        			} else if (exp == STARTING_PAGE_NUM && 
        					curPath.get(curPath.size() - 1) == exp) { // starting page self cycle
        				directedCycleCount++;
        			} else {
        				directedCycleCount++;
        				curPath.remove(curPath.size() - 1); // remove cycle node for next path
        			}        			
        		} else {
        			curPath.add(exp);
        			parsePage(new URL(BASE_URL, String.valueOf(exp)));
        		}
        	}
        }
        in.close();		
	}

	public static ArrayList<Long> extractDirectedCycle(long n) {
		ArrayList<Long> directedCycle = new ArrayList<Long>();
		for (int i = 0; i < curPath.size(); i++) {
			if (curPath.get(i) == n) {
				for (int j = i; j < curPath.size(); j++) {
					directedCycle.add(curPath.get(j));
				}
				//directedCycle.add(curPath.get(i));
				break;
			}
		}
		Collections.sort(directedCycle);
		return directedCycle;
	}
	
	public static void printCycle(ArrayList<Long> cycle) {
		for (int i = 0; i < cycle.size(); i++) {
			System.out.print(cycle.get(i) + " ");
		}
		System.out.println();
	}
	
	public static void parsePage2(URL page) throws IOException {
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(page.openStream()));
		String inputLine;
        while ((inputLine = in.readLine()) != null) {
        	if (inputLine.equals("DEADEND")) {
        		//System.out.println("DEADEND"); // add DEADEND process here?
        		//curPath.remove(curPath.size() - 1); // remove dead end node for next path
        	} else if (inputLine.equals("GOAL")) {
        		//System.out.println("GOAL"); // add GOAL process here?
        		if (isFirstPath) {
        			goal = curPath.get(curPath.size() - 1);
        			shortestPath.addAll(curPath);
        			isFirstPath = false;
        		} else {
        			if (curPath.size() < shortestPath.size()) {
        				shortestPath.clear();
        				shortestPath.addAll(curPath);
        			}
        		}
        		//curPath.remove(curPath.size() - 1); // remove goal node for next path        		
        	} else { // list page
        		long exp = evaluateExpression(inputLine);
        		if (curPath.contains(exp)) { // DFS meet directed cycle        			
/*        			if (exp == STARTING_PAGE_NUM && 
        					curPath.get(curPath.size() - 1) == exp) { // starting page self cycle
        				directedCycleCount++;
        			} else {
*/        				//curPath.add(exp);
        				ArrayList<Long> cycle = extractDirectedCycle(exp);        				
						if (!directedCycles.contains(cycle)) { // unique directed cycle
							directedCycleCount++;							
							directedCycles.add(cycle); //printCycle(cycle);						
						}
        				//curPath.remove(curPath.size() - 1); // remove cycle node for next path
 //       			}        			
        		} else {
        			nodes.add(exp);
        			curPath.add(exp);
        			parsePage2(new URL(BASE_URL, String.valueOf(exp)));
        			curPath.remove(curPath.size() - 1); 
        		}
        	}
        }
        in.close();		
	}
	
	public static void main(String[] args) throws IOException {
		BASE_URL = new URL(
					"http://www.crunchyroll.com/tech-challenge/roaming-math/myspiritcrazy@gmail.com/");
		nodes.add(STARTING_PAGE_NUM);
		curPath.add(STARTING_PAGE_NUM);
		//parsePage(new URL(BASE_URL, String.valueOf(STARTING_PAGE_NUM)));
		parsePage2(new URL(BASE_URL, String.valueOf(STARTING_PAGE_NUM)));
		output();
	}
}
