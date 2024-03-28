package entity;

public class CourseInstructor {
    private int courseId;
    private int instructorId;

    // Default constructor
    public CourseInstructor() {
    }

    // Parameterized constructor
    public CourseInstructor(int courseId, int instructorId) {
        this.courseId = courseId;
        this.instructorId = instructorId;
    }

    // Getters and setters


    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    // toString method

    @Override
    public String toString() {
        return "CourseInstructorDao{" +
                "courseId=" + courseId +
                ", instructorId=" + instructorId +
                '}';
    }
}
