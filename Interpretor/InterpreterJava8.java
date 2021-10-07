package temp;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

class Product {
	int color, price, size;
	public Product(final int color, final int price, final int size) {
		super();
		this.color = color;
		this.price = price;
		this.size = size;
	}
	@Override public String toString() {
		return String.format("color=%d price=%d size=%d", color,
		        price, size);
	}
}

class ProductFinder {
	List<Product> all = new LinkedList<Product>();
	List<Product> selectBy(Predicate<Product> condition) {
		return all.stream().filter(condition).toList();
	}
}

public class InterpreterJava8 {
	private static void printAllProducts(final List<Product> ps) {
		int i = 0;
		for (Product p : ps)
			System.out.printf("%d %s%n", ++i, p);
	}
	public static void main(final String[] args) {
		ProductFinder pf = new ProductFinder();
		pf.all.add(new Product(10, 100, 3));
		pf.all.add(new Product(11, 500, 3));
		pf.all.add(new Product(12, 400, 3));
		//printAllProducts(pf.belowPriceAvoidingAColor(450, 12));
		Predicate<Product> colorSpec = p -> p.color == 12;
		Predicate<Product> belowPrice = p -> p.price < 450;
		printAllProducts(pf.selectBy(belowPrice.and(colorSpec.negate())));
	}
}
