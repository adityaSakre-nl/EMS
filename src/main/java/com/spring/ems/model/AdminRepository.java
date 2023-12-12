package com.spring.ems.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AdminRepository extends MongoRepository<Admin, ObjectId> {

    //Query to get Administrator by Email
    @Query("{email: \"?0\"}")
    List<Admin> getUserByEmail(String email);
}
