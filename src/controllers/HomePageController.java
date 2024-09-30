package controllers;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entity.Credit;
import Entity.Loan;
import Entity.Mortgage;
import Entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import server.mysqlConnection;

public class HomePageController implements Initializable {

	public static String myId;
	public static String firstname;
	public static String lastname;
	// public static float current_amount;

	@FXML
	private Label frame_txt;

	@FXML
	private Label visa;

	@FXML
	private Label visapayment;
	@FXML
	private Button activities_button;

	@FXML
	private Label balance;

	@FXML
	private Button logout_button;

	@FXML
	private Button foryou_button;

	@FXML
	private Button home_button;

	@FXML
	private ComboBox<Credit> credits;

	@FXML
	private ComboBox<Loan> loans;

	@FXML
	private ComboBox<Mortgage> mortgages;

	@FXML
	private Label myLabel1;

	@FXML
	private Label amount;

	@FXML
	private Label loan_next_payment;

	@FXML
	private Label mortgage_next_payment;

	@FXML
	private Label payment_title;

	@FXML
	private Label remain;

	@FXML
	private Label mortgage;

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
			float current_amount = mysqlConnection.calculateAmount(HomePageController.myId);
			mysqlConnection.updateAmount(HomePageController.myId, current_amount, LocalDateTime.now());
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

	void loanclick() {
		float nxtPayment = 0;
		Loan l1 = loans.getSelectionModel().getSelectedItem();
		if(l1.getNumbersOfPayments() != l1.getPaymentNumber()){
			nxtPayment = l1.getLoanAmountWithFee() / l1.getNumbersOfPayments();
			remain.setText(String.valueOf(l1.getLoanAmountWithFee() - l1.getPaymentNumber() * nxtPayment)+"$");
		} else {
			remain.setText("0.0$");
		}
		loan_next_payment.setText(String.valueOf(nxtPayment)+"$");
	}

	void creditclick() {
		Credit c1 = credits.getSelectionModel().getSelectedItem();
		visa.setText(String.valueOf(c1.getCreditAmount())+"$");
		visapayment.setText(String.valueOf(c1.getCreditTotalPays())+"$");
	}

	void mortgageclick() {
		float nxtPayment = 0;
		Mortgage m1 = mortgages.getSelectionModel().getSelectedItem();
		if(m1.getNumOfPaymment() != m1.getPaymentNumber()){
			nxtPayment = m1.getMortgageAmountWithFee() / m1.getNumOfPaymment();
		}
		mortgage.setText(String.valueOf(m1.getMortgageAmountWithFee() - m1.getPaymentNumber() * nxtPayment)+"$");
		mortgage_next_payment.setText(String.valueOf(nxtPayment)+"$");
	}

	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/view/HomePage.fxml"));

		Scene scene = new Scene(root);

		primaryStage.setTitle("Home");
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		amount.setAlignment(Pos.CENTER);
		remain.setAlignment(Pos.CENTER);
		visa.setAlignment(Pos.CENTER);
		visapayment.setAlignment(Pos.CENTER);
		loan_next_payment.setAlignment(Pos.CENTER);
		mortgage_next_payment.setAlignment(Pos.CENTER);
		mortgage.setAlignment(Pos.CENTER);

		loans.setPromptText("LOANS");
		//loans.setStyle("color: yellow;");
		credits.setPromptText("CREDITS");
		mortgages.setPromptText("MORTGAGES");
		User user = mysqlConnection.getUsersDetails(HomePageController.myId);
		if(user.getLastLogInAmount() < 0) {
			amount.setTextFill(Color.RED);
		} else {
			amount.setTextFill(Color.LIGHTGREEN);
		}
		amount.setText("My Amount "+String.valueOf(user.getLastLogInAmount() + "$"));
		myLabel1.setText("Hello Mr." + firstname + " " + lastname);
		frame_txt.setText("Your frame is : " + String.valueOf(user.getFrame())+"$");
		ArrayList<Loan> myLoans = mysqlConnection.getAllLoans(myId);
		ArrayList<Credit> myCredits = mysqlConnection.getAllCredits(myId);
		ArrayList<Mortgage> myMortgages = mysqlConnection.getAllMortgages(myId);

		myLoans.forEach((n) -> loans.getItems().add(n));
		myCredits.forEach((n) -> credits.getItems().add(n));
		myMortgages.forEach((n) -> mortgages.getItems().add(n));

		loans.setOnAction((event) -> {
			loanclick();
		});

		credits.setOnAction((event) -> {
			creditclick();
		});

		mortgages.setOnAction((event) -> {
			mortgageclick();
		});
	}

}
