package service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.*;
import model.Doctor;
import model.Patient;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DoctorService {
    private static List<Doctor> doctors = new ArrayList<Doctor>();

    public static void loadDoctorsFromFile() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = new FileInputStream(new File("D:/SEF_project/MyProject/src/main/resources/datastorage/patient.json"));
            com.fasterxml.jackson.core.type.TypeReference<List<Doctor>> typeReference = new TypeReference<List<Doctor>>() {
            };
            doctors = mapper.readValue(inputStream, typeReference);
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addDoctor(String username, String password) throws UsernameAlreadyExistsException, EmptyPasswordException, EmptyUsernameException {
        loadDoctorsFromFile();
        checkDoctorDoesNotExist(username);
        checkUsernameIsNotEmpty(username);
        checkPasswordIsNotEmpty(password);
        Doctor newDoctor = new Doctor(username, encodePassword(username, password));
        doctors.add(newDoctor);
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file=new File("D:/Project_SEF/Maven/src/main/resources/datastorage/doctor.json");
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, doctors);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void checkDoctor(String username, String password) throws EmptyPasswordException, EmptyUsernameException, WrongPasswordException, WrongUsernameException {
        loadDoctorsFromFile();
        checkUsernameIsNotEmpty(username);
        checkPasswordIsNotEmpty(password);
        checkAccount(username, password);
    }

    private static void checkAccount(String username, String password) throws WrongPasswordException, WrongUsernameException {
        int ok = 0;
        for (Doctor doctor : doctors) {
            if(Objects.equals(username, doctor.getUsername()))
            {
                ok=1;

                if(Objects.equals(encodePassword(username, password), doctor.getPassword()))
                    ok=2;
            }
        }
        if(ok==0)
            throw new WrongUsernameException();
        if(ok==1)
            throw new WrongPasswordException();

    }

    private static void checkDoctorDoesNotExist(String username) throws UsernameAlreadyExistsException, NullPointerException{

        for (Doctor doctor : doctors) {
            if (Objects.equals(username, doctor.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    static void checkUsernameIsNotEmpty(String username)throws EmptyUsernameException {

        if(Objects.equals(username, ""))
            throw new EmptyUsernameException(username);
    }

    private static void checkPasswordIsNotEmpty(String password)throws EmptyPasswordException {

        if(Objects.equals(password,""))
            throw new EmptyPasswordException(password);
    }


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
