package com.microservice.employee.repository.elasticsearch;


import com.microservice.employee.model.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeSearchRepository extends ElasticsearchRepository<Employee, Long> {


}
