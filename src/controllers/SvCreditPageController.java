package controllers;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entity.CreditRequest;
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


public class SvCreditPageController implements Initializable {
	
	@FXML
	private TableView<CreditRequest> creditRequests;
	
	@FXML
	private TableColumn<CreditRequest, Button> approve;

	@FXML
	private TableColumn<CreditRequest, Float> creditAmount;
	
    @FXML
    private TableColumn<CreditRequest, String> id;
    
    @FXML
    private TableColumn<CreditRequest, Date> requestDate;
    
    @FXML
    private TableColumn<CreditRequest, Button> reject;
    
	public static ArrayList<CreditRequest> arr;
    
    private CreditRequest rowData=null;
    
	@FXML
	private Button credit_button;

	@FXML
	private Button frames_button;

	@FXML
	private Button loan_button;

	@FXML
	private Button logout_button;

	@FXML
	private Button mortgage_button;

	@FXML
	private Label myLabel1;

	@FXML
	void credit(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		SvCreditPageController svCreditPageController = new SvCreditPageController();
		try {
			svCreditPageController.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void frame(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		SvFramePageController svFramePageController = new SvFramePageController();
		try {
			svFramePageController.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void loan(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		SvLoanPageController svLoanPageController = new SvLoanPageController();
		try {
			svLoanPageController.start(stage);
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
	void mortgage(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		SvMortgagePageController svMortgagePageController = new SvMortgagePageController();
		try {
			svMortgagePageController.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/supervisor/CreditPage.fxml"));

		Scene scene = new Scene(root);

		primaryStage.setTitle("Credits Requests");
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();

		primaryStage.show();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		id.setCellValueFactory(new PropertyValueFactory<CreditRequest, String>("id"));
    	creditAmount.setCellValueFactory(new PropertyValueFactory<CreditRequest, Float>("creditAmount"));
    	requestDate.setCellValueFactory(new PropertyValueFactory<CreditRequest, Date>("requestDate"));
    	creditRequests.getItems().setAll(mysqlConnection.getSvCreditRequests("Credit"));
    	creditRequests.setRowFactory( request -> {
    	    TableRow<CreditRequest> row = new TableRow<>();
    	    row.setOnMouseClicked(event -> {
    	        if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
    	        	rowData = row.getItem();
    	        }
    	    });
    	    return row ;
    	});
    	
    	approve.setCellFactory(ActionButtonTableCell.<CreditRequest>forTableColumn("Approve", (CreditRequest p) -> {
    		mysqlConnection.deleteFrameAndCreditRequest(p.getRequestId());
    		mysqlConnection.inserCreditSvResponse(p,"approve", "Credit", "Request has been approved");
			mysqlConnection.insertCredit(p.getId(), p.getCreditAmount());
        	creditRequests.getItems().setAll(mysqlConnection.getSvCreditRequests("Credit"));
        	
    		return p;
    	}));
    	
    	reject.setCellFactory(ActionButtonTableCell.<CreditRequest>forTableColumn("Reject", (CreditRequest p) -> {
    		mysqlConnection.deleteFrameAndCreditRequest(p.getRequestId());
    		mysqlConnection.inserCreditSvResponse(p, "reject", "Credit", "Request has been rejected");
        	creditRequests.getItems().setAll(mysqlConnection.getSvCreditRequests("Credit"));
    		return p;
    	}));
 
	}
	
	
}
