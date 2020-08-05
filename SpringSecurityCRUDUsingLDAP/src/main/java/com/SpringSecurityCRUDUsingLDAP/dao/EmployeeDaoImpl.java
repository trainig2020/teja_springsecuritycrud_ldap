package com.SpringSecurityCRUDUsingLDAP.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.SpringSecurityCRUDUsingLDAP.model.Employee;

@Repository
@Transactional
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void insertEmployee(Employee emp) {

		Transaction transaction = null;
		try {
			Session session = sessionFactory.openSession();

			transaction = session.beginTransaction();
			session.save(emp);
			System.out.println("Datas Inserted");
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public Employee updateEmployee(Employee emp) {

		Transaction transaction = null;
		try {
			Session session = sessionFactory.openSession();

			transaction = session.beginTransaction();
			session.update(emp);
			
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return emp;


	}

	@SuppressWarnings("unchecked")
	public List<Employee> getEmployeesByDept(int deptId) {

		List<Employee> lst =  sessionFactory.getCurrentSession().createQuery("from Employee where deptId="+deptId).list();
		for (Employee emp : lst) {
			System.out.println("EmpID is : " + emp.getEmpId() + " EmpName is :" + emp.getEmpName());
		}
		return lst;

	}

	public void deleteEmployee(int empId) {

		Session session = sessionFactory.getCurrentSession();

		Employee emp1 = (Employee) session.get(Employee.class, empId);

		session.delete(emp1);

	}

	@SuppressWarnings("unchecked")
	public List<Employee> getAllEmployees() {
		// List<Employee> lstOfEmp = null;
		Session session = sessionFactory.getCurrentSession();
		// session.beginTransaction();

		List<Employee> lstOfEmp = session.createQuery("from Employee").list();

		for (Employee emp : lstOfEmp) {
			System.out.println("EmpID is : " + emp.getEmpId() + " EmpName is :" + emp.getEmpName());
		}

		return lstOfEmp;

	}

	public Employee getEmployees(int id) {
		Employee emp_det = null;
		Session session = sessionFactory.getCurrentSession();
		// session.beginTransaction();

		emp_det = (Employee) session.get(Employee.class, id);
		
		 

		System.out.println("EmpID  : " + emp_det.getEmpId() + " EmpName  :" + emp_det.getEmpName() + " Age  : "
				+ emp_det.getAge() + " DeptId  : " + emp_det.getDeptId());

		return emp_det;
	}

}
