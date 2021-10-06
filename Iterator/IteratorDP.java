package aPatternsCode;
import java.util.Iterator;

class IntRange implements Iterable<Integer> {
	private final int lower, upper;
	public IntRange(int lower, int upper) {
		this.lower = lower;
		this.upper = upper;
	}
	@Override public Iterator<Integer> iterator() {
		return new Iterator<>() {
			int current = lower;
			@Override public boolean hasNext() {
				return current <= upper;
			}
			@Override public Integer next() {
				return current++;
			}
			@Override public void remove() {
				throw new RuntimeException("Not implemented");
			}
		};
	}
}

public class IteratorDP {
	public static void main(String[] args) {
		for (int n : new IntRange(2, 10))
			System.out.println(n);
	}
}
