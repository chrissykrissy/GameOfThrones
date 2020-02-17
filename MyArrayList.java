package project1;

import java.util.*;

public class MyArrayList<E extends Comparable<E>> extends ArrayList<E> {

	public void sort() {
		Collections.sort(this);
	}

	public boolean contains(E find) {
		if (this.isSorted() == true) {
			return binarySearch(this, 0, this.size() - 1, find);
		} else {
			return super.contains(find);
		}
	}

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

	private boolean isSorted() {
		for (int i = 0; i < this.size() - 1; i++) {
			if (this.get(i).compareTo(this.get(i + 1)) > 0) {
				return false;
			}
		}
		return true;
	}
	
	public String toString() {
		String strOutput = "";
		for (int i = 0; i < this.size(); i++) {
			strOutput += this.get(i) + "\n";
		}
		return strOutput;
	}
}
