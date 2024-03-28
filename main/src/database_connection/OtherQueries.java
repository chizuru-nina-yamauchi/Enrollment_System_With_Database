package database_connection;
public enum OtherQueries {

    // Consistency
    CHECK_COURSE_CAPACITY(
            "SELECT capacity FROM Courses WHERE course_id = ?"
    ), // CourseDaoImpl
    CHECK_CLASSROOM_CAPACITY(
            "SELECT capacity FROM Classrooms WHERE classroom_id = ?"
    ), // ClassroomDaoImpl
    CHECK_INSTRUCTOR_ASSIGNMENT(
            "SELECT COUNT(*) FROM CourseInstructors WHERE course_id = ? AND instructor_id = ?"
    ), // CourseInstructorDaoImpl
    ASSIGN_INSTRUCTOR_TO_COURSE("INSERT INTO CourseInstructors (course_id, instructor_id) VALUES (?, ?)"),
    UNASSIGN_INSTRUCTOR_FROM_COURSE("DELETE FROM CourseInstructors WHERE course_id = ? AND instructor_id = ?"),
    ENROLL_STUDENT_IN_COURSE_TRANSACTIONAL(
            "BEGIN;"
                    + "UPDATE Courses SET capacity = capacity - 1 WHERE course_id = ? AND capacity > 0;"
                    + "INSERT INTO Enrollments (student_id, course_id, enrollment_date) VALUES (?, ?, CURRENT_DATE);"
                    + "COMMIT;"
    ),
    ADD_COURSE_ATOMICALLY(
            "BEGIN;"
                    + "INSERT INTO Courses (title, capacity) VALUES (?, ?);"
                    + "COMMIT;"
    ),
    DROP_COURSE_ATOMICALLY(
            "BEGIN;"
                    + "DELETE FROM Courses WHERE course_id = ?;"
                    + "COMMIT;"
    ),
    DROP_COURSE_ENROLLMENT_ROLLBACK_ON_FAILURE(
            "BEGIN;"
                    + "DELETE FROM Enrollments WHERE enrollment_id = ?;"
                    + "UPDATE Courses SET capacity = capacity + 1 WHERE course_id = ?;"
                    + "COMMIT;"
    ),
    // Isolation
    LOCK_CLASSROOM_FOR_ENROLLMENT(
            "SELECT * FROM Classrooms WHERE classroom_id = ? FOR UPDATE"
    ),
    LOCK_INSTRUCTOR_FOR_ASSIGNMENT(
            "SELECT * FROM Instructors WHERE instructor_id = ? FOR UPDATE"
    ),
    LOCK_COURSE_FOR_ENROLLMENT(
            "SELECT * FROM Courses WHERE course_id = ? FOR UPDATE;"
    );

    private final String query;

    OtherQueries(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}

