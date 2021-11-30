package com.revature.shms.repositories;

import com.revature.shms.enums.EmployeeType;
import com.revature.shms.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    /**
     * List of all Employees ordered by Employee type.
     * @return List<Employee> ordered by Employee Type.
     */
    List<Employee> findAllByOrderEmployeeType();

    /**
     * List of all Employees with a specific job title.
     * @param employeeType enum job titles.
     * @return List<Employee> for specific job title.
     */
    List<Employee> findByEmployeeType(EmployeeType employeeType);

    /**
     * A singular Employee with the specified username.
     * @param username of Employee.
     * @return Employee with specified username.
     */
    Employee findByUsername(String username);
}
