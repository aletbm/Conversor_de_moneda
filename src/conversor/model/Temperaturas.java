package conversor.model;

public class Temperaturas {
	protected double tempIn;
	protected double tempOut;
	
	public double getTempIn() {
		return tempIn;
	}

	public void setTempIn(double tempIn) {
		this.tempIn = tempIn;
	}

	public double getTempOut() {
		return tempOut;
	}

	public Temperaturas() {}
	
	public Temperaturas(double tempIn) {
		this.tempIn = tempIn;
	}
	
	public double convertir() {
		return tempIn;
	}
}
