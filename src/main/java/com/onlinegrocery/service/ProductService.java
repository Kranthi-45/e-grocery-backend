package com.onlinegrocery.service;

import java.util.List;

import com.onlinegrocery.dto.ProductDto;
import com.onlinegrocery.entity.Product;
import com.onlinegrocery.enums.Category;



public interface ProductService {
	String addProduct(Product product);
	String deleteProduct(int productId);
	List<Product> getAllProducts();
	List<Product> getProductByCategory(Category category);
	Product getById(int productId);
	String updateProduct(ProductDto product, int productid);
	boolean isProductAvailable(int productId);
    boolean updateProductStock(int productId, int quantity);
}
