package conversor.controller;

import javafx.scene.control.TextField;

public class chartControl {

	protected String Out;
	protected String In;
	protected String valor;
	protected boolean onlyNumbers;
	public boolean isOnlyNumbers() {
		return onlyNumbers;
	}

	protected TextField result;

	public chartControl(String In, String Out, String valor, TextField result) {
		this.Out = Out;
		this.In = In;
		this.valor = valor;
		this.onlyNumbers = this.valor.matches("[.0-9]+");
		this.result = result;
	}
	
	public String getOut() {
		return Out;
	}

	public void setOut(String out) {
		Out = out;
	}

	public String getIn() {
		return In;
	}

	public void setIn(String in) {
		In = in;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public TextField getResult() {
		return result;
	}

}
