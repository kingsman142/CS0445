// CS 0445 Spring 2016
// Read this class and its comments very carefully to make sure you implement
// the class properly.  Note the items that are required and that cannot be
// altered!  Generally speaking you will implement your MyStringBuilder using
// a singly linked list of nodes.  See more comments below on the specific
// requirements for the class.

// For more details on the general functionality of most of these methods,
// see the specifications of the similar method in the StringBuilder class.
public class MyStringBuilder2
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
	public MyStringBuilder2(String s)
	{
		if(s != null && s.length() > 0)
			makeBuilder(s, 0);
		else{
			length = 0;
			firstC = null;
			lastC = null;
		}
	}

	// Recursive method to set up a new MyStringBuilder2 from a String
	private void makeBuilder(String s, int pos)
	{
	      // Recursive case – we have not finished going through the String
	      if (pos < s.length()-1)
	      {
	            // Note how this is done – we make the recursive call FIRST, then
	            // add the node before it.  In this way the LAST node we add is
	            // the front node, and it enables us to avoid having to make a
	            // special test for the front node.  However, many of your
	            // methods will proceed in the normal front to back way.
	            makeBuilder(s, pos+1);
	            firstC = new CNode(s.charAt(pos), firstC);
	            length++;
	      }
	      else if (pos == s.length()-1) // Special case for last char in String
	      {                             // This is needed since lastC must be
	                                    // set to point to this node
	            firstC = new CNode(s.charAt(pos));
	            lastC = firstC;
	            length = 1;
	      }
	      else  // This case should never be reached, due to the way the
	            // constructor is set up.  However, I included it as a
	      {     // safeguard (in case some other method calls this one)
	            length = 0;
	            firstC = null;
	            lastC = null;
	      }
	}

	// Create a new MyStringBuilder initialized with the chars in array s
	public MyStringBuilder2(char [] s)
	{
		if(s != null && s.length > 0)
			makeBuilder(s, 0);
		else{
			length = 0;
			firstC = null;
			lastC = null;
		}
	}

	// Recursive method to set up a new MyStringBuilder2 from a String
	private void makeBuilder(char[] s, int pos)
	{
	      // Recursive case – we have not finished going through the String
	      if (pos < s.length-1)
	      {
	            // Note how this is done – we make the recursive call FIRST, then
	            // add the node before it.  In this way the LAST node we add is
	            // the front node, and it enables us to avoid having to make a
	            // special test for the front node.  However, many of your
	            // methods will proceed in the normal front to back way.
	            makeBuilder(s, pos+1);
	            firstC = new CNode(s[pos], firstC);
	            length++;
	      }
	      else if (pos == s.length-1) // Special case for last char in String
	      {                             // This is needed since lastC must be
	                                    // set to point to this node
	            firstC = new CNode(s[pos]);
	            lastC = firstC;
	            length = 1;
	      }
	      else  // This case should never be reached, due to the way the
	            // constructor is set up.  However, I included it as a
	      {     // safeguard (in case some other method calls this one)
	            length = 0;
	            firstC = null;
	            lastC = null;
	      }
	}

	// Create a new empty MyStringBuilder
	public MyStringBuilder2()
	{
		length = 0;
		firstC = null;
		lastC = null;
	}

	// Append MyStringBuilder b to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder2 append(MyStringBuilder2 b)
	{
		if(b.length() == 0 || b == null) return this;
		else return traverseAppend(b.firstC, 0, b.length());
	}

	public MyStringBuilder2 traverseAppend(CNode node, int pos, int bLength){
		if(length > 0 && pos < bLength){
			lastC.next = node;
			lastC = node;
			length++;

			if(node.next == null) return this;
			else return traverseAppend(node.next, pos+1, bLength);
		} else if(length == 0){
			firstC = node;
			lastC = firstC;
			length++;
			return traverseAppend(node.next, pos+1, bLength);
		} else{
			return this;
		}
	}

	// Append String s to the end of the current MyStringBuilder, and return
	// the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder2 append(String s)
	{
		if(s.length() == 0 || s == null) return this;
		else return traverseAppend(s, s.length(), 0);
		/*else{
			/*if(length == 0){
				CNode newNode = new CNode(s.charAt(0));
				firstC = newNode;
				length++;
			}
			char[] string = s.toCharArray();
			return append(string);
		}*/
	}

	public MyStringBuilder2 traverseAppend(String s, int sLength, int pos){
		CNode newNode = new CNode(s.charAt(pos));
		System.out.println("STRING APPEND");
		if(length > 0 && pos < sLength){
			System.out.println("(1) APPENDING " + newNode.data + " TO " + lastC.data);
			lastC.next = newNode;
			lastC = newNode;
			length++;
			System.out.println("LASTC: " + lastC.data);

			if(pos == sLength-1) return this;
			else return traverseAppend(s, sLength, pos+1);
		} else if(length == 0){
			firstC = newNode;
			lastC = firstC;
			length++;
			return traverseAppend(s, sLength, pos+1);
		} else{
			return this;
		}
	}

	// Append char array c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder2 append(char [] c)
	{
		if(c.length == 0 || c == null) return this;
		else return traverseAppend(c, 0);
	}

	public MyStringBuilder2 traverseAppend(char[] c, int pos){
		CNode newNode = new CNode(c[pos]);

		if(length > 0 && pos < c.length){
			lastC.next = newNode;
			lastC = newNode;
			length++;
			System.out.println("LASTC: " + lastC.data);

			if(pos == c.length-1) return this;
			else return traverseAppend(c, pos+1);
		} else if(length == 0){
			firstC = newNode;
			lastC = firstC;
			length++;
			return traverseAppend(c, pos+1);
		} else{
			return this;
		}

		/*if(pos == c.length-1) return append(c[c.length-1]);
		else if(pos < c.length-1){
			append(c[pos]);
			return traverseAppend(c, pos+1);
		}

		return this;*/
	}

	// Append char c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder2 append(char c)
	{
		CNode newNode = new CNode(c);
		System.out.println("ADDING A CHARACTER: " + newNode.data);

		if(length > 0){
			lastC.next = newNode;
			lastC = newNode;
			length++;
			System.out.println("LASTC: " + lastC.data);

			return this;
		} else if(length == 0){
			firstC = newNode;
			lastC = firstC;
			length++;

			return this;
		} else{
			return this;
		}
	}

	// Return the character at location "index" in the current MyStringBuilder.
	// If index is invalid, throw an IndexOutOfBoundsException.
	public char charAt(int index)
	{
		if(index > length-1 || index < 0) throw new IndexOutOfBoundsException();
		else return traversionRecursion(firstC, index, 0);
	}

	public char traversionRecursion(CNode node, int index, int pos){
		if(pos == index) return node.data;
		else return traversionRecursion(node.next, index, pos+1);
	}

	// Delete the characters from index "start" to index "end" - 1 in the
	// current MyStringBuilder, and return the current MyStringBuilder.
	// If "start" is invalid or "end" <= "start" do nothing (just return the
	// MyStringBuilder as is).  If "end" is past the end of the MyStringBuilder,
	// only remove up until the end of the MyStringBuilder. Be careful for
	// special cases!
	public MyStringBuilder2 delete(int start, int end)
	{
		if(end > length) end = length;

		if(start < 0 || end <= start) return this;
		else if(start == 0 && end == length){
			length = 0;
			firstC = null;
			lastC = null;
			return this;
		} else{
			return deleteRecurs(0, start, end, null, firstC, firstC.next, length);
		}
	}

	public MyStringBuilder2 deleteRecurs(int pos, int start, int end, CNode before, CNode node, CNode after, int leng){
		//This is for the deleteCharAt() method
		if(end == -1){
			//System.out.printf("pos: %d, start: %d, end: %d, before: %s, now: %s, after: %s, leng: %d, length: %d\n", pos, start, end, null, node.data, after.data, leng, length);
			if(start == length-1 && pos == length-3){
				lastC = node;
				lastC.next = null;
				length--;
				return this;
			} else if(pos == start){
				before.next = after;
				length--;
				return this;
			} else{
				return deleteRecurs(pos+1, start, end, node, after, after.next, leng);
			}
		}

		//The following is for the delete() method
		if(end >= leng){
			if(pos == start-1){
				lastC = node;
				lastC.next = null;
				length = start;
				return this;
			} else{
				return deleteRecurs(pos+1, start, end, before, after, after.next, leng);
			}
		} else if(pos < start) return deleteRecurs(pos+1, start, end, node, after, after.next, leng);
		else if(start == 0){
			if(pos == end) return this;

			firstC = firstC.next;
			length--;
			return deleteRecurs(pos+1, start, end, before, after, after.next, leng);
		} else if(pos >= start && pos <= end && end != leng){
			if(pos == end) return this;

			before.next = after;
			length--;
			return deleteRecurs(pos+1, start, end, before, after, after.next, leng);
		} else{
			return this;
		}
	}

	// Delete the character at location "index" from the current
	// MyStringBuilder and return the current MyStringBuilder.  If "index" is
	// invalid, do nothing (just return the MyStringBuilder as is).
	// Be careful for special cases!
	public MyStringBuilder2 deleteCharAt(int index)
	{
		if(index < 0 || index > length-1) return this; //Do nothing if index is invalid

		if(index == 0) firstC = firstC.next;
		else return deleteRecurs(0, index, -1, null, firstC, firstC.next, -1);

		return this;
	}

	// Find and return the index within the current MyStringBuilder where
	// String str first matches a sequence of characters within the current
	// MyStringBuilder.  If str does not match any sequence of characters
	// within the current MyStringBuilder, return -1.  Think carefully about
	// what you need to do for this method before implementing it.
	public int indexOf(String str)
	{
		if(str == null) return -1;
		else return traverseIndexOf(firstC, 0, str, 0, -1);
	}

	public int traverseIndexOf(CNode node, int pos, String str, int matches, int startIndex){
		if(matches == str.length()) return startIndex;
		else if(pos == length && matches != str.length()) return -1;
		else if(node.data == str.charAt(matches)){
			if(matches == 0) startIndex = pos;
			matches++;
			return traverseIndexOf(node.next, pos+1, str, matches, startIndex);
		}
		else return traverseIndexOf(node.next, pos+1, str, 0, -1);
	}

	// Insert String str into the current MyStringBuilder starting at index
	// "offset" and return the current MyStringBuilder.  if "offset" ==
	// length, this is the same as append.  If "offset" is invalid
	// do nothing.
	public MyStringBuilder2 insert(int offset, String str)
	{
		if(offset == length) return append(str);
		else if(offset < 0 || offset > length) return this;
		else return traverseInsert(offset, str, 0, 0, firstC);
	}

	public MyStringBuilder2 traverseInsert(int offset, String str, int pos, int insertAmount, CNode node){
		if(offset == 0 && insertAmount < str.length()){
			CNode newNode;
			if(insertAmount == 0) newNode = new CNode(str.charAt(str.length()-1), firstC.next);
			else newNode = new CNode(str.charAt(str.length()-insertAmount-1), firstC.next);

			CNode oldFirst = firstC;
			firstC = newNode;
			firstC.next = oldFirst;
			return traverseInsert(offset, str, pos+1, ++insertAmount, null);
		} else{
			if(pos >= offset-1 && pos < offset+str.length()-1){
				CNode newNode;
				if(insertAmount == 0) newNode = new CNode(str.charAt(insertAmount));
				else newNode = new CNode(str.charAt(insertAmount));

				newNode.next = node.next;
				node.next = newNode;

				return traverseInsert(offset, str, pos+1, ++insertAmount, node.next);
			} else if(insertAmount == str.length()){
				length += str.length();
				return this;
			} else{
				return traverseInsert(offset, str, pos+1, insertAmount, node.next);
			}
		}
	}

	// Insert character c into the current MyStringBuilder at index
	// "offset" and return the current MyStringBuilder.  If "offset" ==
	// length, this is the same as append.  If "offset" is invalid,
	// do nothing.
	public MyStringBuilder2 insert(int offset, char c)
	{
		if(offset < 0 || offset > length) return this;
		else if(offset == length) return append(c);
		else if(offset == 0){
			CNode newNode = new CNode(c);

			newNode.next = firstC;
			firstC = newNode;
			return this;
		} else{
			return traverseInsert(offset, 0, c, firstC, firstC.next);
		}
	}

	public MyStringBuilder2 traverseInsert(int offset, int pos, char c, CNode now, CNode after){
		if(pos == offset){
			CNode newNode = new CNode(c);

			now.next = newNode;
			newNode.next = after;
			length++;
			return this;
		} else{
			return traverseInsert(offset, pos+1, c, after, after.next);
		}
	}

	// Insert char array c into the current MyStringBuilder starting at index
	// index "offset" and return the current MyStringBuilder.  If "offset" is
	// invalid, do nothing.
	public MyStringBuilder2 insert(int offset, char [] c)
	{
		if(offset < 0 || offset > length) return this;
		else if(offset == length) return append(c);
		else if(offset == 0 && c.length == 1){
			CNode newNode = new CNode(c[0]);

			newNode.next = firstC;
			firstC = newNode;
			return this;
		} else{
			return traverseInsert(offset, c, 0, 0, firstC);
		}
	}

	public MyStringBuilder2 traverseInsert(int offset, char[] c, int pos, int insertAmount, CNode node){
		if(offset == 0 && insertAmount < c.length){
			CNode newNode;
			if(insertAmount == 0) newNode = new CNode(c[c.length-1], firstC.next);
			else newNode = new CNode(c[c.length-insertAmount-1], firstC.next);

			CNode oldFirst = firstC;
			firstC = newNode;
			firstC.next = oldFirst;
			return traverseInsert(offset, c, pos+1, ++insertAmount, null);
		} else{
			if(pos >= offset-1 && pos < offset+c.length-1){
				CNode newNode;
				if(insertAmount == 0) newNode = new CNode(c[insertAmount]);
				else newNode = new CNode(c[insertAmount]);

				newNode.next = node.next;
				node.next = newNode;

				return traverseInsert(offset, c, pos+1, ++insertAmount, node.next);
			} else if(insertAmount == c.length){
				length += c.length;
				return this;
			} else{
				return traverseInsert(offset, c, pos+1, insertAmount, node.next);
			}
		}
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
	public MyStringBuilder2 replace(int start, int end, String str)
	{
		if(end > length) return replaceHelperEnd(start, end, str, null, null, 0, firstC, null, 0);

		if(start == 0 && end == length){
			firstC = null;
			lastC = null;
			length = 0;
			makeBuilder(str, 0);
			return this;
		} else if(start < 0 || end <= start) return this;
		else return replaceHelper(start, end, str, null, null, 0, firstC, null, 0);
	}

	public MyStringBuilder2 replaceHelper(int start, int end, String str, CNode begin, CNode prev, int pos, CNode node, CNode endNode, int replaceAmount){
		if(pos == start-1){
			return replaceHelper(start, end, str, node, prev, pos+1, node.next, endNode, replaceAmount);
		} else if(pos >= start && pos < end){
			CNode newNode = new CNode(str.charAt(pos-start));

			if(pos == start){
				begin.next = newNode;
				return replaceHelper(start, end, str, begin, newNode, pos+1, node.next, endNode, replaceAmount);
			} else if(pos-end == -1){
				prev.next = newNode;
				endNode = node.next;
				return replaceHelper(start, end, str, begin, newNode, pos+1, node.next, endNode, replaceAmount);
			} else{
				prev.next = newNode;
				return replaceHelper(start, end, str, begin, newNode, pos+1, node.next, endNode, replaceAmount);
			}
		} else if(pos >= end && replaceAmount+(end-start) < str.length()){
			if(pos == length-1){
				CNode newNode = new CNode(str.charAt(replaceAmount+(end-start)));
				prev.next = newNode;
				return replaceHelper(start, end, str, begin, newNode, pos+1, node.next, endNode, ++replaceAmount);
			} else{
				CNode newNode = new CNode(str.charAt(replaceAmount+(end-start)));
				prev.next = newNode;
				return replaceHelper(start, end, str, begin, newNode, pos+1, node.next, endNode, ++replaceAmount);
			}
		} else if(pos >= end && replaceAmount+(end-start) == str.length()){
			CNode newNode = new CNode(str.charAt(replaceAmount+(end-start)-1));

			prev.next = endNode;
			newNode.next = endNode;
			length = length + str.length()-(end-start);
			return this;
		} else{
			return replaceHelper(start, end, str, begin, prev, pos+1, node.next, endNode, replaceAmount);
		}
	}

	public MyStringBuilder2 replaceHelperEnd(int start, int end, String str, CNode begin, CNode prev, int pos, CNode node, CNode endNode, int replaceAmount){
		end = length;

		if(pos == start-1){
			return replaceHelperEnd(start, end, str, node, prev, pos+1, node.next, null, replaceAmount);
		} else if(pos >= start && replaceAmount < str.length()){
			CNode newNode = new CNode(str.charAt(pos-start));
			if(pos == start) begin.next = newNode;
			else prev.next = newNode;
			lastC = newNode;
			return replaceHelperEnd(start, end, str, begin, newNode, pos+1, null, null, ++replaceAmount);
		} else if(pos >= start && replaceAmount >= str.length()){
			return this;
		} else{
			return replaceHelperEnd(start, end, str, begin, prev, pos+1, node.next, null, replaceAmount);
		}
	}

	// Return as a String the substring of characters from index "start" to
	// index "end" - 1 within the current MyStringBuilder
	public String substring(int start, int end)
	{
		if(end < start || start < 0 || end > length) return new String();
		else return new String(gatherString(start, end, new char[end-start], 0, 0, firstC));
	}

	public char[] gatherString(int start, int end, char[] c, int arrLoc, int pos, CNode node){
		if(pos >= start && pos < end){
			c[arrLoc++] = node.data;
			return gatherString(start, end, c, arrLoc, pos+1, node.next);
		} else if(pos == end){
			return c;
		} else{
			return gatherString(start, end, c, arrLoc, pos+1, node.next);
		}
	}

	// Return the entire contents of the current MyStringBuilder as a String
	public String toString()
	{
		if(length == 0) return new String();
		else return getChar(firstC);
	}

	public String getChar(CNode node){
		System.out.println("GETTING " + node.data + " , LASTC: " + lastC.data);
		if(node == lastC) return String.valueOf(node.data);
		else return (node.data + getChar(node.next));
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
