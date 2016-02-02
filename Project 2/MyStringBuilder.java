// CS 0445 Spring 2016
// Read this class and its comments very carefully to make sure you implement
// the class properly.  Note the items that are required and that cannot be
// altered!  Generally speaking you will implement your MyStringBuilder using
// a singly linked list of nodes.  See more comments below on the specific
// requirements for the class.

// For more details on the general functionality of most of these methods,
// see the specifications of the similar method in the StringBuilder class.
public class MyStringBuilder
{
	// These are the only three instance variables you are allowed to have.
	// See details of CNode class below.  In other words, you MAY NOT add
	// any additional instance variables to this class.  However, you may
	// use any method variables that you need within individual methods.
	// But remember that you may NOT use any variables of any other
	// linked list class or of the predefined StringBuilder or
	// or StringBuffer class in any place in your code.  You may only use the
	// String class where it is an argument or return type in a method.
	private CNode firstC;	// reference to front of list.  This reference is necessary
							// to keep track of the list
	private CNode lastC; 	// reference to last node of list.  This reference is
							// necessary to improve the efficiency of the append()
							// method
	private int length;  	// number of characters in the list

	// You may also add any additional private methods that you need to
	// help with your implementation of the public methods.

	// Create a new MyStringBuilder initialized with the chars in String s
	public MyStringBuilder(String s)
	{
		if (s == null || s.length() == 0) // Special case for empty String
		{					 			  // or null reference
			firstC = null;
			lastC = null;
			length = 0;
		}
		else
		{
			// Create front node to get started
			firstC = new CNode(s.charAt(0));
			length = 1;
			CNode currNode = firstC;
			// Create remaining nodes, copying from String.  Note
			// how each new node is simply added to the end of the
			// previous one.  Trace this to see what is going on.
			for (int i = 1; i < s.length(); i++)
			{
				CNode newNode = new CNode(s.charAt(i));
				currNode.next = newNode;
				currNode = newNode;
				length++;
			}
			lastC = currNode;
		}
	}

	// Create a new MyStringBuilder initialized with the chars in array s
	public MyStringBuilder(char [] s)
	{
		//Special case for an empty array
		if(s == null || s.length == 0){
			firstC = null;
			lastC = null;
			length = 0;
		} else{
			//Create the first node
			firstC = new CNode(s[0]);
			length = 1;
			CNode currNode = firstC;

			//Create the rest of the nodes
			for(int i = 1; i < s.length; i++){
				CNode newNode = new CNode(s[i]);
				currNode.next = newNode;
				currNode = newNode;
				length++;
			}
			lastC = currNode;
		}
	}

	// Create a new empty MyStringBuilder
	public MyStringBuilder()
	{
		//Building everything from scratch so the default values are just null and 0
		firstC = null;
		lastC = null;
		length =  0;
	}

	// Append MyStringBuilder b to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(MyStringBuilder b)
	{
		for(int i = 0; i < b.length(); i++){
			if(length > 0){
				CNode newNode = new CNode((char) b.charAt(i));
				lastC.next = newNode;
				lastC = newNode;
				length++;
			} else{
				firstC = new CNode(b.charAt(i));
				lastC = firstC;
				length++;
			}
		}

		return this;
	}


	// Append String s to the end of the current MyStringBuilder, and return
	// the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(String s)
	{
		for(int i = 0; i < s.length(); i++){
			if(length > 0){
				CNode newNode = new CNode(s.charAt(i));
				lastC.next = newNode;
				lastC = newNode;
				length++;
			} else{
				firstC = new CNode(s.charAt(i));
				lastC = firstC;
				length++;
			}
		}

		return this;
	}

	// Append char array c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(char [] c)
	{
		for(int i = 0; i < c.length; i++){
			if(length > 0){
				CNode newNode = new CNode(c[i]);
				lastC.next = newNode;
				lastC = newNode;
				length++;
			} else{
				firstC = new CNode(c[i]);
				lastC = firstC;
				length++;
			}
		}

		return this;
	}

	// Append char c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(char c)
	{
		if(length > 0){
			CNode newNode = new CNode(c);
			lastC.next = newNode;
			lastC = newNode;
			length++;
		} else{
			firstC = new CNode(c);
			lastC = firstC;
			length++;
		}

		return this;
	}

	// Return the character at location "index" in the current MyStringBuilder.
	// If index is invalid, throw an IndexOutOfBoundsException.
	public char charAt(int index)
	{
		CNode nextNode = firstC; //Start with index 0 in MyStringBuilder being the first node

		//Invalid Index check
		if(index < 0 || index >= length){
			throw new IndexOutOfBoundsException();
		} else{
			//Loop through the linked list
			for(int i = 0; i < length; i++){
				//Check if the loop is at the index, and if so, return that character
				//Otherwise, just move onto the next node in the linked list
				if(i == index) return nextNode.data;
				else nextNode = nextNode.next;
			}
		}

		return '\0';
	}

	// Delete the characters from index "start" to index "end" - 1 in the
	// current MyStringBuilder, and return the current MyStringBuilder.
	// If "start" is invalid or "end" <= "start" do nothing (just return the
	// MyStringBuilder as is).  If "end" is past the end of the MyStringBuilder,
	// only remove up until the end of the MyStringBuilder. Be careful for
	// special cases!
	public MyStringBuilder delete(int start, int end)
	{
		CNode startNode = null;
		CNode endNode = null;
		CNode temp = firstC;

		if(end >= length-1){
			for(int i = 0; i < length; i++){
				if(i == start-1){
					lastC = temp;
					lastC.next = null;
					length = start;
					return this;
				}
				temp = temp.next;
			}
		} else if(start > 0 && end < length-1){
			for(int i = 0; i < end+1; i++){
				if(i == start-1) startNode = temp;
				if(i == end) endNode = temp;
				temp = temp.next;
			}

			startNode.next = endNode;

			length -= (end-start);
			return this;
		} else if(end <= start || start < 0){
			return this;
		} else if(start == 0){
			for(int i = 0; i < end+1; i++){
				if(i == end){
					firstC = temp;
					firstC.next = temp.next;
				}
				temp = temp.next;
			}

			length -= (end-start);
			return this;
		} else if(start == 0 && end == length-1){
			firstC = null;
			lastC = null;
			length = 0;

			return this;
		}

		return this;
	}

	// Delete the character at location "index" from the current
	// MyStringBuilder and return the current MyStringBuilder.  If "index" is
	// invalid, do nothing (just return the MyStringBuilder as is).
	// Be careful for special cases!
	public MyStringBuilder deleteCharAt(int index)
	{
		CNode startNode = null;
		CNode endNode = null;
		CNode temp = firstC;

		if(index < 0 || index > length-1){
			return this;
		} else if(index > 0 && index < length-1){
			for(int i = 0; i < index+2; i++){
				if(i == index-1) startNode = temp;
				else if(i == index) endNode = temp.next;
				temp = temp.next;

				if(startNode != null && endNode != null){
					startNode.next = endNode;
					length--;
					return this;
				}
			}
		} else if(index == 0){
			firstC = firstC.next;
			length--;
			return this;
		} else if(index == length-1){
			for(int i = 0; i < length; i++){
				if(i == length-2){
					lastC = temp.next;
					length--;
					return this;
				}
				temp = temp.next;
			}
		}

		return this;
	}

	// Find and return the index within the current MyStringBuilder where
	// String str first matches a sequence of characters within the current
	// MyStringBuilder.  If str does not match any sequence of characters
	// within the current MyStringBuilder, return -1.  Think carefully about
	// what you need to do for this method before implementing it.
	public int indexOf(String str)
	{
		CNode temp = firstC;
		int charMatches = 0;

		for(int i = 0; i < length; i++){
			if(temp.data == str.charAt(0)){
				CNode temp2 = temp.next;
				charMatches++;
				for(int j = 1; j < str.length(); j++){
					if(temp2.data == str.charAt(j)) charMatches++;
					else break;

					if(temp2.next != null) temp2 = temp2.next;
				}

				if(charMatches == str.length())	return i;

				charMatches = 0;
			}

			temp = temp.next;
		}

		return -1;
	}

	// Insert String str into the current MyStringBuilder starting at index
	// "offset" and return the current MyStringBuilder.  if "offset" ==
	// length, this is the same as append.  If "offset" is invalid
	// do nothing.
	public MyStringBuilder insert(int offset, String str)
	{
		if(offset == length){
			append(str);
		} else if(offset < 0 || offset > length){
			return this;
		} else if(offset > 0 && offset < length){
			CNode startNode = null;
			CNode currNode = null;
			CNode temp = firstC;

			for(int i = 0; i < length; i++){
				if(i == offset-1){
					currNode = temp;
				} else if(i == offset){
					for(int j = 0; j < str.length(); j++){
						CNode newNode = new CNode(str.charAt(j));
						currNode.next = newNode;
						currNode = newNode;
						length++;
					}
				} else if(i == offset+str.length()){
					currNode.next = temp;
				}

				temp = temp.next;
			}
		}

		return this;
	}

	// Insert character c into the current MyStringBuilder at index
	// "offset" and return the current MyStringBuilder.  If "offset" ==
	// length, this is the same as append.  If "offset" is invalid,
	// do nothing.
	public MyStringBuilder insert(int offset, char c)
	{
		return this;
	}

	// Insert char array c into the current MyStringBuilder starting at index
	// index "offset" and return the current MyStringBuilder.  If "offset" is
	// invalid, do nothing.
	public MyStringBuilder insert(int offset, char [] c)
	{
		return this;
	}

	// Return the length of the current MyStringBuilder
	public int length()
	{
		return length;
	}


	// Delete the substring from "start" to "end" - 1 in the current
	// MyStringBuilder, then insert String "str" into the current
	// MyStringBuilder starting at index "start", then return the current
	// MyStringBuilder.  If "start" is invalid or "end" <= "start", do nothing.
	// If "end" is past the end of the MyStringBuilder, only delete until the
	// end of the MyStringBuilder, then insert.
	public MyStringBuilder replace(int start, int end, String str)
	{
		return this;
	}

	// Return as a String the substring of characters from index "start" to
	// index "end" - 1 within the current MyStringBuilder
	public String substring(int start, int end)
	{
		return null;
	}

	// Return the entire contents of the current MyStringBuilder as a String
	public String toString()
	{
		String contents = ""; //The String that will be returned
		CNode node = firstC; //First node to look at and append to the String

		//Loop through the entire list and append the characters to the String
		for(int i = 0; i < length; i++){
			if(node == null) System.out.println("node " + i + " is null");

			contents += node.data;
			node = node.next;
		}

		return contents;
	}

	// You must use this inner class exactly as specified below.  Note that
	// since it is an inner class, the MyStringBuilder class MAY access the
	// data and next fields directly.
	private class CNode
	{
		private char data;
		private CNode next;

		public CNode(char c)
		{
			data = c;
			next = null;
		}

		public CNode(char c, CNode n)
		{
			data = c;
			next = n;
		}
	}
}
