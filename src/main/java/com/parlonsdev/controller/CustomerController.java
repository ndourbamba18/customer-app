package com.parlonsdev.controller;

import com.parlonsdev.dto.CustomerDto;
import com.parlonsdev.entities.Customer;
import com.parlonsdev.exception.ResourceNotFoundException;
import com.parlonsdev.message.ResponseMessage;
import com.parlonsdev.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1")
@CrossOrigin(origins = "http://localhost:8080")
public class CustomerController {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // GET ALL CUSTOMERS FROM DATABASE
    @GetMapping(path = "/customers")
    public ResponseEntity<List<Customer>> getAllCustomers(){
       try {
           List<Customer> customers = customerRepository.findAll();
           if(customers.isEmpty())
               return new ResponseEntity(new ResponseMessage("No Customers"), HttpStatus.NO_CONTENT);
           return new ResponseEntity<>(customers, HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity(new ResponseMessage("ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    // GET A SINGLE CUSTOMER BY ID FROM DATABASE
    @GetMapping(path = "/customers/{customerId}")
    public ResponseEntity<?> getCustomerById(@PathVariable("customerId") Long customerId){
       try {
           Customer customer = customerRepository.findById(customerId).
                   orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId));
           return new ResponseEntity<>(customer, HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>(new ResponseMessage("Error CustomerId : "+customerId), HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    // DELETE CUSTOMER BY ID FROM DATABASE
    @DeleteMapping(path = "/customers/{customerId}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable("customerId") Long customerId){
       try {
           Customer customer = customerRepository.findById(customerId).
                   orElseThrow(()-> new ResourceNotFoundException("Customer", "customerId", customerId));
           customerRepository.delete(customer);
           return new ResponseEntity<>(new ResponseMessage("Customer (id = "+customerId+") is deleted successfully!"), HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>(new ResponseMessage("Error CustomerId : "+customerId), HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    // CREATE NEW CUSTOMER
    @PostMapping(path = "/customers")
    public ResponseEntity<?> saveCustomer(@Valid @RequestBody CustomerDto customerDto){
        try {
            if(customerRepository.existsByEmail(customerDto.getEmail()))
                return new ResponseEntity<>(new ResponseMessage("Email customer already exists!"), HttpStatus.BAD_REQUEST);
            if (customerRepository.existsByPhone(customerDto.getPhone()))
                return new ResponseEntity<>(new ResponseMessage("Phone Number customer already exists!"), HttpStatus.BAD_REQUEST);

            customerDto.setCustomerCode(UUID.randomUUID().toString());
            Customer customer = new Customer(customerDto.getName(), customerDto.getEmail(), customerDto.getPhone(), customerDto.getCustomerCode(),
                    customerDto.getActive(), customerDto.getAddress());
            customerRepository.save(customer);
            return new ResponseEntity<>(new ResponseMessage("Customer ("+customer.getName()+") has bean created successfully!"), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage("ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // EDIT CUSTOMER BY ID
    @PutMapping(path = "/customers/{customerId}")
    public ResponseEntity<?> updateCustomerById(@PathVariable("customerId") Long customerId, @Valid @RequestBody CustomerDto customerDto){
        try {
            if (!customerRepository.existsById(customerId))
                return new ResponseEntity<>(new ResponseMessage("Customer id : "+customerId+" does not exists!"), HttpStatus.NOT_FOUND);
            if(customerRepository.existsByEmail(customerDto.getEmail()) && customerRepository.findByEmailContaining(customerDto.getEmail()).get().getId() != customerId)
                return new ResponseEntity<>(new ResponseMessage("Email customer already exists!"), HttpStatus.BAD_REQUEST);
            if (customerRepository.existsByPhone(customerDto.getPhone()) && customerRepository.findByPhoneContaining(customerDto.getPhone()).get().getId() != customerId)
                return new ResponseEntity<>(new ResponseMessage("Phone Number customer already exists!"), HttpStatus.BAD_REQUEST);

            Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new ResourceNotFoundException("Customer", "customerId", customerId));
            customer.setName(customerDto.getName());
            customer.setEmail(customerDto.getEmail());
            customer.setPhone(customerDto.getPhone());
            customer.setActive(customerDto.getActive());
            customer.setAddress(customerDto.getAddress());
            customerRepository.save(customer);
            return new ResponseEntity<>(new ResponseMessage("Customer ("+customer.getName()+") has bean updated successfully!"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage("ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
