package aPatternsCode;
//Alternate method for Observer Design Pattern that is now deprecated since Java 9
import java.util.Observable;
import java.util.Observer;

class WeatherMeasurer1 extends Observable {
	public void change() {
		setChanged();
		notifyObservers();
	}
	int getTemperature() {
		return 25;
	}
}
class MaxMinTemp1 implements Observer {
	WeatherMeasurer1 wm;
	public MaxMinTemp1(WeatherMeasurer1 wm) {
		this.wm = wm;
		wm.addObserver(this);
	}
	@Override public void update(Observable o, Object arg) {
		System.out.printf("Temp changed to %d%n", wm.getTemperature());
	}
}
class Forecaster1 implements Observer {
	WeatherMeasurer1 wm;
	public Forecaster1(WeatherMeasurer1 wm) {
		this.wm = wm;
		wm.addObserver(this);
	}
	@Override public void update(Observable o, Object arg) {
		System.out.printf("Forecast changed for temp %d%n", wm.getTemperature());
	}
}

public class ObserverDP1 {
	public static void main(String[] args) {
		WeatherMeasurer1 wm = new WeatherMeasurer1();
		MaxMinTemp1 m1 = new MaxMinTemp1(wm);
		Forecaster1 m2 = new Forecaster1(wm);
		wm.change();
	}
}
