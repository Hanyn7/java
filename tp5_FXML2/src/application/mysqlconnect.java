package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class mysqlconnect {
 Connection conn =null;
 private static String login = "root";

 public static Connection ConnectDb() {
	 try {
		 
		 Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/fxml",login,"");
		 Class.forName("com.mysql.cj.jdbc.Driver");

		 JOptionPane.showMessageDialog(null,"Connection Established");
		 return conn;
	 }catch(Exception e) {
		 JOptionPane.showMessageDialog(null,e);
		 return null;
	 }
 }
 public static ObservableList<Personne> getDatausers(){
	 Connection conn =ConnectDb();
	 ObservableList<Personne> list =FXCollections.observableArrayList();
	 try {
		 PreparedStatement ps =conn.prepareStatement("select * from personne");
		 ResultSet rs=ps.executeQuery();
		 while(rs.next()) {
			 list.add(new Personne(rs.getString("firstName"), rs.getString("lastName"),rs.getString("email")));
			 
		 }
	 }catch(Exception e) {
		 
	 }
	 return list;

 }
}
