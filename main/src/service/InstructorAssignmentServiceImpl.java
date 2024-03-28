package service;

import database_connection.ConnectionFactory;
import database_connection.OtherQueries;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InstructorAssignmentServiceImpl implements InstructorAssignmentService {
    @Override
    public void assignInstructorToCourse(int courseId, int instructorId) throws SQLException{
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(OtherQueries.ASSIGN_INSTRUCTOR_TO_COURSE.getQuery())) {
            preparedStatement.setInt(1, courseId);
            preparedStatement.setInt(2, instructorId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Assigning instructor to course failed, no rows affected.");
            }
        }
    }

    @Override
    public void unassignInstructorFromCourse(int courseId, int instructorId)  throws SQLException{
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(OtherQueries.UNASSIGN_INSTRUCTOR_FROM_COURSE.getQuery())) {
            preparedStatement.setInt(1, courseId);
            preparedStatement.setInt(2, instructorId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Unassigning instructor from course failed, no rows affected.");
            }
        }
    }

}
