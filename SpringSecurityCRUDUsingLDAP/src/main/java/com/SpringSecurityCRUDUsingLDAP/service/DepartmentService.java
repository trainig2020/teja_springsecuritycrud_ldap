package com.SpringSecurityCRUDUsingLDAP.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.SpringSecurityCRUDUsingLDAP.model.Department;

@Service
public interface DepartmentService {

	
	public void insertDepartment(Department dept);
	public List<Department> getAllDepartments();
	public String updateDepartment(Department dept);
	public void deleteDepartment(int deptId);
	Department getDeptById(int deptId);


}
