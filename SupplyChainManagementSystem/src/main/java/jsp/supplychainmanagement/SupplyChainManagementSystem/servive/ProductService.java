package jsp.supplychainmanagement.SupplyChainManagementSystem.servive;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.supplychainmanagement.SupplyChainManagementSystem.dao.ProductDao;
import jsp.supplychainmanagement.SupplyChainManagementSystem.dao.SupplierDao;
import jsp.supplychainmanagement.SupplyChainManagementSystem.dto.ResponseStructure;
import jsp.supplychainmanagement.SupplyChainManagementSystem.entity.Product;
import jsp.supplychainmanagement.SupplyChainManagementSystem.entity.Supplier;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private SupplierDao supplierDao;
	
	ArrayList<Product> productList = new ArrayList<Product>();
	
	public ResponseEntity<ResponseStructure<Product>> saveProduct(int supplierId, Product product) {
	    Optional<Supplier> optSupplier = supplierDao.getSupplierById(supplierId);
	    ResponseStructure<Product> structure = new ResponseStructure<>();

	    if (optSupplier.isPresent()) {
	    	 product.setSupplier(optSupplier.get());
	        Product receivedProduct = productDao.saveProduct(product);
	        productList.add(receivedProduct);
	        optSupplier.get().setProducts(productList);
	        structure.setStatusCode(HttpStatus.CREATED.value());
	        structure.setMessage("Product saved successfully!");
	        structure.setData(receivedProduct);
	       
	        return new ResponseEntity<>(structure, HttpStatus.CREATED);
	    } else {
	        structure.setStatusCode(HttpStatus.NOT_FOUND.value());
	        structure.setMessage("Order Not Found!");
	        structure.setData(null);
	        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND); 
	    }
	}

	public ResponseEntity<ResponseStructure<Product>> getProductById(int id){
		Optional<Product> opt = productDao.getProductById(id);
		ResponseStructure<Product> structure = new ResponseStructure<Product>();
		if(opt.isPresent()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Successfully fetched");
			structure.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.OK);
		}
		else {
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not fetched");
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.NOT_FOUND);
		}	
	}
	
	public ResponseEntity<ResponseStructure<List<Product>>> getAllProduct(){
		List<Product> productList  = productDao.getAllProduct();
		ResponseStructure<List<Product>> structure = new ResponseStructure<List<Product>>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("All Fetched");
		structure.setData(productList);
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product product){
		Product modifiedProduct = productDao.updateProduct(product);
		ResponseStructure<Product> structure = new ResponseStructure<Product>();
		structure.setStatusCode(HttpStatus.ACCEPTED.value());
		structure.setMessage("Updated");
		structure.setData(modifiedProduct);
		return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<ResponseStructure<Product>> deleteProduct(int id){
		Optional<Product> opt = productDao.deleteProduct(id);
		ResponseStructure<Product> structure = new ResponseStructure<Product>();
		if(opt.isPresent()) {
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			structure.setMessage("Successfully Deleted");
			structure.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.ACCEPTED);
		}
		else {
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not found");
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.NOT_FOUND);
		}
		
	}
	
	 public ResponseEntity<ResponseStructure<Product>> getProductByName(String name) {
	        Product product = productDao.getProductByName(name);
	        ResponseStructure<Product> response = new ResponseStructure<>();
	        if (product != null) {
	            response.setStatusCode(HttpStatus.OK.value());
	            response.setMessage("Product Found");
	            response.setData(product);
	            return new ResponseEntity<>(response, HttpStatus.OK);
	        } else {
	            response.setStatusCode(HttpStatus.NOT_FOUND.value());
	            response.setMessage("Product Not Found");
	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	        }
	    }

}
