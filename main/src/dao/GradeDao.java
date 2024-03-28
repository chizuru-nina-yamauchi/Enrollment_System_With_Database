package dao;

import entity.Grade;

import java.util.Set;

public interface GradeDao {
    Set<Grade> getAllGrades();
    Grade getGradeByEnrollmentId(int enrollmentId);
    Grade insertGrade(Grade grade);
    boolean updateGrade(Grade grade);
    boolean deleteGradeByEnrollmentId(int enrollmentId);
}
