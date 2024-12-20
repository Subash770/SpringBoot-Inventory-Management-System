package com.example.laptop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.laptop.entity.Laptop;
public interface LaptopRepository extends JpaRepository<Laptop, Integer>{
	
}