package com.spring.hazelcast.resource;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.hazelcast.model.Employee;
import com.spring.hazelcast.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeResource {
	
	@Autowired
	private EmployeeService employeeService;


	@GetMapping("/id/{employeeId}")
	public Employee getEmployee(@PathVariable String employeeId) throws Exception {
		return employeeService.getEmployee(employeeId);
	}
	
	@GetMapping("/all")
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployees();
	}
}
