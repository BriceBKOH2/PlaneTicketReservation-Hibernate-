package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

//Local
import table.*;

public class ReservationDAO {
	// Methods
	
		// Getters
	
	public static List<Flight> getFlightReservation(String reservNumber) {
		EntityManager entManag = DBHelper.createEntityManager();
		TypedQuery<Flight> query = entManag.createQuery("SELECT DISTINCT f FROM Flight f "
				+ "INNER JOIN f.reservList r"
				+ "WHERE r.reservNumber =:reservNumber "
				+ "ORDER BY f.timeDeparture",Flight.class);
		query.setParameter("reservNumber", reservNumber);
		List<Flight>flights = query.getResultList();
		entManag.close();
		return flights;
	}
		
	public static List<Reservation> getReservation(String lastName) {
		EntityManager entManag = DBHelper.createEntityManager();
		TypedQuery<Reservation> query = entManag.createQuery("SELECT DISTINCT r FROM Reservation r "
				+ "WHERE r.lastName =:lastName ",Reservation.class);
		query.setParameter("lastName", lastName);
		List<Reservation> reserv = query.getResultList();
		entManag.close();
		return reserv;
	}
	
		// Setters
	
	public static void insert(Reservation...reservs) {
		EntityManager entManag = DBHelper.createEntityManager();
		DBHelper.beginTx(entManag);
		for (Reservation r : reservs) {
			entManag.persist(r);
			r.setReservNumber();
		}
		DBHelper.commitTxAndClose(entManag);
	}
	
	public static void drop(Reservation...reservs) {
		EntityManager entManag = DBHelper.createEntityManager();
		DBHelper.beginTx(entManag);
		for (Reservation r : reservs)
			entManag.remove(r);
		DBHelper.commitTxAndClose(entManag);
	}
	
	public static void drop(String reservNumber) {
		EntityManager entManag = DBHelper.createEntityManager();
		DBHelper.beginTx(entManag);
		TypedQuery<Reservation> query = entManag.createQuery("SELECT DISTINCT r FROM Reservation r "
				+ "WHERE r.reservNumber =:reservNumber",Reservation.class);
		query.setParameter("reservNumber", reservNumber);
		entManag.remove(query.getSingleResult());
		DBHelper.commitTxAndClose(entManag);
	}
}
