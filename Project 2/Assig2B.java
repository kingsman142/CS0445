/*
James Hahn
This file is part of Assignment 2 for CS0445.
This program takes one command-line argument for args[0], which is the name of the file to be read in (Test8K.txt, Test16K.txt, Test32K.txt).

It compares the time taken to perform three different operations (append, insert, delete)
with three different classes (String, MyStringBuilder, StringBuilder).
It outputs the total time for operation and the average time taken per character for each operation.
*/

import java.io.*;

public class Assig2B{
	public static void main(String[] args) throws IOException{
		//Create the BufferedReader and check if the file exists
		BufferedReader testFile = new BufferedReader(new FileReader(args[0]));
		if(!(new File(args[0])).exists()) System.exit(0);

		//Create a size variable to find average operation times later on
		double size = 0.0;
		if(args[0].equals("Test8K.txt")) size = 8000.0;
		else if(args[0].equals("Test16K.txt")) size = 16000.0;
		else if(args[0].equals("Test32K.txt")) size = 32000.0;

		long start = 0; //start time
		long end = 0; //end time

		//Elapsed times
		long sAppend = 0;
		long msbAppend = 0;
		long sbAppend = 0;
		double sAppendAvg = 0;
		double msbAppendAvg = 0;
		double sbAppendAvg = 0;

		long sDelete = 0;
		long msbDelete = 0;
		long sbDelete = 0;
		double sDeleteAvg = 0;
		double msbDeleteAvg = 0;
		double sbDeleteAvg = 0;

		long sInsert = 0;
		long msbInsert = 0;
		long sbInsert = 0;
		double sInsertAvg = 0;
		double msbInsertAvg = 0;
		double sbInsertAvg = 0;

		//APPEND TESTS
		//String
		String s = new String();
		start = System.nanoTime();
		while(testFile.ready()){ s += (char) testFile.read(); };
		end = System.nanoTime();
		sAppend = end-start;
		sAppendAvg = (double) sAppend/size;

		//MyStringBuilder
		MyStringBuilder msb = new MyStringBuilder();
		start = 0;
		end = 0;
		start = System.nanoTime();
		while(testFile.ready()){ msb.append((char) testFile.read()); };
		end = System.nanoTime();
		msbAppend = end-start;
		msbAppendAvg = (double) msbAppend/size;

		//StringBuilder
		StringBuilder sb = new StringBuilder();
		start = 0;
		end = 0;
		start = System.nanoTime();
		while(testFile.ready()){ sb.append((char) testFile.read()); };
		end = System.nanoTime();
		sbAppend = end-start;
		sbAppendAvg = (double) sbAppend/size;

		//DELETE TESTS
		//String
		s = new String();
		start = 0;
		end = 0;
		start = System.nanoTime();
		while(s.length() > 0) s = s.substring(1);
		end = System.nanoTime();
		sDelete = end-start;
		sDeleteAvg = (double) sDelete/size;

		//MyStringBuilder
		msb = new MyStringBuilder();
		start = 0;
		end = 0;
		start = System.nanoTime();
		while(msb.length() > 0) msb.delete(0, 1);
		end = System.nanoTime();
		msbDelete = end-start;
		msbDeleteAvg = (double) msbDelete/size;

		//StringBuilder
		sb = new StringBuilder();
		start = 0;
		end = 0;
		start = System.nanoTime();
		while(sb.length() > 0) sb.delete(0, 1);
		end = System.nanoTime();
		sbDelete = end-start;
		sbDeleteAvg = (double) sbDelete/size;

		//INSERT TESTS
		//String
		s = new String();
		start = 0;
		end = 0;
		start = System.nanoTime();
		while(testFile.ready()) s = s.substring(0, s.length()/2) + (char)testFile.read() + s.substring(s.length()/2);
		end = System.nanoTime();
		sInsert = end-start;
		sInsertAvg = (double) sInsert/size;

		//MyStringBuilder
		msb = new MyStringBuilder();
		start = 0;
		end = 0;
		start = System.nanoTime();
		while(testFile.ready()) msb.insert(msb.length()/2, (char) testFile.read());
		end = System.nanoTime();
		msbInsert = end-start;
		msbInsertAvg = (double) msbInsert/size;

		//StringBuilder
		sb = new StringBuilder();
		start = 0;
		end = 0;
		start = System.nanoTime();
		while(testFile.ready()) sb.insert(sb.length()/2, (char) testFile.read());
		end = System.nanoTime();
		sbInsert = end-start;
		sbInsertAvg = (double) sbInsert/size;

		System.out.printf("String append: %d (Avg time: %.2f)\n", sAppend, sAppendAvg);
		System.out.printf("MyStringBuilder append: %d (Avg time: %.2f)\n", msbAppend, msbAppendAvg);
		System.out.printf("StringBuilder append: %d (Avg time: %.2f)\n\n", sbAppend, sbAppendAvg);
		System.out.printf("String delete: %d (Avg time: %.2f)\n", sDelete, sDeleteAvg);
		System.out.printf("MyStringBuilder delete: %d (Avg time: %.2f)\n", msbDelete, msbDeleteAvg);
		System.out.printf("StringBuilder delete: %d (Avg time: %.2f)\n\n", sbDelete, sbDeleteAvg);
		System.out.printf("String insert: %d (Avg time: %.2f)\n", sInsert, sInsertAvg);
		System.out.printf("MyStringBuilder insert: %d (Avg time: %.2f)\n", msbInsert, msbInsertAvg);
		System.out.printf("StringBuilder insert: %d (Avg time: %.2f)\n", sbInsert, sbInsertAvg);
	}
}
