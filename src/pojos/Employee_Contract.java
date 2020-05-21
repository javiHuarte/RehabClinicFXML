package pojos;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

import javafx.beans.property.*;

public class Employee_Contract implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2122101848221178012L;

	private SimpleIntegerProperty id;
	private SimpleIntegerProperty free_days;
	private LocalDate starting_date;
	private LocalDate finishing_date;
	private SimpleFloatProperty week_hours;
	private SimpleFloatProperty salary;

	public Employee_Contract() {
		super();
	}



	public Employee_Contract(Integer free_days, LocalDate startingDate, LocalDate finishDate, Float week_hours,
			Float salary) {
		super();

		this.free_days = new SimpleIntegerProperty(free_days);
		this.starting_date = startingDate;
		this.finishing_date = finishDate;
		this.week_hours = new SimpleFloatProperty(week_hours);
		this.salary = new SimpleFloatProperty(salary);
	}


	public Employee_Contract(Integer id, Integer free_days, LocalDate starting_date, LocalDate finishing_date,
			Float week_hours, Float salary) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.free_days = new SimpleIntegerProperty(free_days);
		this.starting_date = starting_date;
		this.finishing_date = finishing_date;
		this.week_hours = new SimpleFloatProperty(week_hours);
		this.salary = new SimpleFloatProperty(salary);
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee_Contract other = (Employee_Contract) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}





	public Integer getId() {
		return id.get();
	}



	public void setId(Integer id) {
		this.id =  new SimpleIntegerProperty(id);
	}



	public Integer getFree_days() {
		return free_days.get();
	}



	public void setFree_days(Integer free_days) {
		this.free_days = new SimpleIntegerProperty(free_days);;
	}



	public LocalDate getStarting_date() {
		return starting_date;
	}



	public void setStarting_date(LocalDate starting_date) {
		this.starting_date = starting_date;
	}



	public LocalDate getFinishing_date() {
		return finishing_date;
	}



	public void setFinishing_date(LocalDate finishing_date) {
		this.finishing_date = finishing_date;
	}



	public Float getWeek_hours() {
		return week_hours.get();
	}



	public void setWeek_hours(Float week_hours) {
		this.week_hours = new SimpleFloatProperty(week_hours);
	}



	public Float getSalary() {
		return salary.get();
	}



	public void setSalary(Float salary) {
		this.salary = new SimpleFloatProperty(salary);
	}



	@Override
	public String toString() {
		return "Employee_Contract [id=" + id + ", free_days=" + free_days + ", starting_date=" + starting_date
				+ ", finishing_date=" + finishing_date + ", week_working_hours=" + week_hours + ", salary="
				+ salary + "]";
	}


}
