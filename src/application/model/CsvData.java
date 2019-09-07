package application.model;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CsvData {
	
	private ArrayList<String> list;
	
	public CsvData(ArrayList<String> list) {
		this.list = list;
	}
	
	public void add(String data) {
		list.add(data);
	}
	
	public StringProperty get(int index) {
		return new SimpleStringProperty(list.get(index));
	}

}
