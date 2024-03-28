package dao;

import entity.Course;

import java.util.Set;

public interface CourseDao {
    Set<Course> getAllCourses();
    Course getCourseById(int courseId);
    Course insertCourse(Course course);
    boolean updateCourse(Course course);
    boolean deleteCourseById(int courseId);

    // Other operation
    int checkCourseCapacity(int courseId);
}
