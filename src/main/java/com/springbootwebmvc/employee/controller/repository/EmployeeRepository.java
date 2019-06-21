package com.springbootwebmvc.employee.controller.repository;

import com.springbootwebmvc.employee.controller.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/*
/* Extends JpaRepository which has provide all basic crud methods to entiry Employee table. */


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    /* We add a custom method here but do not use it in this example.
     * The method name must follow findBy<column name> pattern, then JpaRepository
     * will implement the detail jdbc query code which we do not need to care, we just
     * need to call this method and pass in the userName to get result list.
     **/
    List<Employee> findByUserName(String userName);
}