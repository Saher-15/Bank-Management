package controllers;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import Entity.CreditRequest;
import Entity.LoanRequest;
import Entity.MortgageRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import server.mysqlConnection;

public class SvMortgagePageController implements Initializable {

	@FXML
	private TableColumn<MortgageRequest, Button> approve;

	@FXML
	private Button credit_button;

	@FXML
	private Button frames_button;

	@FXML
	private TableColumn<MortgageRequest, String> id;

	@FXML
	private TableView<MortgageRequest> mortgageRequests;

	@FXML
	private Button loan_button;

	@FXML
	private Button logout_button;

	@FXML
	private TableColumn<MortgageRequest, Float> mortgageAmount;

	@FXML
	private Button mortgage_button;

	@FXML
	private TableColumn<MortgageRequest, Integer> numbersOfPayments;

	@FXML
	private TableColumn<MortgageRequest, Button> reject;

	@FXML
	private TableColumn<MortgageRequest, Date> requestDate;

	private MortgageRequest rowData = null;

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

		Parent root = FXMLLoader.load(getClass().getResource("/supervisor/MortgagePage.fxml"));

		Scene scene = new Scene(root);

		primaryStage.setTitle("Mortgages Requests");
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();

		primaryStage.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		id.setCellValueFactory(new PropertyValueFactory<MortgageRequest, String>("id"));
		mortgageAmount.setCellValueFactory(new PropertyValueFactory<MortgageRequest, Float>("mortgageAmount"));
		numbersOfPayments.setCellValueFactory(new PropertyValueFactory<MortgageRequest, Integer>("numbersOfPayments"));
		requestDate.setCellValueFactory(new PropertyValueFactory<MortgageRequest, Date>("requestDate"));
		mortgageRequests.getItems().setAll(mysqlConnection.getSvMortgageRequests("Mortgage"));
		mortgageRequests.setRowFactory(request -> {
			TableRow<MortgageRequest> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 1 && (!row.isEmpty())) {
					rowData = row.getItem();
				}
			});
			return row;
		});

		approve.setCellFactory(ActionButtonTableCell.<MortgageRequest>forTableColumn("Approve", (MortgageRequest p) -> {
			mysqlConnection.deleteLoanAndMortgageRequest(p.getRequestId());
			mysqlConnection.insertMortgageSvResponse(p, "approve", "Mortgage", "Request has been approved");
			mysqlConnection.insertMortgage(p.getId(), p.getMortgageAmount(), p.getNumbersOfPayments());
			mortgageRequests.getItems().setAll(mysqlConnection.getSvMortgageRequests("Mortgage"));

			return p;
		}));

		reject.setCellFactory(ActionButtonTableCell.<MortgageRequest>forTableColumn("Reject", (MortgageRequest p) -> {
			mysqlConnection.deleteLoanAndMortgageRequest(p.getRequestId());
			mysqlConnection.insertMortgageSvResponse(p, "reject", "Mortgage", "Request has been rejected");
			mortgageRequests.getItems().setAll(mysqlConnection.getSvMortgageRequests("Mortgage"));
			return p;
		}));

	}
}
