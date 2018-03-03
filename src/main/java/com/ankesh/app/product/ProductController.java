package com.ankesh.app.product;

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
@RequestMapping("/api/product")

public class ProductController {
	
	@Autowired
	private ProductRepository repository;
	
	
	
		 @RequestMapping(method = RequestMethod.GET)
		 @ResponseBody
		 
		    public ResponseEntity<List<Product>> getAll() {
			 
			 List<Product> products = repository.findAll();
		        return new ResponseEntity(products,products.size() !=0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
		    }

		 @RequestMapping(method = RequestMethod.GET, value= "/{id}")
		    public ResponseEntity<Product> get(@PathVariable String id) {
			 	Product product1 = repository.findOne(id);
		        return new ResponseEntity(product1, product1 != null ? HttpStatus.OK : HttpStatus.NOT_FOUND) ;
		    }
		 
		 @RequestMapping(method = RequestMethod.POST, produces = {"application/json"})
		    @ResponseBody
		    public ResponseEntity<Product> create(@RequestBody Product product){
		        Product savedProduct = repository.save(product);
		        return new ResponseEntity(product, HttpStatus.OK);
		    }

		    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json", consumes = {"application/json"}, produces = {"application/json"})
		    public ResponseEntity<Product> update(@PathVariable String id, @RequestBody Product product){
		        product.setId(id);
		        Product savedProduct = repository.save(product);
		        return new ResponseEntity(savedProduct, HttpStatus.OK);
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
