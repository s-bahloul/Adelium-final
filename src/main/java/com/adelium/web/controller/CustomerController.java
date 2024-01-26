package com.adelium.web.controller;


import com.adelium.web.entity.Customer;
import com.adelium.web.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository repository;
    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewCustomer (@RequestParam String firstName
            , @RequestParam String lastName) {

        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Customer n = new Customer();
        n.setFirstName(firstName);
        n.setLastName(lastName);
        repository.save(n);
        return "Saved";

    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Customer> getAllCustomer() {
        // This returns a JSON or XML with the users
        return repository.findAll();
    }

}
