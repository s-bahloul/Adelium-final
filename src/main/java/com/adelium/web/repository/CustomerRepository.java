package com.adelium.web.repository;

import com.adelium.web.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerRepository extends CrudRepository<Customer , Long> {
    List<Customer> findByLastName(String lastName);

    Customer findById(long id);
}
