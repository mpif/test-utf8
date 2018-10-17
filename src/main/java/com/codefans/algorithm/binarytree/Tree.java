package com.codefans.algorithm.binarytree;

public class Tree {
	// -------------------------------
	// property
	// -------------------------------
	private Node root;

	// -------------------------------
	// API
	// -------------------------------
	public Node find(int key) {
		if (root == null) {
			return null; // input control
		}
		Node current = root; // start at the root

		while (current.iData != key) {
			if (key < current.iData) { // go left
				current = current.leftChild;
			} else { // go right
				current = current.rightChild;
			}
			if (current == null) { // base case: if no child
				return null;
			}
		} // end while

		return current;
	}

	public void insert(int id, double dd) {
		// found the node like the find method where to insert until this node
		// has no child ,
		// then insert the insertNode as this node's child
		Node insertNode = new Node(id, dd);

		Node current = root;
		Node parent = null;

		if (root == null) // no node in tree
			root = insertNode;
		else {
			while (true) {
				parent = current;
				if (id <= current.iData) {
					current = current.leftChild;
					if (current == null) {
						parent.leftChild = insertNode;
						return;
					}
				} // end if
				else {
					current = current.rightChild;
					if (current == null) {
						parent.rightChild = insertNode;
						return;
					}
				} // end else
			} // end while
		} // end else
	}

	/**
	 * finding the node you want to delete then considering three cases: 1. The
	 * node to be deleted is a leaf (has no children). 2. The node to be deleted
	 * has one child. 3. The node to be deleted has two children.
	 * 
	 * @param key
	 *            delete node with given key
	 * @return if the key exit and deletes it, return true, otherwise return
	 *         false
	 */
	public boolean delete(int key) {
		Node current = root; // record the delete node
		Node parent = root; // parent of current node
		boolean isLeftChild = true; // if current is parent's left
									// child,isLeftChild equals true, otherwise
									// false

		// find the node with key
		while (current.iData != key) {
			parent = current;
			if (key < current.iData) {
				isLeftChild = true;
				current = current.leftChild;
			} // end if
			else {
				isLeftChild = false;
				current = current.rightChild;
			} // end else
			if (current == null)
				return false; // didn't find
		} // end while

		// found node to delete
		// ---case 1: The node to be deleted is a leaf (has no children).
		// specialized situation: the delete node is the root
		if (current.leftChild == null && current.rightChild == null) {
			if (current == root) // if the found node is root
				root = null;
			else if (isLeftChild)
				parent.leftChild = null;
			else
				parent.rightChild = null;
		} // end if

		// ---case 2: The node to be deleted has one child.
		// specialized situation: the node to be deleted may be the root, in
		// which case it has no parent and is simply replaced by the appropriate
		// subtree.
		else if (current.rightChild == null) // if no right child,replace with
												// left subtree
		{
			if (current == root)
				root = current.leftChild;
			else if (isLeftChild)
				parent.leftChild = current.leftChild;
			else
				parent.rightChild = current.rightChild;
		} // end else if
		else if (current.leftChild == null) // if no left chuld
		{
			if (current == root)
				root = current.rightChild;
			else if (isLeftChild)
				parent.leftChild = current.leftChild;
			else
				parent.rightChild = current.rightChild;
		} // end else if

		// ---base case3: The node to be deleted has two children.
		else {
			// get successor of node to delete(current)
			Node successor = getSuccessor(current);

			// connect parent of current to successor instead
			if (current == root)
				root = successor;
			else if (isLeftChild)
				parent.leftChild = successor;
			else
				parent.rightChild = successor;
			// connect successor to current's left child
			successor.leftChild = current.leftChild;
		} // end else

		return true;
	}

	/**
	 * find the successor which is the smallest of the set of nodes that are
	 * larger than the deleted node the algorithm of finding the successor:
	 * First, the program goes to the original node’s right child, which must
	 * have a key larger than the node. Then it goes to this right child’s left
	 * child (if it has one), and to this left child’s left child, and so on,
	 * following down the path of left children. The last left child in this
	 * path is the successor of the original node, as shown in Figure
	 * specialized situation: successor is the delete node's right child, so
	 * successor has no left child
	 * 
	 * @param delNode
	 *            the deleted node
	 * @return the successor node which has made connection already
	 */
	private Node getSuccessor(Node delNode) {
		Node successorParent = delNode;
		Node successor = delNode;
		Node current = delNode.rightChild; // go to right node
		while (current != null) {
			successorParent = successor;
			successor = current;
			current = current.leftChild; // go to left node
		} // end while

		// if successor not the delete node's right child, making connections
		if (successor != delNode.rightChild) {
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		} // end if
		return successor;
	}

	/**
	 * postOrder(先序遍历) 思想： step1: do something with the node step2: visit the
	 * node's left child step3: visit the node's right child base case: the node
	 * passed as an argument is null
	 * 
	 * @param localRoot
	 */
	public void traversePreOrder(Node localRoot) {
		if (localRoot != null) {
			System.out.println(localRoot.iData);
			traversePostOrder(localRoot.leftChild);
			traversePostOrder(localRoot.rightChild);
		} // end if
	}

	/**
	 * inOrder(中序遍历LCR:中序遍历可以得到由小到大的有序序列) 思想： step1: visit the node's left child
	 * step2: do something with the node step3: visit the node's right child
	 * base case: the node passed as an argument is null
	 * 
	 * @param localRoot
	 *            the node to visit
	 */
	public void traverseInOrder(Node localRoot) {
		if (localRoot != null) {
			traverseInOrder(localRoot.leftChild);
			System.out.println(localRoot.iData);
			traverseInOrder(localRoot.rightChild);
		} // end if
	}

	/**
	 * postOrder(后序遍历) 思想： step1: visit the node's left child step2: visit the
	 * node's right child step3: do something with the node base case: the node
	 * passed as an argument is null
	 * 
	 * @param localRoot
	 */
	public void traversePostOrder(Node localRoot) {
		if (localRoot != null) {
			traversePostOrder(localRoot.leftChild);
			traversePostOrder(localRoot.rightChild);
			System.out.println(localRoot.iData);
		} // end if
	}

	public Node minimum() {
		Node current = root;
		Node last = null;

		while (current != null) {
			last = current; // remember the node
			current = current.leftChild; // go to left child
		} // end while
		return last;
	}

}
