package service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.*;
import model.Patient;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PatientService {
    private static List<Patient> patients=new ArrayList<Patient>();

    public static void loadPatientsFromFile() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = new FileInputStream(new File("D:/Project_SEF/Maven/src/main/resources/datastorage/patient.json"));
            TypeReference<List<Patient>> typeReference = new TypeReference<List<Patient>>() {
            };
            patients = mapper.readValue(inputStream, typeReference);
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addPatient(String username, String password) throws UsernameAlreadyExistsException, EmptyPasswordException, EmptyUsernameException {
        loadPatientsFromFile();
        checkPatientDoesNotExist(username);
        checkUsernameIsNotEmpty(username);
        checkPasswordIsNotEmpty(password);
        Patient newPatient = new Patient(username, encodePassword(username, password));
        patients.add(newPatient);
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file=new File("D:/Project_SEF/Maven/src/main/resources/datastorage/patient.json");
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, patients);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void checkPatient(String username, String password) throws EmptyPasswordException, EmptyUsernameException, WrongPasswordException, WrongUsernameException {
        loadPatientsFromFile();
        checkUsernameIsNotEmpty(username);
        checkPasswordIsNotEmpty(password);
        checkAccount(username, password);
    }
    private static void checkAccount(String username, String password) throws WrongPasswordException, WrongUsernameException {
        int ok = 0;
        for (Patient patient : patients) {
            if(Objects.equals(username, patient.getUsername()))
            {
                ok=1;

                if(Objects.equals(encodePassword(username, password), patient.getPassword()))
                    ok=2;
            }
        }
        if(ok==0)
            throw new WrongUsernameException();
        if(ok==1)
            throw new WrongPasswordException();
    }

    private static void checkPatientDoesNotExist(String username) throws UsernameAlreadyExistsException{

        for (Patient patient : patients) {
            if (Objects.equals(username, patient.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    private static void checkUsernameIsNotEmpty(String username)throws EmptyUsernameException {

        if(Objects.equals(username, ""))
            throw new EmptyUsernameException(username);
    }

    private static void checkPasswordIsNotEmpty(String password)throws EmptyPasswordException {

        if(Objects.equals(password,""))
            throw new EmptyPasswordException(password);
    }

    //private static void persistPatients() { /*WRITE THE LIST BACK IN THE JSON FILE

   // }

    private static String encodePassword(String salt, String password) {

        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {

        MessageDigest md;

        try {
            md = MessageDigest.getInstance("SHA-512");

        } catch (NoSuchAlgorithmException e) {

            throw new IllegalStateException("SHA-512 does not exist!");
        }

        return md;
    }
}
