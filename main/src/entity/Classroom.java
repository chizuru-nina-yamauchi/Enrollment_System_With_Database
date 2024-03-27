package entity;

public class Classroom {
    private int classroomId;
    private String location;
    private int capacity;

    // Default constructor
    public Classroom() {
    }

    // Parameterized constructor
    public Classroom(int classroomId, String location, int capacity) {
        this.classroomId = classroomId;
        this.location = location;
        this.capacity = capacity;
    }

    // Constructor without classroomId

    public Classroom(String location, int capacity) {
        this.location = location;
        this.capacity = capacity;
    }

    // Getters and setters


    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
        return "Classroom{" +
                "classroomId=" + classroomId +
                ", location='" + location + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
