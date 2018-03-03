package com.ankesh.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Configuration
public class MongoDBConfig extends AbstractMongoConfiguration{
	
	private Mongo mongo;
	
	@Value("${mongodb.connection.string}")
	private String MONGODB_CONNECTION_STRING;
	
	
	@Override
	public String getDatabaseName() {
		return "pratice" ;
	}
	
	@Override
	protected String getMappingBasePackage() {
		return "com.ankesh.app";
	}

	@Override
    @Bean
    public Mongo mongo() throws Exception {
        MongoClientURI uri  = new MongoClientURI(MONGODB_CONNECTION_STRING);
        this.mongo = new MongoClient(uri);
        return mongo;
    }
}
