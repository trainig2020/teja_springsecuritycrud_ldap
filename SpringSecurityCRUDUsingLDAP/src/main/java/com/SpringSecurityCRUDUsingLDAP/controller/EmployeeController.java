package com.SpringSecurityCRUDUsingLDAP.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.SpringSecurityCRUDUsingLDAP.model.Department;
import com.SpringSecurityCRUDUsingLDAP.model.Employee;
import com.SpringSecurityCRUDUsingLDAP.service.DepartmentService;
import com.SpringSecurityCRUDUsingLDAP.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private DepartmentService departmentService;

	/*@RequestMapping(value = "listEmp", method = RequestMethod.GET)
	public ModelAndView listEmployee(ModelAndView modelAndView) throws IOException {
		int id = Integer.parseInt(request.getParameter("deptId"));
		
		//Department department = departmentService.getDeptById(id);
		List<Department> lst = departmentService.getAllDepartments();
		List<Employee> empLst =   employeeService.getEmployeesByDept(id);
		//modelAndView.addObject("department", department);
		
		modelAndView.addObject("deptLst", lst);
		modelAndView.addObject("empLst", empLst);
		modelAndView.addObject("home", "homemp");
		modelAndView.addObject("check", "checklist");
		modelAndView.setViewName("form");
		
		return modelAndView;
	}*/

	@RequestMapping(value = "newEmp", method = RequestMethod.GET)
	public ModelAndView showFormForAdd(HttpServletRequest request,ModelAndView modelAndView) {
		
		
		
		String Register  = "NewForm";
		HttpSession session1 = request.getSession();
		List<Employee> lst =(List<Employee>)session1.getAttribute("empLst");
		List<Department> deplst = departmentService.getAllDepartments();
		modelAndView.addObject("deptLst", deplst);
		ModelAndView model = new ModelAndView("form");
		model.addObject("empLst", lst);
		model.addObject("Register", Register);
		model.addObject("addEmp", "regEmp");
		model.addObject("home", "homemp");	
		return model;	
	}

	@RequestMapping(value = "saveEmp",method = RequestMethod.POST)
	public ModelAndView saveEmployee( HttpServletRequest request,@ModelAttribute("employee") Employee employee) {
		//String deptName =request.getParameter("deptName");
		//List<Department> lst = departmentService.getAllDepartments();
		int deptId =  Integer.parseInt(request.getParameter("deptId"));
		Employee employee1 = new Employee();
		employee1.setEmpId(employee.getEmpId());
		employee1.setEmpName(employee.getEmpName());
		employee1.setAge(employee.getAge());
		employee1.setDeptId(employee.getDeptId());
		
		
		employeeService.insertEmployee(employee1);

		return new ModelAndView("redirect:/listDeptName?deptId="+deptId);
	}

	@RequestMapping(value = "/editEmp", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
		int employeeId = Integer.parseInt(request.getParameter("empId"));
		int did =  Integer.parseInt(request.getParameter("deptId"));
		HttpSession session2 = request.getSession();
		List<Employee> lst =(List<Employee>) session2.getAttribute("empLst");
		session2.setAttribute("empLst", lst);
		ModelAndView model = new ModelAndView("form");
		model.addObject("home", "homemp");
		model.addObject("empLst", lst);
		model.addObject("employeeid", employeeId);
		model.addObject("deptId", did);
		return model;
	}

	@RequestMapping(value = "/updateEmp", method = RequestMethod.POST)
	public ModelAndView updateEmployee(HttpServletRequest request, @ModelAttribute Employee employee) {
		//String deptName =request.getParameter("deptName");
		//List<Department> lst = departmentService.getAllDepartments();
		int deptId =  Integer.parseInt(request.getParameter("deptId"));
		
		  Employee employee1 = new Employee();
		  employee1.setEmpId(employee.getEmpId());
		  employee1.setEmpName(employee.getEmpName());
		  employee1.setAge(employee.getAge());
		  employee1.setDeptId(employee.getDeptId());
		 
		employeeService.updateEmployee(employee1);

		return new ModelAndView("redirect:/listDeptName?deptId="+deptId);

	}

	@RequestMapping(value = "/deleteEmp", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(HttpServletRequest request) {
		int employeeId = Integer.parseInt(request.getParameter("empId"));
		int deptId = Integer.parseInt(request.getParameter("deptId"));
		employeeService.deleteEmployee(employeeId);
		return new ModelAndView("redirect:/listDeptName?deptId="+deptId);
	}

}
