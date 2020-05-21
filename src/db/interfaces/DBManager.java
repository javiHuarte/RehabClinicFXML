package db.interfaces;

public interface DBManager {

	public void connect();
	public void disconnect();
	public void createTables();
	public int lastId();

	public PacientManager getPacientManager();
	public DepartmentManager getDepartmentManager();
	public MedicalProfessionalManager getMedicalProfessionalManager();
	public StaffManager getStaffManager();
	public TreatmentManager getTreatmentManager();
	public Employee_ContractManager getEmployee_ContractManager();
	public int getLastId();
}
