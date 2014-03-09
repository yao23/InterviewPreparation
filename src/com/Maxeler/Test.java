package com.Maxeler;

public class Test {
	public static void main(String[] args) {  
          
        int i = 49;  
        byte b = (byte)i;
        
        String hex = Integer.toHexString(i);  
          
        System.out.println(hex);  
        System.out.println(b);
        System.out.println((char)b); // ASCII text represented by 8-bit byte 
    }
}
