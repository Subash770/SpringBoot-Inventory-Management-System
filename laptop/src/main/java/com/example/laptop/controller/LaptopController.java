package com.example.laptop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.laptop.entity.Laptop;
import com.example.laptop.service.LaptopService;

@RestController
@CrossOrigin
public class LaptopController {

	@Autowired
	private LaptopService laptopService;

	@GetMapping("/")
	public ResponseEntity<String> getMessage(){

		return new ResponseEntity<>("Hello World!!!", HttpStatus.OK);
	}

	@GetMapping("/laptops")
	public ResponseEntity<List<Laptop>> listLaptops() {

		List<Laptop> allLaptops = laptopService.getAllLaptops();

		if (allLaptops.isEmpty()) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
		else {

			return new ResponseEntity<>(allLaptops, HttpStatus.OK);

		}
	}
	

	@PostMapping("/laptops")
    public ResponseEntity<Laptop> addLaptop(@RequestBody Laptop laptop) {
        try {
            Laptop savedLaptop = laptopService.saveLaptop(laptop);
            return new ResponseEntity<>(savedLaptop, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/laptops/{laptopId}")
    public ResponseEntity<HttpStatus> deleteLaptop(@PathVariable Integer laptopId) {
        try {
            String response = laptopService.deleteLaptopById(laptopId);
            if ("The requested id got deleted".equals(response)) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}