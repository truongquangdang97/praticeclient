package com.example.practiceclientweb.controller;

import com.example.practiceclientweb.entity.Employee;
import com.example.practiceclientweb.retrofit.RetrofitGenerator;
import com.example.practiceclientweb.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetEmployeeController extends HttpServlet {
    private final EmployeeService employeeService;

    public GetEmployeeController() {
        employeeService = RetrofitGenerator.createService(EmployeeService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> employees = employeeService.getEmployees().execute().body();
        req.setAttribute("listEmployees", employees);
        req.getRequestDispatcher("/pages/employee/list.jsp").forward(req, resp);
    }
}
