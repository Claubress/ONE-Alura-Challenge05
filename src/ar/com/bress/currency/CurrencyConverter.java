package ar.com.bress.currency;

import java.util.Arrays;
import java.util.Currency;
import java.util.Iterator;

import org.json.JSONObject;

public class CurrencyConverter {

	private String currencyFrom;
	private String currencyTo;
	private double currencyAmount;
	private double currencyConvert;
	
	CurrencyService currencyService = new CurrencyService();
	

	public CurrencyConverter() {
	}


	public CurrencyConverter(String currencyFrom, String currencyTo, double currencyAmount) {
		this.currencyFrom = currencyFrom;
		this.currencyTo = currencyTo;
		this.currencyAmount = currencyAmount;

		converter();
	}
		
	
	public double getCurrencyConvert() {
		return this.currencyConvert;
	}
	

	public Symbol[] getSymbol() {

		int i = 0;
		String description;
		
		try {				
			JSONObject jsonObject = currencyService.getSymbols(); 
				
			
			Symbol [] symbols = new Symbol[jsonObject.length()];


			Iterator<String> keys = jsonObject.keys();
			
			while (keys.hasNext()) {
	
				String key = keys.next();
	
				
				try {
					
					Currency cr = Currency.getInstance(key);
					
					char[] arr = cr.getDisplayName().toCharArray();
					arr[0] = Character.toUpperCase(arr[0]);
					description = new String(arr);
				} catch (Exception e) {
					description = jsonObject.getString(key);					
				}	

				symbols[i] = new Symbol(key, description);
				i++;
		    }

			
			Arrays.sort(symbols);			
			return symbols;
		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	
	}


	private void converter() {
		try {			
			
			this.currencyConvert = currencyService.getAmountConverted(currencyFrom, currencyTo, currencyAmount);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
