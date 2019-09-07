package application.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import application.MainStockApp;
import application.model.Stocks;

public class StockOverviewController {

	@FXML
    private TableView<Stocks> stocksTable;
    @FXML
    private TableColumn<Stocks, String> stockSymbolColumn;
    @FXML
    private TableColumn<Stocks, String> companyNameColumn;
    @FXML
    private TableColumn<Stocks, Double> latestSharePriceColumn;
    @FXML
    private TableColumn<Stocks, String> buttonColumn;
    
    // Reference to MainStockApp
    private MainStockApp mainStockApp;
    
    // Constructor
    public StockOverviewController() {
    }
    
    
    // Initializes the Controller class. 
    @FXML
    private void initialize() {
    	// Initialize the Stocks table with the 3 columns
    	stockSymbolColumn.setCellValueFactory(cellData -> cellData.getValue().stockSymbolProperty());
    	companyNameColumn.setCellValueFactory(cellData -> cellData.getValue().companyNameProperty());
    	latestSharePriceColumn.setCellValueFactory(cellData -> cellData.getValue().latestSharePriceProperty().asObject());
    	buttonColumn.setCellValueFactory(new PropertyValueFactory<Stocks, String>("button"));
    	
    }
    
    // Called by MainStockApp to give reference back to itself
    public void setMainStockApp(MainStockApp mainStockApp) {
    	this.mainStockApp = mainStockApp;
    	
    	// Add observable list data to the table
    	stocksTable.setItems(mainStockApp.getStocksData());
    }
}
