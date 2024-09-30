package controllers;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entity.CreditResponse;
import Entity.LoanRequest;
import Entity.Response;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import server.mysqlConnection;

public class ActivitiesPageController implements Initializable {
	public static String myId;
	public static String firstname;
	public static String lastname;

	private Response rowData=null;
	
    @FXML
    private TableColumn<Response, String> status;

    @FXML
    private TableColumn<Response, Integer> requestId;

    @FXML
    private TableView<Response> response;
    
    @FXML
    private TableColumn<Response, String> type;
    
    @FXML
    private TableColumn<Response, Date> responseDate;
    
	public static ArrayList<Response> arr;
	
	@FXML
	private Label myLabel1;

	@FXML
	private Button credit_button;

	@FXML
	private Button loan_button;

	@FXML
	private Button frame_button;

	@FXML
	private Button transfer_button;

	@FXML
	private Button activities_button;

	@FXML
	private Button mortgage_button;

	@FXML
	private Button logout_button;

	@FXML
	private Button foryou_button;

	@FXML
	private Button setting_button;

	@FXML
	private Button home_button;

	@FXML
	void credit(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		CreditPageController creditPageController = new CreditPageController();
		try {
			creditPageController.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void loan(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		LoanPageController loanPageController = new LoanPageController();
		try {
			loanPageController.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void mortgage(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		MortgagePageController mortgagePageController = new MortgagePageController();
		try {
			mortgagePageController.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void transfer(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		TransferPageController transferPageController = new TransferPageController();
		try {
			transferPageController.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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

	@FXML
	void frame(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FramePageController framePageController = new FramePageController();
		try {
			framePageController.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/view/ActivitiesPage.fxml"));

		Scene scene = new Scene(root);

		primaryStage.setTitle("Activities");
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		myLabel1.setText("Hello Mr." + firstname + " " + lastname);
		requestId.setCellValueFactory(new PropertyValueFactory<Response, Integer>("requestId"));
		status.setCellValueFactory(new PropertyValueFactory<Response, String>("status"));
		responseDate.setCellValueFactory(new PropertyValueFactory<Response, Date>("responseDate"));
		type.setCellValueFactory(new PropertyValueFactory<Response, String>("type"));
		ArrayList<Response> arr = new ArrayList<>();
		arr = mysqlConnection.getFrameAndCreditResponses(HomePageController.myId);
		arr.addAll(mysqlConnection.getLoanAndMortgageResponses(HomePageController.myId));
		response.getItems().setAll(arr);
		response.setRowFactory(request -> {
			TableRow<Response> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 1 && (!row.isEmpty())) {
					rowData = row.getItem();
				}
			});
			return row;
		});
	}
}
