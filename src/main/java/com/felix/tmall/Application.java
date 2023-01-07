package com.felix.tmall;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.felix.tmall.util.PortUtil;
@SpringBootApplication
@EnableCaching
@EnableElasticsearchRepositories(basePackages = "com.felix.tmall.es")
@EnableJpaRepositories(basePackages = {"com.felix.tmall.dao", "com.felix.tmall.pojo"})
public class Application {
    static {
        PortUtil.checkPort(6379,"Redis server",true);
        PortUtil.checkPort(9300,"ElasticSearch server",true);
        PortUtil.checkPort(5601,"Kibana server", true);
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
