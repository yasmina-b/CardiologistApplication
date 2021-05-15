package service;

import exceptions.*;
import model.Doctor;
import service.DoctorService;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DoctorServiceTest {
    private DoctorService doctorService = new DoctorService();

    @Test
    public void testLoadDoctorsFromFile() throws Exception {
        DoctorService.loadDoctorsFromFile();
        assertNotNull(DoctorService.doctors);
        assertEquals(0, DoctorService.doctors.size());
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
        doctorService.addDoctor("doctor_Popescu_Andreea", "password", "Popescu Andreea","Timisoara","250","8:00-17:00");

        doctorService.checkDoctor("doctor_Popescu_Andreea", "password");
    }
    @Test
    public void testPasswordEncoding() {
        assertNotEquals("password", DoctorService.encodePassword("doctor_Popescu_Andreea", "password"));
    }

}


