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



	public void addPacient (Pacient pacient){

		dbManager = new SQLiteManager();
		dbManager.connect();

		dbManager.createTables();

		pacientManager = dbManager.getPacientManager();
		pacientManager.add(pacient);
		dbManager.disconnect();

	}

	public void addMedicalProfessional (MedicalProfessional medicalProfessional){

		dbManager = new SQLiteManager();
		dbManager.connect();

		dbManager.createTables();

		medicalProfessionalManager = dbManager.getMedicalProfessionalManager();
		medicalProfessionalManager.add(medicalProfessional);
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

		pacientList = pacientManager.listAllPacients();

		return pacientList;


	}

	public List<Pacient> searchPacientByName (String name){

		dbManager = new SQLiteManager();
		dbManager.connect();
		pacientManager = dbManager.getPacientManager();
		return pacientManager.searchByName(name) ;

	}


	public List<Pacient> listAllPacients (){
		dbManager = new SQLiteManager();
		dbManager.connect();
		pacientManager = dbManager.getPacientManager();
		return pacientManager.listAllPacients() ;
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

	public List<MedicalProfessional> listAllMedicalProfessionals(){

		List<MedicalProfessional> list = new ArrayList();
		dbManager = new SQLiteManager();
		dbManager.connect();

		medicalProfessionalManager = dbManager.getMedicalProfessionalManager();
		list = medicalProfessionalManager.listAll();
		return list;

	}


}
