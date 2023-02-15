
package application;
 
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
 
public class RegisterController {
	
	private String error_css = "-fx-text-box-border: red ;\r\n"
			+ "  -fx-focus-color: red ;";
	
	private String d1;

	@FXML
    private TextField textName, textEmail, textMobile, textPassword, textRePassword;
	
	@FXML
    private RadioButton radioButton,radioButton2;
	
	@FXML
	private Button btnRegister;
	
	@FXML
	private DatePicker dateDOB;
    
    @FXML private Hyperlink reg;
    
    @FXML
    ToggleGroup group;
    
    @FXML
    private void initialize() {
        group = new ToggleGroup();
        radioButton.setToggleGroup(group);
        radioButton2.setToggleGroup(group);
        radioButton.setSelected(true);
    }
    

    
    @FXML protected void handleRegisterButtonAction(ActionEvent event) throws SQLException, ClassNotFoundException {
//        System.out.println("Sign in button pressed");
    	//---- find the selected gender from radio button group----
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
        String toogleGroupValue = selectedRadioButton.getText();
        
        //-------end find radio
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try{
        	LocalDate localDate = dateDOB.getValue();
        	d1= localDate.format(formatter);
        }catch(NullPointerException ex) {}
        //---- validation start------
        if(textName.getText().isEmpty()) {
            textName.setStyle(error_css);
            return;
        }
        if(textEmail.getText().isEmpty()) {
        	textEmail.setStyle(error_css);
            return;
        }
        if(d1==null) {
        	dateDOB.setStyle(error_css);
            return;
        }
        if(textPassword.getText().isEmpty()) {
        	textPassword.setStyle(error_css);
            return;
        }
        if(textRePassword.getText().isEmpty()) {
        	textRePassword.setStyle(error_css);
            return;
        }
//        if(textPassword.getText()!=textRePassword.getText())
//        {
//        	Alert alert = new Alert(AlertType.ERROR);
//			alert.setTitle("Error Message");
//			alert.setContentText("Password and Re-entered password do not match.");
//			alert.show();
//        	return;
//        }
        try {
            UsersDAO.insertEmp(textName.getText(),textEmail.getText(),textMobile.getText(),toogleGroupValue,d1,textPassword.getText());

            Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Registration Message");
			alert.setContentText("Registration Success: Thank You..");
//			alert.show();
			alert.showAndWait().ifPresent(rs -> {
	    	    if (rs == ButtonType.OK) {
	    	        //System.out.println("Pressed OK.");
	    	        handleHyperLinkAction(event);
	    	    }
	    	    else {
	    	    	Platform.exit();
	    	    }
			});
        } catch (SQLException e) {
        	Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Registration Message");
			alert.setContentText("Registartion Fail: ");
			alert.show();
            throw e;
        }
		
    }
    
    
    @FXML protected void handleHyperLinkAction(ActionEvent event) {
    	try {
    		//--------- Link the new window----
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(new Scene(root1));  
            stage.show();
            
            //----- Close the Current Window-----------
            final Node source = (Node) event.getSource();
            final Stage stage1 = (Stage) source.getScene().getWindow();
            stage1.close();
            //----------end close----------
        } catch(Exception e) {
            e.printStackTrace();
        }
    	
    }
	
	    	

}
