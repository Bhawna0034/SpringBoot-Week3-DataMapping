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

    public DepartmentEntity assignManagerToDepartment(Long departmentId, Long employeeId) {
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);
        return departmentEntity.flatMap(department ->
            employeeEntity.map(employee-> {
                department.setManager(employee);
                return departmentRepository.save(department);
            } )).orElse(null);
    }

    public DepartmentEntity getAssignedDepartmentOfManager(Long employeeId){
      //  Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);
      //  return employeeEntity.map(employee -> employee.getManagedDepartment()).orElse(null);

        EmployeeEntity employeeEntity = EmployeeEntity.builder().id(employeeId).build();
        return departmentRepository.findByManager(employeeEntity);
        }

    public DepartmentEntity assignWorkerToDepartment(Long  departmentId, Long employeeId) {
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);
        return departmentEntity.flatMap(department ->
                employeeEntity.map(employee-> {
                    employee.setWorkerDepartment(department);
                    employeeRepository.save(employee);

                    department.getWorkers().add(employee);
                    return department;
                } )).orElse(null);

    }

    public DepartmentEntity assignFreelancerToDepartment(Long departmentId, Long employeeId) {
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);
        return departmentEntity.flatMap(department ->
                employeeEntity.map(employee-> {
                    employee.getFreelanceDepartments().add(department);
                    employeeRepository.save(employee);
                    department.getFreelancers().add(employee);
                    return department;
                } )).orElse(null);
    }
}

