package entity;

public class Course {

    private int courseId;
    private String courseTitle;
    private int capacity;

    // Default constructor
    public Course() {
    }

    // Parameterized constructor

    public Course(int courseId, String courseTitle, int capacity) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.capacity = capacity;
    }

    // Constructor without courseId
    public Course(String courseTitle, int capacity) {
        this.courseTitle = courseTitle;
        this.capacity = capacity;
    }

    // Getters and setters


    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    // toString method

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseTitle='" + courseTitle + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
