package com.spring.hazelcast.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.spring.hazelcast.model.Employee;
import com.spring.hazelcast.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Cacheable("employee")
	public Employee getEmployee(final String employeeId) throws Exception {
		System.out.println("Calling DB====================>>>>>");
		return employeeRepository.findById(employeeId).orElseThrow(()->new Exception("Employee not found for ID"+employeeId));
	}
	
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}

}
