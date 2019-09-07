package application.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

// Helper class to wrap a list of stock data 
// Used for saving the list of Stock Data to XML

@XmlRootElement(name = "stocks")
public class StockListWrapper {

	private List<Stocks> stocks;
	
	@XmlElement(name = "stock")
	public List<Stocks> getStocks() {
		return stocks;
	}
	
	public void setStocks(List<Stocks> stocks) {
		this.stocks = stocks;
	}

}
