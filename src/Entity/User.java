package Entity;

import java.time.LocalDateTime;

public class User {
	private String Id;
	private String Password;
	private String FirstName;
	private String LastName;
	private String PhoneNumber;
	private String Address;
	private String Email;
	private float LastLogInAmount;
	private LocalDateTime DateOfLastLogInAmount;
	private boolean IfSupervisor;
	private float Frame;

	public User(String id, String password, String firstName, String lastName, String phoneNumber, String address,
			String email, float lastLogInAmount, LocalDateTime dateOfLastLogInAmount,boolean ifSupervisor, float frame) {
		super();
		Id = id;
		Password = password;
		FirstName = firstName;
		LastName = lastName;
		PhoneNumber = phoneNumber;
		Address = address;
		Email = email;
		LastLogInAmount = lastLogInAmount;
		DateOfLastLogInAmount = dateOfLastLogInAmount;
		IfSupervisor = ifSupervisor;
		Frame = frame;
	}

	public boolean getIfSupervisor() {
		return IfSupervisor;
	}

	public void setIfSupervisor(boolean ifSupervisor) {
		IfSupervisor = ifSupervisor;
	}
	
	public Float getLastLogInAmount() {
		return LastLogInAmount;
	}

	public void setLastLogInAmount(Float lastLogInAmount) {
		LastLogInAmount = lastLogInAmount;
	}
	
	public Float getFrame() {
		return Frame;
	}

	public void setFrame(Float frame) {
		Frame = frame;
	}

	public LocalDateTime getDateOfLastLogInAmount() {
		return DateOfLastLogInAmount;
	}

	public void setDateOfLastLogInAmount(LocalDateTime dateOfLastLogInAmount) {
		DateOfLastLogInAmount = dateOfLastLogInAmount;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}
}
