package dao;

import entity.CourseInstructor;

import java.util.Set;

public interface CourseInstructorDao {
    Set<CourseInstructor> getAllCourseInstructors();
   Set<CourseInstructor> getCourseInstructorsByCourseId(int courseId);
    Set<CourseInstructor> getCourseInstructorsByInstructorId(int instructorId);
    CourseInstructor insertCourseInstructor(CourseInstructor courseInstructor);
    boolean deleteCourseInstructor(int courseId, int instructorId);
    boolean updateCourseInstructor(CourseInstructor courseInstructor);

    // Other operations
    int checkInstructorAssignment(int courseId, int instructorId);

}
