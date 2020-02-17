package project4;

import java.util.*;

/**
 * This is an ArrayList class that acts as a generic element container class.
 * This class extends Comparable<E> and ArrayList<E>
 * @author Chrissy Jeon (jj2174)
 */
public class MyArrayList<E extends Comparable<E>> extends ArrayList<E> {
	protected int size = 0;
	
	/**
	 * Overloads the sort method of the ArrayList class.
	 * Does not take any parameters and does not return anything. 
	 */
	public void sort() {
		Collections.sort(this);
	}

	/**
	 * Overrides contains method implemented in the ArrayList class.
	 * This method determines if the elements stored in the container are sorted, and if so, apply a binary search algorithm.
	 * If the elements are not in sorted order, the method should call the contains implemented in the ArrayList class (linear search)
	 * @param find
	 * @return boolean (true : if contains, false : if not contains)
	 */
	public boolean contains(E find) {
		if (this.isSorted() == true) {
			return binarySearch(this, 0, this.size() - 1, find);
		} else {
			return super.contains(find);
		}
	}

	/**
	 * This is a helper method for contains method.
	 * This method performs binary search with given list, position of start and end, and object to find. 
	 * @param a (ArrayList<E>)
	 * @param start (index of position to start searching)
	 * @param end (index of the position to end searching)
	 * @param find (object to find)
	 * @return boolean (true : if contains, false : if not contains)
	 */
	private boolean binarySearch(ArrayList<E> a, int start, int end, E find) {
		if (end >= start) {
			int mid = start + (end - start) / 2;
			if (a.get(mid).compareTo(find) == 0) {
				return true;
			}
			if (a.get(mid).compareTo(find) < 0) {
				return binarySearch(a, mid + 1, end, find);
			}
			if (a.get(mid).compareTo(find) > 0) {
				return binarySearch(a, start, mid - 1, find);
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * This method returns true or false if the elements stored in the collection are sorted or not sorted respectively.
	 * This uses the natural ordering of the elements.
	 * @return boolean (true : if sorted, false : if not sorted)
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
	 * Returns the information stored in MyArrayList as a string
	 * @return String (string of elements stored)
	 */
	public String toString() {
		String strOutput = "";
		for (int i = 0; i < this.size(); i++) {
			strOutput += this.get(i) + "\n";
		}
		return strOutput;
	}
}
