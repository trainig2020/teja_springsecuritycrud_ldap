package com.SpringSecurityCRUDUsingLDAP.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
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
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService; 
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/")
	public ModelAndView loginPage() {
		return new ModelAndView("redirect:/listDept");

	}
	@RequestMapping("/login")
	public ModelAndView loginPage1() {
		return new ModelAndView("redirect:/listDept");

	}

	/*
	 * @RequestMapping("deptControl") public String deptControl(HttpServletRequest
	 * request){ List<Department> lst = departmentService.getAllDepartments();
	 * request.setAttribute("deptLst", lst); request.setAttribute("hoser",
	 * "Civil"); return "form"; }
	 */

	@RequestMapping("/home")
	public ModelAndView listDeptId(HttpServletRequest request) {
		List<Department> lst = departmentService.getAllDepartments();
		int deptId = lst.get(0).getDeptId();
		return new ModelAndView("redirect:/listDeptName?deptId=" + deptId);

	}

	@RequestMapping("/listDeptName")
	public ModelAndView listDeptName(HttpServletRequest request, ModelAndView modelAndView) {
		int id = Integer.parseInt(request.getParameter("deptId"));

		// Department department = departmentService.getDeptById(id);
		List<Department> lst = departmentService.getAllDepartments();
		List<Employee> empLst = employeeService.getEmployeesByDept(id);
		// modelAndView.addObject("department", department);
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("empLst", empLst);
		modelAndView.addObject("deptLst", lst);
		modelAndView.addObject("empLst", empLst);
		modelAndView.addObject("home", "homemp");
		modelAndView.addObject("check", "checklist");
		modelAndView.setViewName("form");
		modelAndView.addObject("loggedInUser", httpSession.getAttribute("loggedInUser"));

		return modelAndView;

	}

	@RequestMapping(value = "/listDept")
	public ModelAndView listDepartment(HttpServletRequest request) {

		List<Department> lst = departmentService.getAllDepartments();
		ModelAndView modelAndView = new ModelAndView("form");
		modelAndView.addObject("deptList", lst);

		HttpSession session = request.getSession();
		session.setAttribute("hoser", "hseval");
		session.setAttribute("deptList", lst);
		UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder
				.getContext().getAuthentication();

		String loggedInUser = authentication.getName();
		System.out.println("User name in controller " + loggedInUser);
		session.setAttribute("loggedInUser", loggedInUser);
		modelAndView.addObject("loggedInUser", loggedInUser);
		return modelAndView;
	}

	@RequestMapping(value = "/newDept", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model, HttpServletRequest request) {
		String Register = "NewFormDept";
		HttpSession session1 = request.getSession();
		List<Department> lst = (List<Department>) session1.getAttribute("deptList");
		session1.setAttribute("deptList", lst);
		model.addObject("Register", Register);
		model.addObject("insertDept", "newDept");
		model.setViewName("form");
		Department department = new Department();
		model.addObject("department", department);
		session1.setAttribute("hoser", "hseval");
		model.addObject("loggedInUser", session1.getAttribute("loggedInUser"));
		return model;
	}
	
	@RequestMapping(value = "/saveDept", method = RequestMethod.POST)
	public ModelAndView saveDepartment1(@ModelAttribute Department department,HttpServletRequest request) {
		System.out.println("In before");
		ModelAndView model = new ModelAndView("form");
		HttpSession session1 = request.getSession();
		Department department1 = new Department();
		//department1.setDeptId(department.getDeptId());
		department1.setDeptName(department.getDeptName());
		departmentService.insertDepartment(department1);
		model.addObject("loggedInUser", session1.getAttribute("loggedInUser"));
		System.out.println("In save department");
		return new ModelAndView("redirect:/listDept");
		
		
	}

	/*
	 * @RequestMapping(value = "/saveDept1", method = RequestMethod.POST) public
	 * ModelAndView saveDepartment(@ModelAttribute Department department,
	 * BindingResult result,HttpServletRequest request) { if (result.hasErrors()) {
	 * ModelAndView mv = new ModelAndView("form"); return mv; } else { ModelAndView
	 * model = new ModelAndView("form"); HttpSession session1 =
	 * request.getSession(); Department department1 = new Department();
	 * department1.setDeptId(department.getDeptId());
	 * department1.setDeptName(department.getDeptName());
	 * departmentService.insertDepartment(department1);
	 * model.addObject("loggedInUser", session1.getAttribute("loggedInUser"));
	 * return new ModelAndView("redirect:/listDept"); } }
	 */

	@RequestMapping(value = "/deleteDept", method = RequestMethod.GET)
	public ModelAndView deleteDepartment(HttpServletRequest request) {
		int departId = Integer.parseInt(request.getParameter("id"));
		departmentService.deleteDepartment(departId);
		return new ModelAndView("redirect:/listDept");
	}
	

	@RequestMapping(value = "/editDept", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
		int departId = Integer.parseInt(request.getParameter("id"));
		HttpSession session2 = request.getSession();
		Department department = departmentService.getDeptById(departId);
		session2.setAttribute("department", department);
		List<Department> lst = (List<Department>) session2.getAttribute("deptList");
		session2.setAttribute("deptList", lst);
		ModelAndView model = new ModelAndView("form");
		model.addObject("deptList", lst);
		model.addObject("departId", departId);
		model.addObject("loggedInUser", session2.getAttribute("loggedInUser"));
		return model;
	}
	
	@RequestMapping(value = "/updateDept", method = RequestMethod.POST)
	public ModelAndView updateEmployee(HttpServletRequest request, @ModelAttribute Department department) {
		//String deptName =request.getParameter("deptName");
		//List<Department> lst = departmentService.getAllDepartments();
		//int deptId =  Integer.parseInt(request.getParameter("deptId"));
		
		  Department department1 = new Department();
		  department1.setDeptId(department.getDeptId());
		  department1.setDeptName(department.getDeptName());
		 
		departmentService.updateDepartment(department1);

		return new ModelAndView("redirect:/listDept");

	}
}
