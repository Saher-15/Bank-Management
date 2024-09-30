package controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import server.mysqlConnection;

public class PersonalInformationPageController implements Initializable {
	public static String phonenumber;
	public static String email;
	public static String address;

	@FXML
	private Button back;

	@FXML
	private Label initaddress;

	@FXML
	private Label initemail;

	@FXML
	private Label initphone;

	@FXML
	private TextField changeaddress_txt;

	@FXML
	private TextField changeemail_txt;

	@FXML
	private TextField changephonenumber_txt;

	@FXML
	private Button submit1;

	@FXML
	private Button submit2;

	@FXML
	private Button submit3;

	@FXML
	void back(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		SettingPageController SettingPageController = new SettingPageController();
		try {
			SettingPageController.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void changeaddress(ActionEvent event) throws SQLException {
		Alert a = new Alert(AlertType.NONE);
		String str = "";
		if (changeaddress_txt.getText().equals("")) {
			str = "You have to insert address!";
		} else {
			str = mysqlConnection.changeAddress(HomePageController.myId, changeaddress_txt.getText());
		}
		if (str.equals("Address changed Successfully!")) {
			a.setAlertType(AlertType.INFORMATION);
			a.setContentText(str);
			// show the dialog
			a.show();
		} else {
			a.setAlertType(AlertType.WARNING);
			a.setContentText(str);
			// show the dialog
			a.show();
		}

	}

	@FXML
	void changeemail(ActionEvent event) throws SQLException {
		Alert a = new Alert(AlertType.NONE);
		String str = "";
		if (changeemail_txt.getText().equals("")) {

			str = "You have to insert email!";
		} else {
			str = mysqlConnection.changeEmail(HomePageController.myId, changeemail_txt.getText());
		}
		if (str.equals("Email changed Successfully!")) {
			a.setAlertType(AlertType.INFORMATION);
			a.setContentText(str);
			// show the dialog
			a.show();
		} else {
			a.setAlertType(AlertType.WARNING);
			a.setContentText(str);
			// show the dialog
			a.show();
		}

	}

	@FXML
	void changephonenumber(ActionEvent event) throws SQLException {
		Alert a = new Alert(AlertType.NONE);
		String str = "";
		if (changephonenumber_txt.getText().equals("")) {

			str = "You have to insert phone number!";
		} else {
			str = mysqlConnection.changePhoneNumber(HomePageController.myId, changephonenumber_txt.getText());
		}
		if (str.equals("PhoneNumber changed Successfully!")) {
			a.setAlertType(AlertType.INFORMATION);
			a.setContentText(str);
			// show the dialog
			a.show();
		} else {
			a.setAlertType(AlertType.WARNING);
			a.setContentText(str);
			// show the dialog
			a.show();
		}

	}

	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/view/PersonalInformationPage.fxml"));

		Scene scene = new Scene(root);

		primaryStage.setTitle("Personal Information");
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.show();

	}

	public static void numericOnly(TextField field) {
		field.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*"))
					field.setText(newValue.replaceAll("[^\\d]", ""));

			}
		});
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		// initphone.setText("asdas");
		initphone.setText("Current Number " + phonenumber);
		initemail.setText("Current Email " + email);
		initaddress.setText("Current Address " + address);
		numericOnly(changephonenumber_txt);
	}
}
