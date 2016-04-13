public class BinaryNode<T>{
	private BinaryNode<T> leftNode;
	private BinaryNode<T> rightNode;
	private char internalData;
	private String path;

	public BinaryNode(){
		leftNode = null;
		rightNode = null;
		internalData = '\0';
		path = null;
	}

	public BinaryNode(char c){
		leftNode = null;
		rightNode = null;
		internalData = c;
		path = null;
	}

	public BinaryNode(BinaryNode<T> left, BinaryNode<T> right, BinaryNode<T> parent, Character data){
		leftNode = left;
		rightNode = right;
		internalData = data;
		path = null;
	}

	public void insertLeft(BinaryNode<T> data){
		leftNode = data;
	}

	public void insertRight(BinaryNode<T> data){
		rightNode = data;
	}

	public void changeData(char data){
		internalData = data;
	}

	public char getData(){
		return internalData;
	}

	public BinaryNode<T> getLeftNode(){
		return leftNode;
	}

	public BinaryNode<T> getRightNode(){
		return rightNode;
	}

	public void setPath(String newPath){
		path = newPath;
	}

	public String getPath(){
		return path;
	}
}
