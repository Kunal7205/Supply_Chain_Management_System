package jsp.supplychainmanagement.SupplyChainManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jsp.supplychainmanagement.SupplyChainManagementSystem.dto.ResponseStructure;
import jsp.supplychainmanagement.SupplyChainManagementSystem.entity.Customer;
import jsp.supplychainmanagement.SupplyChainManagementSystem.servive.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<ResponseStructure<Customer>> saveCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Customer>> getCustomerById(@PathVariable int id){
        return customerService.getCustomerById(id);
    }

    @GetMapping
    public ResponseEntity<ResponseStructure<List<Customer>>> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @PutMapping
    public ResponseEntity<ResponseStructure<Customer>> updateCustomer(@RequestBody Customer customer){
        return customerService.updateCustomer(customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<Customer>> deleteCustomer(@PathVariable int id){
        return customerService.deleteCustomer(id);
    }
}

