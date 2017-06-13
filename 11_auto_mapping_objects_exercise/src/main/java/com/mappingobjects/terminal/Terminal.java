package com.mappingobjects.terminal;

import com.mappingobjects.domain.dto.EmployeeDto;
import com.mappingobjects.domain.dto.EmployeeWithManagerDto;
import com.mappingobjects.domain.dto.ManagerDto;
import com.mappingobjects.domain.entity.Employee;
import com.mappingobjects.io.Writer;
import com.mappingobjects.parsers.ModelParser;
import com.mappingobjects.service.EmployeeService;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class Terminal implements CommandLineRunner {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ModelParser modelParser;

    @Autowired
    @Qualifier(value = "ConsoleWriter")
    private Writer consoleWriter;

    @Autowired
    @Qualifier(value = "JsonWriter")
    private Writer jsonWriter;

    @Override
    public void run(String... strings) throws Exception {
        this.convertEmployeeToEmployeeDto();
        this.convertEmployeeToManagerDto();
        this.createEmployee();
        this.printEmployeesDtoBefore1980();
    }

    private void convertEmployeeToEmployeeDto() {
        Employee employee = new Employee();
        employee.setFirstName("Johny");
        employee.setLastName("Bravo");
        employee.setSalary(new BigDecimal("100000"));
        employee.setAddress("Haskovo");
        //convert the employee to DTO
        EmployeeDto employeeDto = this.modelParser.convert(employee, EmployeeDto.class);
    }

    private void convertEmployeeToManagerDto() {
        Employee employeeOne = new Employee();
        employeeOne.setFirstName("Johny");
        employeeOne.setLastName("Bravo");
        employeeOne.setSalary(new BigDecimal("100000"));
        employeeOne.setAddress("Haskovo");

        Employee employeeTwo = new Employee();
        employeeTwo.setFirstName("Kevin");
        employeeTwo.setLastName("Spacey");
        employeeTwo.setSalary(new BigDecimal("88888"));
        employeeTwo.setAddress("Mineralni Bani");

        Employee employeeManager = new Employee();
        employeeManager.setFirstName("Dido");
        employeeManager.setLastName("Di");
        employeeManager.addEmployee(employeeOne);
        employeeManager.addEmployee(employeeTwo);

//        //we can the simple mapping by having Set<EmployeeDto> managerEmployees in ManagerDto (if the name was different than managerEmployees
//        //we need to use PropertyMapper
//        ManagerDto managerDto = this.modelParser.convert(employeeManager, ManagerDto.class);

        //using property map
        PropertyMap<Employee, ManagerDto> propertyMap = new PropertyMap<Employee, ManagerDto>() {
            @Override
            protected void configure() {
                //we have changed the name to private Set<EmployeeDto> employeesDtos
                //now we map from Source Employee managerEmployees to Destination ManagerDto employeeDtos
                map(source.getManagerEmployees(), destination.getEmployeesDtos());
            }
        };
        //we need to pass the propertyMap to the method overload
        ManagerDto managerDto = this.modelParser.convert(employeeManager, ManagerDto.class, propertyMap);
    }

    private void createEmployee(){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("John");
        employeeDto.setLastName("Jovi");
        employeeDto.setSalary(new BigDecimal("88888"));

        //in our service implementation we accept DTO, then convert it to entity and then store it
        this.employeeService.create(employeeDto);
    }

    private void printEmployeesDtoBefore1980(){
        List<EmployeeWithManagerDto> employeeWithManagerDtos =
                this.employeeService.findAllBefore1980();

        for (EmployeeWithManagerDto employeeWithManagerDto : employeeWithManagerDtos) {
            this.consoleWriter.write(employeeWithManagerDto.toString());
        }
    }
}
