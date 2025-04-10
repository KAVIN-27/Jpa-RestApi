package com.crud.rest.JpaRestApi.dao;


import com.crud.rest.JpaRestApi.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> getAllEmployees();

    Employee findById(int empID);

    Employee save(Employee employee);

    void deleteById(int empId);

}
