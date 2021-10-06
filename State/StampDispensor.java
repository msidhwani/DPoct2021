package temp;
class HasCoins {
	int currentAmount;
	public static final int COST_OF_STAMP = 20;
	private void increment(int amount) {
		currentAmount += amount;
		if (currentAmount >= COST_OF_STAMP) {
			System.out.println("Dispense stamp");
			currentAmount -= COST_OF_STAMP;
		}
	}
	public void FiveCentsAdded() { increment(5); }
	public void TenCentsAdded() { increment(10); }
	public void TwentyCentsAdded() {increment(20);}
}

public class StampDispensor {
	HasCoins hc = new HasCoins();
	public static void main(String[] args) {
		var sd = new StampDispensor();
		sd.hc.FiveCentsAdded();
		sd.hc.TenCentsAdded();
		sd.hc.TenCentsAdded();
	}
}
