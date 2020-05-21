package db.interfaces;

import pojos.Employee_Contract;

public interface Employee_ContractManager {

	public void add (Employee_Contract contract);
	public Employee_Contract searchById(Integer id);
	public void deleteById(Integer id);
	public void updateContract(Employee_Contract contract);
}
