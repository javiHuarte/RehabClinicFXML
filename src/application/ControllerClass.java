package application;

import pojos.Department;
import pojos.MedicalProfessional;
import pojos.Pacient;
import pojos.Patientfxml;

public interface ControllerClass {

	public abstract void preloadData(Pacient patient);
	public abstract void preloadMedicalProfessional(MedicalProfessional medicalProfessional);
	public abstract void preloadDepartment(Department department);
}
