package service;

import database_connection.ConnectionFactory;
import database_connection.OtherQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CourseEnrollmentServiceImpl implements CourseEnrollmentService{

    public boolean enrollStudentInCourseTransactional(int courseId, int studentId) {
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(OtherQueries.ENROLL_STUDENT_IN_COURSE_TRANSACTIONAL.getQuery())) {
            preparedStatement.setInt(1, courseId);
            preparedStatement.setInt(2, studentId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Enrolling student in course failed, no rows affected.");
            }
            return true; // Enrollment successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Enrollment failed
        }
    }
}
