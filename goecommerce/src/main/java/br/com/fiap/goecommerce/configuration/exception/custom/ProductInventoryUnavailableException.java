package br.com.fiap.goecommerce.configuration.exception.custom;

public class ProductInventoryUnavailableException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ProductInventoryUnavailableException(String msg) {
		super(msg);
	}
	
	public ProductInventoryUnavailableException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	

}