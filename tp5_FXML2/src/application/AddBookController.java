package application;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableColumn.CellEditEvent;
public class AddBookController implements Initializable {

@FXML
private TextField tfLastName;
@FXML
private TextField tfFirstName;
@FXML
private TextField tfEmail;
@FXML
private Button addbtn;
@FXML
private Button exportbtn;
@FXML
private Button importbtn;
@FXML
private Button removebtn;
@FXML
private Button quitbtn;
@FXML
private TableView <Personne> table;
@FXML
private TableColumn <Personne, String> emailCol;
@FXML
private TableColumn <Personne, String> firstNameCol;
@FXML
private TableColumn <Personne, String> lastNameCol;
private DataClass data;

ObservableList<Personne> listM;
int index=-1;
Connection conn=null;
ResultSet rs =null;
PreparedStatement pst=null;


//A Completer
@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	firstNameCol.setCellValueFactory(new PropertyValueFactory<Personne, String>("firstName"));
	lastNameCol.setCellValueFactory(new PropertyValueFactory<Personne, String>("lastName"));
	emailCol.setCellValueFactory(new PropertyValueFactory<Personne, String>("email"));
	listM=mysqlconnect.getDatausers();
    table.setItems(listM);
	
}

public void add() {
	String lastName = tfLastName.getText();
    String firstName = tfFirstName.getText();
    String email = tfEmail.getText();

    if (lastName.isEmpty() || firstName.isEmpty() || email.isEmpty()) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Champ vide");
        alert.setHeaderText(null);
        alert.setContentText("Remplir tout les champs");
        alert.showAndWait();
    } else if(!isEmailAdress(email)) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Adresse e-mail invalide");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez entrer une adresse e-mail valide");
        alert.showAndWait();
        return;
    }else {
        Personne p = new Personne(lastName, firstName, email);
        table.getItems().add(p);
        tfLastName.clear();
        tfFirstName.clear();
        tfEmail.clear();
    }
}

public void remove() {
	int selectedIndex = table.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        table.getItems().remove(selectedIndex);
    }
}

public void importe() {
	List<Personne> importedPersons = DataClass.getImportList();
    if (importedPersons != null && !importedPersons.isEmpty()) {
        table.getItems().addAll(importedPersons);
    }
}

public void export() {
	DataClass data = new DataClass();
    List<Personne> exportList = table.getItems();
    data.setExportList(exportList);
}

public void quit() {
	System.exit(0);
}

public static boolean isEmailAdress(String email){
Pattern p = Pattern.compile("[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}");
Matcher m = p.matcher(email.toUpperCase());
return m.matches(); 
}

}