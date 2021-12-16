package com.microservice.employee.service;

import com.microservice.employee.controller.error.type.ResourceNotFoundException;
import com.microservice.employee.model.Employee;
import com.microservice.employee.repository.elasticsearch.EmployeeSearchRepository;
import com.microservice.employee.repository.jpa.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeService {


    private final Logger log = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;
    private final EmployeeSearchRepository employeeSearchRepository;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeSearchRepository employeeSearchRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeSearchRepository = employeeSearchRepository;
    }


    public Employee save(Employee employee) {
        log.debug("Service request to save Employee : {}", employee);

        return employeeRepository.save(employee);
    }

    public Employee saveEL(Employee employee) {
        log.debug("Service request to create Employee-EL : {}", employee);

        return employeeSearchRepository.save(employee);
    }

    public Employee update(Employee employee) {
        log.debug("Service request to update Employee : {}", employee);

        if (employee.getId() == null) {

            throw new ResourceNotFoundException("Requested resource is not found.");
        }

        return saveEL(save(employee));
    }

    public void deleteById(Long id) {
        log.debug("Service request to delete Employee id : {}", id);

        if (!employeeRepository.existsById(id)) {

            throw new ResourceNotFoundException("Requested resource is not found.");
        }

        employeeRepository.deleteById(id);
        employeeSearchRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Employee findById(Long id) {
        log.debug("Service request to get Employee id : {}", id);

        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Requested resource is not found."));
    }


//    @Transactional(readOnly = true)
//    public Page<City> search(String query , Pageable pageable) {
//        log.debug("Request to search Centers for query {}", query);
//
//        return  citySearchRepository.searchSimilar(City.class ,queryStringQuery(query), pageable);
//    }


}
