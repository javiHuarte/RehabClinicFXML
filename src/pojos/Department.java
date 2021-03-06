package pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Department implements Serializable {

	
	
	private static final long serialVersionUID = -6234791722618349000L;
	private Integer id;
	private String name;
	private int floor;
	private float budget;
	private Integer boss_id;
	private List<Staff> staffList = new ArrayList<Staff> ();	
	private List<MedicalProfessional> medicalProfessionalList = new ArrayList<MedicalProfessional> ();	
	
	public Department() {
		super();
	}
	
	

	public Department(String name, int floor, float budget) {
		super();
		this.name = name;
		this.floor = floor;
		this.budget = budget;
	}



	public Department(String name, int floor, float budget, Integer boss_id) {
		super();
		this.name = name;
		this.floor = floor;
		this.budget = budget;
		this.boss_id = boss_id;
	}



	public Department(Integer id, String name, int floor, float budget, Integer boss_id, List medicalProfessionalList, List staffList) {
		super();
		this.id = id;
		this.name = name;
		this.floor = floor;
		this.budget = budget;
		this.boss_id = boss_id;
		this.medicalProfessionalList = medicalProfessionalList;
		this.staffList = staffList;
	}
	
	public Department(Integer id, String name, int floor, float budget, Integer boss_id) {
		super();
		this.id = id;
		this.name = name;
		this.floor = floor;
		this.budget = budget;
		this.boss_id = boss_id;
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
		Department other = (Department) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
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

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public float getBudget() {
		return budget;
	}

	public void setBudget(float budget) {
		this.budget = budget;
	}

	public Integer getBoss_id() {
		return boss_id;
	}

	public void setBoss_id(Integer boss_id) {
		this.boss_id = boss_id;
	}

	public List<Staff> getStaffList() {
		return staffList;
	}

	public void setStaffList(ArrayList<Staff> staffList) {
		this.staffList = staffList;
	}

	

	

	public List<MedicalProfessional> getMedicalProfessionalList() {
		return medicalProfessionalList;
	}



	public void setMedicalProfessionalList(List<MedicalProfessional> medicalProfessionalList) {
		this.medicalProfessionalList = medicalProfessionalList;
	}



	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", floor=" + floor + ", budget=" + budget + ", boss_id="
				+ boss_id + ", staffList=" + staffList + ", medicalProfessionalList=" + medicalProfessionalList + "]";
	}
	
	
}
