package aPatternsCode;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

class WeatherMeasurer {
	public static String TEMP = "Temperature";
	private int temperature = 25;
	private int pressure = 30;
	private int humidity = 50;
	PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public void addObserverForTemperatureOnly(PropertyChangeListener observer) {
		pcs.addPropertyChangeListener(TEMP, observer);
	}
	public void addObserverForAllEvents(PropertyChangeListener observer) {
		pcs.addPropertyChangeListener(observer);
	}
	public void setTemperature(int newTemperature) {
		var oldTemperature = temperature;
		temperature = newTemperature;
		pcs.firePropertyChange(TEMP, oldTemperature, newTemperature);
	}
	public void setPressure(int newPressure) {
		var oldPressure = pressure;
		pressure = newPressure;
		pcs.firePropertyChange("Pressure", oldPressure, newPressure);
	}
	public void setHumidity(int newHumidity) {
		var oldHumidity = humidity;
		humidity = newHumidity;
		pcs.firePropertyChange("Pressure", oldHumidity, newHumidity);
	}
	// ...
}

class MaxMinTemp implements PropertyChangeListener {
	public MaxMinTemp(WeatherMeasurer subject) {
		subject.addObserverForTemperatureOnly(this);
	}
	@Override public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("Temperature changed from " + evt.getOldValue() + " to " + evt.getNewValue());
	}
	// ...
}

class Forecaster implements PropertyChangeListener {
	public Forecaster(WeatherMeasurer subject) {
		subject.addObserverForAllEvents(this);
	}
	@Override public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("Property " + evt.getPropertyName() + " changed from " + evt.getOldValue() + " to " + evt.getNewValue());
	}
	// ...
}

public class ObserverDP {
	public static void main(String[] args) {
		var wm = new WeatherMeasurer();
		new MaxMinTemp(wm);
		new Forecaster(wm);
		wm.setTemperature(26);
		wm.setPressure(31);
	}
}
