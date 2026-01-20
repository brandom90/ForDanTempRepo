package assign02;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;

/**
 * This class represents a record of patients that have visited a UHealth
 * facility. It maintains a collection of CurrentPatientGeneric Generics.
 *
 * @author CS 2420 course staff and Brandon Miles & Gia Giang
 * @version 1/17/2026
 */
public class FacilityGeneric<Type> {

	private ArrayList<CurrentPatientGeneric<Type>> patientList;

	/**
	 * Creates an empty facility record.
	 */
	public FacilityGeneric() {
		patientList= new ArrayList<CurrentPatientGeneric<Type>>();
	}

	/**
	 * Adds the given patient to the list of patients, avoiding duplicates.
	 *
	 * @param patient - patient to be added to this record
	 * @return true if the patient was added,
	 *         false if the patient was not added because they already exist in the record
	 */
	public boolean addPatient(CurrentPatientGeneric<Type> patient) {
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
	public void addAll(ArrayList<CurrentPatientGeneric<Type>> patients) {
		patientList.addAll(patients);
	}

	/**
	 * Retrieves the patient with the given UHealthID.
	 *
	 * @param patientID - ID of patient to be retrieved
	 * @return the patient with the given ID, or null if no such patient
	 *         exists in the record
	 */
	public CurrentPatientGeneric<Type> lookUpByUHID(UHealthID patientID) {
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
	public ArrayList<CurrentPatientGeneric<Type>> lookupByPhysician(Type physician) {
		ArrayList<CurrentPatientGeneric<Type>> patientWithPhysician = new ArrayList<>();
		for (int i = 0; i < patientList.size(); i++) {
			if(patientList.get(i).getPhysician().equals(physician))
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
	public ArrayList<CurrentPatientGeneric<Type>> getRecentPatients(GregorianCalendar date) {
		ArrayList<CurrentPatientGeneric<Type>> recentDate = new ArrayList<>();
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
	public ArrayList<Type> getPhysicianList() {
		ArrayList<Type> physicians = new ArrayList<>();
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
	public void setPhysician(UHealthID patientID, Type Physician) {
		for (int i = 0; i < patientList.size(); i++) {
			if (patientList.get(i).getUHealthID().equals(patientID)) {
				patientList.get(i).updatePhysician(Physician);
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
	
	/**
	 * Returns the list of current patients in this facility, sorted according to
	 * the provided Comparator.
	 * 
	 * @param cmp - the Comparator to use when sorting
	 * @return an ordered list of all patients in this facility
	 */
	public ArrayList<CurrentPatientGeneric<Type>> getOrderedPatients(Comparator<CurrentPatientGeneric<Type>> cmp) {
	    ArrayList<CurrentPatientGeneric<Type>> patientListCopy = new ArrayList<CurrentPatientGeneric<Type>>();
	    for(CurrentPatientGeneric<Type> patient : patientList) {
	        patientListCopy.add(patient);
	    }
	    sort(patientListCopy, cmp);
	    return patientListCopy;
	}

	/**
	 * Performs a SELECTION SORT on the input ArrayList.
	 * 
	 * 1. Finds the smallest item in the list. 
	 * 2. Swaps the smallest item with the first item in the list. 
	 * 3. Reconsiders the list to be the remaining unsorted portion (second item to Nth item) and 
	 *    repeats steps 1, 2, and 3.
	 * 
	 * @param list - to sort
	 * @param cmp  - Comparator to use
	 */
	private static <ListType> void sort(ArrayList<ListType> list, Comparator<ListType> cmp) {
	    for(int i = 0; i < list.size() - 1; i++) {
	        int j, minIndex;
	        for(j = i + 1, minIndex = i; j < list.size(); j++) {
	            if(cmp.compare(list.get(j), list.get(minIndex)) < 0) {
	                minIndex = j;
	            }
	        }
	        ListType temp = list.get(i);
	        list.set(i, list.get(minIndex));
	        list.set(minIndex, temp);
	    }
	}
	
}
