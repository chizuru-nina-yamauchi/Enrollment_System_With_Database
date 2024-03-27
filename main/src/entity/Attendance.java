package entity;

import java.time.LocalDate;

public class Attendance {
    private int attendanceId;
    private int enrollmentId;
    private LocalDate attendanceDate;
    private String status;

    // Default constructor
    public Attendance() {
    }

    // Parameterized constructor
    public Attendance(int attendanceId, int enrollmentId, LocalDate attendanceDate, String status) {
        this.attendanceId = attendanceId;
        this.enrollmentId = enrollmentId;
        this.attendanceDate = attendanceDate;
        this.status = status;
    }

    // Constructor without attendanceId
    public Attendance(int enrollmentId, LocalDate attendanceDate, String status) {
        this.enrollmentId = enrollmentId;
        this.attendanceDate = attendanceDate;
        this.status = status;
    }

    // Getters and setters

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // toString method

    @Override
    public String toString() {
        return "Attendance{" +
                "attendanceId=" + attendanceId +
                ", enrollmentId=" + enrollmentId +
                ", attendanceDate=" + attendanceDate +
                ", status='" + status + '\'' +
                '}';
    }
}
