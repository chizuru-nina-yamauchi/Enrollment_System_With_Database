package dao;

import entity.Student;

import java.util.Set;

public interface StudentDao {
    // CRUD operations
    Student insertStudent(Student student);
    Set<Student> getAllStudents();
    Student getStudentById(int studentId);
    boolean updateStudent(Student student);
    boolean deleteStudentById(int studentId);

    // Other operations






}
