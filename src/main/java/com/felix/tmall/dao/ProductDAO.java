package com.felix.tmall.dao;

import com.felix.tmall.pojo.Category;
import com.felix.tmall.pojo.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product,Integer>{
	Page<Product> findByCategory(Category category, Pageable pageable);
	List<Product> findByCategoryOrderById(Category category);
	//this method no longer needed, since we use elasticsearch
	List<Product> findByNameLike(String keyword, Pageable pageable);

}
