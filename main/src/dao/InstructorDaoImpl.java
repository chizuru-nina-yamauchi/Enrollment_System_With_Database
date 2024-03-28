package dao;

import database_connection.CRUDQueries;
import database_connection.ConnectionFactory;
import entity.Instructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class InstructorDaoImpl implements InstructorDao{
    @Override
    public Set<Instructor> getAllInstructors() {
        Set<Instructor> instructors = new HashSet<>();
        try(Connection connection = ConnectionFactory.getInstance().createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.GET_ALL_INSTRUCTORS.getQuery());
            ResultSet resultSet = preparedStatement.executeQuery()){
            while(resultSet.next()){
                Instructor instructor = new Instructor();
                instructor.setInstructorId(resultSet.getInt("instructor_id"));
                instructor.setInstructorName(resultSet.getString("name"));
                instructor.setDepartmentId(resultSet.getInt("department_id"));
                instructors.add(instructor);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return instructors;
    }

    @Override
    public Instructor getInstructorById(int instructorId) {
        Instructor instructor = null;
        try(Connection connection = ConnectionFactory.getInstance().createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.GET_INSTRUCTOR_BY_ID.getQuery())){
            preparedStatement.setInt(1, instructorId);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    instructor = new Instructor();
                    instructor.setInstructorId(resultSet.getInt("instructor_id"));
                    instructor.setInstructorName(resultSet.getString("name"));
                    instructor.setDepartmentId(resultSet.getInt("department_id"));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return instructor;
    }

    @Override
    public Instructor insertInstructor(Instructor instructor) {
        try(Connection connection = ConnectionFactory.getInstance().createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.INSERT_INSTRUCTOR.getQuery())){
            preparedStatement.setString(1, instructor.getInstructorName());
            preparedStatement.setInt(2, instructor.getDepartmentId());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0){
                throw new SQLException("Inserting instructor failed, no rows affected.");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return instructor;
    }

    @Override
    public boolean updateInstructor(Instructor instructor) {
    try(Connection connection = ConnectionFactory.getInstance().createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.UPDATE_INSTRUCTOR.getQuery())){
        preparedStatement.setString(1, instructor.getInstructorName());
        preparedStatement.setInt(2, instructor.getDepartmentId());
        preparedStatement.setInt(3, instructor.getInstructorId());
        int rowsAffected = preparedStatement.executeUpdate();
        if(rowsAffected == 0){
            throw new SQLException("Updating instructor failed, no rows affected.");
        }
    }catch (SQLException e){
        e.printStackTrace();
        return false;
    }
     return true;
    }

    @Override
    public boolean deleteInstructorById(int instructorId) {
        try(Connection connection = ConnectionFactory.getInstance().createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.DELETE_INSTRUCTOR_BY_ID.getQuery())){
            preparedStatement.setInt(1, instructorId);
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0){
                throw new SQLException("Deleting instructor failed, no rows affected.");
            }
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
