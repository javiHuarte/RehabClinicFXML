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

	private  DBManager dbManager = new SQLiteManager();
	private  PacientManager pacientManager;
	private  DepartmentManager departmentManager;
	private  MedicalProfessionalManager medicalProfessionalManager;
	private  TreatmentManager treatmentManager;
	private Employee_ContractManager contractManager;





	public void addPacient (Pacient pacient){


		pacientManager = dbManager.getPacientManager();
		pacientManager.add(pacient);


	}

	public void addMedicalProfessional(MedicalProfessional mp){

		medicalProfessionalManager = dbManager.getMedicalProfessionalManager();
		medicalProfessionalManager.add(mp);
	}

	public void addContract(Employee_Contract contract){

		contractManager = dbManager.getEmployee_ContractManager();
		contractManager.add(contract);
	}

	public void updatePacient(Pacient pacient){


		pacientManager = dbManager.getPacientManager();
		pacientManager.updatePacient(pacient);


	}

	public void updateContract(Employee_Contract contract){

		contractManager = dbManager.getEmployee_ContractManager();
		contractManager.updateContract(contract);

	}

	public void updateMedicalProfessional(MedicalProfessional medicalProfessional){

		medicalProfessionalManager =  dbManager.getMedicalProfessionalManager();
		medicalProfessionalManager.add(medicalProfessional);
	}

	public void deleteMedicalProfessional(Integer id, Integer contract_id){

		medicalProfessionalManager = dbManager.getMedicalProfessionalManager();
		contractManager = dbManager.getEmployee_ContractManager();
		medicalProfessionalManager.deleteById(id);
		contractManager.deleteById(contract_id);

	}

	public List<Pacient> listPacients(){

		List<Pacient> pacientList = null;



		pacientManager = dbManager.getPacientManager();

		pacientList = pacientManager.listAllPacients();

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

		List<Pacient> lista = pacientManager.listAllPacients();

		return lista;
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

	public Employee_Contract searchContractById(Integer id){

		contractManager = dbManager.getEmployee_ContractManager();
		Employee_Contract contract = contractManager.searchById(id);

		return contract;
	}

	public void updateTreatment (Treatment treatment){

		treatmentManager = dbManager.getTreatmentManager();
		treatmentManager.updateTreatment(treatment);

	}

	public void addDepartment(Department department){

		dbManager = new SQLiteManager();
		dbManager.connect();

		//dbManager.createTables();
		departmentManager = dbManager.getDepartmentManager();
		departmentManager.add(department);
		dbManager.disconnect();

	}

	public int getDepId(String name){

		List<Department> departmentlist = new ArrayList();

		departmentManager = dbManager.getDepartmentManager();
		departmentlist.addAll(departmentManager.searchByName(name));

		Department dep = departmentlist.get(0);
		int dep_id = dep.getId();

		return dep_id;
	}



	public int lastId(){

		int id = dbManager.getLastId();
		return id;
	}

}
