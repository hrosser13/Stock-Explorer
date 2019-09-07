package application.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;


// Model class for Stocks

public class Stocks {

	private final StringProperty stockSymbol;
	private final StringProperty companyName;
	private final DoubleProperty latestSharePrice;
	private Button button;

    
    // Default constructor
    public Stocks() {
    	this(null, null, null, null);
    }
    
    // Constructor with data specs
    public Stocks(String stockSymbol, String companyName, Double latestSharePrice, Button button) {
    	this.stockSymbol = new SimpleStringProperty(stockSymbol);
    	this.companyName = new SimpleStringProperty(companyName);
    	this.latestSharePrice = new SimpleDoubleProperty(latestSharePrice);
    	this.button = button;
    	this.button.setText("View Details");
    
    }
    
    // getters and setters
    
    public String getStockSymbol() {
    	return stockSymbol.get();
    }
    public void setStockSymbol(String stockSymbol) {
    	this.stockSymbol.set(stockSymbol);
    }
    public StringProperty stockSymbolProperty() {
        return stockSymbol;
    }
      
    public String getCompanyName() {
    	return companyName.get();
    }
    public void setCompanyName(String companyName) {
    	this.companyName.set(companyName);
    }
    public StringProperty companyNameProperty() {
        return companyName;
    }
    
    public Double getLatestSharePrice() {
    	return latestSharePrice.get();
    }
    public void setLatestSharePrice(Double latestSharePrice) {
    	this.latestSharePrice.set(latestSharePrice);
    }
    public DoubleProperty latestSharePriceProperty() {
        return latestSharePrice;
    }
    
    
    public Button getButton() {
    	return button;
    }
    public void setButton(Button button) {
    	this.button = button;
    }
    
    
    
}
