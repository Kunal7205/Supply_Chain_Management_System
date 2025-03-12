package jsp.supplychainmanagement.SupplyChainManagementSystem.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jsp.supplychainmanagement.SupplyChainManagementSystem.entity.Customer;
import jsp.supplychainmanagement.SupplyChainManagementSystem.repository.CustomerRepository;



@Repository
public class CustomerDao {
	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public Optional<Customer> getCustomerById(int id) {
		return customerRepository.findById(id);
	}
	
	public List<Customer> getAllCustomers(){
		return customerRepository.findAll();
	}
	
	public Customer updateCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public  Optional<Customer> deleteCustomer(int id) {
		Optional<Customer> opt = customerRepository.findById(id);
		if(opt.isPresent()) {
			customerRepository.delete(opt.get());
			return opt;
		}
		else {
			return Optional.empty();
		}
		
	}
	
}
