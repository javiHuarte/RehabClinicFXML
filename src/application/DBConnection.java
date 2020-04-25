package application;

import java.util.ArrayList;
import java.util.List;

import db.interfaces.DBManager;
import db.interfaces.DepartmentManager;
import db.interfaces.PacientManager;
import db.sqlite.SQLiteManager;
import pojos.*;

public class DBConnection {

	private static DBManager dbManager;
	private static PacientManager pacientManager;
	private static DepartmentManager departmentManager;



	public void addPacient (Pacient pacient){

		dbManager = new SQLiteManager();
		dbManager.connect();

		//dbManager.createTables();

		pacientManager = dbManager.getPacientManager();
		pacientManager.add(pacient);
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

}
