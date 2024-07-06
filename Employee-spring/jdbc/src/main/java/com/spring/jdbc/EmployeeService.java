package com.spring.jdbc;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public int save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public int update(Employee employee) {
        return employeeRepository.update(employee);
    }

    public int delete(int id) {
        return employeeRepository.delete(id);
    }

    public Employee findById(int id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
}
