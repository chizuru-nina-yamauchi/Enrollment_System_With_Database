package dao;

import entity.CourseInstructor;
import database_connection.ConnectionFactory;
import database_connection.CRUDQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class CourseInstructorDaoImpl implements CourseInstructorDao {

    @Override
    public Set<CourseInstructor> getAllCourseInstructors() {
        Set<CourseInstructor> courseInstructors = new HashSet<>();
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.GET_ALL_COURSE_INSTRUCTORS.getQuery());
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                CourseInstructor courseInstructor = new CourseInstructor();
                courseInstructor.setCourseId(resultSet.getInt("course_id"));
                courseInstructor.setInstructorId(resultSet.getInt("instructor_id"));
                courseInstructors.add(courseInstructor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseInstructors;
    }

    @Override
    public Set<CourseInstructor> getCourseInstructorsByCourseId(int courseId) {
        Set<CourseInstructor> courseInstructors = new HashSet<>();
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.GET_COURSE_INSTRUCTORS_BY_COURSE_ID.getQuery())) {
            preparedStatement.setInt(1, courseId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    CourseInstructor courseInstructor = new CourseInstructor();
                    courseInstructor.setCourseId(resultSet.getInt("course_id"));
                    courseInstructor.setInstructorId(resultSet.getInt("instructor_id"));
                    courseInstructors.add(courseInstructor);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseInstructors;
    }

    @Override
    public Set<CourseInstructor> getCourseInstructorsByInstructorId(int instructorId) {
        Set<CourseInstructor> courseInstructors = new HashSet<>();
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.GET_COURSE_INSTRUCTORS_BY_INSTRUCTOR_ID.getQuery())) {
            preparedStatement.setInt(1, instructorId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    CourseInstructor courseInstructor = new CourseInstructor();
                    courseInstructor.setCourseId(resultSet.getInt("course_id"));
                    courseInstructor.setInstructorId(resultSet.getInt("instructor_id"));
                    courseInstructors.add(courseInstructor);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseInstructors;
    }

    @Override
    public CourseInstructor insertCourseInstructor(CourseInstructor courseInstructor) {
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.INSERT_COURSE_INSTRUCTOR.getQuery())) {
            preparedStatement.setInt(1, courseInstructor.getCourseId());
            preparedStatement.setInt(2, courseInstructor.getInstructorId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Inserting course instructor failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseInstructor;
    }

    @Override
    public boolean deleteCourseInstructor(int courseId, int instructorId) {
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.DELETE_COURSE_INSTRUCTOR.getQuery())) {
            preparedStatement.setInt(1, courseId);
            preparedStatement.setInt(2, instructorId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Deleting course instructor failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateCourseInstructor(CourseInstructor courseInstructor) {
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.UPDATE_COURSE_INSTRUCTOR.getQuery())) {
            preparedStatement.setInt(1, courseInstructor.getCourseId());
            preparedStatement.setInt(2, courseInstructor.getInstructorId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Updating course instructor failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}