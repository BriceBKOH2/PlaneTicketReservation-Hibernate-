
// Local
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Util.InterfaceUser;
import dao.*;
import table.*;

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		

		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Flight f1 = new Flight("0001", PlaneType.valueOf("A320"), 10, 
				"Montpellier", "Paris", LocalDate.parse("05/12/2017",format));
		Flight f2 = new Flight("0002", PlaneType.valueOf("B747"), 10, 
				"Marseille", "Paris", LocalDate.parse("06/12/2017",format));
		Flight f3 = new Flight("0003", PlaneType.valueOf("B747"), 10, 
				"Marseille", "Paris", LocalDate.parse("06/10/2017",format));
		Flight f4 = new Flight("0004", PlaneType.valueOf("A340"), 10, 
				"Montpellier", "Paris", LocalDate.parse("10/11/2017",format));
		FlightDAO.insert(f1,f2,f3,f4);

		Reservation r1 = new Reservation("Brice","Beteille",30,f1);
		Reservation r2 = new Reservation("Brice","Beteille",30,f2);
		Reservation r3 = new Reservation("Maurice","MoMo",30,f2);
		ReservationDAO.insert(r1,r2,r3);

		InterfaceUser.homeStart();
		return;
	}

}
