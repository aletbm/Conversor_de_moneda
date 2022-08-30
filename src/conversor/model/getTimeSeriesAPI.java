package conversor.model;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

public class getTimeSeriesAPI extends getDataAPI{
	
	private String dateNow;
	private String firstDate;
	
	public getTimeSeriesAPI(String dateNow, String firstDate, String divisaIn, String divisaOut) {
		this.dateNow = dateNow;
		this.firstDate = firstDate;
		super.divisaIn = divisaIn;
		super.divisaOut = divisaOut;
		super.url = "https://api.apilayer.com/exchangerates_data/timeseries?start_date=" + firstDate + "&end_date=" + dateNow + "&base=" + super.divisaIn + "&symbols=" + super.divisaOut;
	}
	
	@Override
	public String consulta() throws IOException{
		String respuesta;
		APIConversor example = new APIConversor();
		String response = example.run(super.url);
		JSONObject parser = new JSONObject(response);
		
		try {
			respuesta = parser.get("rates").toString();
		}
		catch (JSONException e){
			respuesta = "Error de consulta";
		}
		
		return respuesta;
	}

	public String getDateNow() {
		return dateNow;
	}

	public void setDateNow(String dateNow) {
		this.dateNow = dateNow;
	}

	public String getFirstDate() {
		return firstDate;
	}

	public void setFirstDate(String firstDate) {
		this.firstDate = firstDate;
	}
}
