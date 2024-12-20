package com.example.laptop.service;

import java.lang.reflect.Field;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import com.example.laptop.entity.Laptop;
import com.example.laptop.repository.*;

@Service
public class LaptopService{
	
	@Autowired
	private LaptopRepository laptopRepository;

	public List<Laptop> getAllLaptops() {
	
		return laptopRepository.findAll();
	}

   
	public Laptop saveLaptop(Laptop laptop) {
        if (laptop.getLaptopBrand() == null || laptop.getLaptopBrand().isEmpty()
                || laptop.getModelName() == null || laptop.getModelName().isEmpty()
                || laptop.getLaptopTag() == null || laptop.getLaptopTag().isEmpty()) {
            throw new IllegalArgumentException("Laptop details cannot be null or empty");
        }
        return laptopRepository.save(laptop);
    }

    public String deleteLaptopById(Integer id) {
        if (laptopRepository.existsById(id)) {
            laptopRepository.deleteById(id);
            return "The requested id got deleted";
        } else {
            throw new IllegalArgumentException("Laptop with the given id does not exist");
        }
    }
}
