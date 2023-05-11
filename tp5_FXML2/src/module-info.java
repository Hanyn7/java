module tp5_FXML {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	requires java.sql;
	requires java.desktop;
	exports application;
	
	opens application to javafx.graphics, javafx.fxml;
}
