package com.SpringSecurityCRUDUsingLDAP.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.SpringSecurityCRUDUsingLDAP.model.Employee;


@Service
public interface EmployeeService {

	
	

	public void insertEmployee(Employee emp);

	public Employee updateEmployee(Employee emp);

	public void deleteEmployee(int id);

	public Employee getEmployees(int empId);

	public List<Employee> getAllEmployees();
	public List<Employee> getEmployeesByDept(int deptId);

}
