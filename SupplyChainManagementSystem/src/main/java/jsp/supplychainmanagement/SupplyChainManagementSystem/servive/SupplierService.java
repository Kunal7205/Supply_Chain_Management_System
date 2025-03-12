package jsp.supplychainmanagement.SupplyChainManagementSystem.servive;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.supplychainmanagement.SupplyChainManagementSystem.dao.SupplierDao;
import jsp.supplychainmanagement.SupplyChainManagementSystem.dto.ResponseStructure;
import jsp.supplychainmanagement.SupplyChainManagementSystem.entity.Supplier;

@Service
public class SupplierService {
	
	@Autowired
	private SupplierDao supplierDao;
	
	public ResponseEntity<ResponseStructure<Supplier>> saveSupplier(Supplier supplier){
		Supplier receivedSupplier = supplierDao.saveSupplier(supplier);
		
		ResponseStructure<Supplier> structure = new ResponseStructure<Supplier>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("success");
		structure.setData(receivedSupplier);
		
		return new ResponseEntity<ResponseStructure<Supplier>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Supplier>> getSupplierById(int id){
		Optional<Supplier> opt = supplierDao.getSupplierById(id);
		ResponseStructure<Supplier> structure = new ResponseStructure<Supplier>();
		if(opt.isPresent()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Successfully fetched");
			structure.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Supplier>>(structure, HttpStatus.OK);
		}
		else {
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not fetched");
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<Supplier>>(structure, HttpStatus.NOT_FOUND);
		}	
	}
	
	public ResponseEntity<ResponseStructure<List<Supplier>>> getAllSupplier(){
		List<Supplier> supplierList  = supplierDao.getAllSupplier();
		ResponseStructure<List<Supplier>> structure = new ResponseStructure<List<Supplier>>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("All Fetched");
		structure.setData(supplierList);
		return new ResponseEntity<ResponseStructure<List<Supplier>>>(structure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Supplier>> updateSupplier(Supplier supplier){
		Supplier modifiedSupplier = supplierDao.updateSupplier(supplier);
		ResponseStructure<Supplier> structure = new ResponseStructure<Supplier>();
		structure.setStatusCode(HttpStatus.ACCEPTED.value());
		structure.setMessage("Updated");
		structure.setData(modifiedSupplier);
		return new ResponseEntity<ResponseStructure<Supplier>>(structure, HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<ResponseStructure<Supplier>> deleteSupplier(int id){
		Optional<Supplier> opt = supplierDao.deleteSupplier(id);
		ResponseStructure<Supplier> structure = new ResponseStructure<Supplier>();
		if(opt.isPresent()) {
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			structure.setMessage("Successfully Deleted");
			structure.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Supplier>>(structure, HttpStatus.ACCEPTED);
		}
		else {
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not found");
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<Supplier>>(structure, HttpStatus.NOT_FOUND);
		}
		
	}

}
