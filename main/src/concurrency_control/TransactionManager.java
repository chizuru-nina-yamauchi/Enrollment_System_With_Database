package concurrency_control;

public interface TransactionManager {
    void addCourseAtomically(String title, int capacity);
    void dropCourseAtomically(int courseId);
    void dropCourseEnrollmentRollbackOnFailure(int enrollmentId, int courseId);
}
