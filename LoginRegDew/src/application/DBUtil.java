package application;
//import javax.sql.rowset.CachedRowSet;
//import com.sun.rowset.CachedRowSetImpl;
import java.sql.*;

public class DBUtil {
	
	// --- Get data from app properties file..
	private static final String host = AppProperties.getInstance().getProperty("host");
    private static final String db_user = AppProperties.getInstance().getProperty("db_user");
    private static final String db_pwd = AppProperties.getInstance().getProperty("db_pwd");
    private static final String sid = AppProperties.getInstance().getProperty("sid");
    private static final String port = AppProperties.getInstance().getProperty("port");
    //Declare JDBC Driver
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";

    //Connection
    private static Connection conn = null;

    //Connection String
    //String connStr = "jdbc:oracle:thin:Username/Password@IP:Port/SID";
    //Username=HR, Password=HR, IP=localhost, IP=1521, SID=xe
   // private static final String connStr = "jdbc:oracle:thin:system/cdac1234@localhost:1521/xe";
    private static final String connStr = "jdbc:oracle:thin:"+db_user+"/"+db_pwd+"@"+host+":"+port+"/"+sid+"";


    //Connect to DB
    public static void dbConnect() throws SQLException, ClassNotFoundException {
        //Setting Oracle JDBC Driver
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            throw e;
        }

        System.out.println("Oracle JDBC Driver Registered!");

        //Establish the Oracle Connection using Connection String
        try {
        	System.out.println(connStr);
            conn = DriverManager.getConnection(connStr);
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console" + e);
            e.printStackTrace();
            throw e;
        }
    }

    //Close Connection
    public static void dbDisconnect() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e){
           throw e;
        }
    }

    //DB Execute Query Operation
    public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        //Declare statement, resultSet and CachedResultSet as null
        Statement stmt = null;
        ResultSet resultSet = null;
        //CachedRowSet crs = null;
        try {
            //Connect to DB (Establish Oracle Connection)
            dbConnect();
            System.out.println("Select statement: " + queryStmt + "\n");

            //Create statement
            stmt = conn.createStatement();

            //Execute select (query) operation
            resultSet = stmt.executeQuery(queryStmt);

            //CachedRowSet Implementation
            //In order to prevent "java.sql.SQLRecoverableException: Closed Connection: next" error
            //We are using CachedRowSet
            //crs = new CachedRowSet();
            //crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);
            throw e;
        } finally {
            if (resultSet != null) {
                //Close resultSet
                //resultSet.close();
            }
            if (stmt != null) {
                //Close Statement
                //stmt.close();
            }
            //Close connection
            //dbDisconnect();
        }
        //Return CachedRowSet
        return resultSet;
    }

    //DB Execute Update (For Update/Insert/Delete) Operation
    public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
        //Declare statement as null
        Statement stmt = null;
        try {
            //Connect to DB (Establish Oracle Connection)
            dbConnect();
            //Create Statement
            stmt = conn.createStatement();
            //Run executeUpdate operation with given sql statement
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            if (stmt != null) {
                //Close statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
    }
}