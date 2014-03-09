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
	private static final String dataPath = filePath.concat("/src/com/output/task1_data.txt");
	private static final String outputPath = filePath.concat("/src/com/output/output_task1.txt");
	private static final int lineSize = 8;
	
	/** Run the example. 
	 * @throws UnsupportedEncodingException */
	public static void main(String[] Args) throws UnsupportedEncodingException {
		VM test = new VM();
	    // read in the bytes
		byte[] fileContents = test.read(inputPath);
	    // write data back out to a different file name
	    //test.write(fileContents, dataPath);

		int[] intRes = loadImage(fileContents);
		 log("Result " + (intRes.length-1) + ": " + intRes[intRes.length-1] + ", hexadecimal: " + 
				 Integer.toHexString(intRes[intRes.length-1]));
/*		for (int i = 0; i < 5; i++) {
			 log("Result " + i + ": " + intRes[i] + ", hexadecimal: " + 
					 Integer.toHexString(intRes[i]));
		}
		*///storeFC(intRes);
/*		
		byte[] byteRes = new byte[intRes.length * 4];
		toByteArray(byteRes, intRes);
		// write result
	    test.write(byteRes, outputPath);
*/	}
	
	private static void storeFC(int[] ints) {
		  FileOutputStream out = null;
		  try {
		    out = new FileOutputStream(outputPath);
		    FileChannel file = out.getChannel();
		    ByteBuffer buf = file.map(FileChannel.MapMode.READ_WRITE, 0, 4 * ints.length);
		    for (int i : ints) {
		      buf.putInt(i);
		    }
		    file.close();
		  } catch (IOException e) {
		    throw new RuntimeException(e);
		  } finally {
		    safeClose(out);
		  }
		}

		private static void safeClose(OutputStream out) {
		  try {
		    if (out != null) {
		      out.close();
		    }
		  } catch (IOException e) {
		    // do nothing
		  }
		}
	
	private static void toByteArray(byte[] byteArray, int[] intArray) { 
		ByteBuffer byteBuffer = ByteBuffer.allocate(intArray.length * 4);        
	    IntBuffer intBuffer = byteBuffer.asIntBuffer();
	    intBuffer.put(intArray);
	
	    byteArray = byteBuffer.array();
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
	 
	 private static int getNumber(byte[] image, int offset) 
			 throws UnsupportedEncodingException {
		 int d = 0;
		 // lineSize == 8
		 // item in index 8 is new line sign '\n'
		 for (int i = 0; i < lineSize; i++) {
			 String digit = new String(new byte[] {image[i + offset * (lineSize + 1)]}, "UTF-8");
			 int dTmp = Integer.parseInt(digit, 16);
			 if (dTmp > 0) {
				 for (int j = 0; j < (lineSize - 1 - i); j++) {
					 dTmp *= 16; // convert to decimal number
				 }
			 }
			 d += dTmp;
		 }
		 return d;
	 }
	 
	 private static void storeInstruction(byte[] image, int[] data, int imageSize) 
			 throws UnsupportedEncodingException {
		 log("byte array size: " + image.length);
		 for (int i = 0; i < imageSize; i++) {  
			 data[i] = getNumber(image, i + 2); // instruction start from line in index 2
		 }
	 }

	 private static int f(int[] data, int sp, int v) {
		 sp = sp - 1;
		 data[sp] = v;
		 return sp;
	 }
	 
	 private static int g(int[] data, int sp) {
			int v = data[sp];
			sp = sp + 1;
			return v;
	 }
	 
	 private static void executeInstruction(int[] data) {
		 int sp = data.length;
		 for (int ip = 0; ip < 1; ) { //ip++) {
		 //for (int ip = 0; ip < data.length; ip++) {
			 int curInstruction = data[ip];
			 ip = ip + 1;
			 log("Instruction " + (ip - 1) + ": " + curInstruction + ", hexadecimal: " + 
					 Integer.toHexString(curInstruction));
			 log("current instruction: " + Integer.toBinaryString(curInstruction));
		   	 // decode instruction
			 int binop = 1 << 31;
			 binop &= curInstruction; 
			 binop >>= 31;  log("binop: " + Integer.toBinaryString(binop));
			 int operation = ((1 << 7) - 1) << 24; 
			 operation &= curInstruction; 
			 operation >>= 24;  log("operation: " + Integer.toBinaryString(operation));
			 int optionalData = (1 << 24) - 1;
			 optionalData &= curInstruction;  log("optional data: " + Integer.toBinaryString(optionalData));
			 // perform action based on operation
			 if (binop == 0) {
				 int addr; log("Before, sp: " + sp + ", data: " + Integer.toHexString(data[sp-1]));
				 switch (operation) {
				 	case 0: // pop 
				 		sp = sp + 1;
				 		break;
				 		
				 	case 1: // "push <const>"
				 		sp = f(data, sp, optionalData); log("After, sp: " + sp + ", data: " + Integer.toHexString(data[sp]));
				 		break;

				 	case 2: // "push ip"
				 		sp = f(data, sp, ip);
				 		break;
				 		
				 	case 3: // "push sp"
				 		sp = f(data, sp, sp);
				 		break;

				 	case 4: // "load"
				 		addr = g(data, sp);
				 		sp++; // sp is a local variable and update after g() operation every time
				 		sp = f(data, sp, data[addr]);
				 		break;

				 	case 5: // "store"
				 		int st_data = g(data, sp);
				 		sp++;
				 		addr = g(data, sp);
				 		sp++;
				 		data[addr] = st_data;
				 		break;

				 	case 6: // "jmp"
				 		int cond = g(data, sp);
				 		sp++;
				 		addr = g(data, sp);
				 		sp++;
				 		if (cond != 0) { // if cond is not equal to zero then set ip = addr
				 			ip = addr;
				 		}
				 		break;

				 	case 7: // "not"
				 		if (g(data, sp) == 0)  { // if g() equals 0 then f(1) else f(0)
				 			sp = f(data, sp, 1);
				 		} else {
				 			sp = f(data, sp, 0);
				 		}
				 		break;
				 		
				 	case 8: // "putc"
				 		// output exactly one byte = (g() & 0xff) to stdout
				 		log(g(data, sp) & 0xff);
				 		sp++;
				 		// Note: Output from the supplied VM images will always be ASCII text when
				 		// functioning correctly and will use '\n' (= 0x0A) to indicate new-line.
				 		break;

				 	case 9: // "getc"
					int x = 0;
						try { 
							x = System.in.read(); // read exactly one byte from stdin
						} catch (IOException e) {
							log("Read error from stdin!");
							e.printStackTrace();
						} 
				 		x = // cast x to 32bits
				 		sp = f(data, sp, x & 0xff); // x & 0xff, hexa to dec
				 		break;

				 	case 10: // (0x0A) - halt
				 		return; // Stop execution

				 	default:
				 		log("Invalid operation!");
				 		break;
				 }
			 } else {
				 int b = g(data, sp);
				 sp++;
				 int a = g(data, sp);
				 sp++;
				 switch (operation) {
				 	case 0: 
				 		sp = f(data, sp, a + b);
				 		break;
				 		
				 	case 1:
				 		sp = f(data, sp, a - b);
				 		break;
				 		
				 	case 2:
				 		sp = f(data, sp, a * b);
				 		break;
				 		
				 	case 3:
				 		sp = f(data, sp, a / b);
				 		break;
				 		
				 	case 4:
				 		sp = f(data, sp, a & b);
				 		break;
				 		
				 	case 5:
				 		sp = f(data, sp, a | b);
				 		break;
				 		
				 	case 6:
				 		sp = f(data, sp, a ^ b);
				 		break;
				 		
				 	case 7:
				 		sp = f(data, sp, (a == b ? 1 : 0));
				 		break;
				 		
				 	case 8:
				 		sp = f(data, sp, (a < b ? 1 : 0));
				 		break;
				 		
				 	default:
				 		log("Invalid operation!");
				 		break;
				 }
			 }
		 }
	 }
	 
	 private static int[] loadImage(byte[] image) throws UnsupportedEncodingException {		 
		 int dataSize = getNumber(image, 0); log("data size: " + dataSize);
		 int imageSize = getNumber(image, 1); log("image size: " + imageSize);
		 int[] data = new int[dataSize];
		 storeInstruction(image, data, imageSize);
/*		  
		 for (int i = 0; i < 5; i++) {
			 log("Instruction: " + data[i] + ", hexadecimal: " + 
					 Integer.toHexString(data[i]));
		 }
*/		 
		 executeInstruction(data);
/*		 
		 log("4th instruction: " + data[3] + ", hexadecimal: " + 
				 Integer.toHexString(data[3]));
		 
		 String decoded = "";
		 for (int i = 0; i < lineSize; i++) { 
			 decoded += new String(new byte[] {image[i + 5 * (lineSize + 1)]}, "UTF-8");
		 }
		 log("decoded: " + decoded);
		 
		 log("origin2: " + image[0]);
		 String decoded2 = "";
		 for (int i = 0; i < 1; i++) { 
			 decoded2 += new String(new byte[] {image[i]}, "UTF-8");
		 }
		 log("decoded2: " + decoded2);
*/		 
		 return data;
	 }
}
