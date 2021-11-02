package com.guestu.springbootelasticsearchjpa.api;

import com.guestu.springbootelasticsearchjpa.model.Category;
import com.guestu.springbootelasticsearchjpa.model.Product;
import com.guestu.springbootelasticsearchjpa.service.ElasticSearchCategoryService;
import com.guestu.springbootelasticsearchjpa.service.ElasticSearchProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search/product")
@CrossOrigin("*")
public class QueryDSLProductControler {
    @Autowired
    private ElasticSearchProductService elasticSearchService;


    @GetMapping("/findAll")
    public List<Product> findAll(){
        return elasticSearchService.searchAll();
    }

    @GetMapping("/searchText/{searchTerm}")
    public List<Product> searchText(@PathVariable String searchTerm){
        return elasticSearchService.searchText(searchTerm);
    }
}
