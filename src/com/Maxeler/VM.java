package com.Maxeler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class VM {
	private static String filePath = new File("").getAbsolutePath();
	private static final String inputPath = filePath.concat(new String("/src/com/input/task1.bin"));
	private static final String outputPath = filePath.concat("/src/com/output/output.txt");
	
	/** Run the example. 
	 * @throws UnsupportedEncodingException */
	public static void main(String[] Args) throws UnsupportedEncodingException {
		VM test = new VM();
	    //read in the bytes
		byte[] fileContents = test.read(inputPath);
		load(fileContents);
	    //write it back out to a different file name
	    //test.write(fileContents, outputPath);
	}
	
	/** Read the given binary file, and return its contents as a byte array.*/ 
	byte[] read(String aInputFileName){
		log("Reading in binary file named : " + aInputFileName);
		File file = new File(aInputFileName);
		log("File size: " + file.length());
		byte[] result = new byte[(int)file.length()];
		try {
			InputStream input = null;
			try {
				int totalBytesRead = 0;
		        input = new BufferedInputStream(new FileInputStream(file));
		        while (totalBytesRead < result.length){
		        	int bytesRemaining = result.length - totalBytesRead;
		        	//input.read() returns -1, 0, or more :
		        	int bytesRead = input.read(result, totalBytesRead, bytesRemaining); 
		        	if (bytesRead > 0) {
		        		totalBytesRead = totalBytesRead + bytesRead;
		        	}
		        }
		        /*
		         the above style is a bit tricky: it places bytes into the 'result' array; 
		         'result' is an output parameter;
		         the while loop usually has a single iteration only.
		        */
		        log("Num bytes read: " + totalBytesRead);
		     }
		     finally {
		        log("Closing input stream.");
		        input.close();
		     }
		}
		catch (FileNotFoundException ex) {
			log("File not found.");
		}
		catch (IOException ex) {
		    log(ex);
		}
		return result;
	}
	  
	/**
	 Write a byte array to the given file. 
	 Writing binary data is significantly simpler than reading it. 
	*/
	void write(byte[] aInput, String aOutputFileName){
		log("Writing binary file..." + aOutputFileName);
	    try {
	    	OutputStream output = null;
	    	try {
	    		output = new BufferedOutputStream(new FileOutputStream(aOutputFileName));
	    		output.write(aInput);
	    	}
	    	finally {
	    		output.close();
	    	}
	    }
	    catch(FileNotFoundException ex){
	      log("File not found.");
	    }
	    catch(IOException ex){
	      log(ex);
	    }
	    log("Complete writing!");
	}
	  
	 private static void log(Object aThing){
		 System.out.println(String.valueOf(aThing));
	 }
	 
	 private static void storeInstruction(byte[] image, int[] data, int imageSize) {
		 log("byte array size: " + image.length);
	 }
	 
	 private static void load(byte[] image) throws UnsupportedEncodingException {
		 String decoded = "";
		 String decoded2 = "";
		 for (int i = 0; i < 8; i++) { // item in index 8 is new line sign \n
		   decoded += new String(new byte[] {image[i]}, "UTF-8");
		   decoded2 += new String(new byte[] {image[i + 9]}, "UTF-8");
		 }
		 
		 int dataSize = Integer.parseInt(decoded, 16);
		 int imageSize = Integer.parseInt(decoded2, 16);
		 int[] data = new int[dataSize];
		 storeInstruction(image, data, imageSize);
		 
	 }
}
