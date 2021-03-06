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
//	private static final String inputPath = filePath.concat(new String("/src/com/input/task1.bin"));
//	private static final String inputPath = filePath.concat(new String("/src/com/input/test01.bin"));
	private static final String inputPath2 = filePath.concat(new String("/src/com/input/task2.bin"));
//	private static final String dataPath = filePath.concat("/src/com/output/task1_data.txt");

	private static final int lineSize = 8;
	private static int ip = 0;
	private static int sp = 0;
	private static int[] data;
	private static byte[] image;

	/** Run the example. 
	 * @throws UnsupportedEncodingException */
	public static void main(String[] Args) throws UnsupportedEncodingException {

	    // read in the bytes
		image = read(inputPath2);

	    // write data back out to a different file name
	    //test.write(fileContents, dataPath);

		loadImage();
	}
	
	
	/** Read the given binary file, and return its contents as a byte array.*/ 
	public static byte[] read(String aInputFileName){
		File file = new File(aInputFileName);
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
		     }
		     finally {
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
	public static void write(byte[] aInput, String aOutputFileName){
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
	 
	 private static int getNumber(int offset) 
			 throws UnsupportedEncodingException {
		 int d = 0;
		 int signBitMask = 0x8;
		 boolean isNegative = false;
		 // lineSize == 8
		 // item in index 8 is new line sign '\n'
		 for (int i = 0; i < lineSize; i++) {			 
			 String digit = new String(new byte[] {image[i + offset * (lineSize + 1)]}, "UTF-8");
			 int dTmp = Integer.parseInt(digit, 16);
			 if (i == 0 && ((dTmp & signBitMask) == signBitMask)) { // get sign 
				 dTmp &= 0x7;
				 isNegative = true;
			 }
			 if (dTmp > 0) {
				 for (int j = 0; j < (lineSize - 1 - i); j++) {
					 dTmp *= 16; // convert to decimal number
				 }
			 }
			 d += dTmp;
		 }
		 return (isNegative ? (d * (-1)) : d);
	 }
	 
	 private static void storeInstruction(int imageSize) 
			 throws UnsupportedEncodingException {
		 for (int i = 0; i < imageSize; i++) {  
			 data[i] = getNumber(i + 2); // instruction start from line in index 2
		 }
	 }

	 private static void f(int v) {
		 sp = sp - 1;
		 data[sp] = v;
	 }
	 
	 private static int g() {
			int v = data[sp];
			sp = sp + 1; 
			return v;
	 }
	 
	 private static void executeInstruction(int imageSize) 
			 throws UnsupportedEncodingException {
		 sp = data.length;

		 for (ip = 0; ip < imageSize; ) { 
			 int curInstruction = data[ip]; 
			 ip = ip + 1;
			 
		   	 // decode instruction
			 int binop = 0;
			 int operation = 0;
			 int optionalData = 0;
			 if (curInstruction > 0) {
				 binop = 1 << 31;
				 binop &= curInstruction; 
				 binop >>= 31;  
				 operation = ((1 << 7) - 1) << 24; 
				 operation &= curInstruction; 
				 operation >>= 24; 
				 optionalData = (1 << 24) - 1;
				 optionalData &= curInstruction; 
			 } else if (curInstruction < 0) {
				 binop = 1;
				 int tmpCur = curInstruction * (-1);
				 operation = (((1 << 7) - 1) & (tmpCur >> 24));
				 optionalData = ((1 << 24) - 1) & tmpCur;
			 } else { // curInstruction == 0
				 int lineNum = ip - 1 + 2; // 2 is offset, 0 for dataSize, 1 for imageSize
				 String digit = new String(new byte[] {image[lineNum * (lineSize + 1)]}, "UTF-8");
				 int dTmp = Integer.parseInt(digit, 16);
				 int signMask = 0x8;
				 if ((dTmp & signMask) == signMask) { // get sign 
					 binop = 1;
				 } else {
					 binop = 0;
				 }
				 operation = 0;
				 optionalData = 0;
			 }
			 // perform action based on operation
			 if (binop == 0) {
				 int addr; 
				 switch (operation) {
				 	case 0: // pop 
				 		sp = sp + 1; 
				 		break;
				 		
				 	case 1: // "push <const>"
				 		f(optionalData); 
				 		break;

				 	case 2: // "push ip"
				 		f(ip);
				 		break;
				 		
				 	case 3: // "push sp"
				 		f(sp);
				 		break;

				 	case 4: // "load"
				 		addr = g();
				 		f(data[addr]);
				 		break;

				 	case 5: // "store"
				 		int st_data = g();
				 		addr = g();
				 		data[addr] = st_data;
				 		break;

				 	case 6: // "jmp"
				 		int cond = g();
				 		addr = g();
				 		if (cond != 0) { // if cond is not equal to zero then set ip = addr
				 			ip = addr;
				 		}
				 		break;

				 	case 7: // "not"
				 		if (g() == 0)  { // if g() equals 0 then f(1) else f(0)
				 			f(1);
				 		} else {
				 			f(0);
				 		}
				 		break;
				 		
				 	case 8: // "putc"
				 		// output exactly one byte = (g() & 0xff) to stdout
				 		int res = g(); 
				 		res &= 0xff; 
				 		System.out.print((char)res);	//	ASCII text		 		

				 		// Note: Output from the supplied VM images will always be ASCII text when
				 		// functioning correctly and will use '\n' (= 0x0A) to indicate new-line.
				 		break;

				 	case 9: // "getc"
				 		int x = 0; // cast x to 32bits
						try { 
							x = System.in.read(); // read exactly one byte from stdin
						} catch (IOException e) {
							log("Read error from stdin!");
							e.printStackTrace();
						} 

				 		f(x & 0xff); 
				 		break;

				 	case 10: // (0x0A) - halt
				 		return; // Stop execution

				 	default:
				 		log("Invalid operation!");
				 		break;
				 }
			 } else {
				 int b = g();
				 int a = g();
				 switch (operation) {
				 	case 0: 
				 		f(a + b); 
				 		break;
				 		
				 	case 1:
				 		f(a - b); 
				 		break;
				 		
				 	case 2:
				 		f(a * b); 
				 		break;
				 		
				 	case 3:
				 		f(a / b); 
				 		break;
				 		
				 	case 4:
				 		f(a & b); 
				 		break;
				 		
				 	case 5:
				 		f(a | b); 
				 		break;
				 		
				 	case 6:
				 		f(a ^ b); 
				 		break;
				 		
				 	case 7:
				 		f(a == b ? 1 : 0); 
				 		break;
				 		
				 	case 8:
				 		f(a < b ? 1 : 0); 
				 		break;
				 		
				 	default:
				 		log("Invalid operation!");
				 		break;
				 }
			 }
		 }
	 }
	 
	 private static void loadImage() throws UnsupportedEncodingException {		 
		 int dataSize = getNumber(0); 
		 int imageSize = getNumber(1); 
		 data = new int[dataSize];
		 storeInstruction(imageSize);
		 executeInstruction(imageSize);
	 }
}
