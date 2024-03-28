package dao;

import database_connection.ConnectionFactory;
import database_connection.CRUDQueries;
import entity.CourseSchedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class CourseScheduleDaoImpl implements CourseScheduleDao {

    @Override
    public Set<CourseSchedule> getAllCourseSchedules() {
        Set<CourseSchedule> courseSchedules = new HashSet<>();
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.GET_ALL_COURSE_SCHEDULES.getQuery());
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                CourseSchedule courseSchedule = new CourseSchedule();
                courseSchedule.setCourseId(resultSet.getInt("course_id"));
                courseSchedule.setClassRoomId(resultSet.getInt("classroom_id"));
                courseSchedule.setDayOfWeek(resultSet.getString("day_of_week"));
                courseSchedule.setStartTime(resultSet.getTime("start_time"));
                courseSchedule.setEndTime(resultSet.getTime("end_time"));
                courseSchedules.add(courseSchedule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseSchedules;
    }

    @Override
    public CourseSchedule getCourseScheduleById(int courseId, String dayOfWeek, java.sql.Time startTime) {
        CourseSchedule courseSchedule = null;
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.GET_COURSE_SCHEDULE_BY_ID.getQuery())) {
            preparedStatement.setInt(1, courseId);
            preparedStatement.setString(2, dayOfWeek);
            preparedStatement.setTime(3, startTime);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    courseSchedule = new CourseSchedule();
                    courseSchedule.setCourseId(resultSet.getInt("course_id"));
                    courseSchedule.setClassRoomId(resultSet.getInt("classroom_id"));
                    courseSchedule.setDayOfWeek(resultSet.getString("day_of_week"));
                    courseSchedule.setStartTime(resultSet.getTime("start_time"));
                    courseSchedule.setEndTime(resultSet.getTime("end_time"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseSchedule;
    }

    @Override
    public CourseSchedule insertCourseSchedule(CourseSchedule courseSchedule) {
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.INSERT_COURSE_SCHEDULE.getQuery())) {
            preparedStatement.setInt(1, courseSchedule.getCourseId());
            preparedStatement.setInt(2, courseSchedule.getClassRoomId());
            preparedStatement.setString(3, courseSchedule.getDayOfWeek());
            preparedStatement.setTime(4, courseSchedule.getStartTime());
            preparedStatement.setTime(5, courseSchedule.getEndTime());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Inserting course schedule failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseSchedule;
    }

    @Override
    public boolean updateCourseSchedule(CourseSchedule courseSchedule) {
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.UPDATE_COURSE_SCHEDULE.getQuery())) {
            preparedStatement.setInt(1, courseSchedule.getClassRoomId());
            preparedStatement.setTime(2, courseSchedule.getEndTime());
            preparedStatement.setInt(3, courseSchedule.getCourseId());
            preparedStatement.setString(4, courseSchedule.getDayOfWeek());
            preparedStatement.setTime(5, courseSchedule.getStartTime());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Updating course schedule failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteCourseSchedule(int courseId, String dayOfWeek, java.sql.Time startTime) {
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.DELETE_COURSE_SCHEDULE_BY_ID.getQuery())) {
            preparedStatement.setInt(1, courseId);
            preparedStatement.setString(2, dayOfWeek);
            preparedStatement.setTime(3, startTime);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Deleting course schedule failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
