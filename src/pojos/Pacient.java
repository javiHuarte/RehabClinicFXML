package pojos;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Pacient implements Serializable{

	/**
	 *
	 */




	private static final long serialVersionUID = 2071328715533081411L;
	private SimpleIntegerProperty id;
	private SimpleStringProperty name;
	private LocalDate dob;
	private SimpleBooleanProperty intern;
	private SimpleStringProperty  nie;
	private Blob photo;
	private SimpleBooleanProperty active;
	private SimpleStringProperty  email;
	private SimpleIntegerProperty phoneNumber;
	private SimpleStringProperty  adress;
	private ArrayList<Dissability> dissabilityList = new ArrayList<Dissability> ();
	private ArrayList<Treatment> treatmentList = new ArrayList<Treatment>();
	private SimpleStringProperty  sex;

	public Pacient() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Pacient(String name, String nie,LocalDate dob, String email, int phoneNumber, String adress, String sex, Boolean intern, Boolean active) {
		super();
		this.name =  new SimpleStringProperty(name);
		this.nie = new SimpleStringProperty(nie);
		this.sex = new SimpleStringProperty(sex);
		this.email = new SimpleStringProperty(email);
		this.adress = new SimpleStringProperty(adress);
		this.phoneNumber = new SimpleIntegerProperty(phoneNumber);
		this.intern = new SimpleBooleanProperty(intern);
		this.active = new SimpleBooleanProperty(active);
		this.dob = dob;
	}

	public Pacient(Integer id, String name, LocalDate dob, String nie, String email, String sex, Integer phoneNumber,
			String adress, Boolean active, Boolean intern) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.name =  new SimpleStringProperty(name);
		this.sex = new SimpleStringProperty(sex);
		this.dob = dob;
		this.nie = new SimpleStringProperty(nie);
		this.email = new SimpleStringProperty(email);
		this.phoneNumber = new SimpleIntegerProperty(phoneNumber);
		this.adress = new SimpleStringProperty(adress);
		this.active = new SimpleBooleanProperty(active);
		this.intern = new SimpleBooleanProperty(intern);
	}



	public Pacient(Integer id, String name, LocalDate dob, Boolean intern, String nie, Boolean active, String email,
			int phoneNumber, String adress, String sex) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.name =  new SimpleStringProperty(name);
		this.sex = new SimpleStringProperty(sex);
		this.dob = dob;
		this.nie = new SimpleStringProperty(nie);
		this.email = new SimpleStringProperty(email);
		this.phoneNumber = new SimpleIntegerProperty(phoneNumber);
		this.adress = new SimpleStringProperty(adress);
		this.active = new SimpleBooleanProperty(active);
		this.intern =new SimpleBooleanProperty(intern);
	}





	public Pacient(String name2, String nif, String phoneNumber2, String adress2, String email2, String sex2,
			Boolean intern2) {
		// TODO Auto-generated constructor stub
	}



	public Integer getId() {
		return id.get();
	}
	public void setId(Integer id) {
		this.id = new SimpleIntegerProperty(id);
	}
	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}
	public String getSex() {
		return sex.get();
	}
	public void setSex(String sex) {
		this.sex = new SimpleStringProperty(sex);
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public Boolean getIntern() {
		return intern.get();
	}
	public void setIntern(Boolean intern) {
		this.intern = new SimpleBooleanProperty(intern);
	}
	public String getNie() {
		return nie.get();
	}
	public void setNie(String nie) {
		this.nie = new SimpleStringProperty(nie);
	}
	public Blob getPhoto() {
		return photo;
	}
	public void setPhoto(Blob photo) {
		this.photo = photo;
	}
	public Boolean getActive() {
		return active.get();
	}
	public void setActive(Boolean active) {
		this.active = new SimpleBooleanProperty(active);
	}
	public String getEmail() {
		return email.get();
	}
	public void setEmail(String email) {
		this.email = new SimpleStringProperty(email);
	}
	public int getPhoneNumber() {
		return phoneNumber.get();
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = new SimpleIntegerProperty(phoneNumber);
	}
	public String getAdress() {
		return adress.get();
	}
	public void setAdress(String adress) {
		this.adress = new SimpleStringProperty(adress);
	}
	public ArrayList<Dissability> getDissabilityList() {
		return dissabilityList;
	}
	public void setDissabilityList(ArrayList<Dissability> dissabilityList) {
		this.dissabilityList = dissabilityList;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pacient other = (Pacient) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Pacient [id=" + id + ", name=" + name + ", sex=" + sex + ", dob=" + dob + ", intern=" + intern
				+ ", nie=" + nie + ", active=" + active + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", adress=" + adress + ", dissabilityList=" + dissabilityList + "]";
	}



}