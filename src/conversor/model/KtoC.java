package conversor.model;

public class KtoC  extends Temperaturas{
	public KtoC(double tempIn) {
		super(tempIn);
	}
	
	@Override
	public double convertir() {
		this.tempOut = tempIn + 273.15;
		return this.tempOut;
	}
}
