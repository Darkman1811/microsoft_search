package com.guestu.springbootelasticsearchjpa.api;

import com.guestu.springbootelasticsearchjpa.model.Category;
import com.guestu.springbootelasticsearchjpa.service.ElasticSearchCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search/category")
@CrossOrigin("*")
public class QueryDSLCategoryControler {
    @Autowired
    private ElasticSearchCategoryService elasticSearchCategoryService2;


    @GetMapping("/findAll")
    public List<Category> findAll(){
        return elasticSearchCategoryService2.searchAll();
    }
}
