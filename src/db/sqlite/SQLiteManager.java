package db.sqlite;

import java.sql.*;

import db.interfaces.DBManager;
import db.interfaces.DepartmentManager;
import db.interfaces.Employee_ContractManager;
import db.interfaces.MedicalProfessionalManager;
import db.interfaces.PacientManager;
import db.interfaces.StaffManager;
import db.interfaces.TreatmentManager;

public class SQLiteManager implements DBManager {

	private Connection c;
	private PacientManager pacient;
	private DepartmentManager department;
	private MedicalProfessionalManager medicalProfessional;
	private StaffManager staff;
	private TreatmentManager treatment;
	private Employee_ContractManager contract;

	public SQLiteManager() {
		this.connect();
	}

	@Override
	public void connect() {

		try {
			Class.forName("org.sqlite.JDBC");
			this.c = DriverManager.getConnection("jdbc:sqlite:./db/RehabilitationClinic.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");

			pacient = new SQLitePacientManager(c);
			department = new SQLiteDepartmentManager(c);
			medicalProfessional = new SQLiteMedicalProfessional(c);
			staff = new SQLiteStaffManager(c);
			treatment = new SQLiteTreatmentManager(c);
			contract = new SQLiteContract(c);
			// We could initialize other manager here
			System.out.println("Database connection opened.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void disconnect() {
		try {
			c.close();
			System.out.println("Database disconnected.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void createTables() {
		// TODO Auto-generated method stub

		//Create tables: begin
		Statement stmt1;
		try {
			stmt1 = c.createStatement();
			String sql1 = "CREATE TABLE department " + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT NOT NULL,"
					+ "budget INTEGER NOT NULL," + "floor INTEGER NOT NULL,"
					+ "boss_id INTEGER REFERENCES medical_professional(id) ON UPDATE CASCADE ON DELETE SET NULL)";

			stmt1.executeUpdate(sql1);
			stmt1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Statement stmt2;
		try {
			stmt2 = c.createStatement();
			String sql2 = "CREATE TABLE dissability " + "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
					+ "name TEXT NOT NULL," + "type TEXT NOT NULL," + "severity TEXT," + "affected_area TEXT NOT NULL,"
					+ "extra_info TEXT,"
					+ "pacient_id INTEGER NOT NULL REFERENCES pacient(id) ON UPDATE CASCADE ON DELETE SET NULL)";

			stmt2.executeUpdate(sql2);
			stmt2.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Statement stmt3;
		try {
			stmt3 = c.createStatement();
			String sql3 = "CREATE TABLE employee_contract" + "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
					+ "free_days INTEGER NOT NULL," + "starting_date  DATE NOT NULL," + "finishing_date DATE NOT NULL,"
					+ "salary REAL NOT NULL," + "week_hours REAL NOT NULL)";

			stmt3.executeUpdate(sql3);
			stmt3.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Statement stmt4;
		try {
			stmt4 = c.createStatement();
			String sql4 = "CREATE TABLE pacient" + "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
					+ "name TEXT NOT NULL," + "sex TEXT," + "dob DATE," + "nie TEXT NOT NULL," + "email TEXT NOT NULL,"
					+ "active BOOLEAN," + "intern BOOLEAN," + "adress TEXT NOT NULL," + "phone INTEGER NOT NULL,"
					+ "photo BLOB)";
			stmt4.executeUpdate(sql4);
			stmt4.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Statement stmt5;
		try {
			stmt5 = c.createStatement();
			String sql5 = "CREATE TABLE treatment" + "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
					+ "type TEXT NOT NULL," + "starting_date DATE," + "finishing_date DATE," + "extra_info TEXT,"
					+ "patient_extra_info TEXT)";
			stmt5.executeUpdate(sql5);
			stmt5.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Statement stmt6;
		try {
			stmt6 = c.createStatement();
			String sql6 = "CREATE TABLE medical_professional" + "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
					+ "name TEXT NOT NULL," + "dob DATE NOT NULL," + "profession TEXT NOT NULL,"
					+ "email TEXT NOT NULL," + "adress TEXT NOT NULL," + "phone INTEGER NOT NULL," + "photo BLOB,"
					+ "sex TEXT NOT NULL," + "nif TEXT NOT NULL,"
					+ "contract_id INTEGER REFERENCES employee_contract(id) ON UPDATE CASCADE ON DELETE SET NULL,"
					+ "dep_id INTEGER REFERENCES department(id) ON UPDATE CASCADE ON DELETE SET NULL)";
			stmt6.executeUpdate(sql6);
			stmt6.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Statement stmt7;
		try {
			stmt7 = c.createStatement();
			String sql7 = "CREATE TABLE staff" + "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," + "name TEXT NOT NULL,"
					+ "dob DATE NOT NULL," + "profession TEXT NOT NULL," + "email TEXT NOT NULL,"
					+ "adress TEXT NOT NULL," + "phone INTEGER NOT NULL," + "photo BLOB," + "sex TEXT NOT NULL,"
					+ "contract_id INTEGER REFERENCES employee_contract(id) ON UPDATE CASCADE ON DELETE SET NULL,"
					+ "dep_id INTEGER REFERENCES department(id) ON UPDATE CASCADE ON DELETE SET NULL)";
			stmt7.executeUpdate(sql7);
			stmt7.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Statement stmt8;
		try {
			stmt8 = c.createStatement();
			String sql8 = "CREATE TABLE medical_professional_treatment "
					+ "(medical_professional_id INTEGER NOT NULL REFERENCES medical_professional(id),"
					+ "treatment_id INTEGER NOT NULL REFERENCES treatment(id),"
					+ "PRIMARY KEY (medical_professional_id, treatment_id))";
			stmt8.executeUpdate(sql8);
			stmt8.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Statement stmt9;
		try {
			stmt9 = c.createStatement();
			String sql9 = "CREATE TABLE treatment_pacient " + "(pacient_id INTEGER NOT NULL REFERENCES pacient(id),"
					+ "treatment_id INTEGER NOT NULL REFERENCES treatment(id),"
					+ "PRIMARY KEY (pacient_id, treatment_id))";
			stmt9.executeUpdate(sql9);
			stmt9.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Statement stmt10;
		try {
			stmt10 = c.createStatement();
			String sql10 = "CREATE TABLE medical_professional_pacient"
					+ "(medical_professional_id INTEGER NOT NULL REFERENCES medical_professional(id),"
					+ "pacient_id INTEGER NOT NULL REFERENCES pacient(id),"
					+ "PRIMARY KEY (medical_professional_id, pacient_id))";
			stmt10.executeUpdate(sql10);
			stmt10.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Tables created.");
	}

	public int getLastId(){

		int result = 1;

		try{

			String query = "SELECT last_insert_rowid() AS lastId";
			PreparedStatement p = c.prepareStatement(query);
			ResultSet rs = p.executeQuery();
			result = rs.getInt("lastId");

		}catch(SQLException e){
			
			e.printStackTrace();
		}

		return result;
	}

	/*
	 * public SQLiteManager() { this.connect(); }
	 */

	@Override
	public PacientManager getPacientManager() {

		return pacient;
	}

	@Override
	public DepartmentManager getDepartmentManager() {

		return department;
	}

	@Override
	public MedicalProfessionalManager getMedicalProfessionalManager() {
		// TODO Auto-generated method stub
		return medicalProfessional;
	}

	@Override
	public StaffManager getStaffManager() {
		// TODO Auto-generated method stub
		return staff;

	}

	@Override
	public TreatmentManager getTreatmentManager() {
		// TODO Auto-generated method stub
		return treatment;
	}


	@Override
	public Employee_ContractManager getEmployee_ContractManager() {
		// TODO Auto-generated method stub
		return contract;
	}

	@Override
	public int lastId() {
		// TODO Auto-generated method stub
		return 0;
	}
}