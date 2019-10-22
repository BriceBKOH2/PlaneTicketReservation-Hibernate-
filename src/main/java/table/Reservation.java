package table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Reservation {

	// Attributes
	
	@Id
	@GeneratedValue
	Long id;
	
	@Column (unique = true)
	String reservNumber;
	
	@Column
	@NotBlank
	String firstName;
	
	@Column
	@NotBlank
	String lastName;
	
	@Column
	@NotNull
	Integer age;
	
	@ManyToOne
	@NotNull
	Flight flight;

	// Constructors
	
	public Reservation() {
		
	}
	
	public Reservation(String firstName, String lastName, Integer age, Flight flight) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.flight = flight;
	}

	// Methods
	
		// Getters
	
	public Long getId() {
		return id;
	}

	public String getReservNumber() {
		return reservNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Integer getAge() {
		return age;
	}

	public Flight getFlight() {
		return flight;
	}

	
		// Setters
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setReservNumber() {
		if (id != null && flight != null)
			reservNumber = "" + flight.getFlightNumber() + "-" + this.getId();
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	
		// Others
	
	@Override
	public String toString() {
		return "Reservation [id= " + id + ", reservNumber= " + reservNumber + ", firstName= " + firstName + ", lastName= "
				+ lastName + ", age= " + age + ", "
						+ "flight= " + flight + " ]";
	}
	
	
	
}
