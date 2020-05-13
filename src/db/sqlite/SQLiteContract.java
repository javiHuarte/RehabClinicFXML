package db.sqlite;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import db.interfaces.*;
import pojos.*;

public class SQLiteContract implements Employee_ContractManager {

		private Connection c;

		public SQLiteContract(Connection n) {
			this.c = n;
		}

		@Override
		public void add(Employee_Contract contract) {

			try {
				String sql = "INSERT INTO employee_contract (free_days, starting_date, finishing_date, salary, week_hours) "
						+ "VALUES (?,?,?,?,?);";

				PreparedStatement prep = c.prepareStatement(sql);
				prep.setInt(1, contract.getFree_days());
				prep.setDate(2, Date.valueOf(contract.getStarting_date()));
				prep.setDate(3, Date.valueOf(contract.getFinishing_date()));
				prep.setFloat(4, contract.getSalary());
				prep.setInt(5, contract.getWeek_working_hours());

				prep.executeUpdate();
				prep.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		@Override
		public Employee_Contract searchById(Integer id){

				Employee_Contract newContract = null;
			try{

			String sql = "SELECT * FROM employee_contract"
						+ " WHERE id LIKE ?;";

				PreparedStatement prep = c.prepareStatement(sql);
				prep.setInt(1, id);
				ResultSet rs = prep.executeQuery();

				Integer mpId = rs.getInt("id");
				Integer free_days = rs.getInt("free_days");
				Date starting_date1 = rs.getDate("starting_date");
				Date finishing_date1 = rs.getDate("finishing_date");
				Float salary = rs.getFloat("salary");
				Integer week_hours = rs.getInt("week_hours");
				
				LocalDate starting_date = starting_date1.toLocalDate();
				LocalDate finishing_date = finishing_date1.toLocalDate();
				
				newContract = new Employee_Contract(mpId,free_days, starting_date, finishing_date,  week_hours, salary);

				

			}catch(Exception e) {
				e.printStackTrace();
			}

			return newContract;

		}
}
