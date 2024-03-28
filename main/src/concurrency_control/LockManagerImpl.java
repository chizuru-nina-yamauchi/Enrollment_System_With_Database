package concurrency_control;

import database_connection.ConnectionFactory;
import database_connection.OtherQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LockManagerImpl implements LockManager{
    @Override
    public void lockClassroomForEnrollment(int classroomId) {
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(OtherQueries.LOCK_CLASSROOM_FOR_ENROLLMENT.getQuery())) {
            preparedStatement.setInt(1, classroomId);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void lockInstructorForAssignment(int instructorId) {
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(OtherQueries.LOCK_INSTRUCTOR_FOR_ASSIGNMENT.getQuery())) {
            preparedStatement.setInt(1, instructorId);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void lockCourseForEnrollment(int courseId) {
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(OtherQueries.LOCK_COURSE_FOR_ENROLLMENT.getQuery())) {
            preparedStatement.setInt(1, courseId);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
