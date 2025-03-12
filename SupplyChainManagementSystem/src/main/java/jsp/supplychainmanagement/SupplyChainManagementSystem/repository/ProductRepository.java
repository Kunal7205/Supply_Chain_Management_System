package jsp.supplychainmanagement.SupplyChainManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.supplychainmanagement.SupplyChainManagementSystem.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	Product findByName(String name);
}
