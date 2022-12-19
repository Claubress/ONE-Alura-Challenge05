package ar.com.bress.temperature;

import java.text.DecimalFormat;

public class TerperatureConverter {
	
	private double temperatureConvert;
	
	
	public void Converter(char tempFrom, char tempTo, double tempAmount) {
		
		final String typeConvert = String.format("%c%c", tempFrom, tempTo);

		switch (typeConvert) {

		case "CF":
			this.temperatureConvert = (tempAmount * 9/5) + 32;
			break;

		case "FC":
			this.temperatureConvert = (tempAmount - 32) * 5/9;			
			break;

		case "CK":
			this.temperatureConvert = tempAmount + 273.15;
			break;

		case "KC":
			this.temperatureConvert = tempAmount - 273.15;
			break;

		case "FK":
			this.temperatureConvert = (tempAmount - 32) * 5 / 9 + 273.15;
			break;

		case "KF":
			this.temperatureConvert = (tempAmount - 273.15) * 9 / 5 + 32;
			break;

		default:
			this.temperatureConvert = tempAmount;
			break;
		}		
	}
	
	
	public Double getTemperatureConvert() {
		return this.temperatureConvert;
	}
}
