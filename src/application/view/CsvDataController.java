package application.view;

import java.util.List;

import application.MainStockApp;
import application.model.CsvData;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CsvDataController {
	
	@FXML
	private TableView table;
	private List<TableColumn> columnList;
	private ObservableList<CsvData> data;
	private Button openButton;
	
	@FXML
	private Button volGraph;

	
	 // Reference to MainStockApp
    private MainStockApp mainStockApp;
    
    // Constructor
    public CsvDataController() {
    }

    // Called by MainStockApp to give reference back to itself
    public void setMainStockApp(MainStockApp mainStockApp) {
    	this.mainStockApp = mainStockApp;

    }
	

}
