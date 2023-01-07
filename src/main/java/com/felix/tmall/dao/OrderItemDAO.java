package com.felix.tmall.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felix.tmall.pojo.Order;
import com.felix.tmall.pojo.OrderItem;
import com.felix.tmall.pojo.Product;
import com.felix.tmall.pojo.User;

public interface OrderItemDAO extends JpaRepository<OrderItem,Integer>{
	List<OrderItem> findByOrderOrderByIdDesc(Order order);
	List<OrderItem> findByProduct(Product product);
	List<OrderItem> findByUserAndOrderIsNull(User user);
}
