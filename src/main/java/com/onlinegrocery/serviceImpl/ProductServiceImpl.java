package com.onlinegrocery.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.onlinegrocery.dto.ProductDto;
import com.onlinegrocery.entity.Product;
import com.onlinegrocery.enums.Category;
import com.onlinegrocery.repo.ProductRepo;
import com.onlinegrocery.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepo productRepo;
	
	
	@Override
	public String addProduct(Product product) {
		// TODO Auto-generated method stub
		Product products = new Product();
		products.setProductName(product.getProductName());
		products.setDescription(product.getDescription());
		products.setImageUrl(product.getImageUrl());
		products.setCategory(product.getCategory());
		products.setPrice(product.getPrice());
		products.setStockQuantity(product.getStockQuantity());
		productRepo.save(products);
		return "Product added successfully";
	}

	@Override
	public String deleteProduct(int productId) {
		productRepo.deleteById(productId);
		return "Product deleted successfully";
	}


	@Override
	public List<Product> getAllProducts() {
		return productRepo.findAll();
		}

	@Override
	public List<Product> getProductByCategory(Category category) {
		return productRepo.findByCategory(category);
	}


	@Override
	public Product getById(int productId) {
		return productRepo.findById(productId).orElseThrow();
	}


	@Override
	public String updateProduct(ProductDto products, int productid) {
		Optional<Product> optionalProduct = productRepo.findById(productid);
      if (optionalProduct.isPresent()) {
          Product productEntity = optionalProduct.get();
          // Update the fields of the product with the values from the DTO
          productEntity.setProductId(products.getProductId());
          productEntity.setProductName(products.getProductName());
          productEntity.setDescription(products.getDescription());
          productEntity.setCategory(products.getCategory());
          productEntity.setImageUrl(products.getImageUrl());
          productEntity.setPrice(products.getPrice());
          productEntity.setStockQuantity(products.getStockQuantity());
          // Save the updated product
          productRepo.save(productEntity);
          return "Product updated successfully.";
      } else {
          return "Product not found.";
      }
	}
	
	 @Override
	    public boolean isProductAvailable(int productId) {
	        Product product = productRepo.findById(productId).orElseThrow();
	        return product != null && product.getStockQuantity() > 0;
	    }

	    @Override
	    public boolean updateProductStock(int productId, int quantity) {
	        Product product = productRepo.findById(productId).orElse(null);
	        if (product != null && product.getStockQuantity() >= quantity) {
	            product.setStockQuantity(product.getStockQuantity() - quantity);
	            productRepo.save(product);
	            return true;
	        } else {
	            return false;
	        }
	    }

}
