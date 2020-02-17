package project2;

/**
 * This is a singly linked list class that has an embedded singly linked node class which has a generic element
 * and reference to the next node in the list.
 * This class extends Comparable<E>. 
 * @author Chrissy Jeon (jj2174)
 */
public class sLinkedList<E extends Comparable<E>> {
	
	/**
	 * 
	 * This is an embedded singly linked node class.
	 * This has a generic element and reference to the next node in the list.
	 * This class extends Comparable<E>. 
	 * @author Chrissy Jeon (jj2174)
	 */
	private static class Node<E extends Comparable<E>> {
		private E element;
		private Node<E> next;

		/**
		 * Node constructor with one parameter.
		 * Sets the element and next of the element to null.
		 * @param element (element of the node)
		 */
		public Node (E element) {
			this.element = element;
			this.next = null;
		}
		
		/**
		 * Node constructor with two parameters.
		 * Sets the first parameter to element and the second parameter to next of the element.
		 * @param e (element)
		 * @param n (next of the element)
		 */
		public Node(E e, Node<E> n) {
			element = e;
			next = n;
		}

		/**
		 * Returns element
		 * @return element
		 */
		public E getElement() {
			return element;
		}

		/**
		 * Returns next of the element
		 * @return next (next of the element)
		 */
		public Node<E> getNext() {
			return next;
		}

		/**
		 * Sets next element
		 * @param n (becomes next element)
		 */
		public void setNext(Node<E> n) {
			next = n;
		}
		
		/**
		 * Compares two nodes and returns int value.
		 * @param n (Node)
		 * @return int (if 0 : same, 1 : bigger, -1: smaller)
		 */
		public int compareTo(Node<E> n) {
			return this.getElement().compareTo(n.getElement());
		}
	}
	
	
	private Node<E> head = null;
	private Node<E> tail = null;
	private int size = 0;
	
	/**
	 * No argument constructor of sLinkedList class
	 */
	public sLinkedList() {
		}
	
	/**
	 * Checks whether the singly linked list is empty
	 * @return boolean (true : empty, false: not empty)
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Adds first to the singly linked list
	 * @param element (element you want to add first)
	 */
	public void addFirst(E element) {
		if (head == null && tail == null) {
			head = new Node<E>(element,null);
			tail = head;
			size++;
			return;
		}
		Node<E> newest = new Node<E>(element,head);
		if (size == 0) {
			tail = head;
		}else {
			newest.next = head.next;
			head = newest;
		}size++;
		}

	/**
	 * Adds last to the singly linked list
	 * @param element (element you want to add last)
	 */
	public void addLast(E element) {
		if (head == null && tail == null) {
			head = new Node<E>(element,null);
			tail = head;
			size++;
			return;
		}
		Node<E> newest = new Node<E>(element,null);
		if (tail == null) {
			tail = newest;
		}else {
			tail.next = newest;
			tail = tail.next;
		}size++;
	}

	/**
	 * Clears the whole singly linked list
	 */
	private void clear() {
		head = null;
		size = 0;
	}
	
	/**
	 * Returns the node element at index
	 * @param index
	 * @return E (Node element at index)
	 */
	public E get(int index) {
		Node<E> curr = head;
		int count = 0;
		while (curr != null) {
			if (count == index) {
				return curr.element;
			}count++;
			curr = curr.next;
		}
		assert(false);
		return null;
	}

	/**
	 * Returns the size of the singly linked list
	 * @return size (size of the list)
	 */
	public int size() {
		return size;
	}

	/**
	 * This checks whether the singly linked list is sorted and returns boolean value
	 * @return boolean (true : sorted, false : not sorted)
	 */
	private boolean isSorted() {
		for (int i = 0; i < this.size() - 1; i++) {
			if (this.get(i).compareTo(this.get(i + 1)) > 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Calls on private mergeSort() method
	 */
	public void sort() {
		Node<E> n = head;
		head = this.mergeSort(n);
	}
	
	/**
	 * This is one of the methods used in mergeSort() method.
	 * This takes in two node heads and merges the list sorted by using comepareTo method. 
	 * @param left (Node head of the first list you want to sorted merge)
	 * @param right (Node head of the second list you want to sorted merge)
	 * @return Node<E> (merged sorted list)
	 */
	private Node<E> sortedMerge(Node<E> left, Node<E> right) {
		Node<E> result = null;
		
		if (left == null) {
			return right;
		}if (right == null) {
			return left;
		}if (left.compareTo(right) <= 0) {
			result = left;
			result.next = sortedMerge(left.next,right);
		}else {
			result = right;
			result.next = sortedMerge(left,right.next);
		}return result;
	}

	/**
	 * This is a recursive method that takes in a Node head and sorts the nodes in the singly linked list
	 * @param n (Node head of the singly linked list that you want to merge sort)
	 * @return Node<E> (sorted list)
	 */
	private Node<E> mergeSort(Node<E> n){
		if (n == null || n.next == null) {
			return n;
		}
		Node<E> middle = getMiddle(n);
		Node<E> first = n;
		Node<E> nextofmiddle = middle.next;
		
		middle.next = null;
		Node<E> left = mergeSort(first);
		Node<E> right = mergeSort(nextofmiddle);
		
		Node<E> sortedList = sortedMerge (left, right);
		return sortedList;
	}
	
	/**
	 * This is one of the methods used in mergeSort() method.
	 * This takes in the head of the node and finds the middle using a fast pointer and a slow pointer.
	 * @param n (Node head of the singly linked list that you want to find middle)
	 * @return Node<E> (middle of the list)
	 */
	private Node<E> getMiddle(Node<E> n) {
		
		if (n == null) {
			return n;
		}
		Node<E> fast = n.next;
		Node<E> slow = n;
		
		while (fast != null) {
			fast = fast.next;
			if (fast != null) {
				slow = slow.next;
				fast = fast.next;
			}
		}return slow;
	}
	
	/**
	 * Calls on linear search method to check whether the singly linked list "contains" E
	 * @param find (What you want to find)
	 * @return (true : if contains, false : if doesn't contain)
	 */
	public boolean contains(E find) {
		return linearSearch(this.head,find);
	}
	
	/**
	 * Does linear search on the object list and returns boolean
	 * @param n (head of the singly linked list you want to perform linear search on)
	 * @param find (What you want to find in the Object list)
	 * @return (true : if exist, false : if doesn't exist)
	 */
	private boolean linearSearch(Node<E> n, E find) {
		Node current = n;
		while (current != null) {
			if (current.element.compareTo(find) == 0) {
				return true;
			}
			current = current.next;
			}
			return false;
			}
	
	/**
	 * Returns the elements in singly linked list as a string
	 * @return String (string of the list elements)
	 */
	public String toString() {
		Node<E>headref = this.head;
		String strOutput = "";
		while (headref != null)  
        { 
            strOutput += headref.element + "\n"; 
            headref = headref.next; 
        } return strOutput;
	}
	
}
