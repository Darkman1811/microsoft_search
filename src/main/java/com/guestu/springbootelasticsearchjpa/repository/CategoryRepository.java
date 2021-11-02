package com.guestu.springbootelasticsearchjpa.repository;

import com.guestu.springbootelasticsearchjpa.model.Category;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CategoryRepository extends ElasticsearchRepository<Category,String> {

}
