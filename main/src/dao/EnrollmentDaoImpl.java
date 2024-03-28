package dao;

import database_connection.CRUDQueries;
import database_connection.ConnectionFactory;
import entity.Enrollment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class EnrollmentDaoImpl implements EnrollmentDao{
    @Override
    public Set<Enrollment> getAllEnrollments() {
        Set<Enrollment> enrollments = new HashSet<>();
        try(Connection connection = ConnectionFactory.getInstance().createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.GET_ALL_ENROLLMENTS.getQuery());
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Enrollment enrollment = new Enrollment();
                enrollment.setEnrollmentId(resultSet.getInt("enrollment_id"));
                enrollment.setStudentId(resultSet.getInt("student_id"));
                enrollment.setCourseId(resultSet.getInt("course_id"));
                enrollment.setEnrollmentDate(resultSet.getDate("enrollment_date").toLocalDate());
                enrollments.add(enrollment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollments;
    }

    @Override
    public Enrollment getEnrollmentById(int enrollmentId) {
        Enrollment enrollment = null;
        try(Connection connection = ConnectionFactory.getInstance().createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.GET_ENROLLMENT_BY_ID.getQuery())) {
            preparedStatement.setInt(1, enrollmentId);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    enrollment = new Enrollment();
                    enrollment.setEnrollmentId(resultSet.getInt("enrollment_id"));
                    enrollment.setStudentId(resultSet.getInt("student_id"));
                    enrollment.setCourseId(resultSet.getInt("course_id"));
                    enrollment.setEnrollmentDate(resultSet.getDate("enrollment_date").toLocalDate());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollment;
    }

    @Override
    public Enrollment insertEnrollment(Enrollment enrollment) {
        try(Connection connection = ConnectionFactory.getInstance().createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.INSERT_ENROLLMENT.getQuery())) {
            preparedStatement.setInt(1, enrollment.getStudentId());
            preparedStatement.setInt(2, enrollment.getCourseId());
            preparedStatement.setDate(3, java.sql.Date.valueOf(enrollment.getEnrollmentDate()));
            preparedStatement.executeUpdate();
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Inserting enrollment failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollment;
    }

    @Override
    public boolean updateEnrollment(Enrollment enrollment){
        try(Connection connection = ConnectionFactory.getInstance().createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.UPDATE_ENROLLMENT.getQuery())) {
            preparedStatement.setInt(1, enrollment.getStudentId());
            preparedStatement.setInt(2, enrollment.getCourseId());
            preparedStatement.setDate(3, java.sql.Date.valueOf(enrollment.getEnrollmentDate()));
            preparedStatement.setInt(4, enrollment.getEnrollmentId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Updating enrollment failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteEnrollmentById(int enrollmentId) {
        try(Connection connection = ConnectionFactory.getInstance().createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.DELETE_ENROLLMENT_BY_ID.getQuery())) {
            preparedStatement.setInt(1, enrollmentId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Deleting enrollment failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        return false;
    }
    return true;
    }
}
