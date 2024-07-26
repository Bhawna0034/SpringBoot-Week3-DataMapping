package com.bhawna.SpringBoot.DataMapping.repositories;

import com.bhawna.SpringBoot.DataMapping.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
