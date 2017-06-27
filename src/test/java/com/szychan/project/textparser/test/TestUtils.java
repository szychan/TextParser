package com.szychan.project.textparser.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TestUtils {

	public static String readFileContentToString(File file){
		StringBuffer sb = new StringBuffer();
		try (Scanner sc = new Scanner(file)){
			while(sc.hasNextLine()){
				sb.append(sc.nextLine());
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return sb.toString();
		
	}
	
	public static PrintWriter getPrintWriter(File file){
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
			return new PrintWriter(osw);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
