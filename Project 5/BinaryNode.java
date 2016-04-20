public class BinaryNode<T>{
	private BinaryNode<T> leftNode; //Left Child
	private BinaryNode<T> rightNode; //Right Child
	private T internalData; //Character data
	private String path; //Path of this node in the tree

	public BinaryNode(){
		leftNode = null;
		rightNode = null;
		internalData = null;
		path = null;
	}

	public BinaryNode(T c){
		leftNode = null;
		rightNode = null;
		internalData = c;
		path = null;
	}

	//Set the left child
	public void insertLeft(BinaryNode<T> data){
		leftNode = data;
	}

	//Set the right child
	public void insertRight(BinaryNode<T> data){
		rightNode = data;
	}

	//Set the node data
	public void changeData(T data){
		internalData = data;
	}

	//Get the node data
	public T getData(){
		return internalData;
	}

	//Get the node's left child
	public BinaryNode<T> getLeftNode(){
		return leftNode;
	}

	//Get the node's right child
	public BinaryNode<T> getRightNode(){
		return rightNode;
	}

	//Set the path of this node in the tree
	public void setPath(String newPath){
		path = newPath;
	}

	//Get the path of this node in the tree
	public String getPath(){
		return path;
	}
}
