package db.sqlite;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
