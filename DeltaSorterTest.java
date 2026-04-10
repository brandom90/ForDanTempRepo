package assign10;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class DeltaSorterTest {

	private <T> List<T> listOf(T... items) {
		List<T> list = new ArrayList<>();
		for (T item : items)
			list.add(item);
		return list;
	}

	@Test
	public void negativeDeltaThrows() {
		assertThrows(IllegalArgumentException.class, () -> DeltaSorter.sort(listOf(5, 4, 3), -1));
	}

	@Test
	public void deltaEqualToSizeThrows() {
		assertThrows(IllegalArgumentException.class, () -> DeltaSorter.sort(listOf(5, 4, 3), 3));
	}

	@Test
	public void naturalOrderDeltaZeroAlreadySorted() {
		List<Integer> list = listOf(10, 8, 6, 4, 2);
		DeltaSorter.sort(list, 0);
		assertEquals(listOf(10, 8, 6, 4, 2), list);
	}

	@Test
	public void naturalOrderTypicalDelta() {
		List<Integer> list = listOf(8, 10, 9, 5, 7, 6, 1, 3, 4, 2);
		DeltaSorter.sort(list, 2);
		assertEquals(listOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1), list);
	}

	@Test
	public void naturalOrderMaxDelta() {
		List<Integer> list = listOf(1, 5, 4, 3, 2);
		DeltaSorter.sort(list, 4);
		assertEquals(listOf(5, 4, 3, 2, 1), list);
	}

	@Test
	public void naturalOrderWithDuplicates() {
		List<Integer> list = listOf(5, 5, 4, 3, 3, 2, 1);
		DeltaSorter.sort(list, 1);
		assertEquals(listOf(5, 5, 4, 3, 3, 2, 1), list);
	}

	@Test
	public void naturalOrderListSizePreserved() {
		List<Integer> list = listOf(8, 10, 9, 5, 7, 6, 1, 3, 4, 2);
		DeltaSorter.sort(list, 2);
		assertEquals(10, list.size());
	}

	@Test
	public void comparatorTypicalDelta() {
		List<Integer> list = listOf(8, 10, 9, 5, 7, 6, 1, 3, 4, 2);
		DeltaSorter.sort(list, 2, Comparator.naturalOrder());
		assertEquals(listOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1), list);
	}

	@Test
	public void comparatorNegativeDeltaThrows() {
		assertThrows(IllegalArgumentException.class,
				() -> DeltaSorter.sort(listOf(5, 4, 3), -1, Comparator.naturalOrder()));
	}
}
