package com.felix.tmall.dao;
 
import org.springframework.data.jpa.repository.JpaRepository;

import com.felix.tmall.pojo.Category;

public interface CategoryDAO extends JpaRepository<Category,Integer>{

}
