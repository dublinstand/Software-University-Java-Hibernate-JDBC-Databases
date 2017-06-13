package com.mappingobjects.service;

import com.mappingobjects.domain.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    void create(EmployeeDto employeeDto);

    List<EmployeeDto> findByName(String name);

}
