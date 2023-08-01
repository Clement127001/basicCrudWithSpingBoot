package com.example.proj1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

import com.example.proj1.Repository.EmpRepository;
import java.util.List;

import com.example.proj1.Model.Employee;

@RestController
@RequestMapping("/api")
public class EmpController {

    @Autowired
    EmpRepository empRepository;

    @GetMapping("/show_all")
    public List<Employee> getDetails() {

        return (List<Employee>) empRepository.findAll();
    }

    @GetMapping("/show_user/{id}")
    public ResponseEntity<Employee> getUserById(@PathVariable("id") Long id) {

        Optional<Employee> emp2 = empRepository.findById(id);

        if (emp2.isPresent()) {
            Employee emp3 = emp2.get();
            return new ResponseEntity<Employee>(emp3, HttpStatus.OK);
        }

        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create_user")

    public ResponseEntity<Employee> createuser(@RequestBody Employee emp) {
        Employee emp2 = empRepository
                .save(new Employee(emp.getId(), emp.getEmpName(), emp.getAge(), emp.getDesignation(),
                        emp.getExperience(), emp.getSalary(), emp.getManagerId()));

        return new ResponseEntity<Employee>(emp2, HttpStatus.OK);
    }

    @DeleteMapping("/delete_user")

    public ResponseEntity<HttpStatus> deleteAllUser() {
        empRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
