package ar.com.bress.currency;


public class Symbol implements Comparable<Symbol> {

	private String symbol;
	private String description;
	
	
	public Symbol () {
	
	}
	
	
	public Symbol(String symbol, String description) {
		this.symbol = symbol;
		this.description = description;
	}
	
	
	public String getSymbol() {
		return this.symbol;
	}
	
	
	public String getDescription() {
		return this.description;
	}
	
	
	@Override
	public String toString() {
		return this.description;
	}

	
	@Override
    public int compareTo(Symbol o)
    {
        return this.description.compareTo(o.getDescription());
    }	
}
