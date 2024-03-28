package dao;

import database_connection.CRUDQueries;
import database_connection.ConnectionFactory;
import database_connection.OtherQueries;
import entity.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;
import java.sql.SQLException;

public class CourseDaoImpl implements CourseDao{
    // CRUD operations
    @Override
    public Set<Course> getAllCourses() {
        Set<Course> courses = new HashSet<>();
        try(Connection connection = ConnectionFactory.getInstance().createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.GET_ALL_COURSES.getQuery());
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Course course = new Course();
                course.setCourseId(resultSet.getInt("course_id"));
                course.setCourseTitle(resultSet.getString("title"));
                course.setCapacity(resultSet.getInt("capacity"));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    @Override
    public Course getCourseById(int courseId) {
        Course course = null;
        try(Connection connection = ConnectionFactory.getInstance().createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.GET_COURSE_BY_ID.getQuery())) {
            preparedStatement.setInt(1, courseId);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    course = new Course();
                    course.setCourseId(resultSet.getInt("course_id"));
                    course.setCourseTitle(resultSet.getString("title"));
                    course.setCapacity(resultSet.getInt("capacity"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }


    @Override
    public Course insertCourse(Course course) {
        try(Connection connection = ConnectionFactory.getInstance().createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.INSERT_COURSE.getQuery())) {
            preparedStatement.setString(1, course.getCourseTitle());
            preparedStatement.setInt(2, course.getCapacity());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0){
                throw new SQLException("Inserting course failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }

    @Override
    public boolean updateCourse(Course course) {
        try(Connection connection = ConnectionFactory.getInstance().createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.UPDATE_COURSE.getQuery())) {
            preparedStatement.setString(1, course.getCourseTitle());
            preparedStatement.setInt(2, course.getCapacity());
            preparedStatement.setInt(3, course.getCourseId());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0){
                throw new SQLException("Updating course failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteCourseById(int courseId) {
        try(Connection connection = ConnectionFactory.getInstance().createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.DELETE_COURSE_BY_ID.getQuery())) {
            preparedStatement.setInt(1, courseId);
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0){
                throw new SQLException("Deleting course failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    // Other operations
    @Override
    public int checkCourseCapacity(int courseId) {
        int capacity = -1;
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(OtherQueries.CHECK_COURSE_CAPACITY.getQuery())) {
            preparedStatement.setInt(1, courseId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    capacity = resultSet.getInt("capacity");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return capacity;
    }

}


