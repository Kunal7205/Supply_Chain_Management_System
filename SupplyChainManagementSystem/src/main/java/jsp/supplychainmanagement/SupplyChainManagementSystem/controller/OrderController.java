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
import jsp.supplychainmanagement.SupplyChainManagementSystem.entity.Orders;
import jsp.supplychainmanagement.SupplyChainManagementSystem.servive.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@PostMapping("/{customerId}")
	public ResponseEntity<ResponseStructure<Orders>> saveOrder(@PathVariable int customerId, @RequestBody Orders order){
	    return orderService.saveOrder(customerId,order);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Orders>> getOrderById(@PathVariable int id){
	    return orderService.getOrderById(id);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<List<Orders>>> getAllOrders(){
	    return orderService.getAllOrders();
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Orders>> updateOrder(@RequestBody Orders order){
	    return orderService.updateOrder(order);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Orders>> deleteOrder(@PathVariable int id){
	    return orderService.deleteOrder(id);
	}
	@GetMapping("/trackingNumber/{trackingNumber}")
	public ResponseEntity<ResponseStructure<Orders>> getOrderByTrackingNumber(@PathVariable String trackingNumber){
		return orderService.getOrderByTrackingNumber(trackingNumber);
	}
}
