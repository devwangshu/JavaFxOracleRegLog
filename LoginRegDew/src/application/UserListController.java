
package application;
 
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import application.DBUtil;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
 
public class UserListController {

    
    @FXML
    private  TableView<Users> tableView;
    
    
    @FXML
    private TableColumn<Users, String> name;
    @FXML
    
    private TableColumn<Users, String> dob;
    @FXML
    private TableColumn<Users, String> mobile;
    
    @FXML
    private TableColumn<Users, String> email;
    
    
    @FXML
    private TableColumn<Users, String> gender;
    /**
     * Initializes the controller class.
     * @throws ParseException 
     */


    public void initialize() throws ClassNotFoundException, SQLException, ParseException {
    	
    	/*
    	// -- suggested way but something is missing or error
    	try {
    		name.setCellValueFactory(new PropertyValueFactory<Users,String>("name"));
            dob.setCellValueFactory(new PropertyValueFactory<Users,String>("dob"));
            mobile.setCellValueFactory(new PropertyValueFactory<Users,String>("mobile"));


            ObservableList<Users> empData = UsersDAO.getAllEmp();
            System.out.println(empData);
            empData.forEach((tab) -> { 
                System.out.println("Stuff with "+tab.getName());
            });
            tableView.setItems(empData);
            //tableView.getItems().addAll(empData);
            
            
        } catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        } 
    	
    	*/
    	
    	
    	
    	//---- another way (not suggested )
    	ResultSet rs=null;
    	try {
			String sql="select * from users";
			
			rs=DBUtil.dbExecuteQuery(sql);
			while(rs.next()){
				System.out.println(""+rs.getString("name")+":"+rs.getString("dob")+": "+rs.getString("mobile"));
//				name.setCellValueFactory(new PropertyValueFactory<Users, String>("Name"));
//				dob.setCellValueFactory(new PropertyValueFactory<Users, String>("Dob"));
//				mobile.setCellValueFactory(new PropertyValueFactory<Users, String>("Moile"));
				
				name.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getName()));
				dob.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getDob()));
				mobile.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getMob()));
				email.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getEmail()));
				gender.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getGender()));
				
				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			    Date date = format.parse(rs.getString("dob"));
			    System.out.println((date));
				
				ObservableList<Users> data = FXCollections.observableArrayList(
				         new Users(rs.getString("name"),rs.getString("dob"),rs.getString("mobile"),rs.getString("email"),rs.getString("gender"))
				);
				tableView.getItems().addAll(data);
				//tableView.setItems(data);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	//--- end another way---
    	 
    }

}
