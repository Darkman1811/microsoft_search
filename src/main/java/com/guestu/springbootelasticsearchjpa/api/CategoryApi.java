package com.guestu.springbootelasticsearchjpa.api;

import com.google.common.collect.Lists;
import com.guestu.springbootelasticsearchjpa.model.Category;
import com.guestu.springbootelasticsearchjpa.repository.CategoryRepository;
import com.guestu.springbootelasticsearchjpa.service.ElasticSearchCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
    @RequestMapping("/crud/category")
@CrossOrigin("*")
public class CategoryApi {
    @Autowired
    private CategoryRepository repository;
    @Autowired
    private ElasticSearchCategoryService service;

    @PostMapping("/save")
    public Iterable<Category> save(@RequestBody Map<String,Category> categoryParam){
        service.save(categoryParam);
        return Lists.newArrayList(repository.findAll()) ;
    }

    @PostMapping("/update")
    public Iterable<Category> update(@RequestBody Map<String,Category> categoryParam){
       service.update(categoryParam);
        return Lists.newArrayList(repository.findAll()) ;
    }

    @PostMapping("/delete")
    public Iterable<Category> delete(@RequestBody Category category){
        service.delete(category);
        return Lists.newArrayList(repository.findAll()) ;
    }


    @GetMapping("/findOne")
    public Category findOne(Category category){
        return repository.findOne(category.getId());
    }


    @GetMapping("/findAll")
    public Iterable<Category> findAll(){
        return service.searchAll();
    }






}
