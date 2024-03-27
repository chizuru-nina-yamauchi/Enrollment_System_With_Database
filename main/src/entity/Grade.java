package entity;

public class Grade {
    private int enrollmentId;
    private String grade;

    // Default constructor
    public Grade() {
    }

    // Parameterized constructor
    public Grade(int enrollmentId, String grade) {
        this.enrollmentId = enrollmentId;
        this.grade = grade;
    }

    // Getters and setters


    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    // toString method

    @Override
    public String toString() {
        return "Grade{" +
                "enrollmentId=" + enrollmentId +
                ", grade='" + grade + '\'' +
                '}';
    }
}
