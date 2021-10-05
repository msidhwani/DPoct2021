package aPatternsCode;
import java.util.concurrent.ConcurrentHashMap;

class MultiTonManage {
	ConcurrentHashMap<String, Reusable1> pool = new ConcurrentHashMap<>();
	static int count = 1;
	private MultiTonManage() {}
	static final MultiTonManage instance = new MultiTonManage();
	public Reusable1 getFor(String key) {
		Reusable1 r = pool.get(key);
		if (r != null)
			return r;
		insertFor(key);
		return pool.get(key);
	}
	private synchronized void insertFor(String key) {
		if (pool.get(key) != null)
			return; // double locking
		pool.put(key, new Reusable1(count++));
	}
}
class Reusable1 {
	final int value;
	public Reusable1(int value) {
		this.value = value;
	}
	@Override public String toString() {
		return "Reusable [value=" + value + "]";
	}
}
public class SingletonMultiTon {
	public static void main(String[] args) {

	}
}
