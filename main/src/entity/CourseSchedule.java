package entity;

import java.sql.Time;

public class CourseSchedule {
    private int courseId;
    private int classRoomId;
    private String dayOfWeek;
    private Time startTime;
    private Time endTime;

    // Default constructor
    public CourseSchedule() {
    }

    // Parameterized constructor
    public CourseSchedule(int courseId, int classRoomId, String dayOfWeek, Time startTime, Time endTime) {
        this.courseId = courseId;
        this.classRoomId = classRoomId;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Constructor without courseId
    public CourseSchedule(int classRoomId, String dayOfWeek, Time startTime, Time endTime) {
        this.classRoomId = classRoomId;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters and setters


    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getClassRoomId() {
        return classRoomId;
    }

    public void setClassRoomId(int classRoomId) {
        this.classRoomId = classRoomId;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    // toString method
    @Override
    public String toString() {
        return "CourseSchedule{" +
                "courseId=" + courseId +
                ", classRoomId=" + classRoomId +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
