package model;

public class Patient {
    private String username;
    private String password;

    public Patient() {}

    public Patient(String username, String password) {
        this.username=username;
        this.password=password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if(this==o) return true;
        if(o==null || getClass()!=o.getClass()) return false;

        Patient patient=(Patient) o;

        if(!username.equals(patient.username)) return false;
        if(!password.equals(patient.password)) return false;

        return true;
    }

    @Override
    public int hashCode (){
        int result=username.hashCode();
        result=31*result+password.hashCode();

        return result;
    }

    @Override
    public String toString() {
        return "Patient -> " + username + password;
    }
}
