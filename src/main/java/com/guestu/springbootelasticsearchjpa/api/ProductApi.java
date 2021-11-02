package com.guestu.springbootelasticsearchjpa.api;

import com.google.common.collect.Lists;
import com.guestu.springbootelasticsearchjpa.model.Category;
import com.guestu.springbootelasticsearchjpa.model.Product;
import com.guestu.springbootelasticsearchjpa.repository.CategoryRepository;
import com.guestu.springbootelasticsearchjpa.repository.ProductRepository;
import com.guestu.springbootelasticsearchjpa.service.ElasticSearchCategoryService;
import com.guestu.springbootelasticsearchjpa.service.ElasticSearchProductService;
import org.elasticsearch.action.search.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
    @RequestMapping("/crud/product")
@CrossOrigin("*")
public class ProductApi {
    @Autowired
    private ProductRepository repository;
    @Autowired
    private ElasticSearchProductService service;

    @PostMapping("/save")
    public Iterable<Product> save(@RequestBody Product product){
        service.save(product);
        return Lists.newArrayList(repository.findAll()) ;
    }



    @PostMapping("/update")
    public Iterable<Product> update(@RequestBody Product product){
       service.update(product);
        return Lists.newArrayList(repository.findAll()) ;
    }

    @PostMapping("/delete")
    public Iterable<Product> delete(@RequestBody Product product){
        service.delete(product);
        return Lists.newArrayList(repository.findAll()) ;
    }


    @GetMapping("/findOne")
    public Product findOne(Product product){
        return repository.findOne(product.getId());
    }


    @GetMapping("/findAll")
    public Iterable<Product> findAll(){
        return service.searchAll();
    }







}
