package com.crud.rest.JpaRestApi.service;

import com.crud.rest.JpaRestApi.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllStudents();

    Employee findById(int empId);

    Employee save(Employee employee);

    void deleteById(int empId);

}
