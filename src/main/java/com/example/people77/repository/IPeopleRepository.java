package com.example.people77.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.people77.model.People;

//Create de Repository JPA (IpeopleRepository) whit People's model

public interface IPeopleRepository extends JpaRepository<People, Integer> {

}