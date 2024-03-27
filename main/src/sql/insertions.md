SQL queries for data insertion
```sql

-- Insert data into Students table
INSERT INTO Students (name, email) VALUES
('Tanjiro Kamado', 'tanjiro@ds.com'),
('Nezuko Kamado', 'nezuko@ds.com'),
('Zenitsu Agatsuma', 'zenitsu@ds.com'),
('Inosuke Hashibira', 'inosuke@ds.com'),
('Kanao Tsuyuri', 'kanao@ds.com');

-- Insert data into Courses table
INSERT INTO Courses (title, capacity) VALUES
('Basic Demon Slayer Training', 20),
('Breath of Water Techniques', 15),
('Insect Breathing Style', 15),
('Flame Breathing Style', 15),
('Flame Hashira Training', 10);


-- Insert data into Enrollments table
INSERT INTO Enrollments (student_id, course_id, enrollment_date) VALUES
(1, 1, '2024-03-01'), -- Tanjiro Kamado enrolled in Basic Demon Slayer Training
(2, 2, '2024-03-01'), -- Nezuko Kamado enrolled in Breath of Water Techniques
(3, 3, '2024-03-01'), -- Zenitsu Agatsuma enrolled in Insect Breathing Style
(4, 4, '2024-03-01'), -- Inosuke Hashibira enrolled in Flame Breathing Style
(5, 5, '2024-03-01'); -- Kanao Tsuyuri enrolled in Flame Hashira Training


-- Insert data into Departments table
INSERT INTO Departments (name) VALUES
('Hashira Corps'),
('Demonology Studies'),
('Combat Techniques');

-- Insert data into Instructors table
INSERT INTO Instructors (name, department_id) VALUES
('Giyu Tomioka', 1),
('Shinobu Kocho', 2),
('Kyojuro Rengoku', 3);

-- Insert data into CourseInstructors table (assigning instructors to courses)
INSERT INTO CourseInstructors (course_id, instructor_id) VALUES
(1, 1), -- Basic Demon Slayer Training - Giyu Tomioka
(2, 1), -- Breath of Water Techniques - Giyu Tomioka
(3, 2), -- Insect Breathing Style - Shinobu Kocho
(4, 3), -- Flame Breathing Style - Kyojuro Rengoku
(5, 3); -- Flame Hashira Training - Kyojuro Rengoku

-- Insert data into Classrooms table
INSERT INTO Classrooms (location, capacity) VALUES
('Training Hall 1', 30),
('Training Hall 2', 30),
('Training Hall 3', 20);

-- Insert data into CourseSchedule table
INSERT INTO CourseSchedule (course_id, classroom_id, day_of_week, start_time, end_time) VALUES
(1, 1, 'Monday', '09:00', '12:00'),
(2, 2, 'Tuesday', '09:00', '12:00'),
(3, 3, 'Wednesday', '09:00', '12:00'),
(4, 1, 'Thursday', '09:00', '12:00'),
(5, 2, 'Friday', '09:00', '12:00');

-- Insert data into Grades table (dummy data for demonstration)
INSERT INTO Grades (enrollment_id, grade) VALUES
(1, 'A'),
(2, 'B'),
(3, 'B+'),
(4, 'A-'),
(5, 'A');

-- Insert data into Attendance table (dummy data for demonstration)
INSERT INTO Attendance (enrollment_id, date, status) VALUES
(1, '2024-04-01', 'Present'),
(2, '2024-04-02', 'Absent'),
(3, '2024-04-03', 'Late'),
(4, '2024-04-04', 'Present'),
(5, '2024-04-05', 'Present');
