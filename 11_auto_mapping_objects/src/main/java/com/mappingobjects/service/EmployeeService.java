package com.mappingobjects.service;

import com.mappingobjects.domain.dto.EmployeeDto;

public interface EmployeeService {

    void create(EmployeeDto employeeDto);

    EmployeeDto findOne(long id);

}
