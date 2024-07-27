package com.bhawna.SpringBoot.DataMapping.repositories;

import com.bhawna.SpringBoot.DataMapping.entities.DepartmentEntity;
import com.bhawna.SpringBoot.DataMapping.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
    DepartmentEntity findByManager(EmployeeEntity employeeEntity);
}
