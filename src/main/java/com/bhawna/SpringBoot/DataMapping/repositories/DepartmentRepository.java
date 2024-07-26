package com.bhawna.SpringBoot.DataMapping.repositories;

import com.bhawna.SpringBoot.DataMapping.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
}
