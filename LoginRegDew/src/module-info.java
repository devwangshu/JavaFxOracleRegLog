module LoginRegDew {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql.rowset;
	requires java.sql;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
}
