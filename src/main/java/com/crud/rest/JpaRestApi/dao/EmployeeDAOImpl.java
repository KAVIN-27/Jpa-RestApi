package com.crud.rest.JpaRestApi.dao;

import com.crud.rest.JpaRestApi.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    private EntityManager entityManager;

    public EmployeeDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> getAllEmployees() {
        TypedQuery<Employee> theQuery = entityManager.createQuery("FROM Employee",Employee.class);
        return theQuery.getResultList();
    }

    @Override
    public Employee findById(int empId) {
        Employee employee = entityManager.find(Employee.class,empId);

        if (employee == null){
            throw new RuntimeException("Cannot find employee with id -"+empId);
        }
        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        Employee dbEmployee = entityManager.merge(employee);

        return dbEmployee;

    }

    @Override
    public void deleteById(int empId) {
        Employee theEmployee = entityManager.find(Employee.class,empId);
        entityManager.remove(theEmployee);
    }
}
