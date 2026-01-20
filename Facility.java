package assign02;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * This class represents a record of patients that have visited a UHealth
 * facility. It maintains a collection of CurrentPatients.
 *
 * @author CS 2420 course staff and Brandon Miles & Gia Giang
 * @version 1/17/2026
 */
public class Facility {

	private ArrayList<CurrentPatient> patientList;

	/**
	 * Creates an empty facility record.
	 */
	public Facility() {
		patientList= new ArrayList<CurrentPatient>();
	}

	/**
	 * Adds the given patient to the list of patients, avoiding duplicates.
	 *
	 * @param patient - patient to be added to this record
	 * @return true if the patient was added,
	 *         false if the patient was not added because they already exist in the record
	 */
	public boolean addPatient(CurrentPatient patient) {
		for (int i = 0; i < patientList.size(); i++)
			if (patientList.get(i).equals(patient)) {
				return false;
			}
		patientList.add(patient);
		return true;
	}
	
	/**
	 * Adds all patients from the given list to the list of patients.
	 * 
	 * @param patients - list of patients to be added to this record
	 */
	public void addAll(ArrayList<CurrentPatient> patients) {
		patientList.addAll(patients);
	}

	/**
	 * Retrieves the patient with the given UHealthID.
	 *
	 * @param patientID - ID of patient to be retrieved
	 * @return the patient with the given ID, or null if no such patient
	 *         exists in the record
	 */
	public CurrentPatient lookUpByUHID(UHealthID patientID) {
		for (int i = 0; i < patientList.size(); i++) {
			UHealthID thisPatient = patientList.get(i).getUHealthID();
			if(thisPatient.equals(patientID))
				return patientList.get(i);
		}
		return null;
	}

	/**
	 * Retrieves the patient(s) with the given physician.
	 *
	 * @param physician - physician of patient(s) to be retrieved
	 * @return a list of patient(s) with the given physician (in any order),
	 *         or an empty list if no such patients exist in the record
	 */
	public ArrayList<CurrentPatient> lookUpByPhysician(int physician) {
		ArrayList<CurrentPatient> patientWithPhysician = new ArrayList<>();
		for (int i = 0; i < patientList.size(); i++) {
			if(patientList.get(i).getPhysician() == (physician))
				patientWithPhysician.add(patientList.get(i));
		}
		return patientWithPhysician;
	}

	/**
	 * Retrieves the patient(s) with last visits newer than a given date.
	 * Note that GregorianCalendar includes a compareTo method that may be useful.
	 *
	 * NOTE: If the last visit date equals this date, do not add the patient.
	 *
	 * @param date - cutoff date earlier than visit date of all returned patients.
	 * @return a list of patient(s) with last visit date after cutoff (in any order),
	 *         or an empty list if no such patients exist in the record
	 */
	public ArrayList<CurrentPatient> getRecentPatients(GregorianCalendar date) {
		ArrayList<CurrentPatient> recentDate = new ArrayList<>();
		for (int i = 0; i < patientList.size(); i++) {
			if (patientList.get(i).getLastVisit().compareTo(date) > 0) {
				recentDate.add(patientList.get(i));
			}
		}
		return recentDate;
	}

	/**
	 * Retrieves a list of physicians assigned to patients at this facility.
	 *
	 * NOTE: Do not put duplicates in the list. Make sure each physician
	 *       is only added once.
	 *
	 * @return a list of physician(s) assigned to current patients,
	 * 	       or an empty list if no patients exist in the record
	 */
	public ArrayList<Integer> getPhysicianList() {
		ArrayList<Integer> physicians = new ArrayList<>();
		for (int i = 0; i < patientList.size(); i++) {
			boolean counterMeasure = true;
			for (int j = 0; j < physicians.size(); j++ ) {
				if (physicians.get(j).equals(patientList.get(i).getPhysician()))
					counterMeasure = false;	
			}
			if (counterMeasure)
				physicians.add(patientList.get(i).getPhysician());
		}
	
		return physicians;
	}

	/**
	 * Sets the physician of a patient with the given UHealthID.
	 *
	 * NOTE: If no patient with the ID exists in the collection, then this
	 * 		 method has no effect.
	 *
	 * @param patientID - UHealthID of patient to modify
	 * @param physician - identifier of patient's new physician
	 */
	public void setPhysician(UHealthID patientID, int physician) {
		for (int i = 0; i < patientList.size(); i++) {
			if (patientList.get(i).getUHealthID().equals(patientID)) {
				patientList.get(i).updatePhysician(physician);
				break;
			}
		}
	}

	/**
	 * Sets the last visit date of a patient with the given UHealthID.
	 *
	 * NOTE: If no patient with the ID exists in the collection, then this
	 * 		 method has no effect.
	 *
	 * @param patientID - UHealthID of patient to modify
	 * @param date - date of last visit
	 */
	public void setLastVisit(UHealthID patientID, GregorianCalendar date) {
		for (int i = 0; i < patientList.size(); i++) {
			if (patientList.get(i).getUHealthID().equals(patientID)) {
				patientList.get(i).updateLastVisit(date);
				break;
			}
		}
	}
}
