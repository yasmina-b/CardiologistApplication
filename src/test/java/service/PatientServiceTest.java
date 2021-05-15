package service;

import exceptions.*;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PatientServiceTest {

    private PatientService patientService = new PatientService();

    @Test
    public void testLoadPatientsFromFile() throws Exception {
        PatientService.loadPatientsFromFile();
        Assert.assertNotNull(PatientService.patients);
        Assert.assertEquals(1, PatientService.patients.size());
    }

    @Test(expected = EmptyUsernameException.class)
    public void testCheckUserNameIsNotEmpty_empty() throws EmptyUsernameException {
        DoctorService.checkUsernameIsNotEmpty("");
    }

    @Test()
    public void testCheckUserNameIsNotEmpty_nonEmpty() throws EmptyUsernameException {
        DoctorService.checkUsernameIsNotEmpty("MOCK_USERNAME");
    }

    @Test
    public void testCheckAccount_exists() throws EmptyUsernameException, EmptyPasswordException, UsernameAlreadyExistsException, WrongUsernameException, WrongPasswordException, EmptyCityException, EmptyNameException, EmptyPriceException, EmptyWorkingHoursException {
        patientService.addPatient("Yasmina Bocereg", "password");

        patientService.checkPatient("Yasmina Bocereg", "password");
    }
    @Test
    public void testPasswordEncoding() {
        assertNotEquals("password", PatientService.encodePassword("Yasmina Bocereg ", "password"));
    }

}