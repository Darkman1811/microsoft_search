package com.guestu.springbootelasticsearchjpa.repository;

import com.guestu.springbootelasticsearchjpa.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepository extends ElasticsearchRepository<Product,String> {
}
