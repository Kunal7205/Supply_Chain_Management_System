package jsp.supplychainmanagement.SupplyChainManagementSystem.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import jsp.supplychainmanagement.SupplyChainManagementSystem.entity.Product;
import jsp.supplychainmanagement.SupplyChainManagementSystem.repository.ProductRepository;

@Repository
public class ProductDao {
	@Autowired
	private ProductRepository productRepository;
	
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
	
	public Optional<Product> getProductById(int id) {
		return productRepository.findById(id);
	}
	
	public List<Product> getAllProduct(){
		return productRepository.findAll();
	}
	
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}
	
	public  Optional<Product> deleteProduct(int id) {
		Optional<Product> opt = productRepository.findById(id);
		if(opt.isPresent()) {
			productRepository.delete(opt.get());
			return opt;
		}
		else {
			return Optional.empty();
		}
		
	}
	
	public Product getProductByName(String name){
		return productRepository.findByName(name);
	}
}
