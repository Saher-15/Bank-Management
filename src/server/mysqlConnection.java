package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;

import Entity.Credit;
import Entity.CreditRequest;
import Entity.FrameRequest;
import Entity.Loan;
import Entity.LoanRequest;
import Entity.Mortgage;
import Entity.MortgageRequest;
import Entity.Response;
import Entity.User;
import controllers.HomePageController;

public class mysqlConnection {
	static Connection conn;
	// mysql://um48gnh5wg5r5ena:LApuucfs1IKw4M77c03z@b8ncbhbif2q9ysj6rwoa-mysql.services.clever-cloud.com:3306/b8ncbhbif2q9ysj6rwoa

	public static String db_name = "jdbc:mysql://um48gnh5wg5r5ena:LApuucfs1IKw4M77c03z@b8ncbhbif2q9ysj6rwoa-mysql.services.clever-cloud.com:3306/b8ncbhbif2q9ysj6rwoa";
	public static String db_user = "um48gnh5wg5r5ena";
	public static String db_password = "LApuucfs1IKw4M77c03z";

	public static void connectToDataBase() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			System.out.println("Driver definition succeed");
		} catch (Exception ex) {
			/* handle the error */
			System.out.println("Driver definition failed");
		}

		try {
			// Connection conn = DriverManager.getConnection(db_name, db_user, db_password);
			Connection conn = DriverManager.getConnection(db_name + "?user=" + db_user + "&password=" + db_password
					+ "&characterEncoding=utf8&useSSL=false&useUnicode=true");

			// DriverManager.getConnection("jdbc:mysql://192.168.3.68/test","root","Root");
			System.out.println("SQL connection succeed");
		} catch (SQLException ex) {/* handle any errors */
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		System.out.println("Connected to DataBase");

	}

	public static Connection getConnection() {

		if (conn == null)
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(db_name + "?user=" + db_user + "&password=" + db_password
						+ "&characterEncoding=utf8&useSSL=false&useUnicode=true");
			} catch (Exception throwables) {
				throwables.printStackTrace();
			}

		return conn;
	}

	public static boolean validatePassword(String id, String password) {
		try {

			Statement st = getConnection().createStatement();
			ResultSet rs = st
					.executeQuery("select distinct * from users WHERE Id='" + id + "' AND Password='" + password + "'");

			if (rs.next()) {
				return true;
			}
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static User getUsersDetails(String id) {
		try {

			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery("select distinct * from users WHERE Id='" + id + "'");

			if (rs.next()) {

				User user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getFloat(8),
						rs.getTimestamp(9).toLocalDateTime(), rs.getBoolean(10), rs.getFloat(11));
				return user;
			}
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String sign_up(String id, String password, String firstname, String lastname, String phonenumber,
			String address, String email, boolean ifSupervisor) {

		// String result = "Database Connection Successful\n";
		Statement st = null;
		String res;

		try {
			st = getConnection().createStatement();
			ResultSet rs = st.executeQuery("select distinct * from users WHERE Id='" + id + "'");
			if (id.equals("")) {
				return "You have to insert your ID";
			}
			if (rs.next()) {
				res = "Account is Already in our system";
			} else {
				st.executeUpdate(
						"INSERT INTO users (Id, Password, FirstName, LastName, PhoneNumber, Address, Email, LastLogInAmount, DateOfLastLogInAmount, IfSupervisor, Frame)  VALUES('"
								+ id + "', '" + password + "','" + firstname + "','" + lastname + "','" + phonenumber
								+ "','" + address + "','" + email + "','" + 0 + "','"
								+ new java.sql.Date(Calendar.getInstance().getTime().getTime()) + "', " + ifSupervisor
								+ ", " + 0 + ")");

				res = "Account Registred Successfully, You can login now...";
			}
			st.close();

			return res;
		} catch (Exception throwables) {
			throwables.printStackTrace();
			return "something wrong";
		}

	}

	public static void updateLastLogInAmount(String id, float Amount) {
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery("select distinct * from users WHERE Id='" + id + "'");
			if (rs.next()) {
				st.executeUpdate("UPDATE users SET LastLogInAmount = '" + (rs.getFloat(8) + Amount)
						+ "', DateOfLastLogInAmount = '" + Timestamp.valueOf(LocalDateTime.now()) + "' WHERE ID='" + id
						+ "'");
			}
			st.close();

		} catch (Exception throwables) {
			throwables.printStackTrace();
		}
	}

	public static void updateFrame(String id, float frame) {
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery("select distinct * from users WHERE Id='" + id + "'");
			if (rs.next()) {

				st.executeUpdate("UPDATE users SET Frame = '" + frame + "' WHERE ID='" + id + "'");
			}
			st.close();

		} catch (Exception throwables) {
			throwables.printStackTrace();
		}
	}

	public static Loan getLoan(String id, String loanid) {
		try {

			Statement st = getConnection().createStatement();
			ResultSet rs = st
					.executeQuery("select distinct * from loans WHERE Id='" + id + "' AND LoanId='" + loanid + "'");

			if (rs.next()) {
				Loan loan = new Loan(rs.getString(1), rs.getString(2), rs.getFloat(3), rs.getFloat(4), rs.getInt(5),
						rs.getInt(6), rs.getTimestamp(7).toLocalDateTime(), rs.getInt(8));
				return loan;
			}
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<Loan> getAllLoans(String id) {
		ArrayList<Loan> loans = new ArrayList<>();
		try {

			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery("select distinct * from loans WHERE Id='" + id + "'");

			while (rs.next()) {
				Loan loan = new Loan(rs.getString(1), rs.getString(2), rs.getFloat(3), rs.getFloat(4), rs.getInt(5),
						rs.getInt(6), rs.getTimestamp(7).toLocalDateTime(), rs.getInt(8));
				loans.add(loan);
			}
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loans;
	}

	public static Credit getCredit(String id, String creditid) {
		try {

			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery(
					"select distinct * from credits WHERE Id='" + id + "' AND CreditId='" + creditid + "'");

			if (rs.next()) {
				Credit credit = new Credit(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getFloat(4), rs.getInt(5),
						rs.getTimestamp(6).toLocalDateTime());
				return credit;
			}
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<Credit> getAllCredits(String id) {
		ArrayList<Credit> credits = new ArrayList<>();
		try {

			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery("select distinct * from credits WHERE Id='" + id + "'");

			while (rs.next()) {
				Credit credit = new Credit(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getFloat(4), rs.getInt(5),
						rs.getTimestamp(6).toLocalDateTime());
				credits.add(credit);
			}
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return credits;
	}

	public static Mortgage getMortgage(String id, String mortgageid) {
		try {

			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery(
					"select distinct * from mortgages WHERE Id='" + id + "' AND MortgageId='" + mortgageid + "'");

			if (rs.next()) {
				Mortgage mortgage = new Mortgage(rs.getString(1), rs.getString(2), rs.getFloat(3), rs.getFloat(4),
						rs.getInt(5), rs.getInt(6), rs.getTimestamp(7).toLocalDateTime(), rs.getInt(8));
				return mortgage;
			}
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<Mortgage> getAllMortgages(String id) {
		ArrayList<Mortgage> mortgages = new ArrayList<>();
		try {

			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery("select distinct * from mortgages WHERE Id='" + id + "'");

			while (rs.next()) {
				Mortgage mortgage = new Mortgage(rs.getString(1), rs.getString(2), rs.getFloat(3), rs.getFloat(4),
						rs.getInt(5), rs.getInt(6), rs.getTimestamp(7).toLocalDateTime(), rs.getInt(8));
				mortgages.add(mortgage);
			}
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mortgages;
	}

	public static void updateLastAmountIncludedIncome(int id) {
		try {
			Statement st = getConnection().createStatement();
			st.executeUpdate("UPDATE income SET LastAmountIncluded = '" + 1 + "' WHERE ReceiveId='" + id + "'");
			st.close();

		} catch (Exception throwables) {
			throwables.printStackTrace();
		}
	}

	public static void updateLastAmountIncludedOutcome(int id) {
		try {
			Statement st = getConnection().createStatement();
			st.executeUpdate("UPDATE outcome SET LastAmountIncluded = '" + 1 + "' WHERE TransferId='" + id + "'");
			st.close();

		} catch (Exception throwables) {
			throwables.printStackTrace();
		}
	}

	public static float calculateAmount(String id) {
		float current_Amount = 0;
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery("select distinct * from users WHERE Id='" + id + "'");
			if (rs.next()) {
				User user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getFloat(8),
						rs.getTimestamp(9).toLocalDateTime().minusHours(2), rs.getBoolean(10), rs.getFloat(11));
				current_Amount = user.getLastLogInAmount();
				rs = st.executeQuery("select * from income WHERE DestId='" + id + "' AND TransferDate>='"
						+ user.getDateOfLastLogInAmount() + "' AND TransferDate<='" + LocalDateTime.now()
						+ "' AND LastAmountIncluded!='" + 1 + "'");
				while (rs.next()) {
					current_Amount += rs.getFloat(4);
					updateLastAmountIncludedIncome(rs.getInt(1));
				}
				rs = st.executeQuery("select * from outcome WHERE SrcId='" + id + "' AND TransferDate>='"
						+ user.getDateOfLastLogInAmount() + "' AND TransferDate<='" + LocalDateTime.now()
						+ "' AND LastAmountIncluded!='" + 1 + "'");
				while (rs.next()) {
					current_Amount -= rs.getFloat(4);
					updateLastAmountIncludedOutcome(rs.getInt(1));
				}
				rs = st.executeQuery("select CreditTotalPays from credits WHERE Id='" + id + "' AND CreditDate>='"
						+ user.getDateOfLastLogInAmount() + "' AND CreditDate<='" + LocalDateTime.now() + "'");
				while (rs.next()) {
					current_Amount -= rs.getFloat(1);
				}
				current_Amount -= getLoansTotalOutComeAmount(id, user, st);
				current_Amount -= getMortgagesTotalOutComeAmount(id, user, st);
				return current_Amount;
			}
			st.close();
		} catch (Exception throwables) {
			throwables.printStackTrace();
			return 0;
		}
		return current_Amount;
	}

	public static float getMortgagesTotalOutComeAmount(String id, User user, Statement st) {
		float totalLoanAmount = 0;
		long diff = ChronoUnit.MONTHS.between(user.getDateOfLastLogInAmount(), LocalDateTime.now());
		try {
			// Statement st = getConnection().createStatement();
			ResultSet rs;
			rs = st.executeQuery("select * from mortgages WHERE Id='" + id + "'");
			while (rs.next()) {
				Mortgage mortgage = new Mortgage(id, rs.getString(2), rs.getFloat(3), rs.getFloat(4), rs.getInt(5),
						rs.getInt(6), rs.getTimestamp(7).toLocalDateTime(), rs.getInt(8));
				if (mortgage.getMortgageStartDate()
						.compareTo(user.getDateOfLastLogInAmount().minusMonths(mortgage.getNumOfPaymment() - 1)) > 0) {
					totalLoanAmount += (mortgage.getMortgageAmountWithFee() / mortgage.getNumOfPaymment()) * diff;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return totalLoanAmount;
	}

	public static float getSumOfIncomes(String id, int month, int year) {
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs;
			rs = st.executeQuery("select SUM(Amount) from income WHERE DestId='" + id + "' AND MONTH(TransferDate)='"
					+ month + "' AND YEAR(TransferDate) = '" + year + "'");
			if (rs.next()) {
				return rs.getFloat(1);
			}
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static boolean isLoanFinish(LocalDateTime loanStartDate, int numOfPayment) {
		if (loanStartDate.compareTo(LocalDateTime.now()) > 0) {
			return true;
		}
		return loanStartDate.plusMonths(numOfPayment).compareTo(LocalDateTime.now()) <= 0;
	}

	public static boolean isMortgageFinish(LocalDateTime mortgageStartDate, int numOfPayment) {
		if (mortgageStartDate.compareTo(LocalDateTime.now()) > 0)
			return true;
		return mortgageStartDate.plusMonths(numOfPayment).compareTo(LocalDateTime.now()) <= 0;
	}

	public static float getSumOfOutcomes(String id, int month, int year) {
		float sum = 0;
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs;
			rs = st.executeQuery("select SUM(Amount) from outcome WHERE SrcId='" + id + "' AND MONTH(TransferDate)='"
					+ month + "' AND YEAR(TransferDate) = '" + year + "'");
			if (rs.next()) {
				sum += rs.getFloat(1);
			}
			st.close();
			sum += getSumOfLoans(id, month, year);
			sum += getSumOfMortgages(id, month, year);
			sum += getSumOfCredits(id, month, year);
			return sum;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	private static float getSumOfCredits(String id, int month, int year) {
		float result = 0;
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs;
			rs = st.executeQuery("select SUM(CreditTotalPays) from credits WHERE Id='" + id
					+ "' AND MONTH(CreditDate)='" + month + "' AND YEAR(CreditDate) = '" + year + "'");
			if (rs.next()) {
				result = rs.getFloat(1);
				st.close();
				return result;
			}
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	private static float getSumOfLoans(String id, int month, int year) {
		float sum = 0;
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs;
			rs = st.executeQuery("select * from loans WHERE Id='" + id + "'");
			while (rs.next()) {
				if (!isLoanFinish(rs.getTimestamp(7).toLocalDateTime(), rs.getInt(5))) {
					sum += rs.getFloat(4) / rs.getInt(5);
				}
			}
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sum;
	}

	private static float getSumOfMortgages(String id, int month, int year) {
		float sum = 0;
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs;
			rs = st.executeQuery("select * from mortgages WHERE Id='" + id + "'");
			while (rs.next()) {
				if (!isMortgageFinish(rs.getTimestamp(7).toLocalDateTime(), rs.getInt(5))) {
					sum += rs.getFloat(4) / rs.getInt(5);
				}
			}
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sum;
	}

	public static float getLoansTotalOutComeAmount(String id, User user, Statement st) {
		float totalLoanAmount = 0;
		long diff;
		long diff1 = ChronoUnit.MONTHS.between(user.getDateOfLastLogInAmount(), LocalDateTime.now());
		if (user.getDateOfLastLogInAmount().getDayOfMonth() > LocalDateTime.now().getDayOfMonth()) {
			diff1++;
		}
		try {
			//Statement st = getConnection().createStatement();
			ResultSet rs;
			rs = st.executeQuery("select * from loans WHERE Id='" + id + "'");
			while (rs.next()) {
				Loan loan = new Loan(id, rs.getString(2), rs.getFloat(3), rs.getFloat(4), rs.getInt(5), rs.getInt(6),
						rs.getTimestamp(7).toLocalDateTime(), rs.getInt(8));
				long diff2 = ChronoUnit.MONTHS.between(loan.getLoanStartDate(), LocalDateTime.now()) + 1;
				diff = diff1 < diff2 ? diff1 : diff2;

				if (loan.getLoanStartDate()
						.compareTo(user.getDateOfLastLogInAmount().minusMonths(loan.getNumbersOfPayments() - 1)) > 0) {
					totalLoanAmount += (loan.getLoanAmountWithFee() / loan.getNumbersOfPayments()) * diff;
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return totalLoanAmount;
	}

	public static void updateAmount(String id, float updateLastLogInAmount, LocalDateTime lastLogInDate) {
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery("select distinct * from users WHERE Id='" + id + "'");
			if (rs.next()) {

				st.executeUpdate(
						"UPDATE users SET LastLogInAmount = '" + updateLastLogInAmount + "', DateOfLastLogInAmount = '"
								+ Timestamp.valueOf(lastLogInDate) + "' WHERE ID='" + id + "'");
			}
			st.close();

		} catch (Exception throwables) {
			throwables.printStackTrace();
		}
	}

	public static String changePassword(String id, String currentPassword, String newPassword, String confirmPassword)
			throws SQLException {

		String res = null;
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery("select distinct * from users WHERE Id='" + id + "'");
			if (rs.next()) {

				if (currentPassword.equals(rs.getString(2))) {
					if (newPassword.equals(confirmPassword)) {
						st.executeUpdate("UPDATE users SET Password = '" + newPassword + "' WHERE ID='" + id + "'");

						res = "Password changed Successfully!";
					} else {
						res = "Password doesn't match!";
					}
				} else {
					res = "Incorrect password!";
				}
			}
			st.close();

			return res;
		} catch (Exception throwables) {
			throwables.printStackTrace();
			return "something wrong";
		}
	}

	public static String changeEmail(String id, String newEmail) throws SQLException {

		String res = null;
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery("select distinct * from users WHERE Id='" + id + "'");
			if (rs.next()) {

				st.executeUpdate("UPDATE users SET Email = '" + newEmail + "' WHERE ID='" + id + "'");

				res = "Email changed Successfully!";
			}

			st.close();

			return res;
		} catch (Exception throwables) {
			throwables.printStackTrace();
			return "something wrong";
		}
	}

	public static String changeAddress(String id, String newAddress) throws SQLException {

		String res = null;
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery("select distinct * from users WHERE Id='" + id + "'");
			if (rs.next()) {

				st.executeUpdate("UPDATE users SET Address = '" + newAddress + "' WHERE ID='" + id + "'");

				res = "Address changed Successfully!";
			}

			st.close();

			return res;
		} catch (Exception throwables) {
			throwables.printStackTrace();
			return "something wrong";
		}
	}

	public static String changePhoneNumber(String id, String newPhoneNmber) throws SQLException {

		String res = null;
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery("select distinct * from users WHERE Id='" + id + "'");
			if (rs.next()) {

				st.executeUpdate("UPDATE users SET PhoneNumber = '" + newPhoneNmber + "' WHERE ID='" + id + "'");

				res = "PhoneNumber changed Successfully!";
			}

			st.close();

			return res;
		} catch (Exception throwables) {
			throwables.printStackTrace();
			return "something wrong";
		}
	}

	public static String transferMoney(String srcId, String destId, float amount) {
		Statement st = null;
		int maxValue = 0; // Initialize maxValue to the lowest possible float value
		String res = "failed!";

		if (!srcId.equals(destId)) {
			try {
				st = getConnection().createStatement();
				// Check if the outcome table is empty
				ResultSet rs = st.executeQuery("SELECT * FROM outcome");
				if (rs.next()) {
					rs = st.executeQuery("SELECT MAX(TransferId) FROM outcome");
					if (rs.next()) {
						maxValue = rs.getInt(1) + 1; // Retrieve the maximum value from the result set
					}
				}

				st.executeUpdate(
						"INSERT INTO outcome (TransferId, SrcId, DestId, Amount, TransferDate, LastAmountIncluded) "
								+ "VALUES(" + maxValue + ", '" + srcId + "', '" + destId + "', " + amount + ", '"
								+ LocalDateTime.now() + "', " + 0 + ")");

				st.executeUpdate(
						"INSERT INTO income (ReceiveId, SrcId, DestId, Amount, TransferDate, LastAmountIncluded) "
								+ "VALUES(" + maxValue + ", '" + srcId + "', '" + destId + "', " + amount + ", '"
								+ LocalDateTime.now() + "', " + 0 + ")");

				float current_amount = calculateAmount(srcId);
				updateAmount(HomePageController.myId, current_amount, LocalDateTime.now());
				res = "Money sent!";
				st.close();
			} catch (Exception throwables) {
				throwables.printStackTrace();
				res = "something wrong";
			}

			return res;
		}
		return "Cant transfer money to your account!";
	}

	public static String creditFrameRequest(String id, float amount, String type) {

		Statement st = null;
		int requestId = 0;
		String res = "failed!";
		try {
			st = getConnection().createStatement();
			// Check if the outcome table is empty
			ResultSet rs = st.executeQuery("SELECT * FROM frameAndCreditRequest");
			if (rs.next()) {
				rs = st.executeQuery("SELECT MAX(requestId) FROM frameAndCreditRequest");
				if (rs.next()) {
					requestId = rs.getInt(1) + 1; // Retrieve the maximum value from the result set
				}
			}
			st.executeUpdate(
					"INSERT INTO frameAndCreditRequest (requestId, userId, creditAmount, requestDate,requestType ) "
							+ "VALUES(" + requestId + ", '" + id + "', " + amount + ", '" + LocalDateTime.now() + "', '"
							+ type + "')");

			res = "Request sent! You requestId is " + String.valueOf(requestId);
			st.close();
		} catch (Exception throwables) {
			throwables.printStackTrace();
			res = "something wrong";
		}
		return res;
	}

	public static String loanMortgageRequest(String id, float amount, int numofpayments, String type) {

		Statement st = null;
		int requestId = 0;
		String res = "failed!";
		try {
			st = getConnection().createStatement();
			// Check if the outcome table is empty
			ResultSet rs = st.executeQuery("SELECT * FROM loanAndMortgageRequest");
			if (rs.next()) {
				rs = st.executeQuery("SELECT MAX(requestId) FROM loanAndMortgageRequest");
				if (rs.next()) {
					requestId = rs.getInt(1) + 1; // Retrieve the maximum value from the result set
				}
			}
			st.executeUpdate(
					"INSERT INTO loanAndMortgageRequest (requestId, userId, amount,numbersOfPayments, requestDate,requestType ) "
							+ "VALUES(" + requestId + ", '" + id + "', " + amount + "," + numofpayments + ", '"
							+ LocalDateTime.now() + "', '" + type + "')");

			res = "Request sent! You requestId is " + String.valueOf(requestId);
			st.close();
		} catch (Exception throwables) {
			throwables.printStackTrace();
			res = "something wrong";
		}

		return res;
	}

	public static ArrayList<CreditRequest> getSvCreditRequests(String type) {
		ArrayList<CreditRequest> result = new ArrayList<>();
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from frameAndCreditRequest WHERE requestType='" + type + "'");
			while (rs.next()) {
				result.add(new CreditRequest(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getDate(4)));
			}
			st.close();
			return result;
		} catch (Exception throwables) {
			throwables.printStackTrace();
			return null;
		}
	}

	public static ArrayList<FrameRequest> getSvFrameRequests(String type) {
		ArrayList<FrameRequest> result = new ArrayList<>();
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from frameAndCreditRequest WHERE requestType='" + type + "'");
			while (rs.next()) {
				result.add(new FrameRequest(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getDate(4)));
			}
			st.close();
			return result;
		} catch (Exception throwables) {
			throwables.printStackTrace();
			return null;
		}
	}

	public static ArrayList<LoanRequest> getSvLoanRequests(String type) {
		ArrayList<LoanRequest> result = new ArrayList<>();
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from loanAndMortgageRequest WHERE requestType='" + type + "'");
			while (rs.next()) {
				result.add(new LoanRequest(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getDate(5)));
			}
			st.close();
			return result;
		} catch (Exception throwables) {
			throwables.printStackTrace();
			return null;
		}
	}

	public static ArrayList<MortgageRequest> getSvMortgageRequests(String type) {
		ArrayList<MortgageRequest> result = new ArrayList<>();
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from loanAndMortgageRequest WHERE requestType='" + type + "'");
			while (rs.next()) {
				result.add(new MortgageRequest(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4),
						rs.getDate(5)));
			}
			st.close();
			return result;
		} catch (Exception throwables) {
			throwables.printStackTrace();
			return null;
		}
	}

	public static void inserCreditSvResponse(CreditRequest creditRequest, String status, String responseType,
			String svResponse) {
		int responseId = 0;
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM frameAndCreditResponse");
			if (rs.next()) {
				rs = st.executeQuery("SELECT MAX(responseId) FROM frameAndCreditResponse");
				if (rs.next()) {
					responseId = rs.getInt(1) + 1; // Retrieve the maximum value from the result set
				}
			}
			st.executeUpdate(
					"INSERT INTO frameAndCreditResponse (responseId, requestId, userId, status, svResponse, responseType, responseDate)"
							+ "VALUES(" + responseId + ", '" + creditRequest.getRequestId() + "', '"
							+ creditRequest.getId() + "', '" + status + "', '" + svResponse + "', '" + responseType
							+ "', '" + LocalDateTime.now() + "')");
			st.close();
			// return result;
		} catch (Exception throwables) {
			throwables.printStackTrace();
			// return null;
		}
	}

	public static void insertFrameSvResponse(FrameRequest frameRequest, String status, String responseType,
			String svResponse) {
		int responseId = 0;
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM frameAndCreditResponse");
			if (rs.next()) {
				rs = st.executeQuery("SELECT MAX(responseId) FROM frameAndCreditResponse");
				if (rs.next()) {
					responseId = rs.getInt(1) + 1; // Retrieve the maximum value from the result set
				}
			}
			st.executeUpdate(
					"INSERT INTO frameAndCreditResponse (responseId, requestId, userId, status, svResponse, responseType, responseDate)"
							+ "VALUES(" + responseId + ", '" + frameRequest.getRequestId() + "', '"
							+ frameRequest.getId() + "', '" + status + "', '" + svResponse + "', '" + responseType
							+ "', '" + LocalDateTime.now() + "')");
			st.close();
			// return result;
		} catch (Exception throwables) {
			throwables.printStackTrace();
			// return null;
		}
	}

	public static void insertLoanSvResponse(LoanRequest loanRequest, String status, String responseType,
			String svResponse) {
		int responseId = 0;
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM loanAndMortgageResponse");
			if (rs.next()) {
				rs = st.executeQuery("SELECT MAX(responseId) FROM loanAndMortgageResponse");
				if (rs.next()) {
					responseId = rs.getInt(1) + 1; // Retrieve the maximum value from the result set
				}
			}
			st.executeUpdate(
					"INSERT INTO loanAndMortgageResponse (responseId, requestId, userId, status, svResponse, responseType, responseDate)"
							+ "VALUES(" + responseId + ", '" + loanRequest.getRequestId() + "', '" + loanRequest.getId()
							+ "', '" + status + "', '" + svResponse + "', '" + responseType + "', '"
							+ LocalDateTime.now() + "')");
			st.close();
			// return result;
		} catch (Exception throwables) {
			throwables.printStackTrace();
			// return null;
		}
	}

	public static void insertMortgageSvResponse(MortgageRequest frameRequest, String status, String responseType,
			String svResponse) {
		int responseId = 0;
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM loanAndMortgageResponse");
			if (rs.next()) {
				rs = st.executeQuery("SELECT MAX(responseId) FROM loanAndMortgageResponse");
				if (rs.next()) {
					responseId = rs.getInt(1) + 1; // Retrieve the maximum value from the result set
				}
			}
			st.executeUpdate(
					"INSERT INTO loanAndMortgageResponse (responseId, requestId, userId, status, svResponse, responseType, responseDate)"
							+ "VALUES(" + responseId + ", '" + frameRequest.getRequestId() + "', '"
							+ frameRequest.getId() + "', '" + status + "', '" + svResponse + "', '" + responseType
							+ "', '" + LocalDateTime.now() + "')");
			st.close();
			// return result;
		} catch (Exception throwables) {
			throwables.printStackTrace();
			// return null;
		}
	}

	public static void deleteFrameAndCreditRequest(int requestId) {
		try {
			Statement st = getConnection().createStatement();

			st.executeUpdate("DELETE FROM frameAndCreditRequest WHERE requestId='" + requestId + "'");
			st.close();
		} catch (Exception throwables) {
			throwables.printStackTrace();
		}
	}

	public static void deleteLoanAndMortgageRequest(int requestId) {
		try {
			Statement st = getConnection().createStatement();

			st.executeUpdate("DELETE FROM loanAndMortgageRequest WHERE requestId='" + requestId + "'");
			st.close();
		} catch (Exception throwables) {
			throwables.printStackTrace();
		}
	}

	public static ArrayList<Response> getFrameAndCreditResponses(String id) {
		ArrayList<Response> result = new ArrayList<>();
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from frameAndCreditResponse WHERE userId = '" + id + "'");
			while (rs.next()) {
				result.add(new Response(rs.getInt(2), rs.getString(5), rs.getDate(7), rs.getString(6)));
			}
			st.close();
			return result;
		} catch (Exception throwables) {
			throwables.printStackTrace();
			return null;
		}
	}

	public static ArrayList<Response> getLoanAndMortgageResponses(String id) {
		ArrayList<Response> result = new ArrayList<>();
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from loanAndMortgageResponse WHERE userId = '" + id + "'");
			while (rs.next()) {
				result.add(new Response(rs.getInt(2), rs.getString(5), rs.getDate(7), rs.getString(6)));
			}
			st.close();
			return result;
		} catch (Exception throwables) {
			throwables.printStackTrace();
			return null;
		}
	}

	public static ArrayList<LoanRequest> getLoanResponse(String type) {
		ArrayList<LoanRequest> result = new ArrayList<>();
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from loanAndMortgageRequest WHERE requestType='" + type + "'");
			while (rs.next()) {
				result.add(new LoanRequest(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getDate(5)));
			}
			st.close();
			return result;
		} catch (Exception throwables) {
			throwables.printStackTrace();
			return null;
		}
	}

	public static ArrayList<MortgageRequest> getMortgageResponse(String type) {
		ArrayList<MortgageRequest> result = new ArrayList<>();
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from loanAndMortgageRequest WHERE requestType='" + type + "'");
			while (rs.next()) {
				result.add(new MortgageRequest(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4),
						rs.getDate(5)));
			}
			st.close();
			return result;
		} catch (Exception throwables) {
			throwables.printStackTrace();
			return null;
		}
	}

	public static void insertLoan(String id, float amount, int numofpayments) {
		int loanId = 0;
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM loans");
			if (rs.next()) {
				rs = st.executeQuery("SELECT MAX(LoanId) FROM loans");
				if (rs.next()) {
					loanId = rs.getInt(1) + 1; // Retrieve the maximum value from the result set
				}
			}
			st.executeUpdate(
					"INSERT INTO loans (Id, LoanId, LoanAmount, LoanAmountWithFee, NumOfPayment, PaymentNumber, LoanStartDate, DayPaymenPerMonth)"
							+ "VALUES('" + id + "', '" + loanId + "', " + amount + ", " + amount * 1.05 + ", "
							+ numofpayments + ", " + 0 + ", '"
							+ LocalDate.now().plusMonths(1).minusDays(LocalDate.now().getDayOfMonth() - 1) + "', '" + 1
							+ "')");
			st.close();
			// return result;
		} catch (Exception throwables) {
			throwables.printStackTrace();
			// return null;
		}
	}

	public static void insertMortgage(String id, float amount, int numofpayments) {
		int mortgageId = 0;
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM mortgages");
			if (rs.next()) {
				rs = st.executeQuery("SELECT MAX(MortgageId) FROM mortgages");
				if (rs.next()) {
					mortgageId = rs.getInt(1) + 1; // Retrieve the maximum value from the result set
				}
			}
			st.executeUpdate(
					"INSERT INTO mortgages (Id, MortgageId, MortgageAmount, MortgageAmountWithFee, NumOfPayments, PaymentNumber, MortgageStartDate, DayPaymentPerMonth)"
							+ "VALUES('" + id + "', '" + mortgageId + "', " + amount + ", " + amount * 1.09 + ", "
							+ numofpayments + ", " + 0 + ", '"
							+ LocalDate.now().plusMonths(1).minusDays(LocalDate.now().getDayOfMonth() - 1) + "', '" + 1
							+ "')");
			st.close();
			// return result;
		} catch (Exception throwables) {
			throwables.printStackTrace();
			// return null;
		}
	}

	public static void insertCredit(String id, float amount) {
		int creditId = 45800000;
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM credits");
			if (rs.next()) {
				rs = st.executeQuery("SELECT MAX(CreditId) FROM credits");
				if (rs.next()) {
					creditId = rs.getInt(1) + 1; // Retrieve the maximum value from the result set
				}
			}
			st.executeUpdate(
					"INSERT INTO credits (CreditId, Id, CreditAmount, CreditTotalPays, DayPaymentPerMonth, CreditDate)"
							+ "VALUES('" + creditId + "', '" + id + "'," + amount + ", " + 0 + "," + 1 + ", '"
							+ LocalDateTime.now() + "')");
			st.close();
			// return result;
		} catch (Exception throwables) {
			throwables.printStackTrace();
			// return null;
		}
	}

	public static void insertFrame(String id, float amount) {
		int frameId = 0;
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM frame");
			if (rs.next()) {
				rs = st.executeQuery("SELECT MAX(frameId) FROM frame");
				if (rs.next()) {
					frameId = rs.getInt(1) + 1; // Retrieve the maximum value from the result set
				}
			}
			st.executeUpdate("INSERT INTO frame (userId, frameId, frameAmount)" + "VALUES('" + id + "', " + frameId
					+ ", " + amount + ")");
			st.close();
			// return result;
		} catch (Exception throwables) {
			throwables.printStackTrace();
			// return null;
		}
	}

}
