package pojos;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Staff implements Serializable{


	private static final long serialVersionUID = 9189858390611804805L;

	private SimpleIntegerProperty id;
	private SimpleStringProperty name;
	private LocalDate dob;
	private SimpleStringProperty  sex;
	private SimpleStringProperty  profession;
	private Blob photo;
	private SimpleStringProperty  email;
	private SimpleStringProperty  adress;
	private SimpleIntegerProperty phoneNumber;
	private SimpleStringProperty  nif;
	private SimpleIntegerProperty dep_id;
	private SimpleStringProperty  department;
	private SimpleIntegerProperty contract_id;





	public Staff(String name, LocalDate dob, String sex, String profession, String email,
			String adress, int phoneNumber, String nif, Integer dep_id, String department, Integer contract_id) {
		super();
		//this.id  = new SimpleIntegerProperty(id);
		this.name =new SimpleStringProperty(name);
		this.dob = dob;
		this.sex = new SimpleStringProperty(sex);
		this.profession = new SimpleStringProperty(profession);
		this.email =new SimpleStringProperty(email);
		this.adress =new SimpleStringProperty(adress);
		this.phoneNumber =new SimpleIntegerProperty(phoneNumber);
		this.nif =new SimpleStringProperty(nif);
		this.dep_id = new SimpleIntegerProperty(dep_id);
		this.department = new SimpleStringProperty(department);
		this.contract_id = new SimpleIntegerProperty(contract_id);
	}
	public Staff(String name, LocalDate dob, String sex, String profession, String email,
			String adress, int phoneNumber, String nif, Integer dep_id) {
		super();

		this.name =new SimpleStringProperty(name);
		this.dob = dob;
		this.sex = new SimpleStringProperty(sex);
		this.profession = new SimpleStringProperty(profession);
		this.email =new SimpleStringProperty(email);
		this.adress =new SimpleStringProperty(adress);
		this.phoneNumber =new SimpleIntegerProperty(phoneNumber);
		this.nif = new SimpleStringProperty(nif);
		this.dep_id = new SimpleIntegerProperty(dep_id);
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
		Staff other = (Staff) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}




	public String getNif() {
		return nif.get();
	}

	public void setNif(String nif) {
		this.nif = new SimpleStringProperty(nif);
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
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getSex() {
		return sex.get();
	}
	public void setSex(String sex) {
		this.sex = new SimpleStringProperty(sex);
	}
	public String getProfession() {
		return profession.get();
	}
	public void setProfession(String profession) {
		this.profession = new SimpleStringProperty(profession);
	}
	public Blob getPhoto() {
		return photo;
	}
	public void setPhoto(Blob photo) {
		this.photo = photo;
	}
	public String getEmail() {
		return email.get();
	}
	public void setEmail(String email) {
		this.email = new SimpleStringProperty(email);
	}
	public String getAdress() {
		return adress.get();
	}
	public void setAdress(String adress) {
		this.adress = new SimpleStringProperty(adress);
	}
	public int getPhoneNumber() {
		return phoneNumber.get();
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = new SimpleIntegerProperty(phoneNumber);
	}




	@Override
	public String toString() {
		return "Staff [id=" + id + ", name=" + name + ", dob=" + dob + ", sex=" + sex + ", profession=" + profession
				+ ", photo=" + photo + ", email=" + email + ", adress=" + adress + ", phoneNumber=" + phoneNumber
				+ ", nif=" + nif + ", dep_id=" + dep_id + ", department=" + department + "]";
	}
	public Integer getDep_id() {
		return dep_id.get();
	}


	public void setDep_id(Integer dep_id) {
		this.dep_id = new SimpleIntegerProperty(dep_id);
	}

	public Integer getContract_id() {
		return contract_id.get();
	}


	public void setContract_id(Integer contract_id) {
		this.contract_id = new SimpleIntegerProperty(contract_id);
	}

	public String getDepartment(){
		return this.department.get();
	}

	public void setDepartment(String department){
		this.department = new SimpleStringProperty(department);
	}


}

