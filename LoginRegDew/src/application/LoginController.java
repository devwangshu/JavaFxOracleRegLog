
package application;
 
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.DBUtil;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
 
public class LoginController {
    @FXML private Text actiontarget;
    
    @FXML private Hyperlink reg;
    
    @FXML
    private TextField emailText, passwordField;
    public void initialize() {

    	emailText.setText("dew@example.com");
    	passwordField.setText("pass@123");

    }
    
    @FXML protected void handleSubmitButtonAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        
    	try {
            Users emp = UsersDAO.searchEmployee(emailText.getText(),passwordField.getText());
            if(emp == null) {
            	Alert alert = new Alert(AlertType.ERROR);
            	alert.setTitle("Login Message");
            	alert.setContentText("Login Fail: Invalid Username or Password");
            	alert.show();
            }
            else {
            	Alert alert = new Alert(AlertType.CONFIRMATION);
            	alert.setTitle("Login Message");
            	alert.setContentText("Login Success: "+emp.getName());
//            	alert.show();
            	alert.showAndWait().ifPresent(rs -> {
    	    	    if (rs == ButtonType.OK) {
    	    	        // Call the user List page
    	    	    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserList.fxml"));
    	                Parent root1;
						try {
							root1 = (Parent) fxmlLoader.load();
							Stage stage = new Stage();
	    	                stage.setScene(new Scene(root1));
	    	                stage.setResizable(false);
	    	                stage.show();
	    	                
	    	              //----- Close the Current Window-----------
	    	                final Node source = (Node) event.getSource();
	    	                final Stage stage1 = (Stage) source.getScene().getWindow();
	    	                stage1.close();
	    	                //----------end close----------
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
    	                
    	    	    }
    	    	    else {
    	    	    	Platform.exit();
    	    	    }
    			});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("Login Message");
        	alert.setContentText("Unable to Fetch DATA using "+emailText.getText()+" because"+e);
        	alert.show();
            throw e;
        }
    }
    
    @FXML protected void handleHyperLinkAction(ActionEvent event) {
    	
    	
    	/*Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Message Here...");
    	alert.setHeaderText("Look, an Information Dialog");
    	alert.setContentText("I have a great message for you!");
    	alert.showAndWait().ifPresent(rs -> {
    	    if (rs == ButtonType.OK) {
    	        System.out.println("Pressed OK.");
    	    }
    	}); */
    	try {
    		//--------- Link the new window----
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Register.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
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
    
	@FXML protected void handleExitAction(ActionEvent event) {
	    	
	    	
	    	Platform.exit();
    	
    }

}
