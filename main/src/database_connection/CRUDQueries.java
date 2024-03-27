package database_connection;

public enum CRUDQueries {

    // Enum constants


    // Student queries
    GET_ALL_STUDENTS("SELECT * FROM Students"),
    GET_STUDENT_BY_ID("SELECT * FROM Students WHERE student_id = ?"),
    INSERT_STUDENT("INSERT INTO Students (name, email) VALUES (?, ?)"),
    UPDATE_STUDENT("UPDATE Students SET name = ?, email = ? WHERE student_id = ?"),
    DELETE_STUDENT_BY_ID("DELETE FROM Students WHERE student_id = ?"),



    // Instructor queries
    GET_ALL_INSTRUCTORS("SELECT * FROM Instructors"),
    GET_INSTRUCTOR_BY_ID("SELECT * FROM Instructors WHERE instructor_id = ?"),
    INSERT_INSTRUCTOR("INSERT INTO Instructors (name, department_id) VALUES (?, ?)"),
    UPDATE_INSTRUCTOR("UPDATE Instructors SET name = ?, department_id = ? WHERE instructor_id = ?"),
    DELETE_INSTRUCTOR_BY_ID("DELETE FROM Instructors WHERE instructor_id = ?"),


    // Department queries
    GET_ALL_DEPARTMENTS("SELECT * FROM Departments"),
    GET_DEPARTMENT_BY_ID("SELECT * FROM Departments WHERE department_id = ?"),
    INSERT_DEPARTMENT("INSERT INTO Departments (name) VALUES (?)"),
    UPDATE_DEPARTMENT("UPDATE Departments SET name = ? WHERE department_id = ?"),
    DELETE_DEPARTMENT_BY_ID("DELETE FROM Departments WHERE department_id = ?"),


    // Course queries
    GET_ALL_COURSES("SELECT * FROM Courses"),
    GET_COURSE_BY_ID("SELECT * FROM Courses WHERE course_id = ?"),
    INSERT_COURSE("INSERT INTO Courses (title, capacity) VALUES (?, ?)"),
    UPDATE_COURSE("UPDATE Courses SET title = ?, capacity = ? WHERE course_id = ?"),
    DELETE_COURSE_BY_ID("DELETE FROM Courses WHERE course_id = ?"),

    // Enrollment queries
    GET_ALL_ENROLLMENTS("SELECT * FROM Enrollments"),
    GET_ENROLLMENT_BY_ID("SELECT * FROM Enrollments WHERE enrollment_id = ?"),
    INSERT_ENROLLMENT("INSERT INTO Enrollments (student_id, course_id, enrollment_date) VALUES (?, ?, ?)"),
    UPDATE_ENROLLMENT("UPDATE Enrollments SET student_id = ?, course_id = ?, enrollment_date = ? WHERE enrollment_id = ?"),
    DELETE_ENROLLMENT_BY_ID("DELETE FROM Enrollments WHERE enrollment_id = ?"),

    // Grade queries
    GET_ALL_GRADES("SELECT * FROM Grades"),
    GET_GRADE_BY_ENROLLMENT_ID("SELECT * FROM Grades WHERE enrollment_id = ?"),
    INSERT_GRADE("INSERT INTO Grades (enrollment_id, grade) VALUES (?, ?)"),
    UPDATE_GRADE("UPDATE Grades SET grade = ? WHERE enrollment_id = ?"),
    DELETE_GRADE_BY_ENROLLMENT_ID("DELETE FROM Grades WHERE enrollment_id = ?"),

    // CourseInstructor queries
    GET_ALL_COURSE_INSTRUCTORS("SELECT * FROM CourseInstructors"),
    GET_COURSE_INSTRUCTORS_BY_COURSE_ID("SELECT * FROM CourseInstructors WHERE course_id = ?"),
    GET_COURSE_INSTRUCTORS_BY_INSTRUCTOR_ID("SELECT * FROM CourseInstructors WHERE instructor_id = ?"),
    INSERT_COURSE_INSTRUCTOR("INSERT INTO CourseInstructors (course_id, instructor_id) VALUES (?, ?)"),
    DELETE_COURSE_INSTRUCTOR("DELETE FROM CourseInstructors WHERE course_id = ? AND instructor_id = ?"),

    // CourseSchedule queries
    GET_ALL_COURSE_SCHEDULES("SELECT * FROM CourseSchedule"),
    GET_COURSE_SCHEDULE_BY_ID("SELECT * FROM CourseSchedule WHERE course_id = ? AND day_of_week = ? AND start_time = ?"), // Primary keys are course_id, day_of_week, start_time
    INSERT_COURSE_SCHEDULE("INSERT INTO CourseSchedule (course_id, classroom_id, day_of_week, start_time, end_time) VALUES (?, ?, ?, ?, ?)"),
    UPDATE_COURSE_SCHEDULE("UPDATE CourseSchedule SET classroom_id = ?, end_time = ? WHERE course_id = ? AND day_of_week = ? AND start_time = ?"), // Primary keys are course_id, day_of_week, start_time
    DELETE_COURSE_SCHEDULE_BY_ID("DELETE FROM CourseSchedule WHERE course_id = ? AND day_of_week = ? AND start_time = ?"), // Primary keys are course_id, day_of_week, start_time

    // Classroom queries
    GET_ALL_CLASSROOMS("SELECT * FROM Classrooms"),
    GET_CLASSROOM_BY_ID("SELECT * FROM Classrooms WHERE classroom_id = ?"),
    INSERT_CLASSROOM("INSERT INTO Classrooms (location, capacity) VALUES (?, ?)"),
    UPDATE_CLASSROOM("UPDATE Classrooms SET location = ?, capacity = ? WHERE classroom_id = ?"),
    DELETE_CLASSROOM_BY_ID("DELETE FROM Classrooms WHERE classroom_id = ?"),

    // Attendance queries
    GET_ALL_ATTENDANCE("SELECT * FROM Attendance"),
    GET_ATTENDANCE_BY_ID("SELECT * FROM Attendance WHERE attendance_id = ?"),
    INSERT_ATTENDANCE("INSERT INTO Attendance (enrollment_id, date, status) VALUES (?, ?, ?)"),
    UPDATE_ATTENDANCE("UPDATE Attendance SET enrollment_id = ?, date = ?, status = ? WHERE attendance_id = ?"),
    DELETE_ATTENDANCE_BY_ID("DELETE FROM Attendance WHERE attendance_id = ?");


    // Variable
    private final String query;


    // Constructor
    CRUDQueries(String query) {
        this.query = query;
    }

    // Getter
    public String getQuery() {
        return query;
    }

}
