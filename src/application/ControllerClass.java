package application;

import pojos.*;
import pojos.Pacient;
import pojos.Patientfxml;

public interface ControllerClass {

	public abstract void preloadData(Pacient patient);
	public abstract void preloadDataMedicalProfessional(MedicalProfessional MedicalProfessional);
	public abstract void preloadDepartment(Department department);
}
