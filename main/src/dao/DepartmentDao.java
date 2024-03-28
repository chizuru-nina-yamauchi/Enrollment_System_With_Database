package dao;

import entity.Department;

import java.util.Set;

public interface DepartmentDao {
    // CURD operations
    Set<Department> getAllDepartments();
    Department getDepartmentById(int departmentId);
    Department insertDepartment(Department department);
    boolean updateDepartment(Department department);
    boolean deleteDepartmentById(int departmentId);
}
