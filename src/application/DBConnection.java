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

<<<<<<< HEAD
	private static DBManager dbManager;
	private static PacientManager pacientManager;
	private static DepartmentManager departmentManager;
	private static MedicalProfessionalManager medicalProfessionalManager;
	private static StaffManager staffManager;
=======
	private  DBManager dbManager = new SQLiteManager();
	private  PacientManager pacientManager;
	private  DepartmentManager departmentManager;
	private  MedicalProfessionalManager medicalProfessionalManager;
	private  TreatmentManager treatmentManager;
>>>>>>> branch 'master' of https://github.com/javiHuarte/RehabClinicFXML.git


	


	public void addPacient (Pacient pacient){

		
		pacientManager = dbManager.getPacientManager();
		pacientManager.add(pacient);

		dbManager.disconnect();

	}


	public void addStaff(Staff staff){

		dbManager = new SQLiteManager();
		dbManager.connect();

		staffManager = dbManager.getStaffManager();
		staffManager.add(staff);
		dbManager.disconnect();

	}

	public void addMedicalProfessional (MedicalProfessional medicalProfessional){

		dbManager = new SQLiteManager();
		dbManager.connect();

		//dbManager.createTables();

		System.out.println(medicalProfessional);
		medicalProfessionalManager = dbManager.getMedicalProfessionalManager();
		medicalProfessionalManager.add(medicalProfessional);
		dbManager.disconnect();

		

	}

	public void updatePacient(Pacient pacient){

		
		pacientManager = dbManager.getPacientManager();
		pacientManager.updatePacient(pacient);
		

	}

	public void updateMedicalProfessional(MedicalProfessional medicalProfessional){

		dbManager = new SQLiteManager();
		dbManager.connect();

		medicalProfessionalManager = dbManager.getMedicalProfessionalManager();
		medicalProfessionalManager.updateMedicalProfessional(medicalProfessional);

		dbManager.disconnect();
	}

	public List<Pacient> listPacients(){

		List<Pacient> pacientList = null;

		

		pacientManager = dbManager.getPacientManager();
<<<<<<< HEAD

		pacientList = pacientManager.listAll();

=======
		
		pacientList = pacientManager.listAllPacients();
		
>>>>>>> branch 'master' of https://github.com/javiHuarte/RehabClinicFXML.git
		return pacientList;


	}

	public List<Treatment> listTreatments(){

		List<Treatment> treatmentList = null;

		
		treatmentManager = dbManager.getTreatmentManager();
		treatmentList = treatmentManager.listAllTreatments();
	
		return treatmentList;
	}

	public List<Pacient> searchPacientByName (String name){

	
		pacientManager = dbManager.getPacientManager();
		List<Pacient> lista = pacientManager.searchByName(name);
		
		return lista;

	}

	public List<Pacient> listAllPacients (){
		
		pacientManager = dbManager.getPacientManager();
<<<<<<< HEAD
		return pacientManager.listAll() ;
=======

		List<Pacient> lista = pacientManager.listAllPacients();
		
		return lista;
>>>>>>> branch 'master' of https://github.com/javiHuarte/RehabClinicFXML.git
	}

	public void addDepartment(Department department){

		

		
		departmentManager = dbManager.getDepartmentManager();
		departmentManager.add(department);
	
	}

	public List<Department> listAllDepartments(){

		List<Department> list = new ArrayList();
		

		//dbManager.createTables();
		departmentManager = dbManager.getDepartmentManager();
		list = departmentManager.listAll();
		
		return list;
	}

	public List<MedicalProfessional> listAllMedicalProfessionals(){

		List<MedicalProfessional> list = new ArrayList();
		

		medicalProfessionalManager = dbManager.getMedicalProfessionalManager();
		list = medicalProfessionalManager.listAll();
		
		return list;

	}

	public void addTreatment (Treatment treatment){

	

		treatmentManager = dbManager.getTreatmentManager();
		treatmentManager.add(treatment);
		
	}

	public void addTreatmentToPatient (Integer treatmentId, Integer patientId){
		

		pacientManager = dbManager.getPacientManager();
		pacientManager.insertIntoTreatmentPatient(patientId, treatmentId);
		
	}

	public List<Treatment> listPatientTreatments (Integer id){
		List<Treatment> list = new ArrayList();
		pacientManager = dbManager.getPacientManager();
		list = pacientManager.searchPatientAndTreatments(id);
		
		return list;

	}

	public void deleteTreatment(Integer id){
		
		
		treatmentManager = dbManager.getTreatmentManager();
		treatmentManager.deleteById(id);
		
	}

	public Pacient searchPatientById (Integer id){
	
		pacientManager = dbManager.getPacientManager();
		Pacient pacient = pacientManager.searchById(id);
		
		return pacient;
	}

	public Treatment searchTreatmentById (Integer id){
		
		treatmentManager = dbManager.getTreatmentManager();
		Treatment treatment = treatmentManager.searchById(id);
		
		return treatment;
	}

	public void updateTreatment (Treatment treatment){
		
		treatmentManager = dbManager.getTreatmentManager();
		treatmentManager.updateTreatment(treatment);
		
	}
	
	public List<MedicalProfessional> listAllMedicalProfessionals(){
		
		List<MedicalProfessional> list = new ArrayList();
		dbManager = new SQLiteManager();
		dbManager.connect();
		
		medicalProfessionalManager = dbManager.getMedicalProfessionalManager();
		list = medicalProfessionalManager.listAll();
		return list;
		
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
	
	public void addDepartment(Department department){

		dbManager = new SQLiteManager();
		dbManager.connect();

		//dbManager.createTables();
		departmentManager = dbManager.getDepartmentManager();
		departmentManager.add(department);
		dbManager.disconnect();

	}

	public Department searchDepartmentById(Integer id){

		dbManager = new SQLiteManager();
		dbManager.connect();
		departmentManager = dbManager.getDepartmentManager();
		Department dep;

		dep = departmentManager.searchById(id);

		System.out.println(dep);

		dbManager.disconnect();

		return dep;

	}

	public void deleteDepartment(Integer id){

		dbManager = new SQLiteManager();
		dbManager.connect();

		departmentManager = dbManager.getDepartmentManager();
		departmentManager.deleteById(id);;
		dbManager.disconnect();
	}






}
