package com.daycodeur.controller;

import com.daycodeur.infrastructure.dao.Customers;
import com.daycodeur.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("customers")
public class UserController {

  private Customers customers;

  @Autowired
  public UserController(final Customers customers) {
    this.customers = customers;
  }

  @GetMapping
  public List<Customer> getAllCustomers() {
    return customers.findAll();
  }

  @GetMapping("/{customerId}")
  public Customer getCustomerFromId(@PathVariable Long customerId) {
    Optional<Customer> optionalCustomer = customers.findById(customerId);
    return optionalCustomer.isPresent() ? optionalCustomer.get() : null;
  }

  @GetMapping("/count")
  public Long getNumberOfCustomers() {
    return customers.count();
  }

  @PostMapping
  public Customer createNewCustomer(@RequestBody Customer customer) {
    return customers.save(customer);
  }

  @DeleteMapping("/{customerId}")
  public void deleteCustomerFromId(@PathVariable Long customerId) {
    customers.deleteById(customerId);
  }

  @DeleteMapping
  public void deleteAll() {
    customers.deleteAll();
  }

}
