package com.spring.jdbc;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employees")
    @ResponseBody
    public int createEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @PutMapping("/employees/{id}")
    @ResponseBody
    public int updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        employee.setId(id);
        return employeeService.update(employee);
    }

    @DeleteMapping("/employees/{id}")
    @ResponseBody
    public int deleteEmployee(@PathVariable int id) {
        return employeeService.delete(id);
    }

    @GetMapping("/employees/{id}")
    @ResponseBody
    public Employee getEmployeeById(@PathVariable int id) {
        return employeeService.findById(id);
    }

    @GetMapping("/employees")
    @ResponseBody
    public List<Employee> getAllEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/")
    public String index() {
        return "index.html";
    }
}
