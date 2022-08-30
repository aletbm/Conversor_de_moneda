package conversor.model;

public class FtoC extends Temperaturas{
	public FtoC(double tempIn) {
		super(tempIn);
	}
	
	@Override
	public double convertir() {
		this.tempOut = tempIn*1.8 + 32;
		return this.tempOut;
	}
}
