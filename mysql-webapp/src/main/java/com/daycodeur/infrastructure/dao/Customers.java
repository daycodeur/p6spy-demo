package com.daycodeur.infrastructure.dao;

import com.daycodeur.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface Customers extends CrudRepository<Customer, Long> {

  Optional<Customer> findById(Long id);

  List<Customer> findAll();

  long count();

  Customer save(Customer customer);

  void deleteById(Long id);

  void deleteAll();

}
