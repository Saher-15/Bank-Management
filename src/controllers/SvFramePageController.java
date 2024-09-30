package controllers;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entity.FrameRequest;
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

public class SvFramePageController implements Initializable {

	@FXML
	private TableView<FrameRequest> frameRequests;

	@FXML
	private TableColumn<FrameRequest, Button> approve;

	@FXML
	private TableColumn<FrameRequest, Float> frameAmount;

	@FXML
	private TableColumn<FrameRequest, String> id;

	@FXML
	private TableColumn<FrameRequest, Date> requestDate;

	@FXML
	private TableColumn<FrameRequest, Button> reject;

	public static ArrayList<FrameRequest> arr;

	private FrameRequest rowData = null;

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

		Parent root = FXMLLoader.load(getClass().getResource("/supervisor/FramePage.fxml"));

		Scene scene = new Scene(root);

		primaryStage.setTitle("Frames Requests");
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();

		primaryStage.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		id.setCellValueFactory(new PropertyValueFactory<FrameRequest, String>("id"));
		frameAmount.setCellValueFactory(new PropertyValueFactory<FrameRequest, Float>("frameAmount"));
		requestDate.setCellValueFactory(new PropertyValueFactory<FrameRequest, Date>("requestDate"));
		frameRequests.getItems().setAll(mysqlConnection.getSvFrameRequests("Frame"));
		frameRequests.setRowFactory(request -> {
			TableRow<FrameRequest> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 1 && (!row.isEmpty())) {
					rowData = row.getItem();
				}
			});
			return row;
		});

		approve.setCellFactory(ActionButtonTableCell.<FrameRequest>forTableColumn("Approve", (FrameRequest p) -> {
			mysqlConnection.deleteFrameAndCreditRequest(p.getRequestId());
			mysqlConnection.insertFrameSvResponse(p, "approve", "Frame", "Request has been approved");
			mysqlConnection.updateFrame(p.getId(), p.getFrameAmount());
			frameRequests.getItems().setAll(mysqlConnection.getSvFrameRequests("Frame"));
			
			return p;
		}));

		reject.setCellFactory(ActionButtonTableCell.<FrameRequest>forTableColumn("Reject", (FrameRequest p) -> {
			mysqlConnection.deleteFrameAndCreditRequest(p.getRequestId());
			mysqlConnection.insertFrameSvResponse(p, "reject", "Frame", "Request has been rejected");
			frameRequests.getItems().setAll(mysqlConnection.getSvFrameRequests("Frame"));
			return p;
		}));

	}

}
