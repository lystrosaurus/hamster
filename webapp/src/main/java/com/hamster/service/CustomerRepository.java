package com.hamster.service;

import com.hamster.entity.Customer;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by opabinia on 2017/5/14.
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {

  Customer findByFirstName(String firstName);

  List<Customer> findByLastName(String lastName);
}
