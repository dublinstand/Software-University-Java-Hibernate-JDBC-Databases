package com.mappingobjects.serviceImpl;

import com.mappingobjects.domain.dto.EmployeeDto;
import com.mappingobjects.domain.entity.Employee;
import com.mappingobjects.parsers.ModelParser;
import com.mappingobjects.repository.EmployeeRepository;
import com.mappingobjects.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelParser modelParser;

    @Override
    public void create(EmployeeDto employeeDto) {
        //converting employeeDto to Employee.class
        Employee employee = this.modelParser.convert(employeeDto, Employee.class);
        employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeDto> findByName(String name) {
        List<Employee> employees = employeeRepository.findByFirstName(name);
        List<EmployeeDto> employeeDtos = this.modelParser.convert(employees, EmployeeDto.class);
        return employeeDtos;
    }
}
