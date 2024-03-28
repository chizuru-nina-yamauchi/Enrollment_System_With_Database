package dao;

import entity.Grade;
import database_connection.ConnectionFactory;
import database_connection.CRUDQueries;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class GradeDaoImpl implements GradeDao {

    @Override
    public Set<Grade> getAllGrades() {
        Set<Grade> grades = new HashSet<>();
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.GET_ALL_GRADES.getQuery());
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Grade grade = new Grade();
                grade.setEnrollmentId(resultSet.getInt("enrollment_id"));
                grade.setGrade(resultSet.getString("grade"));
                grades.add(grade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grades;
    }

    @Override
    public Grade getGradeByEnrollmentId(int enrollmentId) {
        Grade grade = null;
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.GET_GRADE_BY_ENROLLMENT_ID.getQuery())) {
            preparedStatement.setInt(1, enrollmentId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    grade = new Grade();
                    grade.setEnrollmentId(resultSet.getInt("enrollment_id"));
                    grade.setGrade(resultSet.getString("grade"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grade;
    }

    @Override
    public Grade insertGrade(Grade grade) {
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.INSERT_GRADE.getQuery())) {
            preparedStatement.setInt(1, grade.getEnrollmentId());
            preparedStatement.setString(2, grade.getGrade());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Inserting grade failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grade;
    }

    @Override
    public boolean updateGrade(Grade grade) {
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.UPDATE_GRADE.getQuery())) {
            preparedStatement.setString(1, grade.getGrade());
            preparedStatement.setInt(2, grade.getEnrollmentId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Updating grade failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteGradeByEnrollmentId(int enrollmentId) {
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.DELETE_GRADE_BY_ENROLLMENT_ID.getQuery())) {
            preparedStatement.setInt(1, enrollmentId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Deleting grade failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}