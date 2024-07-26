package com.bhawna.SpringBoot.DataMapping.services;

import com.bhawna.SpringBoot.DataMapping.entities.DepartmentEntity;
import com.bhawna.SpringBoot.DataMapping.entities.EmployeeEntity;
import com.bhawna.SpringBoot.DataMapping.repositories.DepartmentRepository;
import com.bhawna.SpringBoot.DataMapping.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DepartmentService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }


    public DepartmentEntity getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId).orElse(null);
    }

    public DepartmentEntity createNewDepartment(DepartmentEntity departmentEntity) {
        return departmentRepository.save(departmentEntity);
    }

    public DepartmentEntity assignManagerToDepartment(DepartmentEntity departmentId, EmployeeEntity employeeId) {
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId.getId());
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId.getId());
        return departmentEntity.flatMap(department ->
            employeeEntity.map(employee-> {
                department.setManager(employee);
                return departmentRepository.save(department);
            } )).orElse(null);
    }
}
