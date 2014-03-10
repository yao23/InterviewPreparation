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
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

public class VM {
	private static String filePath = new File("").getAbsolutePath();
	private static final String inputPath = filePath.concat(new String("/src/com/input/task1.bin"));
//	private static final String inputPath2 = filePath.concat(new String("/src/com/input/task2.bin"));
	private static final String dataPath = filePath.concat("/src/com/output/task1_data.txt");

	private static final int lineSize = 8;
	private static int ip = 0;
	private static int sp = 0;
	private static VM test = new VM();
	/** Run the example. 
	 * @throws UnsupportedEncodingException */
	public static void main(String[] Args) throws UnsupportedEncodingException {

	    // read in the bytes
		byte[] fileContents = test.read(inputPath);

	    // write data back out to a different file name
	    //test.write(fileContents, dataPath);

		loadImage(fileContents);
	}
	
	
	/** Read the given binary file, and return its contents as a byte array.*/ 
	byte[] read(String aInputFileName){
//		log("Reading in binary file named : " + aInputFileName);
		File file = new File(aInputFileName);
//		log("File size: " + file.length());
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
//		        log("Num bytes read: " + totalBytesRead);
		     }
		     finally {
//		        log("Closing input stream.");
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
	 
	 private static int getNumber(byte[] image, int offset) 
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
	 
	 private static void storeInstruction(byte[] image, int[] data, int imageSize) 
			 throws UnsupportedEncodingException {
		 for (int i = 0; i < imageSize; i++) {  
			 data[i] = getNumber(image, i + 2); // instruction start from line in index 2
		 }
	 }

	 private static void f(int[] data, int v) {
		 sp = sp - 1;
		 data[sp] = v;
	 }
	 
	 private static int g(int[] data) {
			int v = data[sp];
			sp = sp + 1; 
			return v;
	 }
	 
	 private static void executeInstruction(int[] data, byte[] image, int imageSize) 
			 throws UnsupportedEncodingException {
		 sp = data.length;

		 for (ip = 0; ip < imageSize; ) { //ip++) {
			 int curInstruction = data[ip]; 
			 ip = ip + 1;
			 
		   	 // decode instruction
			 int binop = 0;
			 int operation = 0;
			 int optionalData = 0;
			 if (curInstruction > 0) {
				 binop = 1 << 31;
				 binop &= curInstruction; 
				 binop >>= 31;  //log("binop: " + Integer.toBinaryString(binop));
				 operation = ((1 << 7) - 1) << 24; 
				 operation &= curInstruction; 
				 operation >>= 24; // log("operation: " + Integer.toBinaryString(operation));
				 optionalData = (1 << 24) - 1;
				 optionalData &= curInstruction; // log("optional data: " + Integer.toBinaryString(optionalData));
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
				 int addr; //log("Before, sp: " + sp + ", data: " + Integer.toHexString(data[sp-1]));
				 switch (operation) {
				 	case 0: // pop 
				 		sp = sp + 1; 
//				 		log("pop");
				 		break;
				 		
				 	case 1: // "push <const>"
				 		f(data, optionalData); //log("After, sp: " + sp + ", data: " + Integer.toHexString(data[sp]));
//				 		log("push_const: " + optionalData + ", hexa: " + Integer.toHexString(optionalData) + ", at " + sp);
				 		break;

				 	case 2: // "push ip"
				 		f(data, ip);
//				 		log("push_ip: " + ip + ", hexa: " + Integer.toHexString(ip) + ", at " + sp);
				 		break;
				 		
				 	case 3: // "push sp"
				 		f(data, sp);
//				 		log("push_sp: " + (sp+1) + ", hexa: " + Integer.toHexString(sp+1) + ", at " + sp);
				 		break;

				 	case 4: // "load"
				 		addr = g(data);
				 		f(data, data[addr]);
//				 		log("load");
				 		break;

				 	case 5: // "store"
				 		int st_data = g(data);
				 		addr = g(data);
				 		data[addr] = st_data;
//				 		log("store");
				 		break;

				 	case 6: // "jmp"
				 		int cond = g(data);
				 		addr = g(data);
				 		if (cond != 0) { // if cond is not equal to zero then set ip = addr
				 			ip = addr;
				 		}
//				 		log("jmp");
				 		break;

				 	case 7: // "not"
				 		if (g(data) == 0)  { // if g() equals 0 then f(1) else f(0)
				 			f(data, 1);
				 		} else {
				 			f(data, 0);
				 		}
//				 		log("not");
				 		break;
				 		
				 	case 8: // "putc"
				 		// output exactly one byte = (g() & 0xff) to stdout
				 		int res = g(data); 
				 		res &= 0xff; 
				 		System.out.print((char)res);	//	ASCII text		 		

				 		// Note: Output from the supplied VM images will always be ASCII text when
				 		// functioning correctly and will use '\n' (= 0x0A) to indicate new-line.
//				 		log("putc");
				 		break;

				 	case 9: // "getc"
				 		int x = 0;
						try { 
							x = System.in.read(); // read exactly one byte from stdin
						} catch (IOException e) {
							log("Read error from stdin!");
							e.printStackTrace();
						} 
				 		//x &= 0xff; // cast x to 32bits
				 		x = Character.getNumericValue(x);
				 		f(data, x & 0xff); 
//				 		log("getc");
				 		break;

				 	case 10: // (0x0A) - halt
//				 		log("halt");
				 		return; // Stop execution

				 	default:
				 		log("Invalid operation!");
				 		break;
				 }
			 } else {
				 int b = g(data);
				 int a = g(data);
				 switch (operation) {
				 	case 0: 
				 		f(data, a + b); 
//				 		log("add");
				 		break;
				 		
				 	case 1:
				 		f(data, a - b); 
//				 		log("sub");
				 		break;
				 		
				 	case 2:
				 		f(data, a * b); 
//				 		log("mul");
				 		break;
				 		
				 	case 3:
				 		f(data, a / b); 
//				 		log("div");
				 		break;
				 		
				 	case 4:
				 		f(data, a & b); 
//				 		log("and");
				 		break;
				 		
				 	case 5:
				 		f(data, a | b); 
//				 		log("or");
				 		break;
				 		
				 	case 6:
				 		f(data, a ^ b); 
//				 		log("xor");
				 		break;
				 		
				 	case 7:
				 		f(data, (a == b ? 1 : 0)); 
//				 		log("eq");
				 		break;
				 		
				 	case 8:
				 		f(data, (a < b ? 1 : 0)); 
//				 		log("lt");
				 		break;
				 		
				 	default:
				 		log("Invalid operation!");
				 		break;
				 }
			 }
		 }
	 }
	 
	 private static void loadImage(byte[] image) throws UnsupportedEncodingException {		 
		 int dataSize = getNumber(image, 0); //log("data size: " + dataSize);
		 int imageSize = getNumber(image, 1); //log("image size: " + imageSize);
		 int[] data = new int[dataSize];
		 storeInstruction(image, data, imageSize);
		 executeInstruction(data, image, imageSize);
	 }
}
