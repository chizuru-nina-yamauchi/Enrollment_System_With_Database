package dao;

import entity.CourseSchedule;

import java.util.Set;

public interface CourseScheduleDao {

        Set<CourseSchedule> getAllCourseSchedules();

        CourseSchedule getCourseScheduleById(int courseId, String dayOfWeek, java.sql.Time startTime);

        CourseSchedule insertCourseSchedule(CourseSchedule courseSchedule);

        boolean updateCourseSchedule(CourseSchedule courseSchedule);

        boolean deleteCourseSchedule(int courseId, String dayOfWeek, java.sql.Time startTime);
}
