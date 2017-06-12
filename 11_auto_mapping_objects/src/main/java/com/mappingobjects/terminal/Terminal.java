package com.mappingobjects.terminal;

import com.mappingobjects.domain.dto.EmployeeDto;
import com.mappingobjects.domain.entity.Address;
import com.mappingobjects.domain.entity.City;
import com.mappingobjects.domain.entity.Employee;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class Terminal implements CommandLineRunner{



    @Override
    public void run(String... strings) throws Exception {

        //Entity to DTO -------------------------------------------------------
        Employee employee = new Employee();
        employee.setFirstName("Johny");
        employee.setLastName("Bravo");
        employee.setSalary(new BigDecimal("100000"));
        employee.setBirthDate(new Date());

        Address address = new Address();
        City city = new City();
        city.setName("Plovdiv");
        address.setCity(city);
        address.setCountry("Bulgaria");

        employee.setAddress(address);

        //this is our model mapper
        ModelMapper modelMapper = new ModelMapper();

        // We want to match the field manually
        // Employee is the source, destination is EmployeeDto. Map is behind DTO (EmployeeDto), source -> Employee
        PropertyMap<Employee, EmployeeDto> propertyMap = new PropertyMap<Employee, EmployeeDto>() {
            @Override
            protected void configure() {
                //this is explicit mapping
                map().setFirstName(source.getFirstName());
                map().setLastName(source.getLastName());

                //for deep mapping - source -> Employee.class; destination -> EmployeeDto.class
                //we get the Address from Employee.class and from Address we get the city, that we map with the city in the
                //destination Dto property
                map(source.getAddress().getCity().getName(), destination.getAddressCityName());
            }
        };



        //here we convert our employee object to EmployeeDto.class
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        //we want to test whether the data from employee has been transferred correct to employeeDto
        System.out.println("Employee -> DTO " + employeeDto.toString());
        //Entity to DTO -------------------------------------------------------

        //DTO to Entity -------------------------------------------------------
        EmployeeDto employeeConvertDto = new EmployeeDto();
        employeeConvertDto.setFirstName("Petar");
        employeeConvertDto.setLastName("Petrov");
        employeeConvertDto.setSalary(new BigDecimal("10000"));
        employeeConvertDto.setAddressCityName("Haskovo");

        Employee employeeConverted = modelMapper.map(employeeConvertDto, Employee.class);
        System.out.println("DTO -> Employee " + employeeConverted.toString());
    }
}
