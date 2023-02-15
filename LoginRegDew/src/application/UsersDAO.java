package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UsersDAO {

	public static Users searchEmployee (String email, String pass) throws SQLException, ClassNotFoundException {
		String ss ="'"+email+"'";
		String pp = "'"+pass+"'";
        String selectStmt = "SELECT * FROM users WHERE users.email ="+ss+" and password ="+pp;
        
        try {
            ResultSet rsEmp = DBUtil.dbExecuteQuery(selectStmt);
            Users employee = getEmployeeFromResultSet(rsEmp);
            return employee;
        } catch (SQLException e) {
            System.out.println("While searching an employee with " + email + " and above password , an error occurred: " + e);
            throw e;
        }
    }
	
    private static Users getEmployeeFromResultSet(ResultSet rs) throws SQLException
    {
        Users emp = null;
        if (rs.next()) {
            emp = new Users();
            emp.setId(rs.getInt("id"));
            emp.setName(rs.getString("name"));
            emp.setEmail(rs.getString("email"));
            emp.setMob(rs.getString("mobile"));
            emp.setGender(rs.getString("gender"));
            emp.setDob(rs.getString("dob"));
            emp.setPassword(rs.getString("password"));
        }
        return emp;
    }
    public static void insertEmp (String name, String email, String mobile,String gender,String dob,String password) throws SQLException, ClassNotFoundException {
//    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//    	dob.format(formatter);
        String updateStmt =
                "BEGIN\n" +
                        "INSERT INTO users\n" +
                        "( NAME, EMAIL, MOBILE, GENDER,DOB,PASSWORD)\n" +
                        "VALUES\n" +
                        "( '"+name+"', '"+email+"','"+mobile+"', '"+gender+"', '"+dob+"', '"+password+"');\n" +
                        "END;";
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }
    

    public static ObservableList<Users> getAllEmp() throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM users";
        ObservableList<Users> empList = FXCollections.observableArrayList();
        try {
            ResultSet rsEmps = DBUtil.dbExecuteQuery(selectStmt);
            while (rsEmps.next()) {
                Users emp = new Users();
                emp.setName(rsEmps.getString("name"));
                System.out.println("Testing....Finding name:  "+rsEmps.getString("name"));
                emp.setDob(rsEmps.getString("dob"));
                emp.setMob(rsEmps.getString("mobile"));
                emp.setDob(rsEmps.getString("dob"));
                emp.setMob(rsEmps.getString("mobile"));
                emp.setDob(rsEmps.getString("email"));
                emp.setMob(rsEmps.getString("gender"));
                empList.add(emp);
            }
            return empList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }
}
