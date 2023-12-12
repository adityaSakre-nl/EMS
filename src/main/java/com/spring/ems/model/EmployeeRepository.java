package com.spring.ems.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface EmployeeRepository extends MongoRepository<Employee, ObjectId> {

    //Query to get Employee by Email
    @Query("{email: \"?0\"}")
    List<Employee> getUserByEmail(String email);


    //Query to get Employee by Employee id
    @Query("{employeeId: \"?0\"}")
    List<Employee> getUserByEmpId(String employeeId);

}
