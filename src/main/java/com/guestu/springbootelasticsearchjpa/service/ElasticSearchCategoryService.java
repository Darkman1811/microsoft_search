package com.guestu.springbootelasticsearchjpa.service;

import com.google.common.collect.Lists;
import com.guestu.springbootelasticsearchjpa.model.Category;
import com.guestu.springbootelasticsearchjpa.repository.CategoryRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Service
public class ElasticSearchCategoryService {

    @Autowired
    private ElasticsearchTemplate template;

    @Autowired
    private CategoryRepository categoryRepository;
/*
    public List<Customer> searchMultifields(String firstName, int age){
        QueryBuilder queryBuilder= QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("firstName",firstName))
                .must(QueryBuilders.matchQuery("age",age));
        NativeSearchQuery nativeSearchQuery=new NativeSearchQueryBuilder().withQuery(queryBuilder).build();
       List<Customer> customers= template.queryForList(nativeSearchQuery,Customer.class);
        return  customers;
    }

    public List<Customer>   getCustomerSearchData(String input){
        String search = ".*"+input+".*";
        SearchQuery searchQuery=new NativeSearchQueryBuilder().withFilter(QueryBuilders.regexpQuery("firstName",search)).build();
        List<Customer> customers= template.queryForList(searchQuery,Customer.class);
        return customers;
    }
*/    public List<Category> searchAll(){
        SearchQuery searchQuery=new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchAllQuery()).build();
        List<Category> categories= template.queryForList(searchQuery,Category.class);
        return categories;
    }

    public void save(@RequestBody Map<String,Category> categoryParam){
        Category category=categoryParam.get("category");
        Category parent= categoryParam.get("parent");
        System.out.println("parent: "+parent);
        category.setParent(parent);
        categoryRepository.save(category);
    }

    public void update(@RequestBody Map<String,Category> categoryParam){
        Category category=categoryParam.get("category");
        Category parent= categoryParam.get("parent");
        category.setParent(parent);
        Category active= categoryRepository.findOne(category.getId());
        BeanUtils.copyProperties(category,active);
        categoryRepository.save(active);
    }

    public void delete(Category category){
        categoryRepository.delete(category);
        return  ;
    }


}
