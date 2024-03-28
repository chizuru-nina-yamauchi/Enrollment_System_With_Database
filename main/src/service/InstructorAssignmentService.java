package service;

import java.sql.SQLException;

public interface InstructorAssignmentService {
    void assignInstructorToCourse(int courseId, int instructorId) throws SQLException;
    void unassignInstructorFromCourse(int courseId, int instructorId) throws SQLException;
}
