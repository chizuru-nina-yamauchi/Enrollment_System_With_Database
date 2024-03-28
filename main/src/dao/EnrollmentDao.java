package dao;

import entity.Enrollment;

import java.util.Set;

public interface EnrollmentDao {
    // CRUD operations
    Set<Enrollment> getAllEnrollments();
    Enrollment getEnrollmentById(int enrollmentId);
    Enrollment insertEnrollment(Enrollment enrollment);
    boolean updateEnrollment(Enrollment enrollment);
    boolean deleteEnrollmentById(int enrollmentId);
}
