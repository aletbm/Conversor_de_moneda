package conversor.model;

public class CtoF extends Temperaturas{
	public CtoF(double tempIn) {
		super(tempIn);
	}
	
	@Override
	public double convertir() {
		this.tempOut = (tempIn-32)/1.8;
		return this.tempOut;
	}
}
