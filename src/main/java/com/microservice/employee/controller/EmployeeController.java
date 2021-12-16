package com.microservice.employee.controller;

import com.microservice.employee.model.Employee;
import com.microservice.employee.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmployeeController {


    private final Logger log = LoggerFactory.getLogger(EmployeeController.class);


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping("/employees")
    public Employee create(@RequestBody Employee employee) {
        log.debug("REST request to save Employee : {}", employee);

        Employee result = employeeService.save(employee);
        employeeService.saveEL(result);


        return result;

    }


    @PutMapping("/employees")
    public Employee update(@RequestBody Employee employee) {
        log.debug("REST request to update Employee : {}", employee);


        return employeeService.update(employee);

    }

    @DeleteMapping("/employees/{id}")
    public void deleteById(@PathVariable Long id) {
        log.debug("REST request to delete Employee id : {}", id);
        employeeService.deleteById(id);

    }


    @GetMapping("/employees/{id}")
    public Employee findById(@PathVariable Long id) {
        log.debug("REST request to get Employee id : {}", id);
        return employeeService.findById(id);

    }


}