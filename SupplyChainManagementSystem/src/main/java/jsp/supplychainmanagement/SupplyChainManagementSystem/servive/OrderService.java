package jsp.supplychainmanagement.SupplyChainManagementSystem.servive;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.supplychainmanagement.SupplyChainManagementSystem.dao.CustomerDao;
import jsp.supplychainmanagement.SupplyChainManagementSystem.dao.OrderDao;
import jsp.supplychainmanagement.SupplyChainManagementSystem.dao.ProductDao;
import jsp.supplychainmanagement.SupplyChainManagementSystem.dto.ResponseStructure;
import jsp.supplychainmanagement.SupplyChainManagementSystem.entity.Customer;
import jsp.supplychainmanagement.SupplyChainManagementSystem.entity.Orders;
import jsp.supplychainmanagement.SupplyChainManagementSystem.entity.Product;
import jsp.supplychainmanagement.SupplyChainManagementSystem.exception.ProductNotFoundException;

@Service
public class OrderService {
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private ProductDao productDao;
	
	List<Orders> orderList = new ArrayList<Orders>();
	public ResponseEntity<ResponseStructure<Orders>> saveOrder(int customerId, Orders order) {
	    ResponseStructure<Orders> response = new ResponseStructure<>();
	    Optional<Customer> optCustomer = customerDao.getCustomerById(customerId);

	    if (!optCustomer.isPresent()) {
	        response.setMessage("Customer not found!");
	        response.setStatusCode(HttpStatus.NOT_FOUND.value());
	        order.setCustomer(null);
	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	        
	    }

	    order.setCustomer(optCustomer.get());

	    List<Product> products = new ArrayList<>();
	    
	    for (Product product : order.getProducts()) {
	        if (product.getId() != 0) { 
	            Optional<Product> existingProduct = productDao.getProductById(product.getId());
	            if (existingProduct.isPresent()) {
	                products.add(existingProduct.get());
	                existingProduct.get().setOrder(order);
	            } else {
	              throw new ProductNotFoundException();
	            }
	        }
	    }
	    orderList.add(order);
	    order.setProducts(products);
	    optCustomer.get().setOrder(orderList);
	    Orders savedOrder = orderDao.saveOrder(order); 
	    response.setMessage("Order saved successfully!");
	    response.setStatusCode(HttpStatus.CREATED.value());
	    response.setData(savedOrder);

	    return new ResponseEntity<>(response, HttpStatus.CREATED);
	}


	public ResponseEntity<ResponseStructure<Orders>> getOrderById(int id){
		Optional<Orders> opt = orderDao.getOrderById(id);
		ResponseStructure<Orders> structure = new ResponseStructure<Orders>();
		if(opt.isPresent()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Successfully fetched");
			structure.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Orders>>(structure, HttpStatus.OK);
		}
		else {
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not fetched");
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<Orders>>(structure, HttpStatus.NOT_FOUND);
		}	
	}
	
	public ResponseEntity<ResponseStructure<List<Orders>>> getAllOrders() {
	    List<Orders> ordersList = orderDao.getAllOrders();
	    ResponseStructure<List<Orders>> structure = new ResponseStructure<>();
	    structure.setStatusCode(HttpStatus.OK.value());
	    structure.setMessage("All Orders Fetched");
	    structure.setData(ordersList);
	    return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Orders>> updateOrder(Orders order) {
	    Orders modifiedOrder = orderDao.updateOrder(order);
	    ResponseStructure<Orders> structure = new ResponseStructure<>();
	    structure.setStatusCode(HttpStatus.ACCEPTED.value());
	    structure.setMessage("Order Updated");
	    structure.setData(modifiedOrder);
	    return new ResponseEntity<>(structure, HttpStatus.ACCEPTED);
	}

	public ResponseEntity<ResponseStructure<Orders>> deleteOrder(int id) {
	    Optional<Orders> opt = orderDao.deleteOrder(id);
	    ResponseStructure<Orders> structure = new ResponseStructure<>();
	    if (opt.isPresent()) {
	        structure.setStatusCode(HttpStatus.ACCEPTED.value());
	        structure.setMessage("Order Successfully Deleted");
	        structure.setData(opt.get());
	        return new ResponseEntity<>(structure, HttpStatus.ACCEPTED);
	    } else {
	        structure.setStatusCode(HttpStatus.NOT_FOUND.value());
	        structure.setMessage("Order Not Found");
	        structure.setData(null);
	        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
	    }
	}
	public ResponseEntity<ResponseStructure<Orders>> getOrderByTrackingNumber(String trackingNumber){
		Optional<Orders> optOrder = orderDao.getOrderByTrackingNumber(trackingNumber);
		ResponseStructure<Orders> structure = new ResponseStructure<Orders>();
		if(optOrder.isPresent()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Successfully fetched");
			structure.setData(optOrder.get());
			return new ResponseEntity<ResponseStructure<Orders>>(structure, HttpStatus.OK);	
		}else {
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not fetched");
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<Orders>>(structure, HttpStatus.NOT_FOUND);
		}
	}
	



}
