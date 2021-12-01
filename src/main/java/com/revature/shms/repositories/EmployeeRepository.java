package com.revature.shms.repositories;

import com.revature.shms.enums.EmployeeType;
import com.revature.shms.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    List<Employee> findAllByOrderByEmployeeType();

    List<Employee> findByEmployeeType(EmployeeType employeeType);

    Employee findByUserName(String username);

    Employee findByEmployeeID(int employeeID);
}
