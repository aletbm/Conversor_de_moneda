package conversor.model;

public class KtoF  extends Temperaturas{
	public KtoF(double tempIn) {
		super(tempIn);
	}
	
	@Override
	public double convertir() {
		this.tempOut = (1.8*(tempIn - 273.15)) + 32;
		return this.tempOut;
	}
}
