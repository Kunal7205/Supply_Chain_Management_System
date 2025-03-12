package jsp.supplychainmanagement.SupplyChainManagementSystem.exception;

public class ProductNotFoundException extends RuntimeException{
	public ProductNotFoundException() {
		super();
	}
	public ProductNotFoundException (String msg){
		super(msg);
	}
	@Override
	public String getMessage() {
		return "Product Not Found";
	}
}
