package db.sqlite;

import db.interfaces.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import pojos.Staff;

public class SQLiteStaffManager implements StaffManager {

	private Connection c;

	public SQLiteStaffManager(Connection n) {
		this.c = n;
	}

	@Override
	public void add(Staff staff) {

		try {

			String sql = "INSERT INTO staff (name, profession, sex ,dob, nie, email, adress , phone) "// dep_id, contract_id) "
					+ "VALUES (?,?,?,?,?,?,?,?);";

			System.out.print("heello");
			LocalDate dob = staff.getDob();

			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, staff.getName());
			prep.setString(2, staff.getProfession());
			prep.setString(3, staff.getSex());
			prep.setDate(4, Date.valueOf(dob));
			prep.setString(5, staff.getNif());
			prep.setString(6, staff.getEmail());
			prep.setString(7, staff.getAdress());
			prep.setInt(8, staff.getPhoneNumber());
			//prep.setInt(9, staff.getDep_id());
		//	prep.setInt(10, staff.getContract_id());

			prep.executeUpdate();
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("juan");
		}

	}

	@Override
	public List<Staff> searchByName(String name) {

		List<Staff> staffList = new ArrayList<Staff>();

		try {

			String sql = "SELECT * FROM staff WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, "%" + name + "%");
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String staffName = rs.getString("name");
				String sex = rs.getString("sex");
				Date dob = rs.getDate("dob");
				String nie = rs.getString("nie");
				String email = rs.getString("email");
				Boolean active = rs.getBoolean("active");
				String adress = rs.getString("adress");
				int phone = rs.getInt("phone");
			//	Staff newStaff = new Staff(id, staffName, dob,email,adress,phone, sex, nie, active);
			//	staffList.add(newStaff);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return staffList;

	}

	@Override
	public Staff searchById(Integer id) {

		Staff newStaff = null;

		try {
			String sql = "SELECT * FROM staff WHERE id LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();

			int staff_id = rs.getInt("id");
			String staffName = rs.getString("name");
			String sex = rs.getString("sex");
			Date dob = rs.getDate("dob");
			String nie = rs.getString("nie");
			String email = rs.getString("email");
			Boolean active = rs.getBoolean("active");
			String adress = rs.getString("adress");
			int phone = rs.getInt("phone");

			//newStaff = new Staff(staff_id, staffName, dob,email,adress,phone, sex, nie, active);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return newStaff;

	}

	@Override
	public void deleteById(Integer id) {

		try {
			String sql = "DELETE FROM staff WHERE id= ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			prep.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateStaff(Staff staff) {

			String sql = "UPDATE staff SET name=?, nie=?, active=?, email=?, phone=?, adress = ?, sex=? WHERE id=?";
			PreparedStatement prep;
			try {
				prep = c.prepareStatement(sql);

				prep.setString(1, staff.getName());
			//	prep.setString(2, staff.getNie());
			//	prep.setBoolean(3, staff.getActive());
				prep.setString(4, staff.getEmail());
				prep.setInt(5, staff.getPhoneNumber());
				prep.setString(6, staff.getAdress());
				prep.setString(7, staff.getSex());
				prep.setInt(8, staff.getId());
				prep.executeUpdate();

				System.out.println("Update finished.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

}
