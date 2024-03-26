sql queries for the tables and relationships between them
```sql
-- Students Table
CREATE TABLE Students (
    student_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);

-- Courses Table
CREATE TABLE Courses (
    course_id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    capacity INT NOT NULL
);

-- Enrollments Table
CREATE TABLE Enrollments (
    enrollment_id SERIAL PRIMARY KEY,
    student_id INT REFERENCES Students(student_id),
    course_id INT REFERENCES Courses(course_id),
    enrollment_date DATE NOT NULL
);

-- Departments Table
CREATE TABLE Departments (
    department_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Instructors Table
CREATE TABLE Instructors (
    instructor_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    department_id INT REFERENCES Departments(department_id)
);

-- CourseInstructors Table (Many-to-Many)
CREATE TABLE CourseInstructors (
    course_id INT REFERENCES Courses(course_id),
    instructor_id INT REFERENCES Instructors(instructor_id),
    PRIMARY KEY (course_id, instructor_id)
);

-- Classrooms Table
CREATE TABLE Classrooms (
    classroom_id SERIAL PRIMARY KEY,
    location VARCHAR(255) NOT NULL,
    capacity INT NOT NULL
);

-- CourseSchedule Table
CREATE TABLE CourseSchedule (
    course_id INT REFERENCES Courses(course_id),
    classroom_id INT REFERENCES Classrooms(classroom_id),
    day_of_week VARCHAR(10) NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    PRIMARY KEY (course_id, day_of_week, start_time)
);

-- Grades Table
CREATE TABLE Grades (
    enrollment_id INT REFERENCES Enrollments(enrollment_id),
    grade VARCHAR(2) NOT NULL
);

-- Attendance Table
CREATE TABLE Attendance (
    attendance_id SERIAL PRIMARY KEY,
    enrollment_id INT REFERENCES Enrollments(enrollment_id),
    date DATE NOT NULL,
    status VARCHAR(10) NOT NULL
);
