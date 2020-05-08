package db.sqlite;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import db.interfaces.PacientManager;
import pojos.Pacient;
import pojos.Treatment;

public class SQLitePacientManager implements PacientManager {

	private Connection c;

	public SQLitePacientManager(Connection n) {
		this.c = n;
	}

	@Override
	public void add(Pacient pacient) {



		try {
			String sql = "INSERT INTO pacient (name, sex, dob, nie, email, active ,intern, adress , phone) "
					+ "VALUES (?,?,?,?,?,?,?,?,?);";

			LocalDate dob = pacient.getDob();

			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, pacient.getName());
			prep.setString(2, pacient.getSex());
			prep.setDate(3, Date.valueOf(dob));
			prep.setString(4, pacient.getNie());
			prep.setString(5, pacient.getEmail());
			prep.setBoolean(6, pacient.getActive());
			prep.setBoolean(7, pacient.getIntern());
			prep.setString(8, pacient.getAdress());
			prep.setInt(9, pacient.getPhoneNumber());

			prep.executeUpdate();
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Pacient> searchByName(String name) {
		List<Pacient> pacientList = new ArrayList<Pacient>();
		try {
			String sql = "SELECT * FROM pacient WHERE name LIKE ?;";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, "%" + name + "%");
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String pacientName = rs.getString("name");
				String sex = rs.getString("sex");
				Date sqlDob = rs.getDate("dob");
				LocalDate dob = sqlDob.toLocalDate();
				String nie = rs.getString("nie");
				String email = rs.getString("email");
				Boolean active = rs.getBoolean("active");
				Boolean intern = rs.getBoolean("intern");
				String adress = rs.getString("adress");
				int phone = rs.getInt("phone");

<<<<<<< HEAD
=======
				Date dob = rs.getDate("dob");
>>>>>>> branch 'master' of https://github.com/javiHuarte/RehabClinicFXML.git

<<<<<<< HEAD

				// Pacient newPacient = new Pacient(id, pacientName, dob,
				// intern, nie, active, email, phone, adress, sex); // catar
				// esto

				Pacient newPacient = new Pacient(id, pacientName, dob, intern, nie, active, email, phone,
						adress, sex); // catar esto

=======
				//Pacient newPacient = new Pacient(id, pacientName, dob, intern, nie, active, email, phone, adress, sex);

			Pacient newPacient = new Pacient(id, pacientName,LocalDate.now(), intern, nie,active, email,phone,adress,sex);
>>>>>>> branch 'master' of https://github.com/javiHuarte/RehabClinicFXML.git
				pacientList.add(newPacient);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pacientList;
	}

	@Override
	public Pacient searchById (Integer id) {

		Pacient newPacient = null;

		try {
			String sql = "SELECT * FROM pacient WHERE id LIKE ?;";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();

<<<<<<< HEAD
			int pacient_id = rs.getInt("id");
			String pacientName = rs.getString("name");
			String sex = rs.getString("sex");
			Date sqlDob = rs.getDate("dob");
			LocalDate dob = sqlDob.toLocalDate();
			String nie = rs.getString("nie");
			String email = rs.getString("email");
			Boolean active = rs.getBoolean("active");
			Boolean intern = rs.getBoolean("intern");
			String adress = rs.getString("adress");
			int phone = rs.getInt("phone");
=======
				int pacient_id = rs.getInt("id");
				String pacientName = rs.getString("name");
				String sex = rs.getString("sex");
				Date sqlDob = rs.getDate("dob");
				String nie = rs.getString("nie");
				String email = rs.getString("email");
				Boolean active = rs.getBoolean("active");
				Boolean intern = rs.getBoolean("intern");
				String adress = rs.getString("adress");
				int phone = rs.getInt("phone");
>>>>>>> branch 'master' of https://github.com/javiHuarte/RehabClinicFXML.git

<<<<<<< HEAD

=======
				LocalDate dob = sqlDob.toLocalDate();
>>>>>>> branch 'master' of https://github.com/javiHuarte/RehabClinicFXML.git

			newPacient = new Pacient(pacient_id, pacientName,dob,nie,email,sex,phone, adress, active, intern);

		} catch (Exception e) {
			e.printStackTrace();

		}
	 return newPacient;
	}

	@Override
	public void deleteById (Integer id) {

		try {
		String sql = "DELETE FROM pacient WHERE id= ?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, id);
		prep.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

		@Override
	public void updatePacient(Pacient pacient) {

		String sql = "UPDATE pacient SET name=? , intern=? , nie=?, active=?, email=?, phone=?, adress = ?, sex=?, dob = ? WHERE id=?";
		PreparedStatement prep;
		try {
			prep = c.prepareStatement(sql);

			LocalDate ld = pacient.getDob();

			prep.setString(1, pacient.getName());
			prep.setBoolean(2, pacient.getIntern());
			prep.setString(3, pacient.getNie());
			prep.setBoolean(4, pacient.getActive());
			prep.setString(5, pacient.getEmail());
			prep.setInt(6, pacient.getPhoneNumber());
			prep.setString(7, pacient.getAdress());
			prep.setString(8, pacient.getSex());
			prep.setDate(9, Date.valueOf(ld));
			prep.setInt(10, 1);

			prep.executeUpdate();
			System.out.println("Update finished.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

<<<<<<< HEAD
	public List<Pacient> listAllPacients (){
		List<Pacient> pacientList = new ArrayList<Pacient>();
		try{
			String sql = "SELECT * FROM pacient;";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String pacientName = rs.getString("name");
				String sex = rs.getString("sex");
				Date sqlDob = rs.getDate("dob");
				LocalDate dob = sqlDob.toLocalDate();
				String nie = rs.getString("nie");
				String email = rs.getString("email");
				Boolean active = rs.getBoolean("active");
				Boolean intern = rs.getBoolean("intern");
				String adress = rs.getString("adress");
				int phone = rs.getInt("phone");
=======
>>>>>>> branch 'master' of https://github.com/javiHuarte/RehabClinicFXML.git

<<<<<<< HEAD
=======
	@Override
	public List<Pacient> listAll() {
			// TODO Auto-generated method stub
>>>>>>> branch 'master' of https://github.com/javiHuarte/RehabClinicFXML.git

				//LocalDate localDate = dob.toLocalDate();
<<<<<<< HEAD
				Pacient newPacient = new Pacient(id,pacientName,dob,nie,email,sex,phone, adress, active, intern);
=======
			List<Pacient> pacients = new ArrayList();
			Pacient newPacient;
>>>>>>> branch 'master' of https://github.com/javiHuarte/RehabClinicFXML.git


<<<<<<< HEAD
				pacientList.add(newPacient);
=======
			try {
				String sql = "SELECT * FROM pacient";
				PreparedStatement prep = c.prepareStatement(sql);
				ResultSet rs = prep.executeQuery();
>>>>>>> branch 'master' of https://github.com/javiHuarte/RehabClinicFXML.git

<<<<<<< HEAD
			}
=======
				while (rs.next()){
					int pacient_id = rs.getInt("id");
					String pacientName = rs.getString("name");
					String sex = rs.getString("sex");
					//Date sqlDob = rs.getDate("dob");
					String nie = rs.getString("nie");
					String email = rs.getString("email");
					Boolean active = rs.getBoolean("active");
					Boolean intern = rs.getBoolean("intern");
					String adress = rs.getString("adress");
					int phone = rs.getInt("phone");

					//LocalDate dob = sqlDob.toLocalDate();

				newPacient = new Pacient(pacient_id, pacientName,LocalDate.of(1995,Month.APRIL,9),nie,email,sex,phone, adress, active, intern);
				pacients.add(newPacient);
				}
>>>>>>> branch 'master' of https://github.com/javiHuarte/RehabClinicFXML.git
			} catch (Exception e) {

				e.printStackTrace();

			}

<<<<<<< HEAD
			return pacientList;
		}


	//Getting all the treatments for a patient
	public List<Treatment> searchPatientAndTreatments (Integer id){
		List<Treatment> treatmentList = new ArrayList<Treatment>();
		try{
			String sql = "SELECT * FROM treatment_pacient AS tp JOIN treatment AS t ON tp.treatment_id = t.id"
					+ " WHERE tp.pacient_id LIKE ?;";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();


			while(rs.next()){
			
				int treatmentId = rs.getInt(3);
				String treatmentType = rs.getString(4);
				Date sqlStart = rs.getDate(5);
				LocalDate startDate = sqlStart.toLocalDate();
				Date sqlFinish = rs.getDate(6);
				LocalDate finishDate = sqlFinish.toLocalDate();
				String extra_info = rs.getString(7);
				String patient_extra_info = rs.getString(8);
				
				Treatment treatment = new Treatment (treatmentId,treatmentType,startDate,finishDate,extra_info,patient_extra_info);
				treatmentList.add(treatment);
			}


			/*boolean patientCreated = false;

			while(rs.next()){

				if(!patientCreated){
					int patientId = rs.getInt(1);
					String patientName = rs.getString(2);
					String patientSex = rs.getString(3);
					Date sqlDob = rs.getDate(4);
					LocalDate dob = sqlDob.toLocalDate();
					String patientNie = rs.getString(6);
					String patientEmail = rs.getString(7);
					Boolean active = rs.getBoolean(8);
					Boolean intern = rs.getBoolean(9);
					String patientAdress = rs.getString(10);
					int phone = rs.getInt(11);
				}


			}*/

		}catch (Exception e){
			e.printStackTrace();
		}
		return treatmentList;
=======

			return pacients;
>>>>>>> branch 'master' of https://github.com/javiHuarte/RehabClinicFXML.git
	}

<<<<<<< HEAD

	public void insertIntoTreatmentPatient (Integer patientId, Integer treatmentId){
		try{
		String sql = "INSERT INTO treatment_pacient(pacient_id, treatment_id) "
				+ "VALUES (?,?);";

		System.out.println("Intoduciendo"+patientId+""+treatmentId);
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, patientId);
		prep.setInt(2, treatmentId);

		prep.executeUpdate();
		prep.close();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	}
=======
}
>>>>>>> branch 'master' of https://github.com/javiHuarte/RehabClinicFXML.git

