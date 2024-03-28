package concurrency_control;

import database_connection.ConnectionFactory;
import database_connection.OtherQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionManagerImpl implements TransactionManager{
    @Override
    public void addCourseAtomically(String title, int capacity) {
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(OtherQueries.ADD_COURSE_ATOMICALLY.getQuery())) {
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, capacity);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void dropCourseAtomically(int courseId) {
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(OtherQueries.DROP_COURSE_ATOMICALLY.getQuery())) {
            preparedStatement.setInt(1, courseId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void dropCourseEnrollmentRollbackOnFailure(int enrollmentId, int courseId) {
        try (Connection connection = ConnectionFactory.getInstance().createConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement deleteEnrollmentStatement = connection.prepareStatement(OtherQueries.DROP_COURSE_ENROLLMENT_ROLLBACK_ON_FAILURE.getQuery())) {
                deleteEnrollmentStatement.setInt(1, enrollmentId);
                deleteEnrollmentStatement.setInt(2, courseId);
                int deletedEnrollmentRows = deleteEnrollmentStatement.executeUpdate();

                if (deletedEnrollmentRows == 0) {
                    throw new SQLException("Failed to delete enrollment.");
                }

                try (PreparedStatement updateCourseStatement = connection.prepareStatement("UPDATE Courses SET capacity = capacity + 1 WHERE course_id = ?")) {
                    updateCourseStatement.setInt(1, courseId);
                    int updatedCourseRows = updateCourseStatement.executeUpdate();

                    if (updatedCourseRows == 0) {
                        throw new SQLException("Failed to update course capacity.");
                    }

                    connection.commit();
                } catch (SQLException updateCourseException) {
                    connection.rollback();
                    throw updateCourseException;
                }
            } catch (SQLException deleteEnrollmentException) {
                connection.rollback();
                throw deleteEnrollmentException;
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
