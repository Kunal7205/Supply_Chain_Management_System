package jsp.supplychainmanagement.SupplyChainManagementSystem.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jsp.supplychainmanagement.SupplyChainManagementSystem.entity.Supplier;
import jsp.supplychainmanagement.SupplyChainManagementSystem.repository.SupplierRepository;

@Repository
public class SupplierDao {
	@Autowired
	private SupplierRepository supplierRepository;
	
	public Supplier saveSupplier(Supplier supplier) {
		return supplierRepository.save(supplier);
	}
	
	public Optional<Supplier> getSupplierById(int id) {
		return supplierRepository.findById(id);
	}
	
	public List<Supplier> getAllSupplier(){
		return supplierRepository.findAll();
	}
	
	public Supplier updateSupplier(Supplier supplier) {
		return supplierRepository.save(supplier);
	}
	
	public  Optional<Supplier> deleteSupplier(int id) {
		Optional<Supplier> opt = supplierRepository.findById(id);
		if(opt.isPresent()) {
			supplierRepository.delete(opt.get());
			return opt;
		}
		else {
			return Optional.empty();
		}
		
	}
}
