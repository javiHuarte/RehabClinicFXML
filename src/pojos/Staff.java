package pojos;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import java.util.ArrayList;

public class Staff implements Serializable{
	
	
	private static final long serialVersionUID = 9189858390611804805L;
	
	private Integer id;
	private String name;
	private Date dob;
	private String profession;
	private String email;
	private String adress;
	private int phoneNumber;
	private Blob photo;
	private String sex;
	private String nie;
	private boolean active;
	private Integer dep_id;
	private Integer contract_id;
	
	
	
	
	public Staff(Integer id, String name, Date dob, String profession, String email, String adress, int phoneNumber,
			String sex, String nie, boolean active, Integer dep_id, Integer contract_id) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.profession = profession;
		this.email = email;
		this.adress = adress;
		this.phoneNumber = phoneNumber;
		this.sex = sex;
		this.nie = nie;
		this.active = active;
		this.dep_id = dep_id;
		this.contract_id = contract_id;
	}
	public Staff(Integer id, String name, Date dob, String email, String adress, int phoneNumber, String sex,
			String nie, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.email = email;
		this.adress = adress;
		this.phoneNumber = phoneNumber;
		this.sex = sex;
		this.nie = nie;
		this.active = active;
	}
	public Staff(String name, String profession, String email, String adress, int phoneNumber, String sex, String nie,
			boolean active, Integer dep_id) {
		super();
		this.name = name;
		this.profession = profession;
		this.email = email;
		this.adress = adress;
		this.phoneNumber = phoneNumber;
		this.sex = sex;
		this.nie = nie;
		this.active = active;
		this.dep_id = dep_id;
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
	
	
	@Override
	public String toString() {
		return "Staff [id=" + id + ", name=" + name + ", dob=" + dob + ", profession=" + profession + ", email=" + email
				+ ", adress=" + adress + ", phoneNumber=" + phoneNumber + ", photo=" + photo + ", sex=" + sex + ", nie="
				+ nie + ", active=" + active + ", dep_id=" + dep_id + ", contract_id=" + contract_id + "]";
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Blob getPhoto() {
		return photo;
	}
	public void setPhoto(Blob photo) {
		this.photo = photo;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNie() {
		return nie;
	}
	public void setNie(String nie) {
		this.nie = nie;
	}
	public boolean getActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Integer getDep_id() {
		return dep_id;
	}
	public void setDep_id(Integer dep_id) {
		this.dep_id = dep_id;
	}
	public Integer getContract_id() {
		return contract_id;
	}
	public void setContract_id(Integer contract_id) {
		this.contract_id = contract_id;
	}
	
	

	

	
	
}

