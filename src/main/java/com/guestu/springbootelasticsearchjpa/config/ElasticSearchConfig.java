package com.guestu.springbootelasticsearchjpa.config;

import org.elasticsearch.node.NodeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.guestu.springbootelasticsearchjpa.repository")
public class ElasticSearchConfig {

    @Bean
    public NodeBuilder builder(){
        return new NodeBuilder();
    }

    public ElasticsearchOperations elasticsearchTemplate(){
        return new ElasticsearchTemplate(builder().local(true).node().client());
    }

}
