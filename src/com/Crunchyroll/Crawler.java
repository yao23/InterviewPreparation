package com.Crunchyroll;

import java.net.*;
import java.io.*;

public class Crawler {
	public static final String STARTING_PAGE_NUM = "64738"; 
	public static URL BASE_URL; 
	
	public static void parsePage(URL page) throws IOException {
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(page.openStream()));
		String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
	}
	public static void main(String[] args) throws IOException {
		BASE_URL = new URL(
					"http://www.crunchyroll.com/tech-challenge/roaming-math/myspiritcrazy@gmail.com/");
		URL	startingPage = new URL(BASE_URL, STARTING_PAGE_NUM);
		
		parsePage(startingPage);
	}
}
