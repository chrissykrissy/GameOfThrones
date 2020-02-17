package project4;

/**
 * This class has a min-heap using MyArrayList structure.
 * This class extends Comparable<E>
 * @author Chrissy Jeon (jj2174)
 */
public class pqMyArrayList <E extends Comparable <E>>{
	private MyArrayList<E> heap;
	
	/**
	 * This is a pqMyArrayList constructor without any parameters.
	 * This creates a heap using MyArrayList<E>
	 */
	public pqMyArrayList() {
		heap = new MyArrayList<E>();
	}
	
	/**
	 * This is a getter method for heap
	 * @return MyArrayList<E> heap
	 */
	public MyArrayList<E> getHeap(){
		return heap;
	}
	
	/**
	 * Returns the index of the parent of node j
	 * @param j (index of node j)
	 * @return (j-1)/2 (index of parent)
	 */
	protected int parent(int j) {
		return (j-1)/2;
		}
	
	/**
	 * Returns the index of the left child of node j
	 * @param j (index of node j)
	 * @return 2*j+1 (index of left child)
	 */
	protected int left(int j) {
		return 2*j+1;
		}
	
	/**
	 * Returns the index of the right child of node j
	 * @param j (index of node j)
	 * @return 2*j+2 (index of right child)
	 */
	protected int right(int j) {
		return 2*j+2;
		}
	
	/**
	 * Returns boolean when node j has left
	 * @param j (index of node j)
	 * @return boolean (true : if left child exists, false : if left child does not exist)
	 */
	protected boolean hasLeft(int j) {
		return left(j)<heap.size();
		}
	
	/**
	 * Returns boolean when node j has right
	 * @param j (index of node j)
	 * @return boolean (true : if right child exists, false : if right child does not exist)
	 */
	protected boolean hasRight(int j) {
		return right(j)<heap.size();
		}
	
	/**
	 * This method swaps two locations i and j in the list
	 * @param i (index of element)
	 * @param j (index of element)
	 */
	private void swap(int i, int j) {
		E temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
		
		//get position and update soldier.pos 
	}
	
	/**
	 *This method moves the element at index j up to restore the heap property
	 * @param j (index of the element)
	 */
	public void upHeap(int j) { //last position 
		while (j>0) {
			int p = parent(j);
			if (heap.get(j).compareTo(heap.get(p))>=0) {
				break;
			}
			swap(j,p);
			j=p;
		}
	}

	/**
	 * This method moves the element at index j down to restore the heap property
	 * @param j (index of the element)
	 */
	public void downHeap(int j) {
		while (hasLeft(j)) {
			int leftIndex = left(j);
			int smallChildIndex = leftIndex;
			if (hasRight(j)) {
				int rightIndex = right(j);
				if (heap.get(leftIndex).compareTo(heap.get(rightIndex))>0) {
					smallChildIndex = rightIndex;
				}
			}if (heap.get(smallChildIndex).compareTo(heap.get(j))>=0) {
				break;
			}swap(j,smallChildIndex);
			j = smallChildIndex;
		}
	}
	
	/**
	 * Insert an element into the priority queue and keeps in heap order
	 * @param newest (the element to insert)
	 * @return E (element that was inserted)
	 */
	public E insert(E newest) {
		heap.add(newest);
		upHeap(heap.size()-1);
		heap.size++;
		return newest;
		
	}
	
	/**
	 * Removes the element with the minimal key (highest priority) from the heap. This is at [0].
	 * @return E (element with the minimal key)
	 */
	public E remove() {
		if (heap.isEmpty()) {
			return null;
		}E remove = heap.get(0);
		swap(0,heap.size()-1);
		heap.remove(heap.size()-1);
		heap.size--;
		downHeap(0);
		return remove;
	}
	
	/**
	 * Return the element with the minimum key, without removing it from the queue.
	 * @return E (element with the minimum key)
	 */
	public E peek() {
		if (heap.size()<=0) {
			return null;
		}
		return heap.get(0);
	}
}
