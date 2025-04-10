package com.crud.rest.JpaRestApi.controller;

import com.crud.rest.JpaRestApi.entity.Employee;
import com.crud.rest.JpaRestApi.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
public class EmployeeController {

    private EmployeeService employeeService;

    private ObjectMapper objectMapper;

    public EmployeeController(EmployeeService employeeService, ObjectMapper objectMapper){
        this.employeeService = employeeService;
        this.objectMapper = objectMapper;
    }


    @GetMapping(path = "/employees")
    public List<Employee> getAll(){
        return employeeService.getAllStudents();
    }

    @GetMapping(path = "/employee/{employeeId}")
    public Employee findEmployeeById(@PathVariable int employeeId){


        Employee foundEmployee = employeeService.findById(employeeId);

        if (foundEmployee == null){
            throw new RuntimeException("The employee with the id-"+employeeId+" doesn't exists");
        }
        return foundEmployee;
    }

    @PostMapping(path = "/employee")
    public Employee addEmployee(@RequestBody Employee theEmployee){

        theEmployee.setId(0);

        Employee dbEmployee = employeeService.save(theEmployee);

        return  dbEmployee;
    }

    @PutMapping(path = "/employee")
    public Employee updateEmployee(@RequestBody Employee employee){
      return   employeeService.save(employee);
    }

    @DeleteMapping(path = "/employee/{empId}")
    public void deleteEmployee(@PathVariable int empId){
        employeeService.deleteById(empId);
    }


    @PatchMapping(path = "/employee/{empId}")
    public Employee patchEmployee(@PathVariable int empId,
                                  @RequestBody Map<String ,Object> patchPayLoad){

        Employee tempEmployee = employeeService.findById(empId);

        if(tempEmployee == null){
            throw new RuntimeException("Employee Id not found-"+empId);
        }

        if(patchPayLoad.containsKey("id")){
            throw new RuntimeException("Employee Id is not allowed in request body -"+empId);

        }

        Employee pathcedEmployee = apply(patchPayLoad,tempEmployee);

        Employee dbEmployee = employeeService.save(pathcedEmployee);

        return dbEmployee;
    }

    private Employee apply(Map<String, Object> patchPayLoad, Employee tempEmployee) {

        //converting employee object to a JSON object node

        ObjectNode employeeNode = objectMapper.convertValue(tempEmployee,ObjectNode.class);

        //convert patch payload map to a JSON object node

        ObjectNode patchNode = objectMapper.convertValue(patchPayLoad,ObjectNode.class);

        //merge the patch updates into the employee node

        employeeNode.setAll(patchNode);

        return objectMapper.convertValue(employeeNode,Employee.class);
    }


}
