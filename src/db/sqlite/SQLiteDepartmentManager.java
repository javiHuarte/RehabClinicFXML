package db.sqlite;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import db.interfaces.DepartmentManager;
import db.interfaces.PacientManager;
import pojos.Department;
import pojos.MedicalProfessional;
import pojos.Pacient;
import pojos.Staff;

public class SQLiteDepartmentManager implements DepartmentManager {

	private Connection c;

	public SQLiteDepartmentManager(Connection n) {
		this.c = n;
	}

	@Override
	public void add(Department department) {

		try {
			String sql = "INSERT INTO department (name, budget ,floor) " + "VALUES (?,?,?);";

			PreparedStatement prep = c.prepareStatement(sql);

			prep.setString(1, department.getName());
			prep.setFloat(2, department.getBudget());
			prep.setInt(3, department.getFloor());
			// prep.setInt(4, department.getBoss_id());

			prep.executeUpdate();
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Department> searchByName(String name) {
		List<Department> departmentList = new ArrayList<Department>();
		try {
			String sql = "SELECT * FROM department WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, "%" + name + "%");
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String departmentName = rs.getString("name");
				Float budget = rs.getFloat("budget");
				int floor = rs.getInt("floor");
				Integer boss_id = rs.getInt("boss_id");

				Department newDepartment = new Department(id, departmentName, floor, budget, boss_id);
				departmentList.add(newDepartment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return departmentList;
	}

@Override
	public Department searchById(Integer id){

			Department newDepartment = null;
		try{

		String sql = "SELECT * FROM department"
					+ " WHERE id LIKE ?;";

			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();

			int mpId = rs.getInt("id");
			String departmentName = rs.getString("name");
			Float budget = rs.getFloat("budget");
			int floor = rs.getInt("floor");
			Integer boss_id = rs.getInt("boss_id");

			newDepartment = new Department(mpId, departmentName, floor, budget, boss_id);

			newDepartment.setMedicalProfessionalList(searchMPfromDepartment(id));
			newDepartment.setStaffList(searchStaffFromDepartment(id));

		}catch(Exception e) {
			e.printStackTrace();
		}

		return newDepartment;

	}

@Override
	public ArrayList<MedicalProfessional> searchMPfromDepartment(Integer id) {

		ArrayList<MedicalProfessional> listMedicalProfessional = new ArrayList<MedicalProfessional>();

		try {

			String sql = "SELECT * FROM department AS d JOIN medical_professional AS m ON d.id = m.dep_id"
					+ " WHERE d.id LIKE ?;";

			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();

			while (rs.next()){

				Integer mpId = rs.getInt(6);
				String name = rs.getString(7);
				// Date date = rs.getDate(8);
				String profession = rs.getString(9);
				String email = rs.getString(10);
				String adress = rs.getString(11);
				int phone = rs.getInt(12);

				String sex = rs.getString(14);
				String nie = rs.getString(15);
				Integer contract_id = rs.getInt(16);
				int dep_id = rs.getInt(17);

				// LocalDate dob = date.toLocalDate();

				MedicalProfessional mp = new MedicalProfessional(mpId, name, LocalDate.now(), sex, profession, email,
						adress, phone, nie, dep_id);
				listMedicalProfessional.add(mp);
			}
			}catch (Exception e) {
			e.printStackTrace();
		}

		return listMedicalProfessional;
	}

	@Override
	public ArrayList<Staff> searchStaffFromDepartment(Integer id){

		ArrayList<Staff> listStaff = new ArrayList<Staff>();

		try{

	String sql = "SELECT * FROM department AS d JOIN staff AS s ON d.id = s.dep_id"
					+ " WHERE d.id LIKE ?;";

			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();

			while (rs.next()){

				int staffId = rs.getInt(6);
				String staffName = rs.getString(7);
				// Date staffDate = rs.getDate(8);
				String staffNIE = rs.getString(9);
				String staffProfession = rs.getString(10);
				String staffEmail = rs.getString(11);
				String staffAdress = rs.getString(12);
				int staffPhone = rs.getInt(13);
				// photo
				String staffSex = rs.getString(15);
				int staffcontract_id = rs.getInt(16);
				int staffdep_id = rs.getInt(17);

				// LocalDate staffDob = staffDate.toLocalDate();

				Staff newStaff = new Staff(staffId, staffName, LocalDate.now(), staffSex, staffProfession, staffEmail,
						staffAdress, staffPhone, staffNIE, staffdep_id);
				listStaff.add(newStaff);
			}


		} catch (Exception e) {
			e.printStackTrace();
		}


		return listStaff;
	}

	@Override
	public void deleteById(Integer id) {

		try {
			String sql = "DELETE FROM department WHERE id= ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			prep.executeUpdate();
			System.out.println("Deletion finished.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateDepartment(Department department) {

		String sql = "UPDATE department SET name=? , budget=? , floor=?, boss_id=? WHERE id=?";
		PreparedStatement prep;
		try {
			prep = c.prepareStatement(sql);

			prep.setString(1, department.getName());
			prep.setFloat(2, department.getBudget());
			prep.setInt(3, department.getFloor());
			prep.setInt(4, department.getBoss_id());
			prep.setInt(5, department.getId());
			prep.executeUpdate();
			System.out.println("Update finished.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Department> listAll() {

		List<Department> departmentList = new ArrayList();

		String sql = "SELECT * FROM department";
		try {
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			List<Department> listDepartment = new ArrayList<Department>();

			while (rs.next()) {

				int id = rs.getInt("id");
				String departmentName = rs.getString("name");
				Float budget = rs.getFloat("budget");
				int floor = rs.getInt("floor");

				int boss_id = rs.getInt("boss_id");

				Department newDepartment = new Department(id, departmentName, floor, budget, boss_id);
				departmentList.add(newDepartment);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return departmentList;

	}
}
