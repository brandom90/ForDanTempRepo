package assign02;

import java.util.Comparator;

/**
 * Comparator that defines an ordering among current patients using their Name.
 * More specifically, the comparison is based on their firstName and if that is
 * equal then lastName and If that is equals compare UHealthIDs UHealthIDs are
 * guaranteed to be unique, making a tie-breaker unnecessary.
 * 
 * @author Brandon Miles and Gia Giang
 * @version 01/17/2026
 */
public class OrderByName<Type> implements Comparator<CurrentPatientGeneric<Type>> {

	/**
	 * Returns a negative value if firstPatient (left-hand side)'s firsName is less
	 * than secondPatient's firsName (right-hand side). Returns a positive value if
	 * firstPatient's firsName is greater than secondPatient's firsName. If
	 * firstName are equal, compare by their lastName. If lastName are equal,
	 * compare by their UHealthID.
	 * 
	 * @param firstPatient - first patient
	 * @param secondPatient - second patient
	 * @return an int as described herein
	 */
	public int compare(CurrentPatientGeneric<Type> firstPatient, CurrentPatientGeneric<Type> secondPatient) {
		if (firstPatient.getFirstName().equals(secondPatient.getFirstName())) {
			if (firstPatient.getLastName().equals(secondPatient.getLastName())) {
				return firstPatient.getUHealthID().toString().compareTo(secondPatient.getUHealthID().toString());
			}
			return firstPatient.getLastName().compareTo(secondPatient.getLastName());
		}
		return firstPatient.getFirstName().compareTo(secondPatient.getFirstName());
	}
}
