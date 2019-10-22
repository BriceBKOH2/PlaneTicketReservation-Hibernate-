package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

// Local
import table.*;

public class FlightDAO {
	// Methods
	
		// Getters
	
	public static List<Flight> flightList() {
		List<Flight> flights;
		EntityManager entManag = DBHelper.createEntityManager();
		TypedQuery<Flight> query = entManag.createQuery("SELECT DISTINCT f FROM Flight f "
				+ "LEFT JOIN fetch f.reservList "
				+ "ORDER BY f.timeDeparture",Flight.class);
		flights = query.getResultList();
		entManag.close();
		return flights;
	}

	public static Flight find(Long id) {
		EntityManager entManag = DBHelper.createEntityManager();
		Flight flight = entManag.find(Flight.class,id);
		DBHelper.commitTxAndClose(entManag);
		return flight;
	}
		
	public static Flight find(String flightNumber) {
		EntityManager entManag = DBHelper.createEntityManager();
		TypedQuery<Flight> query = entManag.createQuery("SELECT DISTINCT f FROM Flight f "
				+ "WHERE f.flightNumber =:flightNumber",Flight.class);
		query.setParameter("flightNumber", flightNumber);
		Flight flight = query.getSingleResult();
		entManag.close();
		return flight;
	}
	
	public static List<Reservation> getReservations(String flightNumber) {
		EntityManager entManag = DBHelper.createEntityManager();
		TypedQuery<Reservation> query = entManag.createQuery("SELECT DISTINCT r FROM Flight f "
				+ "INNER JOIN fetch f.reservList r "
				+ "WHERE f.flightNumber =:flightNumber ",Reservation.class);
		query.setParameter("flightNumber", flightNumber);
		List<Reservation> reservs = query.getResultList();
		entManag.close();
		return reservs;
	}
	
	public static List<Flight> getFlightByCities(String cityDeparture, String cityArrival) {
		EntityManager entManag = DBHelper.createEntityManager();
		TypedQuery<Flight> query = entManag.createQuery("SELECT DISTINCT f FROM Flight f "
				+ "WHERE f.cityDeparture =:cityDeparture AND f.cityArrival =:cityArrival",Flight.class);
		query.setParameter("cityDeparture", cityDeparture);
		query.setParameter("cityArrival", cityArrival);
		List<Flight>flights = query.getResultList();
		entManag.close();
		return flights;
	}
		// Setters
	
	public static void insert(Flight...flights) {
		EntityManager entManag = DBHelper.createEntityManager();
		DBHelper.beginTx(entManag);
		for (Flight f : flights)
			entManag.persist(f);
		DBHelper.commitTxAndClose(entManag);
	}
	
	public static void drop(Flight...flights) {
		EntityManager entManag = DBHelper.createEntityManager();
		DBHelper.beginTx(entManag);
		for (Flight f : flights)
			entManag.remove(f);
		DBHelper.commitTxAndClose(entManag);
	}

}
