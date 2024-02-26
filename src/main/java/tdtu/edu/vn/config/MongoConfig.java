package tdtu.edu.vn.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.TextIndexDefinition;
import org.springframework.data.mongodb.core.index.TextIndexDefinition.TextIndexDefinitionBuilder;
import org.springframework.stereotype.Component;
import tdtu.edu.vn.model.Book;

import javax.annotation.PostConstruct;

@Configuration
public class MongoConfig {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void initIndexes() {
        TextIndexDefinition textIndex = new TextIndexDefinitionBuilder()
                .onField("name")
                .build();
        mongoTemplate.indexOps(Book.class).ensureIndex(textIndex);
    }
}