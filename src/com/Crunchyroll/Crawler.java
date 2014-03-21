package com.Crunchyroll;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

public class Crawler {
	public static final String STARTING_PAGE_NUM = "64738"; 
	public static URL BASE_URL; 
	public static long goal = 0;
	public static int nodeCount = 0;
	public static ArrayList<Long> shortestPath = new ArrayList<Long>();
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
				long a = Long.parseLong(s.substring(idx, i)); // push 2nd integer in operand
				idx = i + 1;
				String opt = operator.pop();
				if (opt == "abs") {
					operand.push(Math.abs(a));
				} else {					
					long b = operand.pop();
					if (opt == "add") {
						operand.push(a + b);
					} else if (opt == "subtract") {
						operand.push(b - a);
					} else if (opt == "multiply") {
						operand.push(a * b);
					} else {
						System.out.println("Invalid operation");
					}
				}
			} else if ( c == ',') { // push 1st integer in operand
				operand.push(Long.parseLong(s.substring(idx, i)));
				idx = i + 1;
				continue;
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
        	} else if (inputLine.equals("GOAL")) {
        		System.out.println("GOAL"); // add GOAL process here?
        	} else { // list page
        		long res = evaluateExpression(inputLine);
        		//System.out.println(inputLine);
        		System.out.println(res);
        	}
        }
        in.close();
	}	
	
	public static void main(String[] args) throws IOException {
		BASE_URL = new URL(
					"http://www.crunchyroll.com/tech-challenge/roaming-math/myspiritcrazy@gmail.com/");
		URL	startingPage = new URL(BASE_URL, STARTING_PAGE_NUM);
		
		parsePage(startingPage);
		
		//output();
	}
}
