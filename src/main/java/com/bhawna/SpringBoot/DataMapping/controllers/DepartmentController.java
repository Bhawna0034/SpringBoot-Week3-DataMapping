package com.bhawna.SpringBoot.DataMapping.controllers;

import com.bhawna.SpringBoot.DataMapping.entities.DepartmentEntity;
import com.bhawna.SpringBoot.DataMapping.entities.EmployeeEntity;
import com.bhawna.SpringBoot.DataMapping.services.DepartmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/{departmentId}")
    public DepartmentEntity getDepartmentById(@PathVariable Long departmentId){
        return departmentService.getDepartmentById(departmentId);
    }

    @PostMapping
    public DepartmentEntity createNewDepartment(@RequestBody DepartmentEntity departmentEntity){
        return departmentService.createNewDepartment(departmentEntity);
    }

    @PutMapping(path = "/{departmentId}/manager/{employeeId}")
    public DepartmentEntity assignManagerToDepartment(@PathVariable DepartmentEntity departmentId,
                                                      @PathVariable EmployeeEntity employeeId){
        return departmentService.assignManagerToDepartment(departmentId, employeeId);
    }
}
