package conversor.model;

public class FtoK  extends Temperaturas{
	public FtoK(double tempIn) {
		super(tempIn);
	}
	
	@Override
	public double convertir() {
		this.tempOut = ((5/9)*(tempIn-32))+273.15;
		return this.tempOut;
	}
}
