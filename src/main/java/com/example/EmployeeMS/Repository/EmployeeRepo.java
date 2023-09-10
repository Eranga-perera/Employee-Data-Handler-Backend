package com.example.EmployeeMS.Repository;

import com.example.EmployeeMS.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee,Integer> {



}
