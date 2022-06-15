package com.example.practiceclientweb.controller;

import com.example.practiceclientweb.entity.Employee;
import com.example.practiceclientweb.retrofit.RetrofitGenerator;
import com.example.practiceclientweb.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class CreateEmployeeController extends HttpServlet {
    private static Employee obj;

    private final EmployeeService employeeService;

    public CreateEmployeeController() {
        employeeService = RetrofitGenerator.createService(EmployeeService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        obj = new Employee("Employee new", new BigDecimal(1000));
        req.setAttribute("employee", obj);
        req.getRequestDispatcher("/pages/employee/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        obj.setName(req.getParameter("name"));
        obj.setSalary(new BigDecimal (req.getParameter("salary")));

        employeeService.addEmployees(obj).execute();
        resp.sendRedirect("/employees");
    }
}
