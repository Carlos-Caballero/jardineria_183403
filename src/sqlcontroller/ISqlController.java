package sqlcontroller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class ISqlController {
        private static Statement st;
	
	public static Statement startConection(String user, String pass) {
		try {
		
	        String url = "jdbc:mysql://localhost:3306/jardineria?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDateTimeCode=false&serverTimezone=UTC";
                  //String url = "jdbc:sqlserver://localhost:1433;databaseName=jardineria";
	        Connection conn = DriverManager.getConnection(url,user,pass); 
	        st  = conn.createStatement();
	        return st;
		} catch (Exception e) {
			return null;
		}
	}
        
        public static Statement getConnection(){
            return st;
        }
	
	public static ResultSet executeViewQuery(String query,Statement st) {
		try {
			ResultSet rs = st.executeQuery(query);
			return rs;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;		
	}
	
	public static int executeDataManipulationQuery(String query,Statement st) {
		try {
			return st.executeUpdate(query);
		} catch (Exception e) {
			System.out.println(e.getMessage());
                        errorAlert("","Error" ,"Hubo un error en su solicitud");
			return 0;
		}
	}
	
	public static Statement closeConection(Statement st) {
		try {
			if(!st.isClosed()){
				st.close();
				
				return st;
			}
		} catch (Exception e) {
			
		}
		return st;
	}
        
        public static void errorAlert(String title, String header, String content){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();	
	}
}
