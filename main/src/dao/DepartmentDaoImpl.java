package dao;

import database_connection.CRUDQueries;
import database_connection.ConnectionFactory;
import entity.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class DepartmentDaoImpl implements DepartmentDao{

    // CRUD operations
    @Override
    public Set<Department> getAllDepartments() {
        Set<Department> departments = new HashSet<>();
        try(Connection connection = ConnectionFactory.getInstance().createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.GET_ALL_DEPARTMENTS.getQuery())) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Department department = new Department();
                department.setDepartmentId(resultSet.getInt("department_id"));
                department.setDepartmentName(resultSet.getString("name"));
                departments.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    @Override
    public Department getDepartmentById(int departmentId) {
        Department department = null;
        try (Connection connection = ConnectionFactory.getInstance().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.GET_DEPARTMENT_BY_ID.getQuery())) {
            preparedStatement.setInt(1, departmentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                department = new Department();
                department.setDepartmentId(resultSet.getInt("department_id"));
                department.setDepartmentName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return department;
        }


    @Override
    public Department insertDepartment(Department department) {
        try(Connection connection = ConnectionFactory.getInstance().createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.INSERT_DEPARTMENT.getQuery())) {
            preparedStatement.setString(1, department.getDepartmentName());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Inserting department failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;
    }


    @Override
    public boolean updateDepartment(Department department) {
        try(Connection connection = ConnectionFactory.getInstance().createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.UPDATE_DEPARTMENT.getQuery())) {
            preparedStatement.setString(1, department.getDepartmentName());
            preparedStatement.setInt(2, department.getDepartmentId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Updating department failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        return false;
    }
    return true;
    }

    @Override
    public boolean deleteDepartmentById(int departmentId) {
        try(Connection connection = ConnectionFactory.getInstance().createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CRUDQueries.DELETE_DEPARTMENT_BY_ID.getQuery())) {
            preparedStatement.setInt(1, departmentId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Deleting department failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        return false;
    }
    return true;
    }
}
