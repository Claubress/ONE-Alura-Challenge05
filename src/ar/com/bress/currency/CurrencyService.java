package ar.com.bress.currency;

import java.io.*;

import org.json.JSONObject;

import okhttp3.*;

public class CurrencyService {

	private boolean production = true;
	private String apiKey = "Colocar la clave de obtenida de Fixer";


	public double getAmountConverted(String currencyFrom, String currencyTo, double currencyAmount) throws IOException {
				
		
		OkHttpClient client = new OkHttpClient().newBuilder().build();

		Request request = new Request.Builder()
			      .url("https://api.apilayer.com/fixer/convert?to=" + currencyTo + "&from=" + currencyFrom + "&amount=" + currencyAmount)
			      .addHeader("apikey", apiKey) 
			      .get()
			      .build();

	    
		try {
			Response response = client.newCall(request).execute();			
			String jsonData = response.body().string();
			
			JSONObject jsonObject = new JSONObject(jsonData);
			return jsonObject.getDouble("result");
		} catch (Exception e) {	
			return 0;
		}
		
	    
	    		
	}

	
	public JSONObject getSymbols() throws IOException {

		OkHttpClient client = new OkHttpClient().newBuilder().build();

	    Request request = new Request.Builder()
	      .url("https://api.apilayer.com/fixer/symbols")
	      .addHeader("apikey", apiKey)
	      .get()
	      .build();

	    if(production) {
	    	
	    	Response response = client.newCall(request).execute();
	    	
	    	String jsonData = response.body().string();
	    	
	    	JSONObject jsonObject = new JSONObject(jsonData);
	    	
	    	return jsonObject.getJSONObject("symbols");

	    } else {
	    	
	    	String responseJson = "{" + "\"AED\"" + ": " + "\"United Arab Emirates Dirham\"" + " , " + "\"ARS\"" + ": " + "\"Argentine pesos\"" + " , " + "\"AFN\"" + ": " + "\"Afghan Afghani\"" + "}";
	    	
	    	JSONObject json = new JSONObject(responseJson);
	    	
	    	return json;
	    }
	}
}
