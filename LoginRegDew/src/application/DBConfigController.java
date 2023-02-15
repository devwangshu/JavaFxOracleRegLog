
package application;
 
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.module.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
 
public class DBConfigController {
	
	 private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	 private static Connection conn = null;

	@FXML
    private TextField textHost, textUser, textPassword, textPort, textSid;
	
	@FXML
	private Label result;
	
	
	@FXML
	private Button btnSave;
	
	
    @FXML
    private void initialize() {

    }
    

    
    @FXML protected void handleSaveButtonAction(ActionEvent event) throws ClassNotFoundException, IOException {
    		System.out.println("Save in button pressed");
    		
//    		String propertiesFile = "C:\\Users\\user0581\\Dewangshu\\LoginRegDew\\src\\application\\app.properties";  
    		String currentDir = System.getProperty("user.dir");
    		
    		String propertiesFile = currentDir+"\\"+"app.properties";
    		System.out.println("Current Directory with file name:"+propertiesFile);
    		//Instantiating the properties file
    	      Properties props = new Properties();
    	      //Populating the properties file
    	      props.put("host", textHost.getText());
    	      props.put("db_user", textUser.getText());
    	      props.put("db_pwd", textPassword.getText());
    	      props.put("port", textPort.getText());
    	      props.put("sid", textSid.getText());
    	      //Instantiating the FileInputStream for output file
    	      String path = propertiesFile;
    	      FileOutputStream outputStrem = new FileOutputStream(path);
    	      //Storing the properties file
    	      props.store(outputStrem, "This is a sample properties file");
    	      System.out.println("Properties file created......");
    	      
    	      Alert alert = new Alert(AlertType.INFORMATION);
  			//alert.setTitle("Registration Message");
	  			alert.setContentText("Configuration File Saved Successfully.. ");
	  			alert.show();
	  			
	  		//----- Close the Current Window-----------
	            final Node source = (Node) event.getSource();
	            final Stage stage1 = (Stage) source.getScene().getWindow();
	            stage1.close();
	            //----------end close----------
		
    }
    @FXML protected void handleCloseButtonAction(ActionEvent event) throws ClassNotFoundException {
		System.out.println("Close in button pressed");
		Platform.exit();
	
    }
    @FXML protected void handleTestConnectionButtonAction(ActionEvent event) throws ClassNotFoundException, SQLException {
    	String connStr = "jdbc:oracle:thin:"+textUser.getText()+"/"+textPassword.getText()+"@"+textHost.getText()+":"+textPort.getText()+"/"+textSid.getText()+"";
    	try {
    		Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(connStr);
            result.setText("Connection Success");
            result.setTextFill(Color.GREEN);
            btnSave.setDisable(false);
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console" + e);
            result.setText("Connection Fail");
            result.setTextFill(Color.RED);
            e.printStackTrace();
            throw e;
        }
	
    }

	
	    	

}
