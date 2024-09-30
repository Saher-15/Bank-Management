package controllers;

import java.time.LocalDateTime;

import Entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import server.mysqlConnection;

public class LoginPageController {

	@FXML
	private TextField account_id_txt_field;

	@FXML
	private Button login_button;

	@FXML
	private PasswordField password_field;

	@FXML
	private Hyperlink signup_txt;

	@FXML
	void login(ActionEvent event) {
		HomePageController.myId = account_id_txt_field.getText();
		Alert a = new Alert(AlertType.NONE);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		boolean next = mysqlConnection.validatePassword(HomePageController.myId, password_field.getText());
		if (next) {
			float current_amount = mysqlConnection.calculateAmount(HomePageController.myId);
			mysqlConnection.updateAmount(HomePageController.myId, current_amount, LocalDateTime.now());
			User user = mysqlConnection.getUsersDetails(HomePageController.myId);

			HomePageController.firstname = user.getFirstName();
			HomePageController.lastname = user.getLastName();
			ActivitiesPageController.firstname = user.getFirstName();
			ActivitiesPageController.lastname = user.getLastName();
			ForYouPageController.firstname = user.getFirstName();
			ForYouPageController.lastname = user.getLastName();
			SettingPageController.firstname = user.getFirstName();
			SettingPageController.lastname = user.getLastName();
			AccountInformationPageController.accountId = user.getId();
			PersonalInformationPageController.phonenumber = user.getPhoneNumber();
			PersonalInformationPageController.address = user.getAddress();
			PersonalInformationPageController.email = user.getEmail();
			HomePageController homePageController = new HomePageController();
			SvCreditPageController svCreditPageController = new SvCreditPageController();

			try {
				if (user.getIfSupervisor())
					svCreditPageController.start(stage);
				else
					homePageController.start(stage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			a.setAlertType(AlertType.WARNING);
			a.setContentText("Incorrect username or password");
			// show the dialog
			a.show();
		}
	}

	@FXML
	void signup(ActionEvent event) {
		// Your sign-up logic here
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		SignUpPageController signupPageController = new SignUpPageController();
		try {
			signupPageController.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"));

		Scene scene = new Scene(root);

		primaryStage.setTitle("Login");
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.show();
	}
}
