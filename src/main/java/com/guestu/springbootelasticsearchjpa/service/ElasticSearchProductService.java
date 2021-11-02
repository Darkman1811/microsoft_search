package com.guestu.springbootelasticsearchjpa.service;

import com.guestu.springbootelasticsearchjpa.model.Category;
import com.guestu.springbootelasticsearchjpa.model.Product;
import com.guestu.springbootelasticsearchjpa.repository.CategoryRepository;
import com.guestu.springbootelasticsearchjpa.repository.ProductRepository;
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
public class ElasticSearchProductService {

    @Autowired
    private ElasticsearchTemplate template;

    @Autowired
    private ProductRepository productRepository;
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
*/    public List<Product> searchAll(){
        SearchQuery searchQuery=new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchAllQuery()).build();
        List<Product> products= template.queryForList(searchQuery,Product.class);
        return products;
    }

    public void save(@RequestBody Product product){
        productRepository.save(product);
    }

    public void update(@RequestBody Product product){
        Product active= productRepository.findOne(product.getId());
        BeanUtils.copyProperties(product,active);
        productRepository.save(active);
    }

    public void delete(Product product){
        productRepository.delete(product);
        return  ;
    }


    public List<Product> searchText(String searchTerm) {
        SearchQuery searchQuery=new NativeSearchQueryBuilder().withQuery(
                QueryBuilders.queryStringQuery(searchTerm)
        ).build();
        List<Product> products= template.queryForList(searchQuery,Product.class);
        return products;
    }
}
