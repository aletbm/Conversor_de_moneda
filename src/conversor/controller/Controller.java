package conversor.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class Controller implements Initializable{
	
	@FXML
	private ComboBox<String> comboBoxIn;
	
	@FXML
	private ComboBox<String> comboBoxOut;
	
	@FXML
	private TextField monto;
	
	@FXML
	private ComboBox<String> comboBoxInTemp;
	
	@FXML
	private ComboBox<String> comboBoxOutTemp;
	
	@FXML
	private TextField ValorTemp;
	
	@FXML
	private TextField result;
	
	@FXML
	private TextField resultTemp;
	
	@FXML
	private AreaChart<String, Number> chartConvert;
	
	@FXML
	private CategoryAxis xAxis;
	
	@FXML
	private NumberAxis yAxis;
	
	@FXML
	private AreaChart<Number, Number> chartConvertTemp;
	
	@FXML
	private NumberAxis xAxisTemp;
	
	@FXML
	private NumberAxis yAxisTemp;
	
	private ObservableList<String> comboLista = FXCollections.observableArrayList("USD", "EUR", "GBP", "JPY", "KRW", "BTC", "ARS");
	private ObservableList<String> comboListaTemp = FXCollections.observableArrayList("°C", "°F", "°K");
	private String StrDateNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	private String StrFirstDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM")) + "-01";
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.comboBoxIn.setItems(this.comboLista);
		this.comboBoxIn.setCellFactory(e -> new StringImageCell());
		this.comboBoxIn.setButtonCell(new StringImageCell());
		
		this.comboBoxOut.setItems(this.comboLista);
		this.comboBoxOut.setCellFactory(e -> new StringImageCell());
		this.comboBoxOut.setButtonCell(new StringImageCell());
		
		this.comboBoxInTemp.setItems(this.comboListaTemp);
		this.comboBoxOutTemp.setItems(this.comboListaTemp);
	}
	
	static class StringImageCell extends ListCell<String> {
		private ImageView view;
		Label label;
		
	    @Override
	    public void updateItem(String name, boolean empty) {
	        super.updateItem(name, empty);
	        if(empty) {
	            setGraphic(null);
	        }
	        else {
	        	view = new ImageView();
	        	setText(name);
	            view.setImage(new Image(new File("src/conversor/img/" + name + ".png").toURI().toString(), 25, 25, true, true));
	        	label = new Label("", view);
	            setGraphic(label);
	        }
	    }
	}
	
	@FXML
	public void handleConversionMoneda(ActionEvent event) throws IOException {
		event.consume();
		String Out = this.comboBoxOut.getValue();
		String In = this.comboBoxIn.getValue();
		String valor = this.monto.getText();
		
		chartControlMonedas chartController = new chartControlMonedas(this.chartConvert, this.xAxis, this.yAxis, In, Out, valor, this.result, this.StrDateNow, this.StrFirstDate);
		
		if(!chartController.updateChart()){
	        Alert a = new Alert(AlertType.INFORMATION);
	        DialogPane dialogPane = a.getDialogPane();
	        dialogPane.getStylesheets().add(getClass().getResource("/conversor/view/App.css").toExternalForm());
	        dialogPane.getStyleClass().add("dialog");
	        
	        a.setTitle("Atencion");
	        a.setHeaderText("Se encontraron datos vacios.");
	        
	        if(Out == null) {
	        	 a.setContentText("Seleccione una divisa de salida.");
	        }
	        else if(In == null){
	        	 a.setContentText("Seleccione una divisa de entrada.");
	        }
	        else {
	        	if(!chartController.isOnlyNumbers()) {
		        	a.setContentText("Solo se permite el ingreso de numeros.");}
	        	else {
	        		a.setContentText("Ingrese el monto que desea convertir.");
	        	}
	        }
			a.show();
		}
	}

	
	@FXML
	public void handleConversionTemperatura(ActionEvent event) throws IOException {
		event.consume();
		String Out = this.comboBoxOutTemp.getValue();
		String In = this.comboBoxInTemp.getValue();
		String valor = this.ValorTemp.getText();
		
		chartControlTemp chartController = new chartControlTemp(this.chartConvertTemp, this.xAxisTemp, this.yAxisTemp, In, Out, valor, this.resultTemp);
		
		if(!chartController.updateChart()){
	        Alert a = new Alert(AlertType.INFORMATION);
	        DialogPane dialogPane = a.getDialogPane();
	        dialogPane.getStylesheets().add(getClass().getResource("/conversor/view/App.css").toExternalForm());
	        dialogPane.getStyleClass().add("dialog");
	        
	        a.setTitle("Atencion");
	        a.setHeaderText("Se encontraron datos vacios.");
	        
	        if(Out == null) {
	        	 a.setContentText("Seleccione una escala de temperatura de salida.");
	        }
	        else if(In == null){
	        	 a.setContentText("Seleccione una escala de temperatura de entrada.");
	        }
	        else {
	        	if(!chartController.isOnlyNumbers()) {
		        	a.setContentText("Solo se permite el ingreso de numeros.");}
	        	else {
	        		a.setContentText("Ingrese el valor de temperatura que desea convertir.");
	        	}
	        }
			a.show();
		}
	}
}
