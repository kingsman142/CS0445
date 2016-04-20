/*
CS0445
Project 5
James Hahn

This is the main class file for project 5.
This project handles Huffman compression trees to not only compress, but also decompress text strings.
Normally, Huffman trees would use actual bits in memory to compress data to save space, but we will be simulating
the process using Strings of bits of data in the Huffman tree.

The user can do 1 of 3 things:
	1) Encode a word.  The user will be informed on all of the possible letters in the Huffman tree.  They will then
	   be prompted to enter a word, or any text, to encode.  The program will then print out to the user the path
	   of each letter in the tree, or in other words, the Huffman code of the word.
	2) Decode a word.  The user will be provided with the Huffman table of the tree, which contains not only all of
	   the letters in the tree, but also each one's respective bitstring representation of its path in the tree.
	   The user will then be prompted to enter in a Huffman code, and the program will return to the user the corresponding
	   word.
	3) Quit the program.

The user must provide the program with a text file containing the tree.  The text file should be of a format similar to this:
I
L A
I
I
L D
I
L E
L F
I
L C
L B

Each line will have an I (interior node) or an L (leaf node) where the third space in the line is the leaf node's character data.
If the line contains an I, the next line will be its left node (I or L) and the line after that will be its right node (given the left node is not an I).
*/

import java.util.*;
import java.io.*;

public class Assig5{
	public static BinaryNode<Character> rootNode = null; //Stores root node
	private static File f = null; //Stores file to read from
	private static Scanner fileRead = null;
	private static boolean initializeRoot = true; //Used for the createTree() method to check if the root has been created yet
	private static int numData = 0;
	private static String[] table = null;

	private static int lowestChar = -1; //Used for the createTable() method

	public static void main(String[] args){
		try{
			Scanner sc = new Scanner(System.in);

			System.out.print("Enter in the filename: ");
			String filename = sc.nextLine();
			f = new File(filename);
			fileRead = new Scanner(f);
			String input = "";
			createTree(rootNode, new StringBuilder(), -1);
			createTable(rootNode);
			System.out.println("Table created");

			do{
				System.out.println("Select one of the following by entering the respective letter:");
				System.out.println("(A) Encode a word");
				System.out.println("(B) Decode a Huffman code");
				System.out.println("(C) Quit the program");
				System.out.print("Input: ");
				input = sc.nextLine();
				System.out.println();

				switch(input.toUpperCase()){
					case "A":
						encodeWord();
						break;
					case "B":
						decodeWord();
						break;
					case "C":
						break;
					default:
						System.out.println("Invalid input!\n");
						break;
				}
			} while(!input.toUpperCase().equals("C"));
		} catch(Exception e){
			System.out.println("Error!");
		}
	}

	//A Huffman compression tree is creating by reading in a file given by the user
	//and linking many BinaryNodes together.
	public static void createTree(BinaryNode<Character> parent, StringBuilder path, int direction){
		if(initializeRoot){ //Initializes the root and creates the current recursive call to make the rest of the tree
			rootNode = new BinaryNode<Character>();
			initializeRoot = false;
			fileRead.nextLine();
			createTree(rootNode, path, 0);
		} else{
			String line = null;
			if(fileRead.hasNextLine()) line = fileRead.nextLine();
			//This is the first line to read in for the parent node (should be the parent's left child)
			if(line != null){
				if(line.equals("I")){ //'I' means create an interior node containing no data
					if(parent.getLeftNode() == null){ //Check if we can add a left node to the parent
						BinaryNode<Character> newNodeLeft = new BinaryNode<Character>();
						parent.insertLeft(newNodeLeft);
						path.append('0');
						createTree(newNodeLeft, path, 0);
						path.deleteCharAt(path.length()-1);
					} else if(parent.getRightNode() == null){ //Check if we can add a right node to the parent
						BinaryNode<Character> newNodeRight = new BinaryNode<Character>();
						parent.insertRight(newNodeRight);
						path.append('1');
						createTree(newNodeRight, path, 1);
						path.deleteCharAt(path.length()-1);
					}
				} else if(line.charAt(0) == 'L'){ //'L' means create a leaf node with some letter (read on the same line) as its data
					char charData = line.charAt(2);
					BinaryNode<Character> newNode = new BinaryNode<Character>(new Character(charData));
					numData++;

					//Set the value of the lowest char so we can make the table later on
					if(lowestChar == -1) lowestChar = (int) charData;
					else if((int) charData < lowestChar) lowestChar = (int) charData;

					if(parent.getLeftNode() == null){ //Check if we can add a left node to the parent
						newNode.setPath(path.toString() + '0');
						parent.insertLeft(newNode);
					} else{ //Check if we can add a right node to the parent
						newNode.setPath(path.toString() + '1');
						parent.insertRight(newNode);
					}
				}
			}

			line = null;
			if(fileRead.hasNextLine()) line = fileRead.nextLine();
			//This is the first line to read in for the parent node (should be the parent's right child)
			if(line != null){
				if(line.equals("I")){ //'I' means create an interior node containing no data
					if(parent.getLeftNode() == null){ //Check if we can add a left node to the parent
						BinaryNode<Character> newNodeLeft = new BinaryNode<Character>();
						parent.insertLeft(newNodeLeft);
						path.append('0');
						createTree(newNodeLeft, path, 0);
						path.deleteCharAt(path.length()-1);
					} else if(parent.getRightNode() == null){ //Check if we can add a right node to the parent
						BinaryNode<Character> newNodeRight = new BinaryNode<Character>();
						parent.insertRight(newNodeRight);
						path.append('1');
						createTree(newNodeRight, path, 1);
						path.deleteCharAt(path.length()-1);
					}
				} else if(line.charAt(0) == 'L'){ //'L' means create a leaf node with some letter (read on the same line) as its data
					char charData = line.charAt(2);
					BinaryNode<Character> newNode = new BinaryNode<Character>(new Character(charData));
					numData++;

					//Set the value of the lowest char so we can make the table later on
					if(lowestChar == -1) lowestChar = (int) charData;
					else if((int) charData < lowestChar) lowestChar = (int) charData;

					if(parent.getLeftNode() == null){ //Check if we can add a left node to the parent
						newNode.setPath(path.toString() + '0');
						parent.insertLeft(newNode);
					} else{ //Check if we can add a right node to the parent
						newNode.setPath(path.toString() + '1');
						parent.insertRight(newNode);
					}
				}
			}
		}
	}

	//Uses Huffman compression tree to encode a word.
	//The user passes in a word to encode and the method prints out the respective bitstring
	//representation of its path in the tree.
	public static void encodeWord(){
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the word to encode (using ");
		printLetters();
		System.out.print("): ");
		String word = sc.nextLine();
		StringBuilder path = new StringBuilder();
		for(char c: word.toCharArray()){
			if(Character.toUpperCase(c) != c){ //Special case: the user entered a word where at least one letter was lower case!  This is invalid input!
				System.out.println("\nYou entered an invalid string of characters!\n");
				return;
			}

			if(searchTree(c, rootNode, path) == null){
				path = new StringBuilder();
				break;
			}
			if(path.length() == 0) break;
			path.append('\n');
		}
		if(path.length() > 0) System.out.println("\nYour huffman code: \n" + path);
		else System.out.println("\nYou entered an invalid string of characters!\n");
	}

	//Uses Huffman compression tree to decode a word.
	//The user passes in a compression code and the method prints out the respective word.
	public static void decodeWord(){
		BinaryNode<Character> curr = rootNode;
		StringBuilder word = new StringBuilder();

		Scanner sc = new Scanner(System.in);
		System.out.println("The encoding table: ");
		printTable();
		System.out.print("\nEnter a bitstring representation to decode: ");
		String bitString = sc.nextLine();

		int counter = 1;
		for(char c: bitString.toCharArray()){
			if(c == '0'){
				curr = curr.getLeftNode();
			} else if(c == '1'){
				curr = curr.getRightNode();
			} else{
				System.out.println("\nYou entered an invalid Huffman bitstring!\n");
				return;
			}

			if(curr.getData() != null){
				word.append(curr.getData().charValue());
				curr = rootNode;
			}

			if(counter == bitString.length() && !curr.equals(rootNode)){
				word = new StringBuilder();
				break;
			}
			counter++;
		}

		if(word.length() > 0) System.out.println("The word: " + word.toString() + "\n");
		else System.out.println("\nYou entered an invalid Huffman bitstring!\n");
	}

	//Searches the binary tree for a character, and returns the path of that node to the program
	public static String searchTree(char c, BinaryNode<Character> curr, StringBuilder bits){
		if(curr == null){ //Base case: backtrack and delete the current path position in the bits StringBuilder
			bits.deleteCharAt(bits.length()-1);
			return null;
		} else if(curr.getData() == null){
			String a = null;
			String b = null;
			bits.append('0'); //Append '0' to the current path because we're going down the left side
			if(curr.getLeftNode() != null){ //Check if we can traverse the left side of the subtree
				b = searchTree(c, curr.getLeftNode(), bits); //Recursively search for the letter in the left subtree
				if(b == null){ //If we returned null, traverse down the right subtree
					bits.deleteCharAt(bits.length()-1);
					bits.append('1'); //Append '1' to the current path because we're going down the right side
					a = searchTree(c, curr.getRightNode(), bits); //Recursively search for the letter in the right subtree
					if(a == null){
						bits.deleteCharAt(bits.length()-1);
					}
				} else{ //Else, we can continue to traverse the left subtree
					return bits.toString();
				}
			} else{
				bits.deleteCharAt(bits.length()-1);
			}

			return a;
		} else if(curr.getData().charValue() == c){ //Base case: we found the character!
			return bits.toString();
		}

		return null;
	}

	//Prints out all of the possible characters in the tree.
	//Used for the encodeWord() function
	private static void printLetters(){
		for(int i = 0; i < table.length; i += 2){
			System.out.print(table[i]);
		}
	}

	//Creates the Huffman table for future reference
	private static void createTable(BinaryNode<Character> curr){
		if(table == null) table = new String[numData*2];
		if(curr.getData() == null){
			createTable(curr.getLeftNode());
			createTable(curr.getRightNode());
		} else{
			table[(((int) curr.getData()) - lowestChar)*2] = String.valueOf(curr.getData());
			table[(((int) curr.getData()) - lowestChar)*2 + 1] = curr.getPath();
		}
	}

	//Prints out the Huffman table (all of the letters with their corresponding paths).
	//Used for the decodeWord() function
	private static void printTable(){
		for(int i = 0; i < table.length; i += 2){
			System.out.println(table[i] + ": " + table[i+1]);
		}
	}


}
