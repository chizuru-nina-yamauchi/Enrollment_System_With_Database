package dao;

import database_connection.ConnectionFactory;
import database_connection.CRUDQueries;
import entity.Attendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.time.LocalDate;

public class AttendanceDaoImpl implements AttendanceDao {

    @Override
    public Set<Attendance> getAllAttendance() {
        Set<Attendance> attendanceSet = new HashSet<>();
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.GET_ALL_ATTENDANCE.getQuery());
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Attendance attendance = new Attendance();
                attendance.setAttendanceId(resultSet.getInt("attendance_id"));
                attendance.setEnrollmentId(resultSet.getInt("enrollment_id"));
                attendance.setAttendanceDate(resultSet.getDate("date").toLocalDate());
                attendance.setStatus(resultSet.getString("status"));
                attendanceSet.add(attendance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendanceSet;
    }

    @Override
    public Attendance getAttendanceById(int attendanceId) {
        Attendance attendance = null;
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.GET_ATTENDANCE_BY_ID.getQuery())) {
            preparedStatement.setInt(1, attendanceId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    attendance = new Attendance();
                    attendance.setAttendanceId(resultSet.getInt("attendance_id"));
                    attendance.setEnrollmentId(resultSet.getInt("enrollment_id"));
                    attendance.setAttendanceDate(resultSet.getDate("date").toLocalDate());
                    attendance.setStatus(resultSet.getString("status"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendance;
    }

    @Override
    public Attendance insertAttendance(Attendance attendance) {
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.INSERT_ATTENDANCE.getQuery())) {
            preparedStatement.setInt(1, attendance.getEnrollmentId());
            preparedStatement.setDate(2, java.sql.Date.valueOf(attendance.getAttendanceDate()));
            preparedStatement.setString(3, attendance.getStatus());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Inserting attendance failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendance;
    }

    @Override
    public boolean updateAttendance(Attendance attendance) {
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.UPDATE_ATTENDANCE.getQuery())) {
            preparedStatement.setInt(1, attendance.getEnrollmentId());
            preparedStatement.setDate(2, java.sql.Date.valueOf(attendance.getAttendanceDate()));
            preparedStatement.setString(3, attendance.getStatus());
            preparedStatement.setInt(4, attendance.getAttendanceId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Updating attendance failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteAttendanceById(int attendanceId) {
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.DELETE_ATTENDANCE_BY_ID.getQuery())) {
            preparedStatement.setInt(1, attendanceId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Deleting attendance failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
