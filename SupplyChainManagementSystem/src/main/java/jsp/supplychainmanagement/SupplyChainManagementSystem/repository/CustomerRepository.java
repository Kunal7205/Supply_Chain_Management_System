package jsp.supplychainmanagement.SupplyChainManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.supplychainmanagement.SupplyChainManagementSystem.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
