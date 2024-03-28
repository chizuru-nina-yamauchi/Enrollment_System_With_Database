package dao;

import entity.Classroom;

import java.util.Set;

public interface ClassroomDao {
    Set<Classroom> getAllClassrooms();
    Classroom getClassroomById(int classroomId);
    Classroom insertClassroom(Classroom classroom);
    boolean updateClassroom(Classroom classroom);
    boolean deleteClassroomById(int classroomId);

}
