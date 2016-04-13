import java.util.*;
import java.io.*;

public class Assig5{
	public static BinaryNode<Character> rootNode = null; //Stores root node
	private static File f = null; //Stores file to read from
	private static Scanner fileRead = null;
	private static boolean initializeRoot = true; //Used for the createTree() method to check if the root has been created yet
	private static ArrayList<String> treeLetters = new ArrayList<String>();
	private static int numData = 0;
	private static String[] table = null;

	private static int currPos = 0;

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
			currPos = 0;

			do{
				System.out.println("Select one of the following by entering the respective letter:");
				System.out.println("(A) Encode a word");
				System.out.println("(B) Decode a Huffman code");
				System.out.println("(C) Quit the program");
				input = sc.next();

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
						System.out.println("\nInvalid input!\n");
						break;
				}
			} while(!input.toUpperCase().equals("C"));
		} catch(Exception e){
			System.out.println("Error!");
		}
	}

	public static void createTree(BinaryNode<Character> parent, StringBuilder path, int direction){
		if(initializeRoot){
			rootNode = new BinaryNode<Character>();
			initializeRoot = false;
			fileRead.nextLine();
			createTree(rootNode, path, 0);
		} else{
			String line = null;
			if(fileRead.hasNextLine()) line = fileRead.nextLine();
			if(line != null){
				if(line.equals("I")){
					if(parent.getLeftNode() == null){
						BinaryNode<Character> newNodeLeft = new BinaryNode<Character>();
						parent.insertLeft(newNodeLeft);
						path.append('0');
						createTree(newNodeLeft, path, 0);
						path.deleteCharAt(path.length()-1);
					} else if(parent.getRightNode() == null){
						BinaryNode<Character> newNodeRight = new BinaryNode<Character>();
						parent.insertRight(newNodeRight);
						path.append('1');
						createTree(newNodeRight, path, 1);
						path.deleteCharAt(path.length()-1);
					}
				} else if(line.charAt(0) == 'L'){
					BinaryNode<Character> newNode = new BinaryNode<Character>(line.charAt(2));
					numData++;
					if(parent.getLeftNode() == null){
						newNode.setPath(path.toString() + '0');
						parent.insertLeft(newNode);
					} else{
						newNode.setPath(path.toString() + '1');
						parent.insertRight(newNode);
					}
				}
			}

			line = null;
			if(fileRead.hasNextLine()) line = fileRead.nextLine();
			if(line != null){
				if(line.equals("I")){
					if(parent.getLeftNode() == null){
						BinaryNode<Character> newNodeLeft = new BinaryNode<Character>();
						parent.insertLeft(newNodeLeft);
						path.append('0');
						createTree(newNodeLeft, path, 0);
						path.deleteCharAt(path.length()-1);
					} else if(parent.getRightNode() == null){
						BinaryNode<Character> newNodeRight = new BinaryNode<Character>();
						parent.insertRight(newNodeRight);
						path.append('1');
						createTree(newNodeRight, path, 1);
						path.deleteCharAt(path.length()-1);
					}
				} else if(line.charAt(0) == 'L'){
					BinaryNode<Character> newNode = new BinaryNode<Character>(line.charAt(2));
					numData++;
					if(parent.getLeftNode() == null){
						newNode.setPath(path.toString() + '0');
						parent.insertLeft(newNode);
					} else{
						newNode.setPath(path.toString() + '1');
						parent.insertRight(newNode);
					}
				}
			}
		}
	}

	public static void encodeWord(){
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the word to encode (using ");
		printLetters();
		System.out.println("): ");
		String word = sc.nextLine();
		StringBuilder path = new StringBuilder();
		for(char c: word.toCharArray()){
			if(searchTree(c, rootNode, path) == null){
				path = new StringBuilder();
				break;
			}
			if(path.length() == 0) break;
			path.append('\n');
		}
		if(path.length() > 0) System.out.println("\nYour huffman code: \n" + path);
		else System.out.println("\nYou entered an incorrect string of characters!\n");
	}

	public static void decodeWord(){
		BinaryNode<Character> curr = rootNode;
		StringBuilder word = new StringBuilder();

		Scanner sc = new Scanner(System.in);
		System.out.println("\nThe encoding table: ");
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

			if(curr.getData() != '\0'){
				word.append(curr.getData());
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

	public static String searchTree(char c, BinaryNode<Character> curr, StringBuilder bits){
		if(curr.getData() == c){
			return bits.toString();
		} else if(curr == null){
			bits.deleteCharAt(bits.length()-1);
			return null;
		} else if(Character.toUpperCase(curr.getData()) != curr.getData()){
			return null;
		} else{
			String a = null;
			String b = null;
			bits.append('0');
			if(curr.getLeftNode() != null){
				b = searchTree(c, curr.getLeftNode(), bits);
				if(b == null){
					bits.deleteCharAt(bits.length()-1);
					bits.append('1');
					a = searchTree(c, curr.getRightNode(), bits);
					if(a == null){
						bits.deleteCharAt(bits.length()-1);
					}
				} else{
					return bits.toString();
				}
			} else{
				bits.deleteCharAt(bits.length()-1);
			}

			return a;
		}
	}

	private static void printLetters(){
		for(int i = 0; i < table.length; i += 2){
			System.out.print(table[i]);
		}
	}

	private static void createTable(BinaryNode<Character> curr){
		if(table == null) table = new String[numData*2];
		if(curr.getData() == '\0'){
			createTable(curr.getLeftNode());
			createTable(curr.getRightNode());
		} else{
			table[currPos++] = String.valueOf(curr.getData());
			table[currPos++] = curr.getPath();
		}
	}

	private static void printTable(){
		for(int i = 0; i < table.length; i += 2){
			System.out.println(table[i] + ": " + table[i+1]);
		}
	}


}
