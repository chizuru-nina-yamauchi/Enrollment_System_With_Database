package dao;

import entity.Attendance;

import java.util.Set;

public interface AttendanceDao {
    Set<Attendance> getAllAttendance();
    Attendance getAttendanceById(int attendanceId);
    Attendance insertAttendance(Attendance attendance);
    boolean updateAttendance(Attendance attendance);
    boolean deleteAttendanceById(int attendanceId);

}
