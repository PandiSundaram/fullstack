package com.pandi.Fullstackapp.Service;

import com.pandi.Fullstackapp.Entities.EmployeeEntity;
import com.pandi.Fullstackapp.Models.Employee;
import com.pandi.Fullstackapp.Repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {


    @Autowired
    EmployeeRepository employeeRepository;
    public Employee saveEmployee(Employee employee){

        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee, employeeEntity);

        employeeRepository.save(employeeEntity);

        return employee;
    }

    public List<Employee> getEmployees(){
      List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();

      List<Employee> employeeList = employeeEntityList.stream().map(e -> new Employee(e.getId(),e.getName(),e.getEmail())).collect(Collectors.toList());

       return employeeList;
    }

    public Boolean deleteEmployee(Long id){

        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        employeeRepository.delete(employeeEntity);
        return true;
    }

    public Employee editEmployee(Long id, Employee employee){
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        employeeEntity.setEmail(employee.getEmail());
        employeeEntity.setName(employee.getName());
        employeeRepository.save(employeeEntity);
        return employee;
    }

    public Employee findEmployee(Long id){
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeEntity,employee);
        return employee;
    }


}
