package com.bankmicroservicesapp.repository;

import com.bankmicroservicesapp.entity.Agreement;
import com.bankmicroservicesapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    @Query(value = "select a from Agreement a where a.manager.id = :managerId")
    List<Agreement> findAgreementByManagerId(@Param("managerId") UUID managerId);
}
