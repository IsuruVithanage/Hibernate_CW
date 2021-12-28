package entity;

import javax.persistence.*;

@Entity
public class Student {
    @Id
    private String studentID;
    private String name;
    private int age;
    private String address;
    private String dateOfBirth;
    private String phoneNumber;
    private String ParentPhoneNumber;
    private String ParentName;
    private String programID;
    private String registrationDate;

    public Student() {
    }

    public Student(String studentID, String name, int age, String address, String dateOfBirth, String phoneNumber, String parentPhoneNumber, String parentName, String programID, String registrationDate) {
        this.studentID = studentID;
        this.name = name;
        this.age = age;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        ParentPhoneNumber = parentPhoneNumber;
        ParentName = parentName;
        this.programID = programID;
        this.registrationDate = registrationDate;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getParentPhoneNumber() {
        return ParentPhoneNumber;
    }

    public void setParentPhoneNumber(String parentPhoneNumber) {
        ParentPhoneNumber = parentPhoneNumber;
    }

    public String getParentName() {
        return ParentName;
    }

    public void setParentName(String parentName) {
        ParentName = parentName;
    }

    public String getProgramID() {
        return programID;
    }

    public void setProgramID(String programID) {
        this.programID = programID;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
}
