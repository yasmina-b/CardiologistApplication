package model;

public class Doctor {
    private String username;
    private String password;
    private String name;
    private String city;
    private String price;
    private String workingHours;

    public Doctor() {}

    public Doctor(String username, String password,String name,String city, String price, String workingHours) {
        this.username=username;
        this.password=password;
        this.name=name;
        this.city=city;
        this.price=price;
        this.workingHours=workingHours;
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

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    public String getCity() { return this.city; }

    public void setCity(String city) { this.city = city; }

    public String getPrice() { return this.price; }

    public String getWorkingHours() { return this.workingHours; }

    public void setWorkingHours(String workingHours) { this.workingHours = workingHours; }

    public void setPrice(String price) { this.price = price; }


    @Override
    public boolean equals(Object o) {
        if(this==o) return true;
        if(o==null || getClass()!=o.getClass()) return false;

        Doctor doctor=(Doctor) o;

        if(!username.equals(doctor.username)) return false;
        if(!password.equals(doctor.password)) return false;

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
        return "Doctor -> " + username + password +name + city+ price+ workingHours;
    }
}
