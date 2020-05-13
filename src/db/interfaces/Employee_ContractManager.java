package db.interfaces;

import pojos.Employee_Contract;

public interface Employee_ContractManager {
	public void add (Employee_Contract employee_Contract);
	public Employee_Contract searchById(Integer id);
}
