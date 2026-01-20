package assign02;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains tests for FacilityGeneric.
 * 
 * @author CS 2420 course staff and Gia Giang
 * @version 1/17/2026
 */
public class FacilityGenericTester {
	
	private FacilityGeneric<Integer> uIDFacility, emptyFacility, phase3Facility;
	private FacilityGeneric<UHealthID> uHIDFacility;
	private FacilityGeneric<String> nameFacility;
	
	private UHealthID[] uHIDs;
	private GregorianCalendar[] dates;
	private String[] firstNames, lastNames, physicianNames;
	// For phase 3
	private UHealthID p3id1, p3id2, p3id3, p3id4;
	private GregorianCalendar p3date1, p3date2, p3date3, p3date4;
	
	@BeforeEach
	void setUp() throws Exception {
		// Modifying these numbers will affect the provided tests
		int nPatients = 20;
		int nPhysicians = 8;
		
		uHIDs = generateUHIDs(nPatients + nPhysicians);
		dates = generateDates(nPatients);
		firstNames = generateNames(nPatients);
		lastNames = generateNames(nPatients);
		physicianNames = generateNames(nPhysicians);
		
		uIDFacility = new FacilityGeneric<Integer>();
		uHIDFacility = new FacilityGeneric<UHealthID>();
		nameFacility = new FacilityGeneric<String>();
		emptyFacility = new FacilityGeneric<Integer>();
		phase3Facility = new FacilityGeneric<Integer>();
		
		for (int i = 0; i < nPatients; i++) {
			uIDFacility.addPatient(new CurrentPatientGeneric<Integer>(
										firstNames[i], lastNames[i], 
										uHIDs[i], 1234567 + i % nPhysicians, dates[i]));
			uHIDFacility.addPatient(new CurrentPatientGeneric<UHealthID>(
										firstNames[i], lastNames[i], 
										uHIDs[i], uHIDs[nPatients + i % nPhysicians], dates[i]));
			nameFacility.addPatient(new CurrentPatientGeneric<String>(
										firstNames[i], lastNames[i], 
										uHIDs[i], physicianNames[i % nPhysicians], dates[i]));
		}
		
		p3id1 = new UHealthID("XXXX-1111");
 		p3id2 = new UHealthID("BBBB-1111");
 		p3id3 = new UHealthID("FFFF-1111");
 		p3id4 = new UHealthID("BBBB-2222");
 		p3date1 = new GregorianCalendar(2019, 1, 5);
 		p3date2 = new GregorianCalendar(2019, 1, 4);
 		p3date3 = new GregorianCalendar(2019, 1, 3);
 		p3date4 = new GregorianCalendar(2019, 1, 2);
 		
		phase3Facility.addPatient(new CurrentPatientGeneric<Integer>("A", "B", new UHealthID("XXXX-1111"), 7, new GregorianCalendar(2019, 1, 5)));
		phase3Facility.addPatient(new CurrentPatientGeneric<Integer>("A", "B", new UHealthID("BBBB-1111"), 7, new GregorianCalendar(2019, 1, 4)));
		phase3Facility.addPatient(new CurrentPatientGeneric<Integer>("A", "C", new UHealthID("FFFF-1111"), 7, new GregorianCalendar(2019, 1, 3)));
		phase3Facility.addPatient(new CurrentPatientGeneric<Integer>("R", "T", new UHealthID("BBBB-2222"), 7, new GregorianCalendar(2019, 1, 2)));
		
		// Extend this tester to add more tests for the facilities above, 
		// as well as to create and test other facilities.
		// (HINT: For a larger facility, use the helpers at the end of this file to
		//        generate names, IDs, and dates.)
	}
	
	// empty Facility tests --------------------------------------------------------
	
	@Test
	public void testEmptyLookupUHID() {
		assertNull(emptyFacility.lookUpByUHID(uHIDs[0]));
	}
	
	@Test
	public void testEmptyLookupPhysician() {
		ArrayList<CurrentPatientGeneric<Integer>> patients = emptyFacility.lookupByPhysician(1010101);
		assertEquals(0, patients.size());
	}
	
	@Test
	public void testEmptySetVisit() {
		// ensure no exceptions thrown
		emptyFacility.setLastVisit(uHIDs[0], dates[3]);
	}

	@Test
	public void testEmptySetPhysician() {
		// ensure no exceptions thrown
		emptyFacility.setPhysician(uHIDs[0], 1010101);
	}
	
	@Test
	public void testEmptyGetRecentPatients() {
		ArrayList<CurrentPatientGeneric<Integer>> patients = emptyFacility.getRecentPatients(dates[4]);
		assertEquals(0, patients.size());
	}
	
	
	@Test
	public void testEmptyAddAll() {
		CurrentPatientGeneric<Integer> tempPatient = new CurrentPatientGeneric<>("Zia", "Zhang", uHIDs[1], 200101, dates[1]);
		CurrentPatientGeneric<Integer> tempPatient2 = new CurrentPatientGeneric<>("Brandon", "Miles", uHIDs[0], 1010101, dates[0]);
		ArrayList<CurrentPatientGeneric<Integer>> arrayListOfPatients = new ArrayList<CurrentPatientGeneric<Integer>>();
		arrayListOfPatients.add(tempPatient);
		arrayListOfPatients.add(tempPatient2);
		emptyFacility.addAll(arrayListOfPatients);
		assertEquals(tempPatient, emptyFacility.lookUpByUHID(uHIDs[1]));
	}
	
	@Test
	public void testEmptyGetPhysicianList() {
		assertEquals(new ArrayList<>(), emptyFacility.getPhysicianList());
	}
	
	@Test
	public void testEmptyAddPatient() {
		CurrentPatientGeneric<Integer> tempPatient = new CurrentPatientGeneric<>("Brandon", "Miles", uHIDs[0], 1010101, dates[0]);
		emptyFacility.addPatient(tempPatient);
		assertEquals(tempPatient, emptyFacility.lookUpByUHID(uHIDs[0]));
	}
	
	// uID Facility tests --------------------------------------------------------
	
	@Test
	public void testUIDLookupPhysicianCount() {
		ArrayList<CurrentPatientGeneric<Integer>> actualPatients = uIDFacility.lookupByPhysician(1234568);
		assertEquals(3, actualPatients.size());
	}
	
	@Test
	public void testUIDLookupPhysicianPatient() {
		Patient expectedPatient = new Patient(firstNames[1], lastNames[1], new UHealthID(uHIDs[1].toString()));
		ArrayList<CurrentPatientGeneric<Integer>> actualPatients = uIDFacility.lookupByPhysician(1234568);
		assertEquals(expectedPatient, actualPatients.get(0));
	}
	
	@Test
	public void testUIDAddPatient() {
		CurrentPatientGeneric<Integer> tempPatient = new CurrentPatientGeneric<>("Brandon", "Miles", uHIDs[0], 1010101, dates[0]);
		uIDFacility.addPatient(tempPatient);
		assertEquals(tempPatient, uIDFacility.lookUpByUHID(uHIDs[0]));
	}
	
	@Test
	public void testUIDGetRecentPatients() {
	    assertEquals(20, uIDFacility.getRecentPatients(new GregorianCalendar(1970, 10, 2)).size());
	}
	
	@Test
	public void testUIDGetPhysicianList() {
		assertEquals(8, uIDFacility.getPhysicianList().size());
	}
	
	@Test
	public void testUIDSetPhysician() {
		CurrentPatientGeneric<Integer> patient = uIDFacility.lookUpByUHID(uHIDs[0]);
		uIDFacility.setPhysician(uHIDs[0], 909090909);
		assertEquals(909090909, patient.getPhysician() );
	}
	
	@Test
	public void testUIDSetPhysicianOneNum() {
		CurrentPatientGeneric<Integer> patient = uIDFacility.lookUpByUHID(uHIDs[0]);
		uIDFacility.setPhysician(uHIDs[0], 1);
		assertEquals(1, patient.getPhysician() );
	}
	
	// UHealthID facility tests ---------------------------------------------------

	@Test
	public void testUHIDLookupPhysicianCount() {
		ArrayList<CurrentPatientGeneric<UHealthID>> actualPatients = uHIDFacility.lookupByPhysician(uHIDs[uHIDs.length - 1]);
		assertEquals(2, actualPatients.size());
	}
	@Test
	public void testUHIDUpdatePhysician() {
		uHIDFacility.lookUpByUHID(uHIDs[2]).updatePhysician(uHIDs[uHIDs.length - 1]);
		CurrentPatientGeneric<UHealthID> patient = uHIDFacility.lookUpByUHID(uHIDs[2]);
		assertEquals(uHIDs[uHIDs.length - 1], patient.getPhysician());
	}
	
	@Test
	public void testUHIDSetPhysician() {
		CurrentPatientGeneric<UHealthID> patient = uHIDFacility.lookUpByUHID(uHIDs[0]);
		uHIDFacility.setPhysician(uHIDs[0], uHIDs[2]);
		assertEquals(uHIDs[2], patient.getPhysician() );
	}
	
	@Test
	public void testUHIDAddPatient() {
		CurrentPatientGeneric<UHealthID> tempPatient = new CurrentPatientGeneric<>("Brandon", "Miles", uHIDs[0], uHIDs[1], dates[0]);
		uHIDFacility.addPatient(tempPatient);
		assertEquals(tempPatient, uHIDFacility.lookUpByUHID(uHIDs[0]));
	}
	
	@Test
	public void testUHIDAddPatientDuplicate() {
	    CurrentPatientGeneric<UHealthID> dup = new CurrentPatientGeneric<>( "Brandon", "Miles", uHIDs[0], uHIDs[2], dates[0]);
	    assertEquals(false, uHIDFacility.addPatient(dup));
	}
	
	@Test
	public void testUHIDSetLastVisit() {
	    CurrentPatientGeneric<UHealthID> patient = uHIDFacility.lookUpByUHID(uHIDs[2]);
	    uHIDFacility.setLastVisit(uHIDs[2], new GregorianCalendar(2020, 0, 1));
	    assertEquals(0, patient.getLastVisit().compareTo( new GregorianCalendar(2020, 0, 1)));
	}
	
	// name facility tests -------------------------------------------------------------------------

	@Test
	public void testNameLookupPhysician() {
		Patient expectedPatient1 = new Patient(firstNames[1], lastNames[1], new UHealthID(uHIDs[1].toString()));
		Patient expectedPatient2 = new Patient(firstNames[9], lastNames[9], new UHealthID(uHIDs[9].toString()));

		ArrayList<CurrentPatientGeneric<String>> actualPatients = nameFacility.lookupByPhysician(physicianNames[1]);
		assertTrue(actualPatients.contains(expectedPatient1) && actualPatients.contains(expectedPatient2));
	}
	
	@Test
	public void testNameGetPhysicianList() {
		ArrayList<String> actual = nameFacility.getPhysicianList();
		assertEquals(8, actual.size());
	}
	
	@Test
	public void testNameSetPhysician() {
	    CurrentPatientGeneric<String> tempPatient = nameFacility.lookUpByUHID(uHIDs[4]);
	    String newPhys = "Dr Brando";
	    nameFacility.setPhysician(uHIDs[4], newPhys);
	    assertEquals(newPhys, tempPatient.getPhysician());
	}
	
	@Test
	public void testNameAddPatient() {
		CurrentPatientGeneric<String> tempPatient = new CurrentPatientGeneric<>("Brandon", "Miles", uHIDs[0], "ZIA", dates[0]);
		nameFacility.addPatient(tempPatient);
		assertEquals(tempPatient, nameFacility.lookUpByUHID(uHIDs[0]));
	}
	
	@Test
	public void testNameAddDuplicate() {
	    CurrentPatientGeneric<String> existing = nameFacility.lookUpByUHID(uHIDs[5]);
	    CurrentPatientGeneric<String> duplicate = new CurrentPatientGeneric<>(
	        existing.getFirstName(), existing.getLastName(), existing.getUHealthID(), existing.getPhysician(), existing.getLastVisit());
	    assertEquals(false, nameFacility.addPatient(duplicate));
	}
	
	@Test
	public void testNameLookupPhysicianEmpty() {
	    ArrayList<CurrentPatientGeneric<String>> patients = nameFacility.lookupByPhysician("New Doc");
	    assertEquals(0, patients.size());
	}
	
	@Test
	public void testNameAddAll() {
	    CurrentPatientGeneric<String> patient1 = new CurrentPatientGeneric<>("Brandon", "Miles", uHIDs[1], "Dr A", dates[0]);
	    CurrentPatientGeneric<String> patient2 = new CurrentPatientGeneric<>("Zia", "Zhang", uHIDs[2], "Dr B", dates[1]);

	    ArrayList<CurrentPatientGeneric<String>> patients = new ArrayList<>();
	    patients.add(patient1);
	    patients.add(patient2);
	    nameFacility.addAll(patients);

	    assertEquals(patient1, nameFacility.lookUpByUHID(uHIDs[1]));
	    assertEquals(patient2, nameFacility.lookUpByUHID(uHIDs[2]));
	}
	
	@Test
	public void testNameGetRecentPatientsSimple() {
	    GregorianCalendar cutoff = new GregorianCalendar(1970, 1, 1);
	    ArrayList<CurrentPatientGeneric<String>> recent = nameFacility.getRecentPatients(cutoff);
	    assertEquals(20, recent.size());
	}
	
	@Test
	public void testNameSetLastVisit() {
		CurrentPatientGeneric<String> existingPatient = nameFacility.lookUpByUHID(uHIDs[5]);
		nameFacility.setLastVisit(existingPatient.getUHealthID(), new GregorianCalendar(1970,10,2));
		 assertEquals(new GregorianCalendar(1970,10,2), existingPatient.getLastVisit());
	}
	
	// phase 3 tests ---------------------------------------------------------------------------
	
	@Test
	  public void testOrderedByUHIDCount() {
	      ArrayList<CurrentPatientGeneric<Integer>> actual = phase3Facility.getOrderedPatients(new OrderByUHealthID<Integer>());
	      assertEquals(4, actual.size());
	  }

    @Test
    public void testOrderedByUHIDOrder() {
    	ArrayList<CurrentPatientGeneric<Integer>> actual = phase3Facility.getOrderedPatients(new OrderByUHealthID<Integer>());
        assertEquals(new CurrentPatientGeneric<Integer>("A", "B", p3id1, 7, p3date1), actual.get(3));
        assertEquals(new CurrentPatientGeneric<Integer>("A", "B", p3id2, 7, p3date2), actual.get(0));
        assertEquals(new CurrentPatientGeneric<Integer>("A", "C", p3id3, 7, p3date3), actual.get(2));
        assertEquals(new CurrentPatientGeneric<Integer>("R", "T", p3id4, 7, p3date4), actual.get(1));
    }
 	
 	// Private helper methods ---------------------------------------------------------------
    
    /**
	 * A private helper to generate unique UHealthIDs.
	 * Valid for up to 260,000 IDs.
	 * 
	 * @param howMany - IDs to make
	 * @return an array of UHealthIDs
	 */
	private UHealthID[] generateUHIDs(int howMany) {
		UHealthID[] ids = new UHealthID[howMany];
		for (int i = 0; i < howMany; i++) {
			String prefix = "JKL" + (char)('A' + (i / 10000) % 26);
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
			dates[i] = new GregorianCalendar(2000 + i%22, i%12, i%28);
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
			names[i] = "" + (char)('A' + rng.nextInt(26)) + (char)('a' + rng.nextInt(26))
					   + (char)('a' + rng.nextInt(26)) + (char)('a' + rng.nextInt(26));
		return names;
	}
}
