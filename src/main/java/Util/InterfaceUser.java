package Util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Local
import dao.*;
import table.*;

public class InterfaceUser {
	
	// Methods
	
		// Access
	
	public static void homeStart() {
		System.out.print("Welcome to [INSERT COMPANY NAME] Flight Service, input a corresponding number to access the service :\n"
				+ "1) Flight Management\n"
				+ "2) Reservation Management\n"
				+ "3) Exit\n");
		Integer inputUser = Integer.valueOf(Scan.scan.nextLine());
		switch (inputUser) {
			case 1 :
				InterfaceUser.flightManagement();
				break;
			case 2 :
				InterfaceUser.reservManagement();
				break;
			default :
				System.out.println("Thank you for using our services, goodbye !");
		}
		return;
	}
	

	public static void flightManagement() {
		System.out.print("Flight management service :\n"
				+ "1) Create a new flight\n"
				+ "2) Flight List\n"
				+ "3) Get flight information with a selected fligh number\n"
				+ "4) Get flight information with a selected city departure and arrival\n"
				+ "3) Exit\n");
		Integer inputUser = Integer.valueOf(Scan.scan.nextLine());
		switch (inputUser) {
			case 1 :
				FlightDAO.insert(InterfaceUser.createFlight());
				break;
			case 2 :
				for (Flight f : FlightDAO.flightList())
					System.out.println(f);
				break;
			case 3 : 
				System.out.println(FlightDAO.find(InterfaceUser.getFlightNumber()));
				break;
			case 4 :
				String cityDep = InterfaceUser.getCityDeparture();
				String cityArriv = InterfaceUser.getCityArrival();
				for (Flight f : FlightDAO.getFlightByCities(cityDep, cityArriv))
					System.out.println(f);
				break;
			default :
				InterfaceUser.homeStart();
		}
		InterfaceUser.homeStart();
		return;
	}

	public static void reservManagement() {
		System.out.print("Reservation management service :\n"
				+ "1) Create a new reservation\n"
				+ "2) Get reservations by flight number\n"
				+ "3) Cancel reservation with reservation number\n"
				+ "4) Get all reservation made by someone\n"
				+ "3) Exit\n");
		Integer inputUser = Integer.valueOf(Scan.scan.nextLine());
		switch (inputUser) {
			case 1 :
				ReservationDAO.insert(InterfaceUser.createReservation());
				break;
			case 2 :
				for (Reservation r : FlightDAO.getReservations(InterfaceUser.getFlightNumber()))
					System.out.println(r);
				break;
			case 3 :
				ReservationDAO.drop(InterfaceUser.getReservNumber());
				break;
			case 4 :
				for (Reservation r : ReservationDAO.getReservation(InterfaceUser.getReservName())) {
//					System.out.println("Reservation :" + r.getReservNumber() + " firstname : " + r.getFirstName() 
//					+ " lastname : " + r.getLastName());
//					for (Flight f : ReservationDAO.getFlightReservation(r.getReservNumber()))
//						System.out.println("Reservation :" + r.getReservNumber() + " firstname : " + r.getFirstName() 
//						+ " lastname : " + r.getLastName()) + " num vol " ;);
				}
				break;
			default :
				InterfaceUser.homeStart();
		}
		InterfaceUser.homeStart();
		return;
	}
	
	
		// Getters
	
	public static String getReservNumber() {
		System.out.print("Insert your Reservation Number : ");
		return Scan.scan.nextLine();
	}
	
	public static String getReservName() {
		System.out.print("Insert your Reservation Number : ");
		return Scan.scan.nextLine();
	}
	
	public static String getFlightNumber() {
		System.out.print("Insert the Flight Number : ");
		return Scan.scan.nextLine();
	}
	
	public static String getCityDeparture() {
		System.out.println("Insert name of city departure");
		return Scan.scan.nextLine();
	}
	
	public static String getCityArrival() {
		System.out.println("Insert name of city arrival");
		return Scan.scan.nextLine();
	}
		// Setters

	public static Flight createFlight() {
		System.out.print("Input a flight number (Integer, format : between 0001 and 9999) : ");
		String flightNumber = Scan.scan.nextLine();
		System.out.print("Input a plane type (possible type : A330, A340, A380, B747) : ");
		PlaneType planeType = PlaneType.valueOf(Scan.scan.nextLine());
		System.out.print("Input number of places : ");
		Integer numberPlace = Integer.valueOf(Scan.scan.nextLine());
		System.out.print("Input a city for departure : ");
		String cityDeparture = Scan.scan.nextLine();
		System.out.print("Input a city for arrival : ");
		String cityArrival = Scan.scan.nextLine();
		System.out.print("Input a date for departure (Format: \"dd/MM/yyyy\") : ");
		DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate dateDeparture = LocalDate.parse(Scan.scan.nextLine(),format);

		return new Flight(flightNumber,planeType,numberPlace,cityDeparture,cityArrival,dateDeparture);
	}
	
	public static Reservation createReservation() {
		System.out.print("Input your firstname : ");
		String firstName = Scan.scan.nextLine();
		System.out.print("Input your lastname : ");
		String lastName = Scan.scan.nextLine();
		System.out.print("Input your age (integer,year) : ");
		Integer age = Integer.valueOf(Scan.scan.nextLine());
		System.out.print("Input the flight number : ");
		String flightNumber = Scan.scan.nextLine();

		return new Reservation(firstName, lastName, age, FlightDAO.find(flightNumber));
	}
}
