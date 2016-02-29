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
		//If nothing is to be appended, return this object
		if(b.length() == 0 || b == null) return this;

		//Add each element of b into this object
		for(int i = 0; i < b.length(); i++){
			if(length > 0){ //Normal: create a new node and add it to the end
				CNode newNode = new CNode((char) b.charAt(i));
				lastC.next = newNode;
				lastC = newNode;
				length++;
			} else{ //Special case: have to set firstC if this object doesn't have it currently
				firstC = new CNode(b.charAt(0));
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
		//If nothing is to be appended, return this object
		if(s.length() == 0 || s == null) return this;

		//Add each element of s into this object
		for(int i = 0; i < s.length(); i++){
			if(length > 0){ //Normal: create a new node and add it to the end
				CNode newNode = new CNode(s.charAt(i));
				lastC.next = newNode;
				lastC = newNode;
				length++;
			} else{ //Special case: have to set firstC if this object doesn't have it currently
				firstC = new CNode(s.charAt(0));
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
		//If nothing is to be appended, return this object
		if(c.length == 0 || c == null) return this;

		//Add each element of c into this object
		for(int i = 0; i < c.length; i++){
			if(length > 0){ //Normal: create a new node and add it to the end
				CNode newNode = new CNode(c[i]);
				lastC.next = newNode;
				lastC = newNode;
				length++;
			} else{ //Speicl case: have to set firstC if this object doesn't have it currently
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
		//Doesn't use for loop like other methods because you only had one element
		if(length > 0){ //Normal: create a new node and add it to the end
			CNode newNode = new CNode(c);
			lastC.next = newNode;
			lastC = newNode;
			length++;
		} else{  //Special case: have to set firstC if this object doesn't have it currently
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

		return '\0'; //If the char is not found, return \0
	}

	// Delete the characters from index "start" to index "end" - 1 in the
	// current MyStringBuilder, and return the current MyStringBuilder.
	// If "start" is invalid or "end" <= "start" do nothing (just return the
	// MyStringBuilder as is).  If "end" is past the end of the MyStringBuilder,
	// only remove up until the end of the MyStringBuilder. Be careful for
	// special cases!
	public MyStringBuilder delete(int start, int end)
	{
		//Variables to keep track of the starting node and the ending node to link
		//them together later; temp is just to store each traversing node
		CNode startNode = null;
		CNode endNode = null;
		CNode temp = firstC;

		//Special case: the end index is higher than the object's length
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
		//Normal case: Start and End are within their constraints
		} else if(start > 0 && end < length-1){
			for(int i = 0; i < end+1; i++){
				if(i == start-1) startNode = temp;
				if(i == end) endNode = temp;
				temp = temp.next;
			}

			startNode.next = endNode;

			length -= (end-start);
			return this;
		//Special case: the indices are extremely invalid; end can't be smaller than start
		} else if(end <= start || start < 0){
			return this;
		//Special case: delete everything from the start until the end; just make the "end" index firstC
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
		//Special case: delete all the contents in the object
		} else if(start == 0 && end == length){
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
		//Variables to keep track of the starting node and the ending node to link
		//them together later; temp is just to store each traversing node
		CNode startNode = null;
		CNode endNode = null;
		CNode temp = firstC;

		//Special case: index is out of range
		if(index < 0 || index > length-1){
			return this;
		//Normal case: the index is within the constraints of the object
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
		//Special case: just delete the first node and make the next node in the linked-list firstC
		} else if(index == 0){
			firstC = firstC.next;
			length--;
			return this;
		//Special case: just delete the last node and make the next to last node in the linked-list lastC
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
		//Variables to keep track of each node being traversed and the similarity between the str and the nodes (charMatches)
		CNode temp = firstC;
		int charMatches = 0;

		if(str == null) return -1;

		//This loop traverses the array and keeps track of how many simultaneous character matches it encounters.
		//Once the loop encounters a str element such that the linked-list node's data is different, it will
		//immediately stop searching for the rest of the word (str), and just return back to searching the
		//linked-list for individual characters.
		for(int i = 0; i < length; i++){
			if(temp.data == str.charAt(0)){
				CNode temp2 = temp.next;
				charMatches++;
				for(int j = 1; j < str.length(); j++){
					if(temp2.data == str.charAt(j)) charMatches++;
					else break;

					if(temp2.next != null) temp2 = temp2.next;
				}

				//Return the index of the first element of str that matches the linked-list if str is found
				if(charMatches == str.length())	return i;

				charMatches = 0;
			}

			temp = temp.next;
		}

		//Return -1 if the str can't be found
		return -1;
	}

	// Insert String str into the current MyStringBuilder starting at index
	// "offset" and return the current MyStringBuilder.  if "offset" ==
	// length, this is the same as append.  If "offset" is invalid
	// do nothing.
	public MyStringBuilder insert(int offset, String str)
	{
		//Special case: append the str to the linked-list
		if(offset == length){
			append(str);
		//Special case: offset is out of range
		} else if(offset < 0 || offset > length){
			return this;
		//Normal case: offset is within the constraints of the linked-list
		} else if(offset > 0 && offset < length){
			//Create variables to traverse linked-list
			CNode endNode = null;
			CNode startNode = null;
			CNode currNode = null;
			CNode temp = firstC;

			//Inserting loop
			for(int i = 0; i < length; i++){
				if(i == offset-1){
					startNode = temp;
					endNode = startNode.next;

					CNode newNode = new CNode(str.charAt(0));
					startNode.next = newNode;
					currNode = newNode;
				} else if(i == offset){
					for(int j = 1; j < str.length(); j++){
						CNode newNode = new CNode(str.charAt(j));
						currNode.next = newNode;
						currNode = newNode;
						length++;
					}
					currNode.next = endNode;
					length++;
				}

				temp = temp.next;
			}
		//Special case: put the str at the very beginning of the linked-list
		} else if(offset == 0){
			CNode endNode = firstC;
			firstC = new CNode(str.charAt(0));
			CNode temp = firstC;

			for(int j = 1; j < str.length(); j++){
				CNode newNode = new CNode(str.charAt(j));
				temp.next = newNode;
				temp = newNode;
				length++;
			}

			temp.next = endNode;
			length++;
		}

		return this;
	}

	// Insert character c into the current MyStringBuilder at index
	// "offset" and return the current MyStringBuilder.  If "offset" ==
	// length, this is the same as append.  If "offset" is invalid,
	// do nothing.
	public MyStringBuilder insert(int offset, char c)
	{
		//Special case: append the str to the linked-list
		if(offset == length){
			append(c);
		//Special case: offset is out of range
		} else if(offset < 0 || offset > length){
			return this;
		//Normal case: offset is within the constraints of the linked-list
		} else if(offset > 0 && offset < length){
			//Create variables to traverse linked-list
			CNode endNode = null;
			CNode temp = firstC;

			//Keep on swapping nodes in the linked-list
			for(int i = 0; i < offset+1; i++){
				if(i == offset){
					CNode newNode = new CNode(c);
					endNode = temp.next;
					temp.next = newNode;
					temp = newNode;
					temp.next = endNode;
				} else temp = temp.next;
			}

			temp.next = endNode;
			length++;
		//Special case: put the str at the very beginning of the linked-list
		} else if(offset == 0){
			CNode endNode = firstC;
			firstC = new CNode(c);
			firstC.next = endNode;
			length++;
		}

		return this;
	}

	// Insert char array c into the current MyStringBuilder starting at index
	// index "offset" and return the current MyStringBuilder.  If "offset" is
	// invalid, do nothing.
	public MyStringBuilder insert(int offset, char [] c)
	{
		//Special case: append the str to the linked-list
		if(offset == length){
			append(c);
		//Special case: offset is out of range
		} else if(offset < 0 || offset > length){
			return this;
		//Normal case: offset is within the constraints of the linked-list
		} else if(offset > 0 && offset < length){
			//Create variables to traverse linked-list
			CNode startNode = null;
			CNode endNode = null;
			CNode currNode = null;
			CNode temp = firstC;

			//Inserting loop
			for(int i = 0; i < length; i++){
				if(i == offset-1){
					startNode = temp;
					endNode = startNode.next;

					CNode newNode = new CNode(c[0]);
					startNode.next = newNode;
					currNode = newNode;
				} else if(i == offset){
					for(int j = 1; j < c.length; j++){
						CNode newNode = new CNode(c[j]);
						currNode.next = newNode;
						currNode = newNode;
						length++;
					}
					currNode.next = endNode;
					length++;
				}

				temp = temp.next;
			}
		//Special case: put the str at the very beginning of the linked-list
		} else if(offset == 0){
			CNode endNode = firstC;
			firstC = new CNode(c[0]);
			CNode temp = firstC;

			for(int j = 1; j < c.length; j++){
				CNode newNode = new CNode(c[j]);
				temp.next = newNode;
				temp = newNode;
				length++;
			}

			temp.next = endNode;
			length++;
		}

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
		//In this method, instead of making it complicated and implementing new stuff,
		//it just utilizes other class methods to look nicer.

		//Special case: start is out of range or end is smaller than start
		if(start <= 0 || start > length || end <= start) return this;
		//Normal case: start and end are within the constraints of the object
		else if(start >= 0 && end <= length-1){
			CNode temp = firstC;
			CNode startNode = null;
			CNode endNode = null;
			CNode currNode = null;
			CNode twoTemp = null;

			for(int i = 0; i < length; i++){
				if(i == start-1){
					startNode = temp;
					twoTemp = temp;

					//Find the "end" node
					for(int j = i; j < end+1; j++){
						if(j == end) endNode = twoTemp;
						twoTemp = twoTemp.next;
					}

					CNode newNode = new CNode(str.charAt(0));
					startNode.next = newNode;
					currNode = newNode;
				} else if(i == start){
					for(int j = 1; j < str.length(); j++){
						CNode newNode = new CNode(str.charAt(j));
						currNode.next = newNode;
						currNode = newNode;
					}
					currNode.next = endNode;
				}

				temp = temp.next;
			}

			length += str.length() - (end-start); //Update the object's length
		//If end is bigger than the length of the linked-list, just treat it like replacing all the way to length
		} else if(end >= length){
			CNode temp = firstC;
			CNode startNode = null;
			CNode currNode = null;

			for(int i = 0; i < length; i++){
				if(i == start-1){
					startNode = temp;

					CNode newNode = new CNode(str.charAt(0));
					startNode.next = newNode;
					currNode = newNode;
				} else if(i == start){
					for(int j = 1; j < str.length(); j++){
						if(j == str.length()){
							lastC = currNode;
							lastC.next = null;
						} else{
							CNode newNode = new CNode(str.charAt(j));
							currNode.next = newNode;
							currNode = newNode;
						}
					}
				}

				temp = temp.next;
			}

			length += str.length() - (length-start); //Update the object's length
		}
		return this;
	}

	//Reverses the MyStringBuilder character sequence
	public MyStringBuilder reverse(){
		//Starter variables
		CNode firstNode = firstC;
		CNode lastNode = lastC;
		CNode temp = firstC;
		CNode oneTemp = firstC;

		//Special case: there's nothing in MyStringBuilder
		if(length == 0 || length == 1) return this;

		//Normal case: reverse MyStringBuilder
		for(int i = 0; i < length; i++){
			temp = firstNode;
			oneTemp = firstNode.next;
			for(int j = 0; j < length-i; j++){
				if(oneTemp == lastNode && i == 0){
					firstC = lastNode;
					firstC.next = temp;
				} else if(oneTemp == firstNode){
					oneTemp.next = temp;
					lastC = temp;
					lastC.next = null;
				} else{
					if(j == length-i-1){
						oneTemp.next = temp;
						break;
					}
					temp = oneTemp;
					oneTemp = oneTemp.next;
				}
			}
		}

		return this;
	}

	// Return as a String the substring of characters from index "start" to
	// index "end" - 1 within the current MyStringBuilder
	public String substring(int start, int end)
	{
		//Create a String variable to store the substring and a temp to keep track of traversing the array
		char[] string = new char[end-start];
		CNode temp = firstC;

		if(end < start) return null; //Invalid index
		else if(end == start) return String.valueOf(charAt(start)); //Return one character at the given index

		//Make sure the two indices are within their respective bounds
		if(end > length) end = length;
		if(start < 0) start = 0;

		//Once the method reaches a point where it should START the substring, then keep
		//on adding the characters to variable s, until the loop reaches END
		for(int i = 0; i < end; i++){
			if(i >= start && i < end) string[i-start] = temp.data;
			temp = temp.next;
		}

		return new String(string);
	}

	// Return the entire contents of the current MyStringBuilder as a String
	public String toString()
	{
		char[] contents = new char[length]; //char array that will be returned as a String
		CNode node = firstC; //First node to look at and append to the String

		if(length == 0) return new String(contents); //Special case: nothing in MyStringBuilder

		//Loop through the entire list and append the characters to the String
		for(int i = 0; i < length; i++){
			contents[i] = node.data;
			node = node.next;
		}

		return new String(contents);
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
