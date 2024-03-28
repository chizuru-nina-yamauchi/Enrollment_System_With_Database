package concurrency_control;

public interface LockManager {
    void lockClassroomForEnrollment(int classroomId);
    void lockInstructorForAssignment(int instructorId);
    void lockCourseForEnrollment(int courseId);
}
