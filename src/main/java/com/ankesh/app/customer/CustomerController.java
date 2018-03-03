package com.ankesh.app.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")

public class CustomerController {
	
	@Autowired
	private CustomerRepository repository;
	
	
		 @RequestMapping(method = RequestMethod.GET)
		 @ResponseBody
		 
		    public ResponseEntity<List<Customer>> getAll() {
			 
			 List<Customer> customers = repository.findAll();
		        return new ResponseEntity(customers,customers.size() !=0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
		    }

		 @RequestMapping(method = RequestMethod.GET, value= "/{id}")
		    public ResponseEntity<Customer> get(@PathVariable String id) {
			 	Customer customer1 = repository.findOne(id);
		        return new ResponseEntity(customer1, customer1 != null ? HttpStatus.OK : HttpStatus.NOT_FOUND) ;
		    }
		 
		 @RequestMapping(method = RequestMethod.POST, produces = {"application/json"})
		    @ResponseBody
		    public ResponseEntity<Customer> create(@RequestBody Customer customer){
		        Customer savedcustomer = repository.save(customer);
		        return new ResponseEntity(customer, HttpStatus.OK);
		    }

		    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json", consumes = {"application/json"}, produces = {"application/json"})
		    public ResponseEntity<Customer> update(@PathVariable String id, @RequestBody Customer customer){
		        customer.setId(id);
		        Customer savedcustomer = repository.save(customer);
		        return new ResponseEntity(savedcustomer, HttpStatus.OK);
		    }

		    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json", produces = {"application/json"})
		    @ResponseBody
		    public ResponseEntity<Boolean> delete(@PathVariable String id){
		        try {
		            repository.delete(id);
		            return new ResponseEntity(Boolean.TRUE, HttpStatus.OK);
		        }
		        catch (Exception ex){
		            return new ResponseEntity(Boolean.FALSE, HttpStatus.NOT_FOUND);
		        }
		    }

}
