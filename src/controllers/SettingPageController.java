package controllers;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import server.mysqlConnection;

public class SettingPageController implements Initializable {

	public static String firstname;
	public static String lastname;

	@FXML
	private Button accountinformation_button;

	@FXML
	private Button activities_button;

	@FXML
	private Button changepassword_button;

	@FXML
	private Button logout_button;

	@FXML
	private Button foryou_button;

	@FXML
	private Button home_button;

	@FXML
	private Label myLabel;

	@FXML
	private Button personalinformation_button;

	@FXML
	private Button setting_button;

	@FXML
	void activities(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		ActivitiesPageController activitiesPageController = new ActivitiesPageController();
		try {
			activitiesPageController.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void logout(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		LoginPageController loginPageController = new LoginPageController();
		try {
			loginPageController.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void foryou(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		ForYouPageController foryouPageController = new ForYouPageController();
		try {
			foryouPageController.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void home(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		HomePageController homePageController = new HomePageController();
		try {
			float current_amount=mysqlConnection.calculateAmount(HomePageController.myId);
			mysqlConnection.updateAmount(HomePageController.myId,current_amount,LocalDateTime.now());
			homePageController.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void setting(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		SettingPageController settingPageController = new SettingPageController();
		try {
			settingPageController.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void accountinformation(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		AccountInformationPageController accountInformationPageController = new AccountInformationPageController();
		try {
			accountInformationPageController.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void changepassword(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		ChangePasswordPageController changePasswordPageController = new ChangePasswordPageController();
		try {
			changePasswordPageController.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void personalinformation(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		PersonalInformationPageController personalInformationPageController = new PersonalInformationPageController();
		try {
			personalInformationPageController.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/view/SettingPage.fxml"));

		Scene scene = new Scene(root);

		primaryStage.setTitle("Setting");
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.show();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		myLabel.setText("Hello Mr." + firstname + " " + lastname);
	}
}
