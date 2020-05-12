package db.interfaces;

public interface DBManager {

	public void connect();
	public void disconnect();
	public void createTables();
	public Integer getLastId();

	public PacientManager getPacientManager();
	public DepartmentManager getDepartmentManager();
	public MedicalProfessionalManager getMedicalProfessionalManager();
	public StaffManager getStaffManager();
	public Employee_ContractManager getEmployee_ContractManager();
	public TreatmentManager getTreatmentManager();
}
