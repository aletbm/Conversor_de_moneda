module Conversores {
	requires javafx.controls;
	requires javafx.fxml;
	requires okhttp3;
	requires org.json;
	requires javafx.base;
	requires javafx.graphics;
	
	opens conversor to javafx.graphics, javafx.fxml;
	opens conversor.controller to javafx.fxml;
}
