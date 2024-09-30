package controllers;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import Entity.LoanRequest;
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

public class SvLoanPageController implements Initializable {

	@FXML
	private TableColumn<LoanRequest, Button> approve;

	@FXML
	private Button credit_button;

	@FXML
	private TableColumn<LoanRequest, Float> loanAmount;

	@FXML
	private TableColumn<LoanRequest, Integer> numbersOfPayments;

	@FXML
	private TableView<LoanRequest> loanRequests;

	@FXML
	private Button frames_button;

	@FXML
	private TableColumn<LoanRequest, String> id;

	@FXML
	private Button loan_button;

	@FXML
	private Button logout_button;

	@FXML
	private Button mortgage_button;

	@FXML
	private TableColumn<LoanRequest, Button> reject;

	@FXML
	private TableColumn<LoanRequest, Date> requestDate;

	private LoanRequest rowData = null;

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

		Parent root = FXMLLoader.load(getClass().getResource("/supervisor/LoanPage.fxml"));

		Scene scene = new Scene(root);

		primaryStage.setTitle("Loans Requests");
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();

		primaryStage.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		id.setCellValueFactory(new PropertyValueFactory<LoanRequest, String>("id"));
		loanAmount.setCellValueFactory(new PropertyValueFactory<LoanRequest, Float>("loanAmount"));
		numbersOfPayments.setCellValueFactory(new PropertyValueFactory<LoanRequest, Integer>("numbersOfPayments"));
		requestDate.setCellValueFactory(new PropertyValueFactory<LoanRequest, Date>("requestDate"));
		loanRequests.getItems().setAll(mysqlConnection.getSvLoanRequests("Loan"));
		loanRequests.setRowFactory(request -> {
			TableRow<LoanRequest> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 1 && (!row.isEmpty())) {
					rowData = row.getItem();
				}
			});
			return row;
		});

		approve.setCellFactory(ActionButtonTableCell.<LoanRequest>forTableColumn("Approve", (LoanRequest p) -> {
			mysqlConnection.deleteLoanAndMortgageRequest(p.getRequestId());
			mysqlConnection.insertLoanSvResponse(p, "approve", "Loan", "Request has been approved");
			mysqlConnection.insertLoan(p.getId(), p.getLoanAmount(), p.getNumbersOfPayments());
			mysqlConnection.updateLastLogInAmount(p.getId(), p.getLoanAmount());
			loanRequests.getItems().setAll(mysqlConnection.getSvLoanRequests("Loan"));

			
			return p;
		}));

		reject.setCellFactory(ActionButtonTableCell.<LoanRequest>forTableColumn("Reject", (LoanRequest p) -> {
			mysqlConnection.deleteLoanAndMortgageRequest(p.getRequestId());
			mysqlConnection.insertLoanSvResponse(p, "reject", "Loan", "Request has been rejected");
			loanRequests.getItems().setAll(mysqlConnection.getSvLoanRequests("Loan"));
			return p;
		}));

	}

}
