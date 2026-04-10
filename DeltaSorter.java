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
	public static <T extends Comparable<? super T>> void sort(List<T> list, int delta) {
	    if (delta < 0 || delta >= list.size())
	        throw new IllegalArgumentException("delta out of range");

	    BinaryMaxHeap<T> heap = new BinaryMaxHeap<>();

	    // Pre-load the first delta+1 elements
	    for (int i = 0; i <= delta; i++)
	        heap.add(list.get(i));

	    int outputIndex = 0;
	    // For each remaining element, extract max and add next element
	    for (int i = delta + 1; i < list.size(); i++) {
	        list.set(outputIndex++, heap.extractMax());
	        heap.add(list.get(i));
	    }

	    // Drain the heap
	    while (heap.size() > 0)
	        list.set(outputIndex++, heap.extractMax());
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
	public static <T> void sort(List<T> list, int delta, Comparator<? super T> cmp) {
	    if (delta < 0 || delta >= list.size())
	        throw new IllegalArgumentException("delta out of range");

	    BinaryMaxHeap<T> heap = new BinaryMaxHeap<>(cmp);

	    // Pre-load the first delta+1 elements
	    for (int i = 0; i <= delta; i++)
	        heap.add(list.get(i));

	    int outputIndex = 0;
	    // For each remaining element, extract max and add next element
	    for (int i = delta + 1; i < list.size(); i++) {
	        list.set(outputIndex++, heap.extractMax());
	        heap.add(list.get(i));
	    }

	    // Drain the heap
	    while (heap.size() > 0)
	        list.set(outputIndex++, heap.extractMax());
	}
}