import java.util.*;
import java.io.*;

public class Assig5{
	public static BinaryNode<Character> rootNode = null;
	private static File f = null;
	private static Scanner fileRead = null;
	private static boolean initializeRoot = true;
	private static ArrayList<String> treeLetters = new ArrayList<String>();

	public static void main(String[] args){
		try{
			Scanner sc = new Scanner(System.in);

			System.out.print("Enter in the filename: ");
			String filename = sc.nextLine();
			f = new File(filename);
			fileRead = new Scanner(f);
			String input = "";
			createTree(rootNode, new StringBuilder());

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
					default:
						break;
				}
			} while(!input.toUpperCase().equals("C"));
		} catch(Exception e){
			System.out.println("Error!");
		}
	}

	public static void createTree(BinaryNode<Character> parent, StringBuilder path){
		if(initializeRoot){
			rootNode = new BinaryNode<Character>();
			//System.out.println("Initialized root!");
			initializeRoot = false;
			fileRead.nextLine();
			createTree(rootNode, path);
		} else{
			String line = null;
			if(fileRead.hasNextLine()) line = fileRead.nextLine();
			//System.out.println("Line: " + line);
			if(line != null){
				if(line.equals("I")){
					if(parent.getLeftNode() == null){
						//System.out.println("LeftNode is null");
						BinaryNode<Character> newNodeLeft = new BinaryNode<Character>();
						parent.insertLeft(newNodeLeft);
						path.append('0');
						createTree(newNodeLeft, path);
						path.deleteCharAt(path.length()-1);
					} else if(parent.getRightNode() == null){
						//System.out.println("RightNode is null");
						BinaryNode<Character> newNodeRight = new BinaryNode<Character>();
						parent.insertRight(newNodeRight);
						path.append('1');
						createTree(newNodeRight, path);
						path.deleteCharAt(path.length()-1);
					}
				} else if(line.charAt(0) == 'L'){
					BinaryNode<Character> newNode = new BinaryNode<Character>(line.charAt(2));
					treeLetters.add(String.valueOf(line.charAt(2)));
					//treeLetters.add(path.toString());
					if(parent.getLeftNode() == null){
						//System.out.println("Adding leaf to Left child!");
						parent.insertLeft(newNode);
					} else{
						//System.out.println("Adding leaf to Right child!");
						parent.insertRight(newNode);
					}
				}
			}

			line = null;
			if(fileRead.hasNextLine()) line = fileRead.nextLine();
			//System.out.println("Line2: " + line);
			if(line != null){
				if(line.equals("I")){
					if(parent.getLeftNode() == null){
						//System.out.println("LeftNode is null");
						BinaryNode<Character> newNodeLeft = new BinaryNode<Character>();
						parent.insertLeft(newNodeLeft);
						path.append('0');
						createTree(newNodeLeft, path);
						path.deleteCharAt(path.length()-1);
					} else if(parent.getRightNode() == null){
						//System.out.println("RightNode is null");
						BinaryNode<Character> newNodeRight = new BinaryNode<Character>();
						parent.insertRight(newNodeRight);
						path.append('1');
						createTree(newNodeRight, path);
						path.deleteCharAt(path.length()-1);
					}
				} else if(line.charAt(0) == 'L'){
					BinaryNode<Character> newNode = new BinaryNode<Character>(line.charAt(2));
					treeLetters.add(String.valueOf(line.charAt(2)));
					treeLetters.add(path.toString());
					if(parent.getLeftNode() == null){
						//System.out.println("Adding leaf to Left child!");
						parent.insertLeft(newNode);
					} else{
						//System.out.println("Adding leaf to Right child!");
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
			String bitString = searchTree(c, rootNode, path);
			path.append('\n');
			//System.out.println(c + " is bit string " + bitString);
		}
		System.out.println(path);
	}

	public static String decodeWord(){
		BinaryNode<Character> curr = rootNode;
		StringBuilder word = new StringBuilder();

		Scanner sc = new Scanner(System.in);
		System.out.println("The encoding table: ");
		printTable();
		System.out.print("\nEnter a bitstring representation to decode: ");
		String bitString = sc.nextLine();

		for(char c: bitString.toCharArray()){
			if(c == '0'){
				curr = curr.getLeftNode();
			} else if(c == '1'){
				curr = curr.getRightNode();
			}

			if(curr.getData() != '\0'){
				word.append(curr.getData());
				curr = rootNode;
			}
		}

		String wordRep = word.toString();
		System.out.println("The word: " + wordRep + "\n");
		return wordRep;
	}

	public static String searchTree(char c, BinaryNode<Character> curr, StringBuilder bits){
		if(curr.getData() == c){
			return bits.toString();
		} else if(curr == null){
			bits.deleteCharAt(bits.length()-1);
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
		for(String a: treeLetters){
			if(a.length() == 1) System.out.print(a);
		}
	}

	private static void printTable(){
		for(String a: treeLetters){
			if(a.length() > 1) System.out.println(a);
		}
	}
}
