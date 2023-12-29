package com.pandi.Fullstackapp.Controller;


import com.pandi.Fullstackapp.Models.Employee;
import com.pandi.Fullstackapp.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

@CrossOrigin("http://localhost:3000")
public class EmployeeController {


    @Autowired
    EmployeeService employeeService;

    @PostMapping("/employee")
    public ResponseEntity addEmployee(@RequestBody Employee employee){
        return new ResponseEntity( employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }


    @GetMapping("/employee")
    public List<Employee> getEmployees(){

        return employeeService.getEmployees();
    }


    @DeleteMapping("/employee/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Long id){

       Boolean status = employeeService.deleteEmployee(id);

       return ResponseEntity.ok(status);

    }

    @PutMapping("/employee/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Long id,@RequestBody Employee employee){
        return ResponseEntity.ok(employeeService.editEmployee(id,employee));

    }

    @GetMapping("/employee/{id}")
    public ResponseEntity getEmployee(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.findEmployee(id));

    }


}
