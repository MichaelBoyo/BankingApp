package FinApp;

import java.time.LocalDate;
import java.time.Period;

public class Customer {
    public Customer(String firstName, String lastName, String email,String phoneNumber, int yearOfBirth, int monthOfBirth, int dayOFBirth, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.year = yearOfBirth;
        this.month = monthOfBirth;
        this.day = dayOFBirth;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        setAge();

    }


    public String getName() {
        return firstName + " " + lastName;
    }

    public int getAge() {
        return age;
    }

    private void setAge() {
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(year, month, day);
        Period getAge = Period.between(birthday, today);
        this.age = getAge.getYears();
    }

    public String getDateOfBirth() {
        return year + " " + month + " " + day;
    }

    @Override
    public String toString() {
        return """
                Account name: %s
                email Address: %s
                phone: %s
                gender: %s
                """.formatted(getName(),getEmail(),getPhoneNumber(),getGender());
    }

    public String getGender() {
        return String.valueOf(gender);
    }
    private final int year;
    private final int month;
    private final int day;
    private final String firstName;
    private final String lastName;
    private final Gender gender;
    private final String email;
    private int age;
    private String phoneNumber;

    public String getEmail() {
        return email;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
}
