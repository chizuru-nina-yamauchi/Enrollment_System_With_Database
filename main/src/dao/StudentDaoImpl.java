package dao;

import database_connection.CRUDQueries;
import database_connection.ConnectionFactory;
import entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class StudentDaoImpl implements StudentDao{
    // CRUD operations

    // *The try-with-resources statement automatically closes the resources at the end of the block, whether an exception occurs or not.
    @Override
    public Student insertStudent(Student student) {
        try(Connection connection = ConnectionFactory.getInstance().createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.INSERT_STUDENT.getQuery())) {
            preparedStatement.setString(1, student.getStudentName());
            preparedStatement.setString(2, student.getStudentEmail());

            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0){
                throw new SQLException("Inserting student failed, no rows affected.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } return student;
    }

}
