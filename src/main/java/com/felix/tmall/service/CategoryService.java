package com.felix.tmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.felix.tmall.dao.CategoryDAO;
import com.felix.tmall.pojo.Category;
import com.felix.tmall.pojo.Product;
import com.felix.tmall.util.Page4Navigator;

@Service
@CacheConfig(cacheNames="categories")
public class CategoryService {
	@Autowired CategoryDAO categoryDAO;

	@CacheEvict(allEntries=true)
//	@CachePut(key="'category-one-'+ #p0")
	public void add(Category bean) {
		categoryDAO.save(bean);
	}

	@CacheEvict(allEntries=true)
//	@CacheEvict(key="'category-one-'+ #p0")
	public void delete(int id) {
		categoryDAO.delete(id);
	}

	
	@Cacheable(key="'categories-one-'+ #p0")
	public Category get(int id) {
		Category c= categoryDAO.findOne(id);
		return c;
	}

	@CacheEvict(allEntries=true)
//	@CachePut(key="'category-one-'+ #p0")
	public void update(Category bean) {
		categoryDAO.save(bean);
	}

	@Cacheable(key="'categories-page-'+#p0+ '-' + #p1")
	public Page4Navigator<Category> list(int start, int size, int navigatePages) {
    	Sort sort = new Sort(Sort.Direction.DESC, "id");
		Pageable pageable = new PageRequest(start, size,sort);
		Page pageFromJPA =categoryDAO.findAll(pageable);
		
		return new Page4Navigator<>(pageFromJPA,navigatePages);
	}

	@Cacheable(key="'categories-all'")
	public List<Category> list() {
    	Sort sort = new Sort(Sort.Direction.DESC, "id");
		return categoryDAO.findAll(sort);
	}

	//this method is used to delete the category field of product。
	//this is because when the category transfer to json, it will translate the product field inside it, if the product still have category field, it will not stop iterate
	//It's fine to do this ,as long as we don't try to achieve category through product
	
	public void removeCategoryFromProduct(List<Category> cs) {
		for (Category category : cs) {
			removeCategoryFromProduct(category);
		}
	}

	public void removeCategoryFromProduct(Category category) {
		List<Product> products =category.getProducts();
		if(null!=products) {
			for (Product product : products) {
				product.setCategory(null);
			}
		}
		
		List<List<Product>> productsByRow =category.getProductsByRow();
		if(null!=productsByRow) {
			for (List<Product> ps : productsByRow) {
				for (Product p: ps) {
					p.setCategory(null);
				}
			}
		}
	}
}
