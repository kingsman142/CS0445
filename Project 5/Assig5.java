import java.util.*;
import java.io.*;

public class Assig5{
	public static BinaryNode<Character> rootNode = null;
	private static File f = null;
	private static Scanner fileRead = null;
	private static boolean initializeRoot = true;

	public static void main(String[] args){
		try{
			Scanner sc = new Scanner(System.in);

			System.out.print("Enter in the filename: ");
			String filename = sc.nextLine();
			f = new File(filename);
			fileRead = new Scanner(f);
			String input = "";
			createTree(rootNode);

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
						break;
					case "C":
						break;
					default:
						break;
				}
			} while(!input.toUpperCase().equals("C"));
		} catch(Exception e){
			System.out.println("Error!");
		}
	}

	public static void createTree(BinaryNode<Character> parent){
		if(initializeRoot){
			rootNode = new BinaryNode<Character>();
			System.out.println("Initialized root!");
			initializeRoot = false;
			fileRead.nextLine();
			createTree(rootNode);
		} else{
			String line = null;
			if(fileRead.hasNextLine()) line = fileRead.nextLine();
			System.out.println("Line: " + line);
			if(line != null){
				if(line.equals("I")){
					if(parent.getLeftNode() == null){
						System.out.println("LeftNode is null");
						BinaryNode<Character> newNodeLeft = new BinaryNode<Character>();
						parent.insertLeft(newNodeLeft);
						createTree(newNodeLeft);
					} else if(parent.getRightNode() == null){
						System.out.println("RightNode is null");
						BinaryNode<Character> newNodeRight = new BinaryNode<Character>();
						parent.insertRight(newNodeRight);
						createTree(newNodeRight);
					}
				} else if(line.charAt(0) == 'L'){
					BinaryNode<Character> newNode = new BinaryNode<Character>(line.charAt(2));
					if(parent.getLeftNode() == null){
						System.out.println("Adding leaf to Left child!");
						parent.insertLeft(newNode);
					} else{
						System.out.println("Adding leaf to Right child!");
						parent.insertRight(newNode);
					}
				}
			}

			line = null;
			if(fileRead.hasNextLine()) line = fileRead.nextLine();
			System.out.println("Line2: " + line);
			if(line != null){
				if(line.equals("I")){
					if(parent.getLeftNode() == null){
						System.out.println("LeftNode is null");
						BinaryNode<Character> newNodeLeft = new BinaryNode<Character>();
						parent.insertLeft(newNodeLeft);
						createTree(newNodeLeft);
					} else if(parent.getRightNode() == null){
						System.out.println("RightNode is null");
						BinaryNode<Character> newNodeRight = new BinaryNode<Character>();
						parent.insertRight(newNodeRight);
						createTree(newNodeRight);
					}
				} else if(line.charAt(0) == 'L'){
					BinaryNode<Character> newNode = new BinaryNode<Character>(line.charAt(2));
					if(parent.getLeftNode() == null){
						System.out.println("Adding leaf to Left child!");
						parent.insertLeft(newNode);
					} else{
						System.out.println("Adding leaf to Right child!");
						parent.insertRight(newNode);
					}
				}
			}
		}
	}

	public static void encodeWord(){
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the word to encode: ");
		String word = sc.nextLine();
		for(char c: word.toCharArray()){
			System.out.println("Searching the code for " + c);
			String bitString = searchTree(c, rootNode, new StringBuilder());
			System.out.println(c + " is bit string " + bitString);
		}
	}

	public static String searchTree(char c, BinaryNode<Character> curr, StringBuilder bits){
		System.out.println("1: " + c + " and " + curr.getData());
		if(curr.getData() == c){
			System.out.println("2");
			System.out.println("RETURNING " + bits.toString());
			return bits.toString();
		} else if(curr == null){
			System.out.println("3");
			System.out.println("curr == null: returning null");
			return null;
		} else{
			String a = null;
			System.out.println("4");
			bits.append('0');
			System.out.println("bits: " + bits.toString());
			if(curr.getLeftNode() != null){
				System.out.println("curr.left: " + curr.getLeftNode().getData());
				if(searchTree(c, curr.getLeftNode(), bits) == null){
					System.out.println("5");
					bits.deleteCharAt(bits.length()-1);
					bits.append('1');
					a = searchTree(c, curr.getRightNode(), bits);
				}
			}

			return a;
		}
	}
}
