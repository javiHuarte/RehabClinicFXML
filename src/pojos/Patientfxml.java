package pojos;

import java.time.LocalDate;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Patientfxml {

	SimpleIntegerProperty id;
	SimpleStringProperty name, nif,sex, adress, email, phoneNumber;
	SimpleBooleanProperty active, intern;
	LocalDate dob;




	public void setId(SimpleIntegerProperty id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}

	public void setNif(String nif) {
		this.nif = new SimpleStringProperty(nif);
	}

	public void setSex(String sex) {
		this.sex = new SimpleStringProperty(sex);
	}

	public String getAdress() {
		return adress.get();
	}

	public void setAdress(String adress) {
		this.adress = new SimpleStringProperty(adress);;
	}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(String email) {
		this.email = new SimpleStringProperty(email);
	}

	public String getPhoneNumber() {
		return phoneNumber.get();
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = new SimpleStringProperty(phoneNumber);
	}


	public void setDob(LocalDate dob) {
		this.dob = dob;
}



	@Override
	public String toString() {
		return "Patient [name=" + name + ", nif=" + nif + ", sex=" + sex + ", adress=" + adress + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", intern=" + intern + ", dob=" + dob + "]";
	}

	public Patientfxml(Integer id, String name, String nif, String sex, LocalDate dob, String adress, String email, String phoneNumber, String intern) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.name =  new SimpleStringProperty(name);
		this.nif = new SimpleStringProperty(nif);
		this.sex = new SimpleStringProperty(sex);
		this.email = new SimpleStringProperty(email);
		this.adress = new SimpleStringProperty(adress);
		this.phoneNumber = new SimpleStringProperty(phoneNumber);
		//this.intern = new SimpleStringProperty(intern);
		this.dob = dob;
	}


	public String getName(){

		return name.get();
	}

	public String getNif(){
		return nif.get();
	}

	public String getSex(){
		return sex.get();
	}

	public Integer getId(){
		return id.get();
	}

	public LocalDate getDob(){
		return this.dob;
	}

}

