package assign10;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * This class contains generic static methods for sorting a descending delta-sorted list.
 * The result of both methods is that the list will be in descending order.
 * 
 * @author CS 2420 course staff and ??
 * @version ??
 */
public class DeltaSorter {
	/**
	 * Fully sorts a descending, delta-sorted list using a BinaryMaxHeap.
	 * After completing, the provided list will contain the same items in descending order.
	 * This version uses the natural ordering of the elements.
	 * 
	 * @param list to sort that is currently delta-sorted and will be fully sorted
	 * @param delta value of the delta-sorted list
	 * @throws IllegalArgumentException if delta is less than 0 or greater than or
	 *         equal to the size of the list
	 */
	public static <T extends Comparable<? super T>> void sort(List<T> list, int delta){
		BinaryMaxHeap<T> heap = new BinaryMaxHeap<>();
		Iterator<T> iter = list.iterator(); int k = 0;
		while(iter.hasNext()) {
			heap.add(list.get(k)); k++; //Maybe?
			if(k > delta) {
				heap.add(list.get(k));
				list.add(k, heap.extractMax());
			}
		}
		for(int i = 0; i < heap.size(); i++) {
			list.add(i, heap.extractMax());
		}
		
	}
	
	/**
	 * Fully sorts a descending, delta-sorted list using a BinaryMaxHeap.
	 * After completing, the provided list will contain the same items in descending order.
	 * This version uses a provided comparator to order the elements.
	 * 
	 * @param list to sort that is currently delta-sorted and will be fully sorted
	 * @param delta value of the delta-sorted list
	 * @param cmp Comparator for ordering the elements
	 * @throws IllegalArgumentException if delta is less than 0 or greater than or
	 *         equal to the size of the list
	 */
	public static <T> void sort(List<T> list, int delta, Comparator<? super T> cmp){
		BinaryMaxHeap<T> heap = new BinaryMaxHeap<T>(cmp);
		heap.add(list.get(delta)); list.remove(delta);
		for(T item: list) {
			heap.add(item);
		}
		for(int i = 0; i < heap.size(); i++) {
			list.add(i, heap.extractMax());
		}
	}
}