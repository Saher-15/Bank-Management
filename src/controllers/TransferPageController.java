package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Entity.User;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import server.mysqlConnection;

public class TransferPageController implements Initializable  {

	@FXML
	private Button back;

	@FXML
	private TextField to_text;

	@FXML
	private Button transfer_btn;

	@FXML
	private TextField transferamount_text;

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

	@FXML
	void transfer(ActionEvent event) {
		Alert a = new Alert(AlertType.NONE);
		String registered = "Can not transfer this amount of money!";
		if (transferamount_text.getText().equals("")) {
			registered = "You have to insert amount to transfer!";
		} else if (to_text.getText().equals("")) {
			registered = "You have to insert bank account to transfer!";
		} else {
			User user = mysqlConnection.getUsersDetails(HomePageController.myId);
			if ((user.getLastLogInAmount() + user.getFrame()) - Float.parseFloat(transferamount_text.getText()) >= 0)
				registered = mysqlConnection.transferMoney(HomePageController.myId, to_text.getText(),
						Float.parseFloat(transferamount_text.getText()));
		}
		if (registered.equals("Money sent!")) {
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

		Parent root = FXMLLoader.load(getClass().getResource("/view/TransferPage.fxml"));

		Scene scene = new Scene(root);

		primaryStage.setTitle("Transfer");
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
		numericOnly(transferamount_text);

	}

}
