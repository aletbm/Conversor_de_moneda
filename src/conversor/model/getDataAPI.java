package conversor.model;

import java.io.IOException;
import org.json.JSONObject;
import org.json.JSONException;

public class getDataAPI {
	
	protected String divisaIn;
	protected String divisaOut;
	private String monto;
	protected String url;
	
	public getDataAPI() {}
	
	public getDataAPI(String divisaIn, String divisaOut, String monto) {
		this.divisaIn = divisaIn;
		this.divisaOut = divisaOut;
		this.monto = monto;
		this.url = "https://api.apilayer.com/exchangerates_data/convert?to=" + this.divisaOut + "&from=" + this.divisaIn + "&amount=" + this.monto;
	}
	
	public String consulta() throws IOException{
		String respuesta;
		APIConversor example = new APIConversor();
		String response = example.run(this.url);
		JSONObject parser = new JSONObject(response);
		
		try {
			respuesta = Float.toString(parser.getFloat("result"));
		}
		catch (JSONException e){
			respuesta = "Error de consulta";
		}
		
		return respuesta;
	}

	public String getDivisaIn() {
		return divisaIn;
	}

	public void setDivisaIn(String divisaIn) {
		this.divisaIn = divisaIn;
	}

	public String getDivisaOut() {
		return divisaOut;
	}

	public void setDivisaOut(String divisaOut) {
		this.divisaOut = divisaOut;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}
	
	public void setUrl(String divisaIn, String divisaOut, String monto){
		this.url = "https://api.apilayer.com/exchangerates_data/convert?to=" + this.divisaOut + "&from=" + this.divisaIn + "&amount=" + this.monto;
	}
	
}
