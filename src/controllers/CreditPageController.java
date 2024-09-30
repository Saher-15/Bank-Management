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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import server.mysqlConnection;

public class CreditPageController implements Initializable{

	@FXML
	private Button askforcredit_btn;

	@FXML
	private Button back;

	@FXML
	private TextField creditline_text;

	@FXML
	void askforcredit(ActionEvent event) {
		Alert a = new Alert(AlertType.NONE);
		String registered = "";
		if(creditline_text.getText().equals("") ) {
			registered="You have to insert amount";
		}
		else {
		registered = mysqlConnection.creditFrameRequest(HomePageController.myId, Float.parseFloat(creditline_text.getText()), "Credit");
		}
		if (registered.equals("Request sent!")) {
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

	@FXML
	void back(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		ActivitiesPageController activitiesPageController = new ActivitiesPageController();
		try {
			activitiesPageController.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/view/CreditPage.fxml"));

		Scene scene = new Scene(root);

		primaryStage.setTitle("Credit");
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
		numericOnly(creditline_text);

	}
}
