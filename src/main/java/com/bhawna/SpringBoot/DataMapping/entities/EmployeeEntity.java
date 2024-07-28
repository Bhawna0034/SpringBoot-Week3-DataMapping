package com.bhawna.SpringBoot.DataMapping.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @OneToOne(mappedBy = "manager")
    @JsonIgnore
    private DepartmentEntity managedDepartment;

    @ManyToOne(cascade = CascadeType.ALL)
   // @JoinColumn(name = "worker_department_id")
    @JoinTable(name = "worker_department_mapping")
    @JsonIgnore
    private DepartmentEntity workerDepartment;

    @ManyToMany
    @JoinTable(name = "freelancer_department_mapping",
    joinColumns = @JoinColumn(name = "employee_id"),
    inverseJoinColumns = @JoinColumn(name = "department_id")
    )
    @JsonIgnore
    private Set<DepartmentEntity> freelanceDepartments;
}
