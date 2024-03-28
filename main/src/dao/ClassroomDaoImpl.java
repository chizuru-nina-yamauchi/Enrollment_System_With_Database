package dao;

import database_connection.ConnectionFactory;
import database_connection.CRUDQueries;
import database_connection.OtherQueries;
import entity.Classroom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ClassroomDaoImpl implements ClassroomDao {

    @Override
    public Set<Classroom> getAllClassrooms() {
        Set<Classroom> classrooms = new HashSet<>();
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.GET_ALL_CLASSROOMS.getQuery());
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Classroom classroom = new Classroom();
                classroom.setClassroomId(resultSet.getInt("classroom_id"));
                classroom.setLocation(resultSet.getString("location"));
                classroom.setCapacity(resultSet.getInt("capacity"));
                classrooms.add(classroom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classrooms;
    }

    @Override
    public Classroom getClassroomById(int classroomId) {
        Classroom classroom = null;
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.GET_CLASSROOM_BY_ID.getQuery())) {
            preparedStatement.setInt(1, classroomId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    classroom = new Classroom();
                    classroom.setClassroomId(resultSet.getInt("classroom_id"));
                    classroom.setLocation(resultSet.getString("location"));
                    classroom.setCapacity(resultSet.getInt("capacity"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classroom;
    }

    @Override
    public Classroom insertClassroom(Classroom classroom) {
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.INSERT_CLASSROOM.getQuery())) {
            preparedStatement.setString(1, classroom.getLocation());
            preparedStatement.setInt(2, classroom.getCapacity());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Inserting classroom failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classroom;
    }

    @Override
    public boolean updateClassroom(Classroom classroom) {
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.UPDATE_CLASSROOM.getQuery())) {
            preparedStatement.setString(1, classroom.getLocation());
            preparedStatement.setInt(2, classroom.getCapacity());
            preparedStatement.setInt(3, classroom.getClassroomId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Updating classroom failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteClassroomById(int classroomId) {
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.DELETE_CLASSROOM_BY_ID.getQuery())) {
            preparedStatement.setInt(1, classroomId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Deleting classroom failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // Other operations

    @Override
    public int checkClassroomCapacity(int classroomId) {
        int capacity = -1;
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(OtherQueries.CHECK_CLASSROOM_CAPACITY.getQuery())) {
            preparedStatement.setInt(1, classroomId);
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

