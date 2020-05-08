package db.sqlite;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import db.interfaces.TreatmentManager;
import pojos.Treatment;

public class SQLiteTreatmentManager implements TreatmentManager {

	private Connection c;

	public SQLiteTreatmentManager(Connection n) {
		this.c = n;
	}

	public void add(Treatment treatment) {
		try {
			String sql = "INSERT INTO treatment(type, finishing_date, starting_date, extra_info, patient_extra_info) "
					+ "VALUES (?,?,?,?,?);";

			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, treatment.getType());

			if (treatment.getFinishing_date() != null) {
				prep.setDate(2, Date.valueOf(treatment.getFinishing_date()));
			}
			if (treatment.getStarting_date() != null) {
				prep.setDate(3, Date.valueOf(treatment.getStarting_date()));
			}
			prep.setString(4, treatment.getExtra_info());
			prep.setString(5, treatment.getPatient_extra_info());

			prep.executeUpdate();
			prep.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Treatment searchById(Integer id) {
		Treatment newTreatment = null;

		try {
			String sql = "SELECT * FROM treatment WHERE id =?;";

			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();

			int treatmentId = rs.getInt("id");
			String type = rs.getString("type");
			Date sqlFinishingDate = rs.getDate("finishing_date");
			LocalDate finishDate = sqlFinishingDate.toLocalDate();
			Date sqlStartingDate = rs.getDate("starting_date");
			LocalDate startDate = sqlStartingDate.toLocalDate();
			String extraInfo = rs.getString("extra_info");
			String patientExtraInfo = rs.getString("patient_extra_info");

			newTreatment = new Treatment(treatmentId, type, finishDate, startDate, extraInfo, patientExtraInfo);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return newTreatment;
	}

	public void deleteById(Integer id) {

		try {
			String sql = "DELETE FROM treatment WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			prep.executeUpdate();
			System.out.println("Tratamiento eliminado");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateTreatment(Treatment treatment) {
		try {
			String sql = "UPDATE treatment SET type = ?, finishing_date = ?,  starting_date = ?, extra_info = ?, patient_extra_info = ? WHERE id LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			
			prep.setString(1, treatment.getType());
			prep.setDate(2, Date.valueOf(treatment.getFinishing_date()));
			prep.setDate(3, Date.valueOf(treatment.getStarting_date()));
			prep.setString(4, treatment.getExtra_info());
			prep.setString(5, treatment.getPatient_extra_info());
			prep.setInt(6, treatment.getId());
			prep.executeUpdate();
			prep.close();
			System.out.println("Update finished");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Treatment> listAllTreatments() {
		List<Treatment> treatmentList = new ArrayList<Treatment>();
		try {
			String sql = "SELECT * FROM treatment;";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String type = rs.getString("type");

				LocalDate finishingDate;
				if (rs.getDate("finishing_date") != null) {
					Date sqlfinishDate = rs.getDate("finishing_date");
					finishingDate = sqlfinishDate.toLocalDate();
				} else {
					finishingDate = null;
				}

				LocalDate startingDate;
				if (rs.getDate("starting_date") != null) {
					Date sqlstartDate = rs.getDate("starting_date");
					startingDate = sqlstartDate.toLocalDate();
				} else {
					startingDate = null;
				}

				String extraInfo = rs.getString("extra_info");
				String patientExtraInfo = rs.getString("patient_extra_info");

				Treatment treatment = new Treatment(id, type, finishingDate, startingDate, extraInfo, patientExtraInfo);

				treatmentList.add(treatment);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return treatmentList;
	}

}
