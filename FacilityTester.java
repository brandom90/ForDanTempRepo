package assign02;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains tests for Facility.
 * 
 * @author CS 2420 course staff and Gia Giang @version1/17/2026
 */
public class FacilityTester {

	private Facility emptyFacility, verySmallFacility, smallFacility;
	private UHealthID uHID1, uHID2, uHID3;
	private GregorianCalendar date1, date2, date3;

	@BeforeEach
	void setUp() throws Exception {

		uHID1 = new UHealthID("AAAA-1111");
		uHID2 = new UHealthID("BCBC-2323");
		uHID3 = new UHealthID("HRHR-7654");

		date1 = new GregorianCalendar(2023, 0, 1);
		date2 = new GregorianCalendar(2023, 3, 17);
		date3 = new GregorianCalendar(2022, 8, 21);

		emptyFacility = new Facility();

		verySmallFacility = new Facility();
		verySmallFacility.addPatient(new CurrentPatient("Jane", "Doe", uHID1, 1010101, date1));
		verySmallFacility.addPatient(new CurrentPatient("Drew", "Hall", uHID2, 3232323, date2));
		verySmallFacility.addPatient(new CurrentPatient("Riley", "Nguyen", uHID3, 9879876, date3));

		smallFacility = new Facility();
		smallFacility.addAll(readFromFile("src/assign02/small_patient_list.txt"));
	}

	// Empty Facility tests --------------------------------------------------------

	@Test
	public void testEmptyLookupUHID() {
		assertNull(emptyFacility.lookUpByUHID(uHID1));
	}

	@Test
	public void testEmptyLookupPhysician() {
		ArrayList<CurrentPatient> patients = emptyFacility.lookUpByPhysician(1010101);
		assertEquals(0, patients.size());
	}

	@Test
	public void testEmptySetVisit() {
		// ensure no exceptions thrown
		emptyFacility.setLastVisit(uHID2, date3);
	}

	@Test
	public void testEmptySetPhysician() {
		// ensure no exceptions thrown
		emptyFacility.setPhysician(uHID2, 1010101);
	}

	@Test
	public void testEmptyGetRecentPatients() {
		ArrayList<CurrentPatient> patients = emptyFacility.getRecentPatients(date3);
		assertEquals(0, patients.size());
	}

	@Test
	public void testEmptyAddPatient() {
		Facility tempFacility = new Facility();
		CurrentPatient tempPatient = new CurrentPatient("Brandon", "Miles", uHID1, 1010101, date1);
		assertEquals(true, tempFacility.addPatient(tempPatient));
	}

	@Test
	public void testEmptyAddDupePatient() {
		Facility tempFacility = new Facility();
		CurrentPatient tempPatient = new CurrentPatient("Brandon", "Miles", uHID1, 1010101, date1);
		tempFacility.addPatient(tempPatient);
		assertEquals(false, tempFacility.addPatient(new CurrentPatient("Gia", "Giang", uHID1, 34234, date3)));
	}

	@Test
	public void testEmptyAddAlltPatient() {
		ArrayList<CurrentPatient> patientList = new ArrayList<>();

		Facility tempFacility = new Facility();
		CurrentPatient tempPatient = new CurrentPatient("Brandon", "Miles", uHID1, 1010102, date1);
		tempFacility.addPatient(tempPatient);
		CurrentPatient tempPatient1 = new CurrentPatient("Brandons", "Mil", uHID2, 1010101, date2);
		tempFacility.addPatient(tempPatient1);
		CurrentPatient tempPatient2 = new CurrentPatient("Brandonz", "Mile", uHID3, 1010101, date3);
		tempFacility.addPatient(tempPatient2);

		tempFacility.addAll(patientList);

		assertEquals(tempPatient, tempFacility.lookUpByUHID(uHID1));
		assertEquals(tempPatient1, tempFacility.lookUpByUHID(uHID2));
		assertEquals(tempPatient2, tempFacility.lookUpByUHID(uHID3));
	}

	@Test
	public void testEmptylookUpByPhysician() {
		ArrayList<CurrentPatient> patientList = new ArrayList<>();

		Facility tempFacility = new Facility();

		CurrentPatient tempPatient1 = new CurrentPatient("Brandons", "Mil", uHID2, 1010101, date2);
		tempFacility.addPatient(tempPatient1);
		CurrentPatient tempPatient2 = new CurrentPatient("Brandonz", "Mile", uHID3, 1010101, date3);
		tempFacility.addPatient(tempPatient2);

		tempFacility.addAll(patientList);

		ArrayList<CurrentPatient> assignedPatientList = new ArrayList<>();
		assignedPatientList.add(tempPatient1);
		assignedPatientList.add(tempPatient2);

		assertEquals(assignedPatientList, tempFacility.lookUpByPhysician(1010101));
	}

	@Test
	public void testEmptyGetRecentPatient() {
		ArrayList<CurrentPatient> patientList = new ArrayList<>();

		Facility tempFacility = new Facility();

		CurrentPatient tempPatient1 = new CurrentPatient("Brandons", "Mil", uHID2, 1010101, date2);
		tempFacility.addPatient(tempPatient1);
		CurrentPatient tempPatient2 = new CurrentPatient("Brandonz", "Mile", uHID3, 1010101, date3);
		tempFacility.addPatient(tempPatient2);
		CurrentPatient tempPatient3 = new CurrentPatient("Brando", "Milez", uHID1, 1010101, date1);
		tempFacility.addPatient(tempPatient3);

		tempFacility.addAll(patientList);

		ArrayList<CurrentPatient> recentPatientList = new ArrayList<>();
		recentPatientList.add(tempPatient1);
		recentPatientList.add(tempPatient2);
		recentPatientList.add(tempPatient3);

		assertEquals(recentPatientList, tempFacility.getRecentPatients(new GregorianCalendar(1900, 3, 16)));
	}

	@Test
	public void testEmptyGetPhysicianList() {
		Facility tempFacility = new Facility();

		CurrentPatient tempPatient1 = new CurrentPatient("Brandons", "Mil", uHID2, 1010101, date2);
		tempFacility.addPatient(tempPatient1);
		CurrentPatient tempPatient2 = new CurrentPatient("Brandonz", "Mile", uHID3, 1010102, date3);
		tempFacility.addPatient(tempPatient2);
		CurrentPatient tempPatient3 = new CurrentPatient("Brando", "Milez", uHID1, 1010101, date1);
		tempFacility.addPatient(tempPatient3);

		ArrayList<Integer> physicianList = new ArrayList<>();

		physicianList.add(1010101);
		physicianList.add(1010102);
		assertEquals(physicianList, tempFacility.getPhysicianList());
	}

	@Test
	public void testEmptyGetEmptyFacilityList() {
		ArrayList<Integer> physicianList = new ArrayList<>();
		assertEquals(physicianList, emptyFacility.getPhysicianList());

	}

	@Test
	public void testEmptySetPhysicians() {
		Facility tempFacility = new Facility();

		CurrentPatient tempPatient1 = new CurrentPatient("Brandons", "Mil", uHID2, 1010101, date2);
		tempFacility.addPatient(tempPatient1);
		CurrentPatient tempPatient2 = new CurrentPatient("Brandonz", "Mile", uHID3, 1010102, date3);
		tempFacility.addPatient(tempPatient2);

		ArrayList<CurrentPatient> patientList = new ArrayList<>();
		tempFacility.setPhysician(uHID2, 1010103);
		patientList.add(tempPatient1);
		assertEquals(patientList, tempFacility.lookUpByPhysician(1010103));
	}

	@Test
	public void testEmptySetLastVisit() {
		Facility tempFacility = new Facility();

		CurrentPatient tempPatient1 = new CurrentPatient("Brandons", "Mil", uHID2, 1010101, date2);
		tempFacility.addPatient(tempPatient1);
		CurrentPatient tempPatient2 = new CurrentPatient("Brandonz", "Mile", uHID3, 1010102, date3);
		tempFacility.addPatient(tempPatient2);

		ArrayList<CurrentPatient> patientList = new ArrayList<>();
		tempFacility.setLastVisit(uHID2, new GregorianCalendar(2100, 12, 31));
		patientList.add(tempPatient1);
		assertEquals(patientList, tempFacility.getRecentPatients(new GregorianCalendar(2099, 12, 31)));
	}

	@Test
	public void testEmptyGetPatient() {
		assertEquals(null, emptyFacility.lookUpByUHID(uHID3));
	}

	@Test
	public void testEmptyLookUpByPhysician() {
		ArrayList<CurrentPatient> tempList = new ArrayList<>();
		assertEquals(tempList, emptyFacility.lookUpByPhysician(101091));
	}

	@Test
	public void testEmptyTestEqualDate() {
		emptyFacility.addPatient(new CurrentPatient("Brandons", "Mil", uHID2, 1010101, date2));
		ArrayList<CurrentPatient> tempList = new ArrayList<>();
		assertEquals(tempList, emptyFacility.getRecentPatients(date2));
	}
	// Very small facility tests ---------------------------------------------------

	@Test
	public void testVerySmallLookupUHID() {
		Patient expected = new Patient("Drew", "Hall", new UHealthID("BCBC-2323"));
		CurrentPatient actual = verySmallFacility.lookUpByUHID(new UHealthID("BCBC-2323"));
		assertEquals(expected, actual);
	}

	@Test
	public void testAddDuplicatePatient() {
		CurrentPatient patient1 = new CurrentPatient("Brandons", "Miles", uHID2, 1010101, date2);
		CurrentPatient patient2 = new CurrentPatient("Gia", "Giang", uHID2, 1010101, date2);
		verySmallFacility.addPatient(patient1);
		verySmallFacility.addPatient(patient2);

		assertEquals(patient1, verySmallFacility.lookUpByUHID(uHID2));
	}

	@Test
	public void testAddAllNonEmptyList() {
		ArrayList<CurrentPatient> patientList = new ArrayList<>();
		CurrentPatient patient1 = new CurrentPatient("Brandons", "Miles", uHID1, 1010101, date2);
		CurrentPatient patient2 = new CurrentPatient("Gia", "Giang", uHID2, 1010101, date2);
		verySmallFacility.addPatient(patient1);
		verySmallFacility.addPatient(patient2);
		patientList.add(patient1);
		patientList.add(patient2);

		verySmallFacility.addAll(patientList);
		assertEquals(patient1, verySmallFacility.lookUpByUHID(uHID1));
		assertEquals(patient2, verySmallFacility.lookUpByUHID(uHID2));
	}

	@Test
	public void testVerySmallLookupPhysicianCount() {
		ArrayList<CurrentPatient> actualPatients = verySmallFacility.lookUpByPhysician(9879876);
		assertEquals(1, actualPatients.size());
	}

	@Test
	public void testVerySmallLookupPhysicianPatient() {
		Patient expectedPatient = new Patient("Riley", "Nguyen", new UHealthID("HRHR-7654"));
		ArrayList<CurrentPatient> actualPatients = verySmallFacility.lookUpByPhysician(9879876);
		assertEquals(expectedPatient, actualPatients.get(0));
	}

	@Test
	public void testVerySmallAddNewPatient() {
		assertTrue(verySmallFacility
				.addPatient(new CurrentPatient("Jane", "Doe", new UHealthID("BBBB-2222"), 1010101, date1)));
	}

	@Test
	public void testSetPhysicianNonexistentPatient() {
		Facility tempFacility = new Facility();
		tempFacility.addPatient(new CurrentPatient("Brandon", "Miles", uHID1, 101, date1));

		tempFacility.setPhysician(new UHealthID("ZZZZ-9999"), 999);

		assertEquals(101, tempFacility.lookUpByUHID(uHID1).getPhysician());
	}

	@Test
	public void testVerySmallUpdatePhysician() {
		verySmallFacility.lookUpByUHID(uHID1).updatePhysician(9090909);
		CurrentPatient patient = verySmallFacility.lookUpByUHID(uHID1);
		assertEquals(9090909, patient.getPhysician());
	}

	// Small facility tests
	// -------------------------------------------------------------------------

	@Test
	public void testSmallGetRecentPatients() {
		ArrayList<CurrentPatient> actual = smallFacility.getRecentPatients(new GregorianCalendar(2020, 0, 0));
		assertEquals(2, actual.size());
	}

	@Test
	public void testSmallAddPatients() {
		int count = 3;
		UHealthID[] randID = generateUHIDs(count);
		String[] randFirstName = generateNames(count);
		String[] randSecondName = generateNames(count);
		GregorianCalendar[] randDate = generateDates(count);

		for (int i = 0; i < randID.length; i++) {
			assertEquals(true, smallFacility
					.addPatient(new CurrentPatient(randFirstName[i], randSecondName[i], randID[i], 192, randDate[i])));
		}
	}

	@Test
	public void testSmallAddAllPatients() {
		int count = 10;
		UHealthID[] randID = generateUHIDs(count);
		String[] randFirstName = generateNames(count);
		String[] randSecondName = generateNames(count);
		GregorianCalendar[] randDate = generateDates(count);
		ArrayList<CurrentPatient> patientList = new ArrayList<>();
		for (int i = 0; i < randID.length; i++) {
			patientList.add(new CurrentPatient(randFirstName[i], randSecondName[i], randID[i], 192, randDate[i]));
		}
		assertEquals(count, patientList.size());
	}

	// Helper methods ------------------------------------------------------------

	/**
	 * A private helper to generate unique UHealthIDs. Valid for up to 260,000 IDs.
	 * 
	 * @param howMany - IDs to make
	 * @return an array of UHealthIDs
	 */
	private UHealthID[] generateUHIDs(int howMany) {
		UHealthID[] ids = new UHealthID[howMany];
		for (int i = 0; i < howMany; i++) {
			String prefix = "JKL" + (char) ('A' + (i / 10000) % 26);
			ids[i] = new UHealthID(prefix + "-" + String.format("%04d", i % 10000));
		}
		return ids;
	}

	/**
	 * A private helper to generate dates.
	 * 
	 * @param howMany - dates to generate
	 * @return an array of dates
	 */
	private GregorianCalendar[] generateDates(int howMany) {
		GregorianCalendar[] dates = new GregorianCalendar[howMany];
		for (int i = 0; i < howMany; i++)
			dates[i] = new GregorianCalendar(2000 + i % 22, i % 12, i % 28);
		return dates;
	}

	/**
	 * A private helper to generate names.
	 * 
	 * @param howMany - names to generate
	 * @return an array of names
	 */
	private String[] generateNames(int howMany) {
		String[] names = new String[howMany];
		Random rng = new Random();
		for (int i = 0; i < howMany; i++)
			names[i] = "" + (char) ('A' + rng.nextInt(26)) + (char) ('a' + rng.nextInt(26))
					+ (char) ('a' + rng.nextInt(26)) + (char) ('a' + rng.nextInt(26));
		return names;
	}

	/**
	 * Adds the patients specified by the input file to a list.
	 * 
	 * Assumes a strict file format: (each line): FirstName LastName ABCD-0123
	 * u0123456 2023 05 16
	 * 
	 * Also assumes there are no duplicate patients in the file.
	 * 
	 * @param filename - full or relative path to file containing patient data
	 */
	public ArrayList<CurrentPatient> readFromFile(String filename) {
		ArrayList<CurrentPatient> patients = new ArrayList<CurrentPatient>();
		try {
			Scanner fileIn = new Scanner(new File(filename));
			int lineNumber = 0;

			while (fileIn.hasNextLine()) {
				String line = fileIn.nextLine();
				lineNumber++;
				patients.add(parsePatient(line, lineNumber));
			}
			fileIn.close();
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage() + "  Patient file couldn't be opened.");
		} catch (ParseException e) {
			System.err.println(e.getLocalizedMessage() + " formatted incorrectly at line " + e.getErrorOffset()
					+ ". Not all patients added to list.");
		}
		return patients;
	}

	/**
	 * Helper method for parsing the information about a patient from file.
	 * 
	 * @param line       - string to be parsed
	 * @param lineNumber - line number in file, used for error reporting (see above)
	 * @return the Patient constructed from the information
	 * @throws ParseException if file containing line is not properly formatted (see
	 *                        above)
	 */
	private CurrentPatient parsePatient(String line, int lineNumber) throws ParseException {
		Scanner lineIn = new Scanner(line);
		lineIn.useDelimiter(" ");

		if (!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("First name", lineNumber);
		}
		String firstName = lineIn.next();

		if (!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("Last name", lineNumber);
		}
		String lastName = lineIn.next();

		if (!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("UHealth ID", lineNumber);
		}
		String patientIDString = lineIn.next();

		if (!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("physician", lineNumber);
		}
		String physicianString = lineIn.next();
		int physician = Integer.parseInt(physicianString.substring(1, 8));

		if (!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("year of last visit", lineNumber);
		}
		String yearString = lineIn.next();
		int year = Integer.parseInt(yearString);

		if (!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("month of last visit", lineNumber);
		}
		String monthString = lineIn.next();
		int month = Integer.parseInt(monthString);

		if (!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("day of last visit", lineNumber);
		}
		String dayString = lineIn.next();
		int day = Integer.parseInt(dayString);

		GregorianCalendar lastVisit = new GregorianCalendar(year, month, day);

		lineIn.close();

		return new CurrentPatient(firstName, lastName, new UHealthID(patientIDString), physician, lastVisit);
	}
}
