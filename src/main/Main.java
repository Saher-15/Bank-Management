
package main;

import controllers.LoginPageController;

import javafx.application.Application;
import javafx.stage.Stage;
import server.mysqlConnection;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		LoginPageController fram1 = new LoginPageController();
		mysqlConnection connection = new mysqlConnection();
		fram1.start(primaryStage);
		try {
			// Establish connection
			// connection.connectToDataBase();

			// Execute SQL queries
			// Example:
			// ResultSet resultSet = connection.executeQuery("SELECT * FROM mytable");
			// Process resultSet...
		} catch (Exception e) {
			System.out.println("Failed to connect to database.");
			e.printStackTrace();
		}
	}

}
