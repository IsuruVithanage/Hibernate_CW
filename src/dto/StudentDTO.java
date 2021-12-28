package dto;

public class StudentDTO {
    private String studentID;
    private String name;
    private int age;
    private String address;
    private String dateOfBirth;
    private String phoneNumber;
    private String parentPhoneNumber;
    private String parentName;

    public StudentDTO() {
    }

    public StudentDTO(String studentID, String name, int age, String address, String dateOfBirth, String phoneNumber, String parentPhoneNumber, String parentName) {
        this.studentID = studentID;
        this.name = name;
        this.age = age;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.parentPhoneNumber = parentPhoneNumber;
        this.parentName = parentName;
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
        return parentPhoneNumber;
    }

    public void setParentPhoneNumber(String parentPhoneNumber) {
        this.parentPhoneNumber = parentPhoneNumber;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "studentID='" + studentID + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", parentPhoneNumber='" + parentPhoneNumber + '\'' +
                ", parentName='" + parentName + '\'' +
                '}';
    }
}
