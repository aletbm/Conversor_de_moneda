package conversor.controller;

import conversor.model.CtoF;
import conversor.model.CtoK;
import conversor.model.FtoC;
import conversor.model.FtoK;
import conversor.model.KtoC;
import conversor.model.KtoF;
import conversor.model.Temperaturas;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.TextField;

public class chartControlTemp extends chartControl{

	private AreaChart<Number, Number> chart;
	private NumberAxis xAxis;
	private NumberAxis yAxis;

	public chartControlTemp(AreaChart<Number, Number> chart, NumberAxis xAxis, NumberAxis yAxis, String In, String Out, String valor, TextField result) {
		super(In, Out, valor, result);
		this.chart = chart;
		this.xAxis = xAxis;
		this.yAxis = yAxis;
	}

	public boolean updateChart() {
		if(super.Out != null && super.In != null && !super.valor.isBlank() && super.onlyNumbers) {
		
		double valor = Double.parseDouble(super.valor);
		Temperaturas tipoConversion = new Temperaturas(valor);
		
		tipoConversion = this.TypeConversor(tipoConversion, valor, super.In, super.Out);
		
		super.result.setText(valor + " " + super.In + " = " + String.format("%.5g%n", tipoConversion.convertir()) + " " + super.Out);

		this.chart.getData().clear();
		
		Series<Number, Number> series = new Series<Number, Number>();
		
		for(int i=-5; i < 10; i++){
			tipoConversion.setTempIn(valor + i);
            series.getData().add(new Data<Number, Number>(valor + i, tipoConversion.convertir()));
		}
		
		tipoConversion.setTempIn(valor);
		
		this.CfgAxis(tipoConversion, valor, In, Out);
	    
        this.chart.getData().add(series);
        
        return true;
		}
		else {
			return false;
		}
	}
	
	public Temperaturas TypeConversor(Temperaturas tipoConversion, double valor, String In, String Out) {
		if(In == "°C") {
			if(Out == "°F") {
				tipoConversion = new CtoF(valor);
			}
			else if(Out == "°K") {
				tipoConversion = new CtoK(valor);
			}
		}
		else if(In == "°F") {
			if(Out == "°C") {
				tipoConversion = new FtoC(valor);
			}
			else if(Out == "°K") {
				tipoConversion = new FtoK(valor);
			}
		}
		else {
			if(Out == "°C") {
				tipoConversion = new KtoC(valor);
			}
			else if(Out == "°F") {
				tipoConversion = new KtoF(valor);
			}
		}
		
		return tipoConversion;
	}
	
	private void CfgAxis(Temperaturas tipoConversion, double valor, String In, String Out) {
		this.yAxis.setLabel(Out);
		this.yAxis.lookup(".axis-label").setStyle("-fx-label-padding: -50 0 0 0;");
		this.yAxis.setAutoRanging(false);
		this.yAxis.setLowerBound(tipoConversion.convertir() - 5);
		this.yAxis.setUpperBound(tipoConversion.convertir() + 5);
		this.yAxis.setTickUnit(0.5);
	    
		this.xAxis.setLabel(In);
		this.xAxis.setAutoRanging(false);
		this.xAxis.setLowerBound(valor - 5);
		this.xAxis.setUpperBound(valor + 5);
		this.xAxis.setTickUnit(0.5);
	}
}
