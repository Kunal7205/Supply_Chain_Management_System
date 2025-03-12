package jsp.supplychainmanagement.SupplyChainManagementSystem.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import jsp.supplychainmanagement.SupplyChainManagementSystem.entity.Orders;
import jsp.supplychainmanagement.SupplyChainManagementSystem.repository.OrderRepository;

@Repository
public class OrderDao {
	@Autowired
	private OrderRepository orderRepositry;
	
	public Orders saveOrder(Orders order) {
		return orderRepositry.save(order);
	}
	public Optional<Orders> getOrderById(int id) {
		return orderRepositry.findById(id);
	}
	public List<Orders> getAllOrders(){
		return orderRepositry.findAll();
	}
	public Orders updateOrder(Orders order) {
		return orderRepositry.save(order);
	}
	public  Optional<Orders> deleteOrder(int id) {
		Optional<Orders> opt = orderRepositry.findById(id);
		if(opt.isPresent()) {
			orderRepositry.delete(opt.get());
			return opt;
		}
		else {
			return Optional.empty();
		}
	}
	public Optional<Orders> getOrderByTrackingNumber(String trackingNumber){
		return orderRepositry.findByTrackingNumber(trackingNumber);
	}

}
