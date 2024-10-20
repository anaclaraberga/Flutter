package com.example.web_inventory.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.web_inventory.dtos.request.CustomerRequestDTO;
import com.example.web_inventory.dtos.response.CustomerResponseDTO;
import com.example.web_inventory.entities.CustomerEntity;
import com.example.web_inventory.services.CustomerService;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/customer")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    public CustomerController(CustomerService customerService) {

        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody CustomerRequestDTO dto) {

        CustomerEntity entity = customerService.createCustomer(dto);

        return ResponseEntity.status(201).body(new CustomerResponseDTO(entity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerEntity> findCustomerById(@PathVariable Long id) {

        CustomerEntity entity = this.customerService.findCustomerById(id);

        return ResponseEntity.ok(entity);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerEntity> getAllCustomers() {

        List<CustomerEntity> entity = customerService.getAllCustomers();
        
        return entity;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CustomerEntity> updateCustomerById(@PathVariable(value = "id") Long id, @RequestBody CustomerRequestDTO dto) {

        return customerService.updateCustomerById(dto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCustomerById(@PathVariable("id") Long id) {

        return customerService.deleteCustomerById(id);
    }

}