package com.mappingobjects.serviceImpl;

import com.mappingobjects.domain.dto.EmployeeDto;
import com.mappingobjects.parsers.ModelParser;
import com.mappingobjects.repository.EmployeeRepository;
import com.mappingobjects.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{


    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelParser modelParser;

    @Override
    public void create(EmployeeDto employeeDto) {

    }

    @Override
    public EmployeeDto findOne(long id) {
        return null;
    }
}
