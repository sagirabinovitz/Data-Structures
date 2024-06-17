package il.ac.telhai.ds.trees;

public class BinarySearchTree<T extends Comparable<T>> {

	BstNode root;
	int size;

	// Binary Search Tree Node
	class BstNode {
		T val;
		BstNode left, right;

		public BstNode(T val) {
			this.val = val;
			left = null;
			right = null;
		}
	}

	private BstNode minimum(BstNode node){
		while(node.left!=null){
			node=node.left;
		}
		return node;
	}

	private BstNode findSuccessor(T val,BstNode node,BstNode root){
		if(root == null) {
			return node;
		}
		if(root.val.compareTo(val) == 0) {
			if(root.right != null) {
				return minimum(root.right);
			}
		}
		else if(val.compareTo(root.val) < 0) {
			node = root;
			return findSuccessor(val, node, root.left);
		}
		else {
			return findSuccessor(val, node, root.right);
		}
		return node;
	}


	public enum Direction {
		LEFT, RIGHT
	}

	// Returns the val given a path from the root.
	// Used for testing. DO NOT DELETE.
	public T getValInPath(Direction... direction) {
		BstNode node = root;
		for (Direction d : direction) {
			if (d.equals(Direction.LEFT) && node.left != null)
				node = node.left;
			else if (d.equals(Direction.RIGHT) && node.right != null)
				node = node.right;
			else
				return null;
		}
		return node.val;
	}


	public BinarySearchTree() {
		root=null;
		size=0;
	}


	public int size() {
		return size;
	}


	public boolean add(T val) {
		if(contains(val)){
			return false;
		}
		size++;
		root = addRec(val,root);

		return true;
	}

	public BstNode addRec(T val, BstNode node){
		if(node==null){
			return new BstNode(val);
		}

		if(val.compareTo(node.val) < 0) {
			node.left = addRec(val,node.left);
		}

		else if(val.compareTo(node.val) > 0) {
			node.right = addRec(val,node.right);
		}

		return node;
	}

	/**
	 * Removes the object in the tree which is equal to the parameter.
	 * Nothing is done if not found
	 *
	 * @param val: the object to be looked for in the tree
	 * @return True, if the element was removed. Otherwise false.
	 */
	public boolean remove(T val) {
		if(!contains(val)){
			return false;
		}
		size--;
		root=removeRec(val,root);
		return true;
	}

	public BstNode removeRec(T val,BstNode node){
		if (node == null)
			return null;
		if(val.compareTo(node.val) < 0) {
			node.left = removeRec(val,node.left);
		}
		else if(val.compareTo(node.val) > 0) {
			node.right = removeRec(val,node.right);
		}
		else {
			if(node.left == null) {
				return node.right;
			}
			else if(node.right == null) {
				return node.left;
			}
			T temp = root.right.val;
			while(root.right.left != null) {
				temp=root.right.left.val;
				root.right = root.right.left;
			}
			node.val=temp;
			node.right = removeRec(val,node.right);
		}
		return node;
	}




	public boolean contains(T val){
		return containsRec(val,root);
	}

	public boolean containsRec(T val,BstNode node) {
		if(node==null||size==0){
			return false;
		}
		if(val.compareTo(node.val)<0 ){
			return containsRec(val,node.left);
		}

		else if(val.compareTo(node.val)>0){
			return containsRec(val,node.right);
		}

		return true;
	}

	/**
	 * Looks for the minimal object in the tree, which is greater than or equal to
	 * the parameter.
	 *
	 * @param val: the object to be looked for in the tree
	 * @return a reference to the found object.
	 */

	public T findGe(T val) {
		if(contains(val)) {
			return val;
		}
		BstNode successor = findSuccessor(val, null, root);
		if(successor != null) {
			return successor.val;
		}
		return null;
	}

}
