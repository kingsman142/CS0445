public class BinaryNode<T>{
	private BinaryNode<T> leftNode;
	private BinaryNode<T> rightNode;
	private BinaryNode<T> parentNode;
	private Character internalData;

	public BinaryNode(){
		leftNode = null;
		rightNode = null;
		parentNode = null;
		internalData = null;
	}

	public BinaryNode(Character c){
		leftNode = null;
		rightNode = null;
		parentNode = null;
		internalData = c;
	}

	public BinaryNode(BinaryNode<T> left, BinaryNode<T> right, BinaryNode<T> parent, Character data){
		leftNode = left;
		rightNode = right;
		parentNode = parent;
		internalData = data;
	}

	public void insertLeft(BinaryNode<T> data){
		leftNode = data;
	}

	public void insertRight(BinaryNode<T> data){
		rightNode = data;
	}

	public void insertParent(BinaryNode<T> data){
		parentNode = data;
	}

	public void changeData(Character data){
		internalData = data;
	}

	public Character getData(){
		return internalData;
	}

	public BinaryNode<T> getLeftNode(){
		return leftNode;
	}

	public BinaryNode<T> getRightNode(){
		return rightNode;
	}
}
