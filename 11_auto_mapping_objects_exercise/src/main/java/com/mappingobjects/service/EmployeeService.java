package com.mappingobjects.service;

import com.mappingobjects.domain.dto.EmployeeDto;
import com.mappingobjects.domain.dto.EmployeeWithManagerDto;

import java.util.List;

public interface EmployeeService {

    void create(EmployeeDto employeeDto);

    List<EmployeeDto> findByName(String name);

    List<EmployeeWithManagerDto> findAllBefore1980();


}
