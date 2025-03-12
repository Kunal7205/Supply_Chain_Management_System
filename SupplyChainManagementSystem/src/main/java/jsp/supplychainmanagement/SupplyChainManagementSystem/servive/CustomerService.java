package jsp.supplychainmanagement.SupplyChainManagementSystem.servive;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.supplychainmanagement.SupplyChainManagementSystem.dao.CustomerDao;
import jsp.supplychainmanagement.SupplyChainManagementSystem.dto.ResponseStructure;
import jsp.supplychainmanagement.SupplyChainManagementSystem.entity.Customer;

@Service
public class CustomerService {
	@Autowired
	private CustomerDao customerDao;
	


	public ResponseEntity<ResponseStructure<Customer>> saveCustomer(Customer customer){
	    Customer receivedCustomer = customerDao.saveCustomer(customer);
	    
	    ResponseStructure<Customer> structure = new ResponseStructure<>();
	    structure.setStatusCode(HttpStatus.CREATED.value());
	    structure.setMessage("success");
	    structure.setData(receivedCustomer);
	    
	    return new ResponseEntity<>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Customer>> getCustomerById(int id){
	    Optional<Customer> opt = customerDao.getCustomerById(id);
	    ResponseStructure<Customer> structure = new ResponseStructure<>();
	    if(opt.isPresent()) {
	        structure.setStatusCode(HttpStatus.OK.value());
	        structure.setMessage("Successfully fetched");
	        structure.setData(opt.get());
	        return new ResponseEntity<>(structure, HttpStatus.OK);
	    }
	    else {
	        structure.setStatusCode(HttpStatus.NOT_FOUND.value());
	        structure.setMessage("Not fetched");
	        structure.setData(null);
	        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
	    }    
	}

	public ResponseEntity<ResponseStructure<List<Customer>>> getAllCustomers() {
	    List<Customer> customersList = customerDao.getAllCustomers();
	    ResponseStructure<List<Customer>> structure = new ResponseStructure<>();
	    structure.setStatusCode(HttpStatus.OK.value());
	    structure.setMessage("All Customers Fetched");
	    structure.setData(customersList);
	    return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(Customer customer) {
	    Customer modifiedCustomer = customerDao.updateCustomer(customer);
	    ResponseStructure<Customer> structure = new ResponseStructure<>();
	    structure.setStatusCode(HttpStatus.ACCEPTED.value());
	    structure.setMessage("Customer Updated");
	    structure.setData(modifiedCustomer);
	    return new ResponseEntity<>(structure, HttpStatus.ACCEPTED);
	}
	public ResponseEntity<ResponseStructure<Customer>> deleteCustomer(int id) {
	    Optional<Customer> opt = customerDao.deleteCustomer(id);
	    ResponseStructure<Customer> structure = new ResponseStructure<>();
	    if (opt.isPresent()) {
	        structure.setStatusCode(HttpStatus.ACCEPTED.value());
	        structure.setMessage("Customer Successfully Deleted");
	        structure.setData(opt.get());
	        return new ResponseEntity<>(structure, HttpStatus.ACCEPTED);
	    } else {
	        structure.setStatusCode(HttpStatus.NOT_FOUND.value());
	        structure.setMessage("Customer Not Found");
	        structure.setData(null);
	        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
	    }
	}
	
}
