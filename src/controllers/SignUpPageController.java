package controllers;

import java.net.URL;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import server.mysqlConnection;

public class SignUpPageController implements Initializable{

	@FXML
	private TextField address_text;

	@FXML
	private TextField email_txt_field;

	@FXML
	private TextField firstname_txt_field;

	@FXML
	private TextField id_text;

	@FXML
	private TextField lastname_txt_field;

	@FXML
	private Hyperlink login_txt;

	@FXML
	private PasswordField password_field;

	@FXML
	private TextField phonenumber_txt;

	@FXML
	private Button signup_button;

	@FXML
	void login(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		LoginPageController LogInPageController = new LoginPageController();
		try {
			LogInPageController.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void signup(ActionEvent event) {
		Alert a = new Alert(AlertType.NONE);

		String registered = mysqlConnection.sign_up(id_text.getText(), password_field.getText(),
				firstname_txt_field.getText(), lastname_txt_field.getText(), phonenumber_txt.getText(),
				address_text.getText(), email_txt_field.getText(), false);

		if (registered.equals("Account Registred Successfully, You can login now...")) {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			LoginPageController LogInPageController = new LoginPageController();
			try {
				LogInPageController.start(stage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			a.setAlertType(AlertType.INFORMATION);
			a.setContentText(registered);
			// show the dialog
			a.show();
		} else {
			a.setAlertType(AlertType.WARNING);
			a.setContentText(registered);
			// show the dialog
			a.show();
		}

	}

	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/view/SignUpPage.fxml"));

		Scene scene = new Scene(root);

		primaryStage.setTitle("SignUp");
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.show();
	}
	
	public static void numericOnly(TextField field) {
	    field.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(
	                ObservableValue<? extends String> observable,
	                String oldValue, String newValue) {
	            if (!newValue.matches("\\d*")) 
	                field.setText(newValue.replaceAll("[^\\d]", ""));
	            
	        }
	    });
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		numericOnly(phonenumber_txt);

	}

}
