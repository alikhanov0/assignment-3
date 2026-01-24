package models;

import java.sql.Date;

public class Student {
    private int studentId;
    private String firstName;
    private String lastName;
    private char gender;
    private Date enrollmentDate;
    private String email;
    private String phone;

    public Student(int studentId, String firstName, String lastName,
            char gender, Date enrollmentDate,
            String email, String phone) {

        this.studentId = studentId;
        setFirstName(firstName);
        setLastName(lastName);
        setGender(gender);
        this.enrollmentDate = enrollmentDate;
        setEmail(email);
        this.phone = phone;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }
}
