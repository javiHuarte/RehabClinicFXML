package application;

import java.util.ArrayList;
import java.util.List;

import db.interfaces.DBManager;
import db.interfaces.DepartmentManager;
import db.interfaces.PacientManager;
import db.interfaces.*;
import db.sqlite.SQLiteManager;
import pojos.*;

public class DBConnection {

	private static DBManager dbManager;
	private static PacientManager pacientManager;
	private static DepartmentManager departmentManager;
	private static MedicalProfessionalManager medicalProfessionalManager;
	private static StaffManager staffManager;



	public void addPacient (Pacient pacient){

		dbManager = new SQLiteManager();
		dbManager.connect();

		dbManager.createTables();

		pacientManager = dbManager.getPacientManager();
		pacientManager.add(pacient);
		dbManager.disconnect();

	}

	public void updatePacient(Pacient pacient){

		dbManager = new SQLiteManager();
		dbManager.connect();
		pacientManager = dbManager.getPacientManager();
		pacientManager.updatePacient(pacient);
		dbManager.disconnect();

	}

	public List<Pacient> listPacients(){

		List<Pacient> pacientList = null;

		dbManager = new SQLiteManager();
		dbManager.connect();

		pacientManager = dbManager.getPacientManager();

		pacientList = pacientManager.listAll();

		return pacientList;


	}

	public List<Pacient> searchPacientByName (String name){

		dbManager = new SQLiteManager();
		dbManager.connect();
		pacientManager = dbManager.getPacientManager();
		return pacientManager.searchByName(name) ;

	}

	public void addDepartment(Department department){

		dbManager = new SQLiteManager();
		dbManager.connect();

		//dbManager.createTables();
		departmentManager = dbManager.getDepartmentManager();
		departmentManager.add(department);
		dbManager.disconnect();

	}

	public List<Department> listAllDepartments(){

		List<Department> list = new ArrayList();
		dbManager = new SQLiteManager();
		dbManager.connect();

		//dbManager.createTables();
		departmentManager = dbManager.getDepartmentManager();
		list = departmentManager.listAll();
		dbManager.disconnect();
		return list;
	}

	public void deleteDepartment(Integer id){

		dbManager = new SQLiteManager();
		dbManager.connect();
		departmentManager = dbManager.getDepartmentManager();
		departmentManager.deleteById(id);
		dbManager.disconnect();
	}

	public List<MedicalProfessional> listAllMedicalProfessionals(){

		List<MedicalProfessional> list = new ArrayList();
		dbManager = new SQLiteManager();
		dbManager.connect();

		medicalProfessionalManager = dbManager.getMedicalProfessionalManager();
		list = medicalProfessionalManager.listAll();
		return list;

	}

	public void addStaff(Staff staff){

		dbManager = new SQLiteManager();
		dbManager.connect();

		System.out.println(staff);

		//dbManager.createTables();

		staffManager = dbManager.getStaffManager();
		staffManager.add(staff);
		dbManager.disconnect();

	}

}
