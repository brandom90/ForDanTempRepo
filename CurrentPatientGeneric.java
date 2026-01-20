package assign02;

import java.util.GregorianCalendar;


/**
 * This generic class represents a UHealth patient who has a unique UHealthID
 * and a first and last name with physicians and last visit date.
 *
 * @author Brandon Miles & Gia Giang
 * @version 1/17/2026
 */

public class CurrentPatientGeneric<Type> extends Patient {
	private Type physician;
	private GregorianCalendar lastVisit;
	
	/**
	 * Creates a patient with a given name, uHealthID, their assigned physician, and they last visit date.
	 * 
	 * @param firstName is the patient's first name
	 * @param lastName is the patient's last name
	 * @param uHealthID is the patient's unique health ID
	 * @param physician is the patient's assigned physician with type generic
	 * @param lastVisit is the patient's last visit date, in Gregorian calendar format
	 */
	public CurrentPatientGeneric(String firstName, String lastName, UHealthID uHealthID, Type physician, GregorianCalendar lastVisit) {
		super(firstName, lastName, uHealthID);
		this.physician = physician;
		this.lastVisit = lastVisit;
	}
	
	/**
	 * Getter method for patient's assigned physician
	 * 
	 * @return patient's physician id
	 */
	public Type getPhysician() {
		return this.physician;
	}
	
	/**
	 * getter method for patient's last visit date
	 * 
	 * @return patient's last visit date
	 */
	public GregorianCalendar getLastVisit() {
		return this.lastVisit;
	}
	
	/**
	 * setter method for patient's newly assigned physician
	 * 
	 * @param newPhysician is the new physician to be assigned to this instance's patient
	 */
	public void updatePhysician(Type newPhysician) {
		this.physician = newPhysician;
	}
	
	/**
	 * setter method for patient's last visit date
	 * 
	 * @param date of last visit for this patient, in YY/MM/DD
	 */
	public void updateLastVisit(GregorianCalendar date) {
		this.lastVisit = date;
	}
}
