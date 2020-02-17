package project4;

/**
 * This class is a simple extension of the sLinkedList class to be a priority queue.
 * This class extends Comparable<E> and sLinkedList<E>
 * @author Chrissy Jeon (jj2174)
 */
public class pqLinkedList <E extends Comparable<E>> extends sLinkedList<E>{
	
	/**
	 * This is a pqLinkedList constructor that calls on sLinkedList constructor
	 */
	public pqLinkedList() {
		super();
	}
	
	/**
	 * Inserts an element into the priority queue and keeps the heap order
	 * @param insert (element to insert)
	 */
	public void insert(E insert){
		if(this.head==null) {
			head = new Node<E>(insert);
			tail = head;
			size++;
			return;
		}
		
		Node<E> insertNode = new Node<>(insert);
		add(insertNode);
	}
	
	/**
	 * This is a helper method for insert.
	 * This compares the new element with the elements already inside the list and determines where to insert the new element
	 * @param insert (element to insert)
	 */
	private void add(Node<E> insert) {
		Node<E> curr = head;
		
		if(insert.compareTo(head) <=0) {
			this.addFirst(insert.getElement());
			return;
		}
		
		while (curr.getNext() != null && curr.getNext().getElement()!=null && curr.getNext().getElement().compareTo(insert.getElement())<=0) {
			curr = curr.getNext();
		}insert.setNext(curr.getNext());
		curr.setNext(insert);
		size++;
	}
	
	/**
	 * Removes the element in the priority queue with the minimal key (highest priority)
	 * @return E (element removed)
	 */
	public E remove() {
		Node<E> curr = head;

		if (head != null) {
			head = head.getNext();
			size--;
			return curr.getElement();
		}else {
			return null;
		}
	}
	
	/**
	 * Shows what the element with the minimal key (highest priority) without removing it.
	 * @return E (element with the minimal key)
	 */
	public E peek(){
		Node<E> curr = head;
		if (head != null) {
			return curr.getElement();
		}else {
			return null;
		}
	}
}
