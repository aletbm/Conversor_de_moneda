package conversor.model;

public class CtoK  extends Temperaturas{
	public CtoK(double tempIn) {
		super(tempIn);
	}
	
	@Override
	public double convertir() {
		this.tempOut = tempIn - 273.15;
		return this.tempOut;
	}
}
