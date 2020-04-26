package db.sqlite;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

			String sql = "INSERT INTO department (name, budget ,floor,boss_id)"
					+ "VALUES (?,?,?,?);";



			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, department.getName());
			prep.setFloat(2, department.getBudget());
			prep.setInt(3, department.getFloor());
			//prep.setInt(4, department.getBoss_id());

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

				Department newDepartment = new Department(id,departmentName,floor,budget,boss_id);
				departmentList.add(newDepartment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return departmentList;
	}

	@Override
	public Department searchById (Integer id) {
	Department newDepartment = new Department();
	try {
		String sql = "SELECT * FROM department AS d JOIN medical_professional AS m ON d.id = m.dep_id"
				+ " JOIN staff AS s ON d.id = s.dep_id"
				+ " WHERE d.id LIKE ?;";


		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, id);
		ResultSet rs = prep.executeQuery();
		List<MedicalProfessional> listMedicalProfessional = new ArrayList<MedicalProfessional>();
		List<Staff> listStaff = new ArrayList<Staff>();

		boolean departmentCreated = false;
		while (rs.next()){

			if(!departmentCreated) {
			int departmentId = rs.getInt(1);
			String departmentName = rs.getString(2);
			Float budget = rs.getFloat(3);
			int floor = rs.getInt(4);
			Integer boss_id = rs.getInt(5);

			newDepartment = new Department(departmentId,departmentName,floor,budget,boss_id,listMedicalProfessional,listStaff);
			departmentCreated = true;
			}


			int medical_professionalId = rs.getInt(6);
			String medical_professionalName = rs.getString(7);
			Date medical_professionaldob = rs.getDate(8);
			String medical_professionalProfession = rs.getString(9);
			String medical_professionalEmail = rs.getString(10);
			String medical_professionalAdress = rs.getString(11);
			int medical_professionalPhone = rs.getInt(12);

			String medical_professionalSex = rs.getString(14);
			String medical_professionalNIE = rs.getString(15);
			Integer medical_professionalContract_id = rs.getInt(16);
			int medical_professionaldep_id = rs.getInt(17);
			MedicalProfessional newMedicalProfessional = new MedicalProfessional(medical_professionalId,medical_professionalName,
					medical_professionaldob,medical_professionalSex,medical_professionalProfession,medical_professionalEmail,
					medical_professionalAdress,medical_professionalPhone,medical_professionalNIE,medical_professionaldep_id);
			listMedicalProfessional.add(newMedicalProfessional);


			int staffId = rs.getInt(18);
			String staffName = rs.getString(19);
			//Date staffdob = rs.getDate(20);
			String staffProfession = rs.getString(21);
			String staffEmail = rs.getString(22);
			String staffAdress = rs.getString(23);
			String staffNIE = rs.getString(24);
			Boolean staffActive = rs.getBoolean(25);
			int staffPhone = rs.getInt(26);
			String staffSex = rs.getString(28);
			int staffcontract_id = rs.getInt(29);
			int staffdep_id = rs.getInt(30);
			Staff newStaff = new Staff(staffId,staffName,null,staffProfession,staffEmail,staffAdress,staffPhone,staffSex,
					staffNIE,staffActive,staffdep_id,staffcontract_id);
			listStaff.add(newStaff);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return newDepartment;
}

	@Override
	public void deleteById (Integer id) {

		try {
		String sql = "DELETE FROM department WHERE id= ?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, id);
		prep.executeUpdate();
		System.out.println("Deletion finished.");
		}catch(Exception e) {
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
				e.printStackTrace();}
		}

		@Override
		public List<Department> listAll() {

			List<Department> departmentList = new ArrayList();

			String sql = "SELECT * FROM department";
			try {
				PreparedStatement prep = c.prepareStatement(sql);
				ResultSet rs = prep.executeQuery();
				List<Department> listDepartment = new ArrayList<Department>();

				while(rs.next()){

					int id = rs.getInt("id");
					String departmentName = rs.getString("name");
					Float budget = rs.getFloat("budget");
					int floor = rs.getInt("floor");

					int boss_id = rs.getInt("boss_id");

					Department newDepartment = new Department(id,departmentName,floor,budget, boss_id);
					departmentList.add(newDepartment);


				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return departmentList;

		}
	}



