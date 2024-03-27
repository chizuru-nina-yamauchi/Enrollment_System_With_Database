package entity;

public class Instructor {
    private int instructorId;
    private String instructorName;
    private int departmentId;

    // Default constructor
    public Instructor() {
    }

    // Parameterized constructor
    public Instructor(int instructorId, String instructorName, int departmentId) {
        this.instructorId = instructorId;
        this.instructorName = instructorName;
        this.departmentId = departmentId;
    }

    // Constructor without instructorId
    public Instructor(String instructorName, int departmentId) {
        this.instructorName = instructorName;
        this.departmentId = departmentId;
    }

    // Getters and setters

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    // toString method

    @Override
    public String toString() {
        return "Instructor{" +
                "instructorId=" + instructorId +
                ", instructorName='" + instructorName + '\'' +
                ", departmentId=" + departmentId +
                '}';
    }
}
