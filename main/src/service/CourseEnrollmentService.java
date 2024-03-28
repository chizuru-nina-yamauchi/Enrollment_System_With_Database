package service;

public interface CourseEnrollmentService {
    boolean enrollStudentInCourseTransactional(int courseId, int studentId);
}
