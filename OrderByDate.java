package assign02;

import java.util.Comparator;

/**
 * Comparator that defines an ordering among current patients using their last visit.
 * If the dates are equal, the comparison is based on the string representation of the ID
 * UHealthIDs are guaranteed to be unique, making a tie-breaker unnecessary.\
 * 
 * @author Brandon Miles & Gia Giang
 * @version 1/17/2026
 */
public class OrderByDate<Type> implements Comparator<CurrentPatientGeneric<Type>> {
	/**
	 * Returns a negative value if firstPatient (left-hand side)'s date is less than secondPatient's date (right-hand side). 
	 * Returns a positive value if firstPatient's date is greater than secondPatient's date.
	 * If dates are equal, compare by their uHealthIDs
	 * 
	 * @param firstPatient - first patient
	 * @param secondPatient - second patient
	 * @return an int as described herein
	 */
	public int compare(CurrentPatientGeneric<Type> firstPatient, CurrentPatientGeneric<Type> secondPatient) {
		if (firstPatient.getLastVisit().compareTo(secondPatient.getLastVisit()) == 0) {
			return firstPatient.getUHealthID().toString().compareTo(secondPatient.getUHealthID().toString());
		}
		return firstPatient.getLastVisit().compareTo(secondPatient.getLastVisit());
	}
}
