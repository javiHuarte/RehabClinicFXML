package db.interfaces;

import java.util.List;
import pojos.*;


public interface PacientManager {

	public void add (Pacient pacient);
	// public void releaseDate
	public void updatePacient (Pacient pacient);
	public List<Pacient> searchByName (String name);
	public Pacient searchById (Integer id);
	public void deleteById(Integer id);
<<<<<<< HEAD
	public List<Pacient> listAllPacients ();
	public List<Treatment> searchPatientAndTreatments (Integer id);
	public void insertIntoTreatmentPatient  (Integer patientId, Integer treatmentId);


=======
	public List<Pacient> listAll();
>>>>>>> branch 'master' of https://github.com/javiHuarte/RehabClinicFXML.git
}
