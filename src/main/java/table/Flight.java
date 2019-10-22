package table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Flight {
	
	// Attributes
	
	@Id
	@GeneratedValue
	Long id;
	
	@Column (unique = true)
	@NotBlank
	String flightNumber;
	
	@Column (name = "type")
	@NotNull
	@Enumerated (EnumType.STRING)
	PlaneType planeType;
	
	@Column (name = "place")
	@NotBlank
	Integer numberPlace;

	@Column
	@NotBlank
	String cityDeparture;
	
	@Column
	@NotBlank
	String cityArrival;
	
	@Column
	@NotNull
//	@Temporal(TemporalType.TIME)
	LocalDate timeDeparture;
	
	@OneToMany (mappedBy = "flight")
	List<Reservation> reservList;

	
	// Constructors
	
	public Flight() {
		
	}

	public Flight(String flightNumber, PlaneType planeType, Integer numberPlace, String cityDeparture, String cityArrival,
			LocalDate timeDeparture) {
		super();
		this.flightNumber = flightNumber;
		this.planeType = planeType;
		this.numberPlace = numberPlace;
		this.cityDeparture = cityDeparture;
		this.cityArrival = cityArrival;
		this.timeDeparture = timeDeparture;
		reservList = new ArrayList<Reservation>();
	}
	
	
	// Methods 
	
		// Getters
	
	public Long getId() {
		return id;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public PlaneType getPlaneType() {
		return planeType;
	}

	public Integer getNumberPlace() {
		return numberPlace;
	}

	public String getCityDeparture() {
		return cityDeparture;
	}

	public String getCityArrival() {
		return cityArrival;
	}

	public LocalDate getTimeDeparture() {
		return timeDeparture;
	}

	public List<Reservation> getReservList() {
		return reservList;
	}

		// Setters
	

	public void setId(Long id) {
		this.id = id;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public void setPlaneType(PlaneType planeType) {
		this.planeType = planeType;
	}


	public void setNumberPlace(Integer numberPlace) {
		this.numberPlace = numberPlace;
	}

	public void setCityDeparture(String cityDeparture) {
		this.cityDeparture = cityDeparture;
	}

	public void setCityArrival(String cityArrival) {
		this.cityArrival = cityArrival;
	}

	public void setTimeDeparture(LocalDate timeDeparture) {
		this.timeDeparture = timeDeparture;
	}

	public void setReservList(Reservation... reservLists) {
		for (Reservation r : reservLists)
			reservList.add(r);
	}
	
	public void setReservList(List<Reservation> reservList) {
		this.reservList = reservList;
	}
	
	public void addReservList(Reservation... reservLists) {
		for (Reservation r : reservLists)
			reservList.add(r);
	}
	
	public void addReservList(List<Reservation> reservList) {
			this.reservList.addAll(reservList);
	}
	
		// Others
	

	@Override
	public String toString() {
		return "Flight [id=" + id + ", flightNumber=" + flightNumber + ", planeType=" + planeType + ", numberPlace="
				+ numberPlace + ", cityDeparture=" + cityDeparture + ", cityArrival=" + cityArrival + ", timeDeparture="
				+ timeDeparture + "]";
	}

	
	
}
