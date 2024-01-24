package com.harshproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harshproject.entity.Department;

public interface Repository extends JpaRepository<Department, Long> {
    List<Department> findByName(String name);
}