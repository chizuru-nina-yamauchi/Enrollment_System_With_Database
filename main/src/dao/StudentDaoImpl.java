package dao;

import database_connection.CRUDQueries;
import database_connection.ConnectionFactory;
import entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;


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

    @Override
    public Set<Student> getAllStudents() {
        Set<Student> students = new HashSet<>();
        try(Connection connection = ConnectionFactory.getInstance().createConnection();
            PreparedStatement ps = connection.prepareStatement(CRUDQueries.GET_ALL_STUDENTS.getQuery());
            ResultSet rs = ps.executeQuery();) {
            while(rs.next()){
                Student student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setStudentName(rs.getString("name"));
                student.setStudentEmail(rs.getString("email"));
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } return students;

    }


    @Override
    public Student getStudentById(int studentId) {
        Student student = null;
        try(Connection connection = ConnectionFactory.getInstance().createConnection();
            PreparedStatement ps = connection.prepareStatement(CRUDQueries.GET_STUDENT_BY_ID.getQuery())) {
            ps.setInt(1, studentId);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()){
                    student.setStudentId(rs.getInt("student_id"));
                    student.setStudentName(rs.getString("name"));
                    student.setStudentEmail(rs.getString("email"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } return student;
    }

    @Override
    public boolean updateStudent(Student student) {
        try(Connection connection = ConnectionFactory.getInstance().createConnection();
            PreparedStatement ps = connection.prepareStatement(CRUDQueries.UPDATE_STUDENT.getQuery())) {
            ps.setString(1, student.getStudentName());
            ps.setString(2, student.getStudentEmail());
            ps.setInt(3, student.getStudentId());
            int rowsAffected = ps.executeUpdate();
            if(rowsAffected == 0){
                throw new SQLException("Updating student failed, no rows affected.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } return true;
    }


    @Override
    public boolean deleteStudentById(int studentId) {
        try(Connection connection = ConnectionFactory.getInstance().createConnection();
            PreparedStatement ps = connection.prepareStatement(CRUDQueries.DELETE_STUDENT_BY_ID.getQuery())) {
            ps.setInt(1, studentId);
            int rowsAffected = ps.executeUpdate();
            if(rowsAffected == 0){
                throw new SQLException("Deleting student failed, no rows affected.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } return true;
    }

}
