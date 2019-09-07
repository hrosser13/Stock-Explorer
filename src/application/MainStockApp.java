package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import application.model.CsvData;
import application.model.Stocks;
import application.view.StockOverviewController;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.CategoryAxis;


public class MainStockApp extends Application {
	
	@FXML
	private TableView table;
	private List<TableColumn> columnList;
	private ObservableList<CsvData> data;
	private Button openButton;
	
	@FXML
	NumberAxis yAxis = new NumberAxis();
	CategoryAxis xAxis = new CategoryAxis();
	LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
	
	XYChart.Series<String, Number> series = new XYChart.Series<>();
	XYChart.Series<String, Number> series2 = new XYChart.Series<>();
	XYChart.Series<String, Number> series3 = new XYChart.Series<>();
	XYChart.Series<String, Number> series4 = new XYChart.Series<>();
	XYChart.Series<String, Number> series5 = new XYChart.Series<>();

	@FXML
	private MenuItem menuSave;
	
	@FXML
	private Label companyLabel;
	
	@FXML
	private Button volGraph;
	private Button hlGraph;
	private Button closeGraph;
	
    private Stage primaryStage;
    private BorderPane rootLayout;
    
    Button []button = new Button[28];
    private static String filename;
    private static String companyName;
    
    int volume;
	String day;
    
    
    // getters and setters for the filename variable -- 
    // allows the parameters to pass through to the new window
    public static String getMyVariable() {
    	return filename;
    }
    public static void setMyVariable(String filename) {
    	MainStockApp.filename = filename;
    }
    public static String getCompanyName() {
    	return companyName;
    }
    public static void setCompanyName(String companyName) {
    	MainStockApp.companyName = companyName;
    }
    

    // Observable list of Stocks
    private ObservableList<Stocks> stocksData = FXCollections.observableArrayList();

    // Constructor
    public MainStockApp() {
    	
    	// for loop to connect each button with an action handler method
    	for (int i = 0; i < button.length; i++) {
    		button[i] = new Button();
    		// on click --> call the function
    		button[i].setOnAction(this::handleButtonAction);
    	}
    	
    
    	// Data to add to the stock tableview
    	stocksData.add(new Stocks("AHT.L", "Ashtead Group plc", 1644.00, button[0]));
    	stocksData.add(new Stocks("ANTO.L", "Antofagasta plc", 866.00, button[1]));
    	stocksData.add(new Stocks("BA.L", "BAE Systems plc", 612.00, button[2]));
    	stocksData.add(new Stocks("BATS.L", "British American Tobacco plc", 5008.00, button[3]));
    	stocksData.add(new Stocks("CCH.L", "Coca-Cola HBC AG", 1826.00, button[4]));
    	stocksData.add(new Stocks("CCL.L", "Carnival plc", 4336.00, button[5]));
    	stocksData.add(new Stocks("CNA.L", "Centrica plc", 234.00, button[6]));
    	stocksData.add(new Stocks("CPG.L", "Compass Group plc", 1445.00, button[7]));
    	stocksData.add(new Stocks("EXPN.L", "Experian plc", 1565.00, button[8]));
    	stocksData.add(new Stocks("EZJ.L", "EasyJet plc", 947.50, button[9]));
    	stocksData.add(new Stocks("GKN.L", "GKN plc", 340.90, button[10]));
    	stocksData.add(new Stocks("MDC.L", "Mediclinic International plc", 835.50, button[11]));
    	stocksData.add(new Stocks("PFG.L", "Provident Financial plc", 2779.00, button[12]));
    	stocksData.add(new Stocks("PPB.L", "Paddy Power Betfair plc", 8700.00, button[13]));
    	stocksData.add(new Stocks("PRU.L", "Prudential plc", 1620.50, button[14]));
    	stocksData.add(new Stocks("PSN.L", "Persimmon plc", 1976.00, button[15]));
    	stocksData.add(new Stocks("RB.L", "Reckitt Benckiser Group plc", 7025.00, button[16]));
    	stocksData.add(new Stocks("RDSA.L", "Royal Dutch Shell plc", 5008.00, button[17]));
    	stocksData.add(new Stocks("RR.L", "Rolls-Royce Holdings plc", 732.50, button[18]));
    	stocksData.add(new Stocks("SDR.L", "Schroders plc", 3041.00, button[19]));
    	stocksData.add(new Stocks("SHP.L", "Shire plc", 4595.00, button[20]));
    	stocksData.add(new Stocks("SKY.L", "Sky plc", 1000.00, button[21]));
    	stocksData.add(new Stocks("SSE.L", "SSE plc", 1537.00, button[22]));
    	stocksData.add(new Stocks("STJ.L", "St. James's Place plc", 1083.00, button[23]));
    	stocksData.add(new Stocks("TSCO.L", "Tesco plc", 198.00, button[24]));
    	stocksData.add(new Stocks("TUI.L", "TUI AG", 1158.00, button[25]));
    	stocksData.add(new Stocks("VOD.L", "Vodafone Group plc", 197.70, button[26]));
    	stocksData.add(new Stocks("WPG.L", "Wordpay Group plc", 272.00, button[27]));	
    }
    
 
    // Returns the stock data as an observable list
    public ObservableList<Stocks> getStocksData() {
    	return stocksData;
    }
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Stock Explorer");

        // Call the scene display functions
        initRootLayout();

        showStockOverview();
        showStockDataScene();
        
    }
    
    
    public void initRootLayout() {
        try {
            // Load fxml file into the scene/root layout.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainStockApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            
            primaryStage.show();  
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }


    public void showStockOverview() {
        try {
            // Load the stock overview
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainStockApp.class.getResource("view/StockOverview.fxml"));
            AnchorPane stockOverview = (AnchorPane) loader.load();
            
            // Display stock overview in the left of the root layout.
            rootLayout.setLeft(stockOverview);
            
            // Give the controller access to MainStockApp
            StockOverviewController controller = loader.getController();
            controller.setMainStockApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public void showStockDataScene() {
    	try {
    		
            // Load the stock data file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainStockApp.class.getResource("view/CsvTable.fxml"));
            VBox stockData = (VBox) loader.load();
            
            // Display individual company table view on the right of the root layout
            rootLayout.setRight(stockData);
            
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    }
    

 
    // On button click -- create the table view for the selected company
	@FXML
	public void btnAction(ActionEvent e) {
		
		// Read the csv file and create table view
		try {
			table.getItems().clear();
			columnList = new ArrayList<TableColumn>();
			File f = new File(filename);
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line;
			int cnt = 0;
			int len = 0;
			while ((line = br.readLine()) != null) {
				String[] d = line.split(",", 0);
				
				// for the first loop -- it will be the table headers
				if (cnt == 0) {

					ArrayList<TableColumn> dataCols = new ArrayList<TableColumn>();
					len = d.length;
					for (int i = 0; i < d.length; i++) {
						if (d[i] == null)
							d[i] = ""; 
						dataCols.add(new TableColumn(d[i]));
						columnList.add(dataCols.get(i));
						final int index = i;
						dataCols.get(i).setCellValueFactory(
								new Callback<CellDataFeatures<CsvData, String>, ObservableValue<String>>() {
									public ObservableValue<String> call(CellDataFeatures<CsvData, String> p) {
										return p.getValue().get(index);
									}
								});
					}
					table.getColumns().addAll(columnList);
					data = FXCollections.observableArrayList();
					table.itemsProperty().setValue(data);
					table.setItems(data);
					
				} else {
					
					// Add the data to the columns

					ArrayList<String> col = new ArrayList<String>();

					for (int i = 0; i < len; i++) {
						if (d.length > i) {
							if (d[i] == null)
								d[i] = "";
							col.add(d[i]);
							
						} else {
							col.add("");
						}
					}
					data.addAll(new CsvData(col));
				}
				cnt++;
			}
			br.close();
		} catch (IOException ie) {
			System.out.println(ie);
		} catch (Exception ie) {
			System.out.println(ie);
		}

	}
	
	
	
	
    // On button click --> Display the graph
    @FXML
    public void btnShowGraphVolume(ActionEvent e) {
    	
    	// Ensure that the 'View Details' button has been clicked in order to set the company name
    	if (MainStockApp.companyName == null) {
    		System.out.println("NONE");
    	} else {
    		System.out.println(MainStockApp.companyName);
    		showVolGraph();
    	}
    }
    
	
	// Create and display the graph from the item selected
	public void showVolGraph() {
		
		// clear the chart data on reload
		lineChart.getData().clear();
		series.getData().clear();

		yAxis.setLabel("Volume");
		xAxis.setLabel("Date");
		lineChart.setTitle("Volume of Stock Traded");
		
		series.setName(companyName);
		
		// read the data from the csv and input into the line chart
		try {
			File f2 = new File(filename);
			BufferedReader br2 = new BufferedReader(new FileReader(f2));
			String line;
			int count = 0;
			int len2 = 0;
	
			while ((line = br2.readLine()) != null) {
				String[] d2 = line.split(",", 0);
	
				// Loop over the headers
				if (count == 0) {

					len2 = d2.length;
	
				} else {
	
					for (int i = 0; i < len2; i++) {
						if (d2.length > i) {
							if (d2[i] == null)
								d2[i] = "";
	
							day = String.valueOf(d2[0]);
							volume = Integer.parseInt(d2[5]);
							
	
							series.getData().add(new XYChart.Data(day, volume));
	
						} else {
							System.out.println("Hi");
						} 
					} 
				} count++;
	
				// close the file
			} br2.close();
	
		} catch (IOException ie) {
			System.out.println(ie);
		} catch (Exception ie) {
			System.out.println(ie);
		}
	
			lineChart.getData().add(series);

			Stage graphStage = new Stage();
			Scene volGraphScene = new Scene(lineChart,800,500);

			graphStage.setScene(volGraphScene);
			graphStage.show();		
	} 
	
	
	
	
	
	
	// On button click --> Display the graph
    @FXML
    public void btnShowGraphHL(ActionEvent e) {
    	
    	// Ensure that the 'View Details' button has been clicked in order to set the company name
    	if (MainStockApp.companyName == null) {
    		System.out.println("NONE");
    	} else {
    		System.out.println(MainStockApp.companyName);
    		showHlGraph();
    	}
    }
    
	
	
	// Create and display the High and Low prices graph from the item selected
	public void showHlGraph() {
		
		// clear the chart data on reload
		lineChart.getData().clear();
		series2.getData().clear();

		yAxis.setLabel("Stock Price");
		xAxis.setLabel("Date");
		lineChart.setTitle("High & Low Prices of " + companyName);
		
		series2.setName("High");
		series3.setName("Low");
		
		// read the data from the csv and input into the line chart
		try {
			File f2 = new File(filename);
			BufferedReader br2 = new BufferedReader(new FileReader(f2));
			String line;
			int count = 0;
			int len2 = 0;
	
			while ((line = br2.readLine()) != null) {
				String[] d2 = line.split(",", 0);
	
				// Loop over the headers
				if (count == 0) {

					len2 = d2.length;
	
				} else {
	
					for (int i = 0; i < len2; i++) {
						if (d2.length > i) {
							if (d2[i] == null)
								d2[i] = "";
	
							day = String.valueOf(d2[0]);
							Double high = Double.valueOf(d2[2]);
							Double low = Double.valueOf(d2[3]);
	
							series2.getData().add(new XYChart.Data(day, high));
							series3.getData().add(new XYChart.Data(day, low));
	
						} else {
							System.out.println("Hi");
						} 
					} 
				} count++;
	
				// close the file
			} br2.close();
	
		} catch (IOException ie) {
			System.out.println(ie);
		} catch (Exception ie) {
			System.out.println(ie);
		}
	
			lineChart.getData().add(series2);
			lineChart.getData().add(series3);

			Stage hlGraphStage = new Stage();
			Scene hlGraphScene = new Scene(lineChart,800,500);

			hlGraphStage.setScene(hlGraphScene);
			hlGraphStage.show();		
	} 
	
	
	
	// On button click --> Display the graph
    @FXML
    public void btnShowGraphClose(ActionEvent e) {
    	
    	// Ensure that the 'View Details' button has been clicked in order to set the company name
    	if (MainStockApp.companyName == null) {
    		System.out.println("NONE");
    	} else {
    		System.out.println(MainStockApp.companyName);
    		showCloseGraph();
    	}
    }
	
	
	// Create and display the High and Low prices graph from the item selected
		public void showCloseGraph() {
			
			// clear the chart data on reload
			lineChart.getData().clear();

			yAxis.setLabel("Stock Price");
			xAxis.setLabel("Date");
			lineChart.setTitle("Opening & Closing Price of " + companyName);
			
			series4.setName("Open");
			series5.setName("Close");
			
			// read the data from the csv and input into the line chart
			try {
				File f2 = new File(filename);
				BufferedReader br2 = new BufferedReader(new FileReader(f2));
				String line;
				int count = 0;
				int len2 = 0;
		
				while ((line = br2.readLine()) != null) {
					String[] d2 = line.split(",", 0);
		
					// Loop over the headers
					if (count == 0) {

						len2 = d2.length;
		
					} else {
		
						for (int i = 0; i < len2; i++) {
							if (d2.length > i) {
								if (d2[i] == null)
									d2[i] = "";
		
								day = String.valueOf(d2[0]);
								Double open = Double.valueOf(d2[1]);
								Double close = Double.valueOf(d2[4]);
		
								series4.getData().add(new XYChart.Data(day, open));
								series5.getData().add(new XYChart.Data(day, close));
		
							} else {
								System.out.println("Hi");
							} 
						} 
					} count++;
		
					// close the file
				} br2.close();
		
			} catch (IOException ie) {
				System.out.println(ie);
			} catch (Exception ie) {
				System.out.println(ie);
			}
		
				lineChart.getData().add(series4);
				lineChart.getData().add(series5);

				Stage closeGraphStage = new Stage();
				Scene closeGraphScene = new Scene(lineChart,800,500);

				closeGraphStage.setScene(closeGraphScene);
				closeGraphStage.show();		
		} 
	
	
	
	
    
	// Handler method for 'View Details' button
    private void handleButtonAction(ActionEvent event) {
    	
    	// Setting the company name for each button
    	if (event.getSource() == button[0]) {
    		MainStockApp.filename = "Files/AHT.csv";
    		MainStockApp.companyName = "Ashtead Group plc";
    		
    	} else if (event.getSource() == button[1]) {
    		MainStockApp.filename = "Files/ANTO.csv";
    		MainStockApp.companyName = "Antofagasta plc";
    	
    	} else if (event.getSource() == button[2]) {
    		MainStockApp.filename = "Files/BA.csv";	
    		MainStockApp.companyName = "BAE Systems plc";
    	
    	} else if (event.getSource() == button[3]) {
    		MainStockApp.filename = "Files/BATS.csv";	
    		MainStockApp.companyName = "British American Tobacco plc";
    		
    	} else if (event.getSource() == button[4]) {
    		MainStockApp.filename = "Files/CCH.csv";
    		MainStockApp.companyName = "Coca-Cola HBC AG";
    	
    	} else if (event.getSource() == button[5]) {
    		MainStockApp.filename = "Files/CCL.csv";
    		MainStockApp.companyName = "Carnvial plc";
    	
    	} else if (event.getSource() == button[6]) {
    		MainStockApp.filename = "Files/CNA.csv";
    		MainStockApp.companyName = "Centrica plc";
    	
    	} else if (event.getSource() == button[7]) {
    		MainStockApp.filename = "Files/CPG.csv";
    		MainStockApp.companyName = "Compass Group plc";
    	
    	} else if (event.getSource() == button[8]) {
    		MainStockApp.filename = "Files/EXPN.csv";	
    		MainStockApp.companyName = "Experian plc";
    	
    	} else if (event.getSource() == button[9]) {
    		MainStockApp.filename = "Files/EZJ.csv";
    		MainStockApp.companyName = "EasyJet plc";
    	
    	} else if (event.getSource() == button[10]) {
    		MainStockApp.filename = "Files/GKN.csv";
    		MainStockApp.companyName = "GKN plc";
    	
    	} else if (event.getSource() == button[11]) {
    		MainStockApp.filename = "Files/MDC.csv";	
    		MainStockApp.companyName = "Mediclinic International plc";
    	
    	} else if (event.getSource() == button[12]) {
    		MainStockApp.filename = "Files/PFG.csv";
    		MainStockApp.companyName = "Provident Financial plc";
    	
    	} else if (event.getSource() == button[13]) {
    		MainStockApp.filename = "Files/PPB.csv";	
    		MainStockApp.companyName = "Paddy Power Betfair plc";
    	
    	} else if (event.getSource() == button[14]) {
    		MainStockApp.filename = "Files/PRU.csv";
    		MainStockApp.companyName = "Prudential plc";
    	
    	} else if (event.getSource() == button[15]) {
    		MainStockApp.filename = "Files/PSN.csv";
    		MainStockApp.companyName = "Persimmon plc";
    	
    	} else if (event.getSource() == button[16]) {
    		MainStockApp.filename = "Files/RB.csv";	
    		MainStockApp.companyName = "Reckitt Benckiser Group plc";
    	
    	} else if (event.getSource() == button[17]) {
    		MainStockApp.filename = "Files/RDSA.csv";	
    		MainStockApp.companyName = "Royal Dutch Shell plc";
    	
    	} else if (event.getSource() == button[18]) {
    		MainStockApp.filename = "Files/RR.csv";	
    		MainStockApp.companyName = "Rolls-Royce Holdings plc";
    	
    	} else if (event.getSource() == button[19]) {
    		MainStockApp.filename = "Files/SDR.csv";
    		MainStockApp.companyName = "Schroders plc";
    	
    	} else if (event.getSource() == button[20]) {
    		MainStockApp.filename = "Files/SHP.csv";
    		MainStockApp.companyName = "Shire plc";
    	
    	} else if (event.getSource() == button[21]) {
    		MainStockApp.filename = "Files/SKY.csv";
    		MainStockApp.companyName = "Sky plc";
    	
    	} else if (event.getSource() == button[22]) {
    		MainStockApp.filename = "Files/SSE.csv";
    		MainStockApp.companyName = "SSE plc";
    	
    	} else if (event.getSource() == button[23]) {
    		MainStockApp.filename = "Files/STJ.csv";	
    		MainStockApp.companyName = "St. James's Place plc";
    	
    	} else if (event.getSource() == button[24]) {
    		MainStockApp.filename = "Files/TSCO.csv";
    		MainStockApp.companyName = "Tesco plc";
    	
    	} else if (event.getSource() == button[25]) {
    		MainStockApp.filename = "Files/TUI.csv";	
    		MainStockApp.companyName = "TUI AG";
    	
    	} else if (event.getSource() == button[26]) {
    		MainStockApp.filename = "Files/VOD.csv";	
    		MainStockApp.companyName = "Vodafone Group plc";
    	
    	} else if (event.getSource() == button[27]) {
    		MainStockApp.filename = "Files/WPG.csv";
    		MainStockApp.companyName = "Worldpay Group plc";
    	}
    	
    	showStockDataScene();
    }
    
    public void handleExit(ActionEvent Event) {
    	System.exit(0);
    }
 
    
    // The code for handleSave and saveFile methods were adapted from Java-Buddy
    // Accessed 01/04/2019
    // http://java-buddy.blogspot.com/2012/05/save-file-with-javafx-filechooser.html
    
    // Handler for the save function
    public void handleSave(ActionEvent event) throws IOException {
    	
    	// Info to be saved in the report
    	final String StockText = "Number: 1\nStock Symbol: AHT.L\nCompany Name: Ashtead Group plc\nHighest: 2017-01-26\nLowest: 2016-11-17\nAverage Close: 1569.29\nClose: 1644.00"
    			+ "\n\nNumber: 2\nStock Symbol: ANTO.L\nCompany Name: Antofagasta plc\nHighest: 2017-01-25\nLowest: 2016-12-20\nAverage Close: 733.65\nClose: 866.00"
    			+ "\n\nNumber: 3\nStock Symbol: BA.L\nCompany Name: BAE Systems plc\nHighest: 2017-01-12\nLowest: 2017-02-01\nAverage Close: 597.45\nClose: 612.00"
    			+ "\n\nNumber: 4\nStock Symbol: BATS.L\nCompany Name: British American Tobacco plc\nHighest: 2017-02-10\nLowest: 2016-11-14\nAverage Close: 4599.52\nClose: 5008.00"
    			+ "\n\nNumber: 5\nStock Symbol: CCH.L\nCompany Name: Coca-Cola HBC AG\nHighest: 2017-02-09\nLowest: 2016-12-15\nAverage Close: 1732.26\nClose: 1826.00"
    			+ "\n\nNumber: 6\nStock Symbol: CCL.L\nCompany Name: Carnival plc\nHighest: 2017-01-26\nLowest: 2016-12-02\nAverage Close: 4146.58\nClose: 4336.00"
    			+ "\n\nNumber: 7\nStock Symbol: CNA.L\nCompany Name: Centrica plc\nHighest: 2017-01-03\nLowest: 2016-11-21\nAverage Close: 221.77\nClose: 234.00"
    			+ "\n\nNumber: 8\nStock Symbol: CPG.L\nCompany Name: Compass Group plc\nHighest: 2017-01-03\nLowest: 2016-12-06\nAverage Close: 1412.26\nClose: 1445.00"
    			+ "\n\nNumber: 9\nStock Symbol: EXPN.L\nCompany Name: Experian plc\nHighest: 2017-01-16\nLowest: 2016-11-14\nAverage Close: 1525.63\nClose: 1565.00"
    			+ "\n\nNumber: 10\nStock Symbol: EZJ.L\nCompany Name: EasyJet plc\nHighest: 2017-01-02\nLowest: 2016-12-16\nAverage Close: 1011.58\nClose: 947.50"
    			+ "\n\nNumber: 11\nStock Symbol: GKN.L\nCompany Name: GKN plc\nHighest: 2017-01-25\nLowest: 2016-12-05\nAverage Close: 327.91\nClose: 340.90"
    			+ "\n\nNumber: 12\nStock Symbol: MDC.L\nCompany Name: Mediclinic International plc\nHighest: 2017-02-10\nLowest: 2016-12-02\nAverage Close: 760.65\nClose: 835.50"
    			+ "\n\nNumber: 13\nStock Symbol: PFG.L\nCompany Name: Provident Financial plc\nHighest: 2016-11-24\nLowest: 2017-02-02\nAverage Close: 2812.48\nClose: 2779.00"
    			+ "\n\nNumber: 14\nStock Symbol: PPB.L\nCompany Name: Paddy Power Betfair plc\nHighest: 2017-01-10\nLowest: 2017-01-25\nAverage Close: 8540.92\nClose: 8700.00"
    			+ "\n\nNumber: 15\nStock Symbol: PRU.L\nCompany Name: Prudential plc\nHighest: 2016-12-08\nLowest: 2016-11-15\nAverage Close: 1576.65\nClose: 1620.50"
    			+ "\n\nNumber: 16\nStock Symbol: PSN.L\nCompany Name: Persimmon plc\nHighest: 2017-02-08\nLowest: 1646.00\nAverage Close: 1825.38\nClose: 1976.00"
    			+ "\n\nNumber: 17\nStock Symbol: RB.L\nCompany Name: Reckitt Benckiser Group plc\nHighest: 2017-02-10\nLowest: 2016-12-13\nAverage Close: 6811.40\nClose: 7025.00"
    			+ "\n\nNumber: 18\nStock Symbol: RDSA.L\nCompany Name: Royal Dutch Shell plc\nHighest: 2017-02-10\nLowest: 2016-11-14\nAverage Close: 4599.52\nClose: 5008.00"
    			+ "\n\nNumber: 19\nStock Symbol: RR.L\nCompany Name: Rolls-Royce Holdings plc\nHighest: 2016-11-16\nLowest: 2017-01-05\nAverage Close: 679.28\nClose: 732.50"
    			+ "\n\nNumber: 20\nStock Symbol: SDR.L\nCompany Name: Schroders plc\nHighest: 2017-01-18\nLowest: 2016-12-02\nAverage Close: 2942.51\nClose: 3041.00"
    			+ "\n\nNumber: 21\nStock Symbol: SHP.L\nCompany Name: Shire plc\nHighest: 2016-11-14\nLowest: 2016-12-18\nAverage Close: 4586.80\nClose: 4595.00"
    			+ "\n\nNumber: 22\nStock Symbol: SKY.L\nCompany Name: Sky plc\nHighest: 2016-12-09\nLowest: 2016-11-21\nAverage Close: 928.90\nClose: 1000.00"
    			+ "\n\nNumber: 23\nStock Symbol: SSE.L\nCompany Name: SSE plc\nHighest: 2017-01-03\nLowest: 2016-11-18\nAverage Close:1507.78 \nClose: 1537.00"
    			+ "\n\nNumber: 24\nStock Symbol: STJ.L\nCompany Name: St. James's Place plc\nHighest: 2017-01-26\nLowest: 2016-12-02\nAverage Close: 1013.62\nClose: 1083.00"
    			+ "\n\nNumber: 25\nStock Symbol: TSCO.L\nCompany Name: Tesco plc\nHighest: 2016-12-09\nLowest: 2017-01-16\nAverage Close: 204.83\nClose: 198.00"
    			+ "\n\nNumber: 26\nStock Symbol: TUI.\nCompany Name: TUI AG\nHighest: 2017-02-08\nLowest: 2016-11-18\nAverage Close: 1113.60\nClose: 1158.00"
    			+ "\n\nNumber: 27\nStock Symbol: VOD.L\nCompany Name: Vodafone Group plc\nHighest: 2017-01-13\nLowest: 2017-02-02\nAverage Close: 200.33\nClose: 197.70"
    			+ "\n\nNumber: 28\nStock Symbol: WPG.L\nCompany Name: Worldpay Group plc\nHighest: 2017-01-13\nLowest: 2016-12-07\nAverage Close: 273.80\nClose: 272.00"
    			
    			;
    	
    	FileChooser fileChooser = new FileChooser();
    	
    	// Set extension filter to TXT
    	FileChooser.ExtensionFilter extFiler = new FileChooser.ExtensionFilter("TXT Files (*.txt)", "*.txt");
    	fileChooser.getExtensionFilters().add(extFiler);
    	
    	// Show save file dialog
    	File file = fileChooser.showSaveDialog(primaryStage);
    	
    	if (file != null) {
    		SaveFile(StockText, file);
    	}
    }
    
    // Write the file and save
    private void SaveFile(String content, File file) {
    	try {
    		FileWriter fileWriter = new FileWriter(file);
    		fileWriter.write(content);
    		fileWriter.close();
    		
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
   
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}