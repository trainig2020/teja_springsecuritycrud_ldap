package com.SpringSecurityCRUDUsingLDAP.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringSecurityCRUDUsingLDAP.dao.DepartmentDao;
import com.SpringSecurityCRUDUsingLDAP.dao.DepartmentDaoImpl;
import com.SpringSecurityCRUDUsingLDAP.model.Department;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService{
	@Autowired
	DepartmentDao dao = new DepartmentDaoImpl();

	

	public void insertDepartment(Department dept) {
		 dao.insertDepartment(dept);
	}

	public List<Department> getAllDepartments() {
		
		return dao.getAllDepartments();
	}

	public String updateDepartment(Department dept) {
		return dao.updateDepartment(dept);
	}

	public void deleteDepartment(int deptId) {
		 dao.deleteDepartment(deptId);
	}

	public Department getDeptById(int deptId) {
		
		return dao.getDeptById(deptId);
	}

}
