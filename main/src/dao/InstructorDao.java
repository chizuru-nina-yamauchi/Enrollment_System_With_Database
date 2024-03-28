package dao;

import entity.Instructor;

import java.util.Set;

public interface InstructorDao {

    // CRUD operations
    Set<Instructor> getAllInstructors();
    Instructor getInstructorById(int instructorId);
    Instructor insertInstructor(Instructor instructor);
    boolean updateInstructor(Instructor instructor);
    boolean deleteInstructorById(int instructorId);

}
