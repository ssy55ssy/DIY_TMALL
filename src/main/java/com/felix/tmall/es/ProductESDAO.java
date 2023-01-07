package com.felix.tmall.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.felix.tmall.pojo.Product;

public interface ProductESDAO extends ElasticsearchRepository<Product,Integer>{

}
