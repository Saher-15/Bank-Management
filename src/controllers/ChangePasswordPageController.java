package controllers;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import server.mysqlConnection;

public class ChangePasswordPageController {

	@FXML
	private PasswordField confirmpassword_txt;

	@FXML
	private Button back;

	@FXML
	private Button changepassword_button;

	@FXML
	private PasswordField changepassword_txt;

	@FXML
	private PasswordField currentpassword_txt;

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
	void changepassword(ActionEvent event) throws SQLException {

		Alert a = new Alert(AlertType.NONE);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		String str = "";
		if (changepassword_txt.getText().equals("")) {
			str = "You have to insert a password!";
		} else {
			str = mysqlConnection.changePassword(HomePageController.myId, currentpassword_txt.getText(),
					changepassword_txt.getText(), confirmpassword_txt.getText());
		}

		ChangePasswordPageController changePasswordPageController = new ChangePasswordPageController();

		try {
			changePasswordPageController.start(stage);
			if (str.equals("Password changed Successfully!")) {
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/view/ChangePasswordPage.fxml"));

		Scene scene = new Scene(root);

		primaryStage.setTitle("Change Password");
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.show();

	}
}
