package com.crud.rest.JpaRestApi.dao;


import com.crud.rest.JpaRestApi.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
