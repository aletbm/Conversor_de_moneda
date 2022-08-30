package conversor.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.json.JSONObject;

import conversor.model.getDataAPI;
import conversor.model.getTimeSeriesAPI;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.TextField;

public class chartControlMonedas extends chartControl{
	
	private AreaChart<String, Number> chart;
	private CategoryAxis xAxis;
	private NumberAxis yAxis;
	private String DateNow;
	private String FirstDate;

	public chartControlMonedas(AreaChart<String, Number> chart, CategoryAxis xAxis, NumberAxis yAxis, String In, String Out, String valor, TextField result, String DateNow, String FirstDate) {
		super(In, Out, valor, result);
		this.chart = chart;
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.DateNow = DateNow;
		this.FirstDate = FirstDate;
	}

	public boolean updateChart() throws JSONException, IOException {
		if(super.Out != null && super.In != null && !super.valor.isBlank() && super.onlyNumbers) {
		
			getDataAPI query = new getDataAPI(super.In, super.Out, super.valor);
			result.setText(super.valor + " " + super.In + " = " + query.consulta()+ " " + super.Out);
			
			getTimeSeriesAPI querySeries = new getTimeSeriesAPI(this.DateNow, this.FirstDate, In, Out);
			JSONObject rates = new JSONObject(querySeries.consulta());
			
			this.chart.getData().clear();
			
			Series<String, Number> series = new Series<String, Number>();
			
			List<String> sortedDates = rates.keySet().stream()
		            .map(this::formatToInstant)
		            .sorted()
		            .map(this::formatToString)
		            .collect(Collectors.toList());
			
			Float maximo = 0.0F;
			Float minimo = 0.0F;
			
			for(String date : sortedDates){
				JSONObject rate = rates.getJSONObject(date);
				Float rateFloat = rate.getFloat(super.Out);
				LocalDateTime dateTime = LocalDateTime.parse(date+"T00:00:00.0");
	            series.getData().add(new Data<String, Number>(Integer.toString(dateTime.getDayOfMonth()), rateFloat));
	            if(rateFloat > maximo) {
	            	maximo = rateFloat;
	            };
	            if(minimo == 0.0F) {
	            	minimo = rateFloat;
	            }
	            if(rateFloat < minimo) {
	            	minimo = rateFloat;
	            }
			}
			
			this.CfgAxis(maximo, minimo);
		    
	        this.chart.getData().add(series);
        
        return true;
		}
		else {
			return false;
		}
	}
	
	public String TranslateMonth(String mes) {
		Map<String, String> Calendario = new HashMap<String, String>();
		
		Calendario.put("JANUARY", "Enero");
		Calendario.put("FEBRUARY", "Febrero");
		Calendario.put("MARCH", "Marzo");
		Calendario.put("APRIL", "Abril");
		Calendario.put("MAY", "Mayo");
		Calendario.put("JUNE", "Junio");
		Calendario.put("JULY", "Julio");
		Calendario.put("AUGUST", "Agosto");
		Calendario.put("SEPTEMBER", "Septiembre");
		Calendario.put("OCTOBER", "Octubre");
		Calendario.put("NOVEMBER", "Noviembre");
		Calendario.put("DECEMBER", "Diciembre");
		
		return Calendario.get(mes);
	}
	
	private void CfgAxis(Float maximo, Float minimo) {
		this.yAxis.setLabel("Tasa de cambio");
		this.yAxis.lookup(".axis-label").setStyle("-fx-label-padding: -50 0 0 0;");
		this.yAxis.setAutoRanging(false);
		this.yAxis.setLowerBound(minimo);
		this.yAxis.setUpperBound(maximo);
		this.yAxis.setTickUnit((maximo-minimo)/10);
	    
		this.xAxis.setLabel("Mes de " + TranslateMonth(LocalDateTime.now().getMonth().toString()));
	}
	
	private LocalDateTime formatToInstant(String timeInString) {
	    return LocalDateTime.parse(timeInString+"T00:00:00.0");
	}
	
	private String formatToString(LocalDateTime time) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    return formatter.format(time);
	}

}
