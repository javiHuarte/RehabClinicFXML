package pojos;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

/**
 * @author Ignacio
 *
 */
public class Employee_Contract implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2122101848221178012L;

	private Integer id;
	private int free_days;
	private LocalDate starting_date;
	private LocalDate finishing_date;
	private int week_working_hours;
	private float salary;

	public Employee_Contract() {
		super();
	}


	public Employee_Contract(int free_days, LocalDate startingDate, LocalDate finishDate, int week_working_hours,
			float salary) {
		super();
		this.free_days = free_days;
		this.starting_date = startingDate;
		this.finishing_date = finishDate;
		this.week_working_hours = week_working_hours;
		this.salary = salary;
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
		return id;
	}
	public int getFree_days() {
		return free_days;
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


	public int getWeek_working_hours() {
		return week_working_hours;
	}
	public float getSalary() {
		return salary;
	}

	@Override
	public String toString() {
		return "Employee_Contract [id=" + id + ", free_days=" + free_days + ", starting_date=" + starting_date
				+ ", finishing_date=" + finishing_date + ", week_working_hours=" + week_working_hours + ", salary="
				+ salary + "]";
	}


}
