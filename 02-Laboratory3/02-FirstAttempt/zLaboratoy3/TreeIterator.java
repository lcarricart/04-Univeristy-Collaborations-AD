package zLaboratoy3;

import java.util.Iterator;

/* Since the tree is sorted, the iterator must visit nodes from smallest (left-most) to largest (right-most). 
 * This is an in-order traversion. The iterator starts from the left-most item in the tree (smallest) and moves
 * in such way that it looks for the following one (by in-order criteria).
 * 
 * The iterator should live as long as the sorted tree lives. It makes no sense without the SortedBinaryTree.
 * 
 * Why constructing the key with a Tree and not with a root? Imagine I create an Iterator and start walking through 
 * the tree. Suddenly, someone else calls tree.insert() and adds a new node while you are walking. <The Crash> the 
 * iterator might get confused or point to a null value unexpectedly.
 */
public class TreeIterator implements Iterator<Node> {
	private SortedBinaryTree<Node> myTree;
	private Node nextNode;
	
	public TreeIterator(SortedBinaryTree<Node> myTree) {
		this.myTree = myTree;
		this.nextNode = myTree.min();
	}
	
	@Override
	public boolean hasNext() {
		if (nextNode != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Node next() {
		Node result;
		
		if (this.hasNext()) {
			result = nextNode;
			nextNode = myTree.succ(nextNode);
			return result;
		} else {
			return null;
		}
	}

}
